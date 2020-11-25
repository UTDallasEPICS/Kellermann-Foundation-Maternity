/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Error;
import model.Vht;
import model.Maternity;
import model.Antenatal;
import model.Village;
import model.Parish;
import model.Subcounty;


public class ChbDAO implements Serializable
{
    private static final long serialVersionUID = 1L;
    public static LocalDateTime now;
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
    static DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_TIME;

    // Selects all subcounties since there is only one district
    public static List<Subcounty> Get_Subcounties() throws SQLException {

        try {
            Connection con;

            Subcounty subcounty;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "potato");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM subcounty ORDER BY SubcountyName ASC");

            ResultSet rs = stmt.executeQuery();
            List<Subcounty> subcounty_list = new ArrayList<>();

            while(rs.next()){
                subcounty = new Subcounty(rs.getString("SubcountyId"), rs.getString("SubcountyName"));
                subcounty_list.add(subcounty);
            }
            con.close();
            return subcounty_list;
        } catch (Exception e) {
            ErrorDAO.Error_Add(new Error("ChbDAO", "ChbDAO_Get_Subcounties", " Message: " + e.getMessage(), now));
            return null;
        }
    }

    // Selects all parishes within a subcounty
    public static List<Parish> Get_Parishes() throws SQLException {

        try {
            Connection con;

            Parish parish;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "potato");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM parish WHERE SubcountyId IN (SELECT SubcountyId FROM subcounty) ORDER BY ParishName ASC");

            ResultSet rs = stmt.executeQuery();
            List<Parish> parish_list = new ArrayList<>();

            while(rs.next()){
                parish = new Parish(rs.getString("ParishId"), rs.getString("SubcountyId"), rs.getString("ParishName"));
                parish_list.add(parish);
            }
            con.close();
            return parish_list;
        } catch (Exception e) {
            ErrorDAO.Error_Add(new Error("ChbDAO", "ChbDAO_Get_Parishes", " Message: " + e.getMessage(), now));
            return null;
        }
    }

    // Selects all villages with a parish
    public static List<Village> Get_Villages() throws SQLException {

        try {
            Connection con;

            Village village;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "potato");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM village WHERE ParishId IN (SELECT ParishId FROM parish) ORDER BY VillageName ASC");

            ResultSet rs = stmt.executeQuery();
            List<Village> village_list = new ArrayList<>();

            while (rs.next()) {
                village = new Village(rs.getString("VillageId"), rs.getString("ParishId"),  rs.getString("VillageName"));
                village_list.add(village);
            }
            con.close();
            return village_list;
        } catch (Exception e) {
            ErrorDAO.Error_Add(new Error("ChbDAO", "ChbDAO_Get_Villages", " Message: " + e.getMessage(), now));
            return null;
        }
    }

    public static List<Vht> Get_Vht_List() throws SQLException {
        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "potato");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("SELECT * From vht, village Where vht.vhtVillage=village.VillageId");

            ResultSet rs = stmt.executeQuery();
            List <Vht> vht_list = new ArrayList <>();

            while (rs.next()) {
                Vht vht = new Vht();

                vht.setVhtId(rs.getInt("vhtId"));
                vht.setVhtName(rs.getString("vhtName"));
                vht.setAge(rs.getInt("age"));
                vht.setSex(rs.getString("sex"));
                vht.setVhtPhoneNumber(rs.getString("vhtPhoneNumber"));
                vht.setIsCBD(rs.getString("isCBD"));
                vht.setVillageId(rs.getString("vhtVillage"));
                vht.setVillageName(rs.getString("VillageName"));
                vht.setRecordDate(rs.getTimestamp("recordDate").toLocalDateTime());
                vht.setUserId(rs.getInt("userId"));

                vht_list.add(vht);
            }
            con.close();
            return vht_list;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new model.Error("ChbDAO", "Get_Vht_List", " Message: " + ex.getMessage(), now));
            return null;
        }
    }

    public static List<Maternity> Get_Maternity_List() throws SQLException {
        try {
            Connection con;

            System.out.println("ChbDAO.Get_Maternity_List");
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "potato");
            now = LocalDateTime.now();


            PreparedStatement stmt = con.prepareStatement("SELECT * From maternity,village Where maternity.matVillage=village.VillageId");

            ResultSet rs = stmt.executeQuery();
            List <Maternity> maternity_list = new ArrayList<>();

            System.out.println("Null?"+rs.wasNull());
            while (rs.next()) {

                Maternity maternity = new Maternity();

                maternity.setMatId(rs.getInt("matId"));
                maternity.setDateOfAdmission(rs.getDate("dateOfAdmission").toLocalDate());
                maternity.setTimeOfAdmission(rs.getTime("timeOfAdmission").toLocalTime());
                maternity.setAdmissionNo(rs.getInt("admissionNo"));
                maternity.setAncNo(rs.getString("ancNo"));
                maternity.setIpdNo(rs.getInt("ipdNo"));
                maternity.setNin(rs.getString("nin"));
                maternity.setClientSurname(rs.getString("clientSurname"));
                maternity.setClientGivenName(rs.getString("clientGivenName"));
                maternity.setAge(rs.getInt("age"));
                maternity.setClientCategory(rs.getString("clientCategory"));
                maternity.setVillageId(rs.getString("matVillage"));
                maternity.setVillageName(rs.getString("villageName"));
                maternity.setParishId(rs.getString("matParish"));
                maternity.setParishName(rs.getString("parishName"));
                maternity.setSubcountyId(rs.getString("matSubcounty"));
                maternity.setSubcountyName(rs.getString("subcountyName"));
                maternity.setDistrictId(rs.getString("matDistrict"));
                maternity.setDistrictName(rs.getString("districtName"));
                maternity.setPhoneNumber(rs.getString("phoneNumber"));
                maternity.setGravidity(rs.getInt("gravidity"));
                maternity.setParity(rs.getInt("parity"));
                maternity.setGestationAge(rs.getInt("gestationAge"));
                maternity.setTerm(rs.getString("term"));
                maternity.setReasonForAdmission(rs.getString("reasonForAdmission"));
                maternity.setRevisit(rs.getBoolean("revisit"));
                maternity.setWhoClinicalStage(rs.getInt("whoClinicalStage"));
                maternity.setCd4Results(rs.getInt("cd4Results"));
                maternity.setCd4Date(rs.getDate("cd4Date").toLocalDate());
                maternity.setViralLoadResults(rs.getInt("viralLoadResults"));
                maternity.setViralLoadDate(rs.getDate("viralLoadDate").toLocalDate());
                maternity.setwInitialResult(rs.getString("wInitialResult"));
                maternity.setwTfv(rs.getString("wTfv"));
                maternity.setpInitialResult(rs.getString("pInitialResult"));
                maternity.setpTfv(rs.getString("pTfv"));
                maternity.setpArtCode(rs.getString("pArtCode"));
                maternity.setArtCode(rs.getString("artCode"));
                maternity.setArtNo(rs.getString("artNo"));
                maternity.setCtxCode(rs.getString("ctxCode"));
                maternity.setwSyphilis(rs.getString("wSyphilis"));
                maternity.setwHepatitisB(rs.getString("whepatitisB"));
                maternity.setpSyphilis(rs.getString("pSyphilis"));
                maternity.setpHepatitisB(rs.getString("pHepatitisB"));
                maternity.setMuac(rs.getString("muac"));
                maternity.setInrNo(rs.getInt("inrNo"));
                maternity.setModeOfDelivery(rs.getString("modeOfDelivery"));
                maternity.setDateOfDelivery(rs.getDate("dateOfDelivery").toLocalDate());
                maternity.setTimeOfDelivery(rs.getTime("timeOfDelivery").toLocalTime());
                maternity.setOxytocin(rs.getBoolean("oxytocin"));
                maternity.setMisoprostol(rs.getBoolean("misoprostol"));
                maternity.setErgometrine(rs.getBoolean("ergometrine"));
                maternity.setManagementProcedure(rs.getString("managementProcedure"));
                maternity.setOtherTreatment(rs.getString("otherTreatment"));
                maternity.setApgarScore(rs.getString("apgarScore"));
                maternity.setSexOfBaby(rs.getString("sexOfBaby"));
                maternity.setNotBreathing(rs.getString("notBreathing"));
                maternity.setImmediateSkinToSkin(rs.getBoolean("immediateSkinToSkin"));
                maternity.setSourceOfWarmth(rs.getString("sourceOfWarmth"));
                maternity.setBreastFed(rs.getBoolean("breastFed"));
                maternity.setTeo(rs.getBoolean("teo"));
                maternity.setVitK(rs.getBoolean("vitK"));
                maternity.setChlorhexidine(rs.getBoolean("chlorhexidine"));
                maternity.setWeight(rs.getFloat("weight"));
                maternity.setRiskStatus(rs.getString("riskStatus"));
                maternity.setArvsAdministered(rs.getString("arvsAdministered"));
                maternity.setSyrupDuration(rs.getInt("syrupDuration"));
                maternity.setBcgImmunization(rs.getString("bcgImmunization"));
                maternity.setPolioImmunization(rs.getString("polioImmunization"));
                maternity.setFamilyPlanningMethod(rs.getInt("familyPlanningMethod"));
                maternity.setFamilyPlanningDate(rs.getDate("familyPlanningDate").toLocalDate());
                maternity.setTreatmentOffered(rs.getString("treatmentOffered"));
                maternity.setBabyFinalDiagnosis(rs.getString("babyFinalDiagnosis"));
                maternity.setDeliveredByName(rs.getString("deliveredByName"));
                maternity.setDeliveredByCadre(rs.getString("deliveredByCadre"));
                maternity.setTransferredByName(rs.getString("transferredByName"));
                maternity.setTransferredByWhere(rs.getString("transferredByWhere"));
                maternity.setMotherBleeding6(rs.getString("motherBleeding6"));
                maternity.setMotherBp6(rs.getInt("motherBP6"));
                maternity.setBabyCheckedCord6(rs.getString("babyCheckedCord6"));
                maternity.setBabyBreastFeeding6(rs.getString("babyBreastFeeding6"));
                maternity.setBabyBreathing6(rs.getString("babyBreathing6"));
                maternity.setLlnsGiven(rs.getString("llnsGiven"));
                maternity.setBabyCondition(rs.getString("babyCondition"));
                maternity.setMotherFinalDiagnosis(rs.getString("motherFinalDiagnosis"));
                maternity.setMotherBleeding24(rs.getString("motherBleeding24"));
                maternity.setMotherBp24(rs.getInt("motherBP24"));
                maternity.setBabyCheckedCord24(rs.getString("babyCheckedCord24"));
                maternity.setBabyBreastFeeding24(rs.getString("babyBreastFeeding24"));
                maternity.setBabyBreathing24(rs.getString("babyBreathing24"));
                maternity.setIycf(rs.getString("iycf"));
                maternity.setIycfOption(rs.getString("iycfOption"));
                maternity.setCounselingDischarged(rs.getString("counselingDischarged"));
                maternity.setMaterNutrCouns(rs.getString("materNutrCouns"));
                maternity.setConditionOfMotherAtDischarge(rs.getString("conditionOfMotherAtDischarge"));
                maternity.setNameOfPersonDischarging(rs.getString("nameOfPersonDischarging"));
                maternity.setCadreOfPersonDischarging(rs.getString("cadreOfPersonDischarging"));
                maternity.setDateOfDischarge(rs.getDate("dateOfDischarge").toLocalDate());
                maternity.setTimeOfDischarge(rs.getTime("timeOfDischarge").toLocalTime());
                maternity.setRecordDate(rs.getTimestamp("recordDate").toLocalDateTime());
                maternity.setUserId(rs.getInt("userId"));
                maternity_list.add(maternity);
            }
            con.close();
            return maternity_list;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new model.Error("ChbDAO", "Get_Hmis_List", " Message: " + ex.getMessage(), now));
            return null;
        }
    }

    public static List<Antenatal> Get_Antenatal_List() throws SQLException {
        try {
            Connection con;

            System.out.println("ChbDAO.Get_Antenatal_List");
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "potato");
            now = LocalDateTime.now();


            PreparedStatement stmt = con.prepareStatement("SELECT * From antenatal,village Where antenatal.antVillage=village.VillageId");

            ResultSet rs = stmt.executeQuery();
            List <Antenatal> antenatal_list = new ArrayList<>();

            System.out.println("Null?"+rs.wasNull());
            while (rs.next()) {
                Antenatal antenatal = new Antenatal();

                antenatal.setAntId(rs.getInt("antId"));
                antenatal_list.add(antenatal);
            }
            con.close();
            return antenatal_list;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new model.Error("ChbDAO", "Get_Antenatal_List", " Message: " + ex.getMessage(), now));
            return null;
        }

    }

    public static List<Vht> Get_Village_Vht_List(String VillageId) throws SQLException {
        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "potato");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("SELECT * From vht Where vhtVillage=?");

            stmt.setString(1, VillageId);

            ResultSet rs = stmt.executeQuery();
            List <Vht> vht_list = new ArrayList <>();

            while (rs.next()) {

                Vht vht = new Vht();

                vht.setVhtId(rs.getInt("vhtId"));
                vht.setVhtName(rs.getString("vhtName"));
                vht.setAge(rs.getInt("age"));
                vht.setSex(rs.getString("sex"));
                vht.setVhtPhoneNumber(rs.getString("vhtPhoneNumber"));
                vht.setIsCBD(rs.getString("isCBD"));
                vht.setVillageId(rs.getString("vhtVillage"));
                vht.setRecordDate(rs.getTimestamp("recordDate").toLocalDateTime());
                vht.setUserId(rs.getInt("userId"));

                vht_list.add(vht);
            }
            con.close();
            return vht_list;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new model.Error("ChbDAO", "Get_Vht_List", " Message: " + ex.getMessage(), now));
            return null;
        }
    }

    public static List<Maternity> Get_Village_Maternity_List(String VillageId) throws SQLException {
        try {
            now = LocalDateTime.now();
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "potato");

            PreparedStatement stmt = con.prepareStatement("SELECT * From maternity WHERE matVillage = ?");

            stmt.setString(1, VillageId);

            ResultSet rs = stmt.executeQuery();
            List <Maternity> maternity_list = new ArrayList <>();

            while (rs.next()) {

                Maternity maternity = new Maternity();

                maternity.setMatId(rs.getInt("matId"));
                maternity.setDateOfAdmission(rs.getDate("dateOfAdmission").toLocalDate());
                maternity.setTimeOfAdmission(rs.getTime("timeOfAdmission").toLocalTime());
                maternity.setAdmissionNo(rs.getInt("admissionNo"));
                maternity.setAncNo(rs.getString("ancNo"));
                maternity.setIpdNo(rs.getInt("ipdNo"));
                maternity.setNin(rs.getString("nin"));
                maternity.setClientSurname(rs.getString("clientSurname"));
                maternity.setClientGivenName(rs.getString("clientGivenName"));
                maternity.setAge(rs.getInt("age"));
                maternity.setClientCategory(rs.getString("clientCategory"));
                maternity.setVillageId(rs.getString("matVillage"));
                maternity.setParishId(rs.getString("matParish"));
                maternity.setSubcountyId(rs.getString("matSubcounty"));
                maternity.setDistrictId(rs.getString("matDistrict"));
                maternity.setPhoneNumber(rs.getString("phoneNumber"));
                maternity.setGravidity(rs.getInt("gravidity"));
                maternity.setParity(rs.getInt("parity"));
                maternity.setGestationAge(rs.getInt("gestationAge"));
                maternity.setTerm(rs.getString("term"));
                maternity.setReasonForAdmission(rs.getString("reasonForAdmission"));
                maternity.setRevisit(rs.getBoolean("revisit"));
                maternity.setWhoClinicalStage(rs.getInt("whoClinicalStage"));
                maternity.setCd4Results(rs.getInt("cd4Results"));
                maternity.setCd4Date(rs.getDate("cd4Date").toLocalDate());
                maternity.setViralLoadResults(rs.getInt("viralLoadResults"));
                maternity.setViralLoadDate(rs.getDate("viralLoadDate").toLocalDate());
                maternity.setwInitialResult(rs.getString("wInitialResult"));
                maternity.setwTfv(rs.getString("wTfv"));
                maternity.setpInitialResult(rs.getString("pInitialResult"));
                maternity.setpTfv(rs.getString("pTfv"));
                maternity.setpArtCode(rs.getString("pArtCode"));
                maternity.setArtCode(rs.getString("artCode"));
                maternity.setArtNo(rs.getString("artNo"));
                maternity.setCtxCode(rs.getString("ctxCode"));
                maternity.setwSyphilis(rs.getString("wSyphilis"));
                maternity.setwHepatitisB(rs.getString("whepatitisB"));
                maternity.setpSyphilis(rs.getString("pSyphilis"));
                maternity.setpHepatitisB(rs.getString("pHepatitisB"));
                maternity.setMuac(rs.getString("muac"));
                maternity.setInrNo(rs.getInt("inrNo"));
                maternity.setModeOfDelivery(rs.getString("modeOfDelivery"));
                maternity.setDateOfDelivery(rs.getDate("dateOfDelivery").toLocalDate());
                maternity.setTimeOfDelivery(rs.getTime("timeOfDelivery").toLocalTime());
                maternity.setOxytocin(rs.getBoolean("oxytocin"));
                maternity.setMisoprostol(rs.getBoolean("misoprostol"));
                maternity.setErgometrine(rs.getBoolean("ergometrine"));
                maternity.setManagementProcedure(rs.getString("managementProcedure"));
                maternity.setOtherTreatment(rs.getString("otherTreatment"));
                maternity.setApgarScore(rs.getString("apgarScore"));
                maternity.setSexOfBaby(rs.getString("sexOfBaby"));
                maternity.setNotBreathing(rs.getString("notBreathing"));
                maternity.setImmediateSkinToSkin(rs.getBoolean("immediateSkinToSkin"));
                maternity.setSourceOfWarmth(rs.getString("sourceOfWarmth"));
                maternity.setBreastFed(rs.getBoolean("breastFed"));
                maternity.setTeo(rs.getBoolean("teo"));
                maternity.setVitK(rs.getBoolean("vitK"));
                maternity.setChlorhexidine(rs.getBoolean("chlorhexidine"));
                maternity.setWeight(rs.getFloat("weight"));
                maternity.setRiskStatus(rs.getString("riskStatus"));
                maternity.setArvsAdministered(rs.getString("arvsAdministered"));
                maternity.setSyrupDuration(rs.getInt("syrupDuration"));
                maternity.setBcgImmunization(rs.getString("bcgImmunization"));
                maternity.setPolioImmunization(rs.getString("polioImmunization"));
                maternity.setFamilyPlanningMethod(rs.getInt("familyPlanningMethod"));
                maternity.setFamilyPlanningDate(rs.getDate("familyPlanningDate").toLocalDate());
                maternity.setTreatmentOffered(rs.getString("treatmentOffered"));
                maternity.setBabyFinalDiagnosis(rs.getString("babyFinalDiagnosis"));
                maternity.setDeliveredByName(rs.getString("deliveredByName"));
                maternity.setDeliveredByCadre(rs.getString("deliveredByCadre"));
                maternity.setTransferredByName(rs.getString("transferredByName"));
                maternity.setTransferredByWhere(rs.getString("transferredByWhere"));
                maternity.setMotherBleeding6(rs.getString("motherBleeding6"));
                maternity.setMotherBp6(rs.getInt("motherBP6"));
                maternity.setBabyCheckedCord6(rs.getString("babyCheckedCord6"));
                maternity.setBabyBreastFeeding6(rs.getString("babyBreastFeeding6"));
                maternity.setBabyBreathing6(rs.getString("babyBreathing6"));
                maternity.setLlnsGiven(rs.getString("llnsGiven"));
                maternity.setBabyCondition(rs.getString("babyCondition"));
                maternity.setMotherFinalDiagnosis(rs.getString("motherFinalDiagnosis"));
                maternity.setMotherBleeding24(rs.getString("motherBleeding24"));
                maternity.setMotherBp24(rs.getInt("motherBP24"));
                maternity.setBabyCheckedCord24(rs.getString("babyCheckedCord24"));
                maternity.setBabyBreastFeeding24(rs.getString("babyBreastFeeding24"));
                maternity.setBabyBreathing24(rs.getString("babyBreathing24"));
                maternity.setIycf(rs.getString("iycf"));
                maternity.setIycfOption(rs.getString("iycfOption"));
                maternity.setCounselingDischarged(rs.getString("counselingDischarged"));
                maternity.setMaterNutrCouns(rs.getString("materNutrCouns"));
                maternity.setConditionOfMotherAtDischarge(rs.getString("conditionOfMotherAtDischarge"));
                maternity.setNameOfPersonDischarging(rs.getString("nameOfPersonDischarging"));
                maternity.setCadreOfPersonDischarging(rs.getString("cadreOfPersonDischarging"));
                maternity.setDateOfDischarge(rs.getDate("dateOfDischarge").toLocalDate());
                maternity.setTimeOfDischarge(rs.getTime("timeOfDischarge").toLocalTime());
                maternity.setRecordDate(rs.getTimestamp("recordDate").toLocalDateTime());
                maternity.setUserId(rs.getInt("userId"));

                maternity_list.add(maternity);
            }
            con.close();
            return maternity_list;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new model.Error("ChbDAO", "Get_Hmis_List", " Message: " + ex.getMessage(), now));
            return null;
        }
    }

    public static boolean Save_New_Vht(Vht new_vht,Integer userId) throws SQLException {
        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "potato");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("insert into vht"
                    + "(vhtName,Age,Sex,vhtPhoneNumber,isCBD,vhtVillage,recordDate,userId)"
                    + "values(?,?,?,?,?,?,?,?)");

            stmt.setString(1, new_vht.getVhtName());
            stmt.setObject(2, new_vht.getAge());
            stmt.setString(3, new_vht.getSex());
            stmt.setString(4, new_vht.getVhtPhoneNumber());
            stmt.setString(5, new_vht.getIsCBD());
            stmt.setString(6, new_vht.getVillageId());
            stmt.setString(7, now.format(dateTimeFormatter));
            stmt.setObject(8, userId);
            stmt.executeUpdate();

            con.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new model.Error("ChbDAO", "Save_New_Vht", " Message: " + ex.getMessage(), now));
            return false;
        }
    }

    public static boolean Save_New_Maternity(Maternity new_maternity, Integer userId) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            Connection con = DriverManager.getConnection(url, "root", "potato");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("insert into maternity"
                    + "(dateOfAdmission, timeOfAdmission, admissionNo, ancNo, ipdNo, nin, clientSurname, "
                    + "clientGivenName, age, clientCategory, villageName, parishName, subcountyName, districtName, "
                    + "phoneNumber, gravidity, parity, gestationAge, term, reasonForAdmission, revisit, "
                    + "whoClinicalStage, cd4Results, cd4Date, viralLoadResults, viralLoadDate, wInitialResult, wTFV, "
                    + "pInitialResult, pTFV, pArtCode, artCode, artNo, ctxCode, wSyphilis, wHepatitisB, muac, inrNo, "
                    + "modeOfDelivery, dateOfDelivery, timeOfDelivery, oxytocin, misoprostol, ergometrine, "
                    + "managementProcedure, otherTreatment, apgarScore, sexOfBaby, notBreathing, immediateSkinToSkin, "
                    + "sourceOfWarmth, breastFed, teo, vitK, chlorhexidine, weight, riskStatus, arvsAdministered, "
                    + "syrupDuration, bcgImmunization, polioImmunization, familyPlanningMethod, familyPlanningDate, "
                    + "treatmentOffered, babyFinalDiagnosis, deliveredByName, deliveredByCadre, transferredByName, "
                    + "transferredByWhere, motherBleeding6, motherBP6, babyCheckedCord6, babyBreastFeeding6, "
                    + "babyBreathing6, llnsGiven, babyCondition, motherFinalDiagnosis, motherBleeding24, motherBP24, "
                    + "babyCheckedCord24, babyBreastFeeding24, babyBreathing24, iycf, iycfOption, "
                    + "counselingDischarged, materNutrCouns. conditionOfMotherAtDischarge, nameOfPersonDischarging, "
                    + "cadreOfPersonDischarging, dateOfDischarge, timeOfDischarge, userId)"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,)");

            stmt.setString(1, new_maternity.getDateOfAdmission().format(dateFormatter));
            stmt.setString(2, new_maternity.getTimeOfAdmission().format(timeFormatter));
            stmt.setInt(3, new_maternity.getAdmissionNo());
            stmt.setString(4, new_maternity.getAncNo());
            stmt.setInt(5, new_maternity.getIpdNo());
            stmt.setString(6, new_maternity.getNin());
            stmt.setString(7, new_maternity.getClientSurname());
            stmt.setString(8, new_maternity.getClientGivenName());
            stmt.setInt(9, new_maternity.getAge());
            stmt.setString(10, new_maternity.getClientCategory());
            stmt.setString(11, new_maternity.getVillageId());
            stmt.setString(12, new_maternity.getParishId());
            stmt.setString(13, new_maternity.getSubcountyId());
            stmt.setString(14, new_maternity.getDistrictId());
            stmt.setString(15, new_maternity.getPhoneNumber());
            stmt.setInt(16, new_maternity.getGravidity());
            stmt.setInt(17, new_maternity.getParity());
            stmt.setInt(18, new_maternity.getGestationAge());
            stmt.setString(19, new_maternity.getTerm());
            stmt.setString(20, new_maternity.getReasonForAdmission());
            stmt.setBoolean(21, new_maternity.getRevisit());
            stmt.setInt(22, new_maternity.getWhoClinicalStage());
            stmt.setInt(23, new_maternity.getCd4Results());
            stmt.setString(24, new_maternity.getCd4Date().format(dateTimeFormatter));
            stmt.setInt(25, new_maternity.getViralLoadResults());
            stmt.setString(26, new_maternity.getViralLoadDate().format(dateTimeFormatter));
            stmt.setString(27, new_maternity.getwInitialResult());
            stmt.setString(28, new_maternity.getwTfv());
            stmt.setString(29, new_maternity.getpInitialResult());
            stmt.setString(30, new_maternity.getpTfv());
            stmt.setString(31, new_maternity.getpArtCode());
            stmt.setString(32, new_maternity.getArtCode());
            stmt.setString(33, new_maternity.getArtNo());
            stmt.setString(34, new_maternity.getCtxCode());
            stmt.setString(35, new_maternity.getwSyphilis());
            stmt.setString(36, new_maternity.getwHepatitisB());
            stmt.setString(37, new_maternity.getpSyphilis());
            stmt.setString(38, new_maternity.getpHepatitisB());
            stmt.setString(39, new_maternity.getMuac());
            stmt.setInt(40, new_maternity.getInrNo());
            stmt.setString(41, new_maternity.getModeOfDelivery());
            stmt.setString(42, new_maternity.getDateOfDelivery().format(dateFormatter));
            stmt.setString(43, new_maternity.getTimeOfDelivery().format(timeFormatter));
            stmt.setBoolean(44, new_maternity.getOxytocin());
            stmt.setBoolean(45, new_maternity.getMisoprostol());
            stmt.setBoolean(46, new_maternity.getErgometrine());
            stmt.setString(47, new_maternity.getManagementProcedure());
            stmt.setString(48, new_maternity.getOtherTreatment());
            stmt.setString(49, new_maternity.getApgarScore());
            stmt.setString(50, new_maternity.getSexOfBaby());
            stmt.setString(51, new_maternity.getNotBreathing());
            stmt.setBoolean(52, new_maternity.getImmediateSkinToSkin());
            stmt.setString(53, new_maternity.getSourceOfWarmth());
            stmt.setBoolean(54, new_maternity.getBreastFed());
            stmt.setBoolean(55, new_maternity.getTeo());
            stmt.setBoolean(56, new_maternity.getVitK());
            stmt.setBoolean(57, new_maternity.getChlorhexidine());
            stmt.setDouble(58, new_maternity.getWeight());
            stmt.setString(59, new_maternity.getRiskStatus());
            stmt.setString(60, new_maternity.getArvsAdministered());
            stmt.setInt(61, new_maternity.getSyrupDuration());
            stmt.setString(62, new_maternity.getBcgImmunization());
            stmt.setString(63, new_maternity.getPolioImmunization());
            stmt.setInt(64, new_maternity.getFamilyPlanningMethod());
            stmt.setString(65, new_maternity.getFamilyPlanningDate().format(dateFormatter));
            stmt.setString(66, new_maternity.getTreatmentOffered());
            stmt.setString(67, new_maternity.getBabyFinalDiagnosis());
            stmt.setString(68, new_maternity.getDeliveredByName());
            stmt.setString(69, new_maternity.getDeliveredByCadre());
            stmt.setString(70, new_maternity.getTransferredByName());
            stmt.setString(71, new_maternity.getTransferredByWhere());
            stmt.setString(72, new_maternity.getMotherBleeding6());
            stmt.setInt(73, new_maternity.getMotherBp6());
            stmt.setString(74, new_maternity.getBabyCheckedCord6());
            stmt.setString(75, new_maternity.getBabyBreastFeeding6());
            stmt.setString(76, new_maternity.getBabyBreathing6());
            stmt.setString(77, new_maternity.getLlnsGiven());
            stmt.setString(78, new_maternity.getBabyCondition());
            stmt.setString(79, new_maternity.getMotherFinalDiagnosis());
            stmt.setString(80, new_maternity.getMotherBleeding24());
            stmt.setInt(81, new_maternity.getMotherBp24());
            stmt.setString(82, new_maternity.getBabyCheckedCord24());
            stmt.setString(83, new_maternity.getBabyBreastFeeding24());
            stmt.setString(84, new_maternity.getBabyBreathing24());
            stmt.setString(85, new_maternity.getIycf());
            stmt.setString(86, new_maternity.getIycfOption());
            stmt.setString(87, new_maternity.getCounselingDischarged());
            stmt.setString(88, new_maternity.getMaterNutrCouns());
            stmt.setString(89, new_maternity.getConditionOfMotherAtDischarge());
            stmt.setString(90, new_maternity.getNameOfPersonDischarging());
            stmt.setString(91, new_maternity.getCadreOfPersonDischarging());
            stmt.setString(92, new_maternity.getDateOfDischarge().format(dateFormatter));
            stmt.setString(93, new_maternity.getTimeOfDischarge().format(timeFormatter));
            stmt.setString(94, now.format(dateTimeFormatter));
            stmt.setInt(95, userId);

//            if(new_maternity.getDeliveryDate()==null)
//            {
//                stmt.setNull(19, java.sql.Types.DATE);
//            }
//            else {
//                stmt.setString(19, dateFormat.format(new_maternity.getDeliveryDate()));
//            }
//            if(new_maternity.getDeliveryTime()==null)
//            {
//                stmt.setNull(20, java.sql.Types.TIME);
//            }

            stmt.executeUpdate();
            con.close();
            return true;
        } catch (Exception var6) {
            //for debugging
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR: "+ var6.getMessage(), "Success"));
            ErrorDAO.Error_Add(new Error("ChbDAO", "Save_New_Hmis", " Message: " + var6.getMessage(), now));
            return false;
        }
    }

    public static Vht Get_Existing_Vht(Integer VhtId) throws SQLException {
        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "potato");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("SELECT * From vht,village Where vht.vhtVillage=village.VillageId and vhtId=?");

            stmt.setObject(1, VhtId);

            ResultSet rs = stmt.executeQuery();

            Vht vht = new Vht();
            while (rs.next()) {

                vht.setVhtId(rs.getInt("vhtId"));
                vht.setVhtName(rs.getString("vhtName"));
                vht.setAge(rs.getInt("age"));
                vht.setSex(rs.getString("sex"));
                vht.setVhtPhoneNumber(rs.getString("vhtPhoneNumber"));
                vht.setIsCBD(rs.getString("isCBD"));
                vht.setVillageId(rs.getString("vhtVillage"));
                vht.setVillageName(rs.getString("VillageName"));
                vht.setRecordDate(rs.getTimestamp("recordDate").toLocalDateTime());
                vht.setUserId(rs.getInt("userId"));

            }
            con.close();
            return vht;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new model.Error("ChbDAO", "Get_Existing_Vht", " Message: " + ex.getMessage(), now));
            return null;
        }
    }

    public static Maternity Get_Existing_Maternity(Integer matId) throws SQLException {
        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "potato");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("SELECT * From hmis,village Where hmis.villageID=village.VillageId and ipd=?");

            stmt.setObject(1, matId);

            ResultSet rs = stmt.executeQuery();

            Maternity maternity = new Maternity();
            while (rs.next()) {

                maternity.setMatId(rs.getInt("matId"));
                maternity.setDateOfAdmission(rs.getDate("dateOfAdmission").toLocalDate());
                maternity.setTimeOfAdmission(rs.getTime("timeOfAdmission").toLocalTime());
                maternity.setAdmissionNo(rs.getInt("admissionNo"));
                maternity.setAncNo(rs.getString("ancNo"));
                maternity.setIpdNo(rs.getInt("ipdNo"));
                maternity.setNin(rs.getString("nin"));
                maternity.setClientSurname(rs.getString("clientSurname"));
                maternity.setClientGivenName(rs.getString("clientGivenName"));
                maternity.setAge(rs.getInt("age"));
                maternity.setClientCategory(rs.getString("clientCategory"));
                maternity.setVillageId(rs.getString("maternityVillage"));
                maternity.setVillageName(rs.getString("villageName"));
                maternity.setParishId(rs.getString("maternityParish"));
                maternity.setParishName(rs.getString("parishName"));
                maternity.setSubcountyId(rs.getString("maternitySubcounty"));
                maternity.setSubcountyName(rs.getString("subcountyName"));
                maternity.setDistrictId(rs.getString("maternityDistrict"));
                maternity.setDistrictName(rs.getString("districtName"));
                maternity.setPhoneNumber(rs.getString("phoneNumber"));
                maternity.setGravidity(rs.getInt("gravidity"));
                maternity.setParity(rs.getInt("parity"));
                maternity.setGestationAge(rs.getInt("gestationAge"));
                maternity.setTerm(rs.getString("term"));
                maternity.setReasonForAdmission(rs.getString("reasonForAdmission"));
                maternity.setRevisit(rs.getBoolean("revisit"));
                maternity.setWhoClinicalStage(rs.getInt("whoClinicalStage"));
                maternity.setCd4Results(rs.getInt("cd4Results"));
                maternity.setCd4Date(rs.getDate("cd4Date").toLocalDate());
                maternity.setViralLoadResults(rs.getInt("viralLoadResults"));
                maternity.setViralLoadDate(rs.getDate("viralLoadDate").toLocalDate());
                maternity.setwInitialResult(rs.getString("wInitialResult"));
                maternity.setwTfv(rs.getString("wTfv"));
                maternity.setpInitialResult(rs.getString("pInitialResult"));
                maternity.setpTfv(rs.getString("pTfv"));
                maternity.setpArtCode(rs.getString("pArtCode"));
                maternity.setArtCode(rs.getString("artCode"));
                maternity.setArtNo(rs.getString("artNo"));
                maternity.setCtxCode(rs.getString("ctxCode"));
                maternity.setwSyphilis(rs.getString("wSyphilis"));
                maternity.setwHepatitisB(rs.getString("whepatitisB"));
                maternity.setpSyphilis(rs.getString("pSyphilis"));
                maternity.setpHepatitisB(rs.getString("pHepatitisB"));
                maternity.setMuac(rs.getString("muac"));
                maternity.setInrNo(rs.getInt("inrNo"));
                maternity.setModeOfDelivery(rs.getString("modeOfDelivery"));
                maternity.setDateOfDelivery(rs.getDate("dateOfDelivery").toLocalDate());
                maternity.setTimeOfDelivery(rs.getTime("timeOfDelivery").toLocalTime());
                maternity.setOxytocin(rs.getBoolean("oxytocin"));
                maternity.setMisoprostol(rs.getBoolean("misoprostol"));
                maternity.setErgometrine(rs.getBoolean("ergometrine"));
                maternity.setManagementProcedure(rs.getString("managementProcedure"));
                maternity.setOtherTreatment(rs.getString("otherTreatment"));
                maternity.setApgarScore(rs.getString("apgarScore"));
                maternity.setSexOfBaby(rs.getString("sexOfBaby"));
                maternity.setNotBreathing(rs.getString("notBreathing"));
                maternity.setImmediateSkinToSkin(rs.getBoolean("immediateSkinToSkin"));
                maternity.setSourceOfWarmth(rs.getString("sourceOfWarmth"));
                maternity.setBreastFed(rs.getBoolean("breastFed"));
                maternity.setTeo(rs.getBoolean("teo"));
                maternity.setVitK(rs.getBoolean("vitK"));
                maternity.setChlorhexidine(rs.getBoolean("chlorhexidine"));
                maternity.setWeight(rs.getFloat("weight"));
                maternity.setRiskStatus(rs.getString("riskStatus"));
                maternity.setArvsAdministered(rs.getString("arvsAdministered"));
                maternity.setSyrupDuration(rs.getInt("syrupDuration"));
                maternity.setBcgImmunization(rs.getString("bcgImmunization"));
                maternity.setPolioImmunization(rs.getString("polioImmunization"));
                maternity.setFamilyPlanningMethod(rs.getInt("familyPlanningMethod"));
                maternity.setFamilyPlanningDate(rs.getDate("familyPlanningDate").toLocalDate());
                maternity.setTreatmentOffered(rs.getString("treatmentOffered"));
                maternity.setBabyFinalDiagnosis(rs.getString("babyFinalDiagnosis"));
                maternity.setDeliveredByName(rs.getString("deliveredByName"));
                maternity.setDeliveredByCadre(rs.getString("deliveredByCadre"));
                maternity.setTransferredByName(rs.getString("transferredByName"));
                maternity.setTransferredByWhere(rs.getString("transferredByWhere"));
                maternity.setMotherBleeding6(rs.getString("motherBleeding6"));
                maternity.setMotherBp6(rs.getInt("motherBP6"));
                maternity.setBabyCheckedCord6(rs.getString("babyCheckedCord6"));
                maternity.setBabyBreastFeeding6(rs.getString("babyBreastFeeding6"));
                maternity.setBabyBreathing6(rs.getString("babyBreathing6"));
                maternity.setLlnsGiven(rs.getString("llnsGiven"));
                maternity.setBabyCondition(rs.getString("babyCondition"));
                maternity.setMotherFinalDiagnosis(rs.getString("motherFinalDiagnosis"));
                maternity.setMotherBleeding24(rs.getString("motherBleeding24"));
                maternity.setMotherBp24(rs.getInt("motherBP24"));
                maternity.setBabyCheckedCord24(rs.getString("babyCheckedCord24"));
                maternity.setBabyBreastFeeding24(rs.getString("babyBreastFeeding24"));
                maternity.setBabyBreathing24(rs.getString("babyBreathing24"));
                maternity.setIycf(rs.getString("iycf"));
                maternity.setIycfOption(rs.getString("iycfOption"));
                maternity.setCounselingDischarged(rs.getString("counselingDischarged"));
                maternity.setMaterNutrCouns(rs.getString("materNutrCouns"));
                maternity.setConditionOfMotherAtDischarge(rs.getString("conditionOfMotherAtDischarge"));
                maternity.setNameOfPersonDischarging(rs.getString("nameOfPersonDischarging"));
                maternity.setCadreOfPersonDischarging(rs.getString("cadreOfPersonDischarging"));
                maternity.setDateOfDischarge(rs.getDate("dateOfDischarge").toLocalDate());
                maternity.setTimeOfDischarge(rs.getTime("timeOfDischarge").toLocalTime());
                maternity.setRecordDate(rs.getTimestamp("recordDate").toLocalDateTime());
                maternity.setUserId(rs.getInt("userId"));

            }
            con.close();
            return maternity;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new model.Error("ChbDAO", "Get_Existing_Hmis", " Message: " + ex.getMessage(), now));
            return null;
        }
    }

    public static boolean Update_Existing_Vht(Vht existing_vht) throws SQLException {
        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "potato");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("UPDATE vht SET "
                    + "Age=?,Sex=?,vhtPhoneNumber=?,isCBD=? Where vhtId=?");

            stmt.setObject(1, existing_vht.getAge());
            stmt.setString(2, existing_vht.getSex());
            stmt.setString(3, existing_vht.getVhtPhoneNumber());
            stmt.setString(4, existing_vht.getIsCBD());
            stmt.setObject(5, existing_vht.getVhtId());
            stmt.executeUpdate();

            con.close();
            return true;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new model.Error("ChbDAO", "Update_Existing_Vht", " Message: " + ex.getMessage(), now));
            return false;
        }
    }

    public static boolean Update_Existing_Maternity(Maternity existing_maternity) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            Connection con = DriverManager.getConnection(url, "root", "potato");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("UPDATE maternity SET " +
                    "dateOfAdmission=?, timeOfAdmission=?, admissionNo=?, ancNo=?, ipdNo=?," +
                    "nin=?, clientSurname=?, clientGivenName=?, age=?, clientCategory=?," +
                    "villageName=?, parishName=?, subcountyName=?, districtName=?, phoneNumber=?, gravidity=?," +
                    "parity=?, gestationAge=?, term=?, reasonForAdmission=?, revisit=?, whoClinicalStage=?," +
                    "cd4Results=?, cd4Date=?, viralLoadResults=?, viralLoadDate=?, wInitialResult=?, wTFV=?," +
                    "pInitialResult=?, pTFV=?, pArtCode=?, artCode=?, artNo=?, ctxCode=?, wSyphilis=?, wHepatitisB=?," +
                    "muac=?, inrNo=?, modeOfDelivery=?, dateOfDelivery=?, timeOfDelivery=?, oxytocin=?," +
                    "misoprostol=?, ergometrine=?, managementProcedure=?, otherTreatment=?, apgarScore=?," +
                    "sexOfBaby=?, notBreathing=?, immediateSkinToSkin=?, sourceOfWarmth=?, breastFed=?, teo=?, vitK=?," +
                    "chlorhexidine=?, weight=?, riskStatus=?, arvsAdministered=?, syrupDuration=?," +
                    "bcgImmunization=?, polioImmunization=?, familyPlanningMethod=?, familyPlanningDate=?," +
                    "treatmentOffered=?, babyFinalDiagnosis=?, deliveredByName=?, deliveredByCadre=?," +
                    "transferredByName=?, transferredByWhere=?, motherBleeding6=?, motherBP6=?, babyCheckedCord6=?," +
                    "babyBreastFeeding6=?, babyBreathing6=?, llnsGiven=?, babyCondition=?, motherFinalDiagnosis=?," +
                    "motherBleeding24=?, motherBP24=?, babyCheckedCord24=?, babyBreastFeeding24=?, babyBreathing24=?," +
                    "iycf=?, iycfOption=?, counselingDischarged=?, materNutrCouns=?, conditionOfMotherAtDischarge=?," +
                    "nameOfPersonDischarging=?, cadreOfPersonDischarging=?, dateOfDischarge=?, timeOfDischarge=?" +
                    "WHERE maternityID=?");

            stmt.setString(1, existing_maternity.getDateOfAdmission().format(dateFormatter));
            stmt.setString(2, existing_maternity.getTimeOfAdmission().format(timeFormatter));
            stmt.setInt(3, existing_maternity.getAdmissionNo());
            stmt.setString(4, existing_maternity.getAncNo());
            stmt.setInt(5, existing_maternity.getIpdNo());
            stmt.setString(6, existing_maternity.getNin());
            stmt.setString(7, existing_maternity.getClientSurname());
            stmt.setString(8, existing_maternity.getClientGivenName());
            stmt.setInt(9, existing_maternity.getAge());
            stmt.setString(10, existing_maternity.getClientCategory());
            stmt.setString(11, existing_maternity.getVillageName());
            stmt.setString(12, existing_maternity.getParishName());
            stmt.setString(13, existing_maternity.getSubcountyName());
            stmt.setString(14, existing_maternity.getDistrictName());
            stmt.setString(15, existing_maternity.getPhoneNumber());
            stmt.setInt(16, existing_maternity.getGravidity());
            stmt.setInt(17, existing_maternity.getParity());
            stmt.setInt(18, existing_maternity.getGestationAge());
            stmt.setString(19, existing_maternity.getTerm());
            stmt.setString(20, existing_maternity.getReasonForAdmission());
            stmt.setBoolean(21, existing_maternity.getRevisit());
            stmt.setInt(22, existing_maternity.getWhoClinicalStage());
            stmt.setInt(23, existing_maternity.getCd4Results());
            stmt.setString(24, existing_maternity.getCd4Date().format(dateFormatter));
            stmt.setInt(25, existing_maternity.getViralLoadResults());
            stmt.setString(26, existing_maternity.getViralLoadDate().format(dateFormatter));
            stmt.setString(27, existing_maternity.getwInitialResult());
            stmt.setString(28, existing_maternity.getwTfv());
            stmt.setString(29, existing_maternity.getpInitialResult());
            stmt.setString(30, existing_maternity.getpTfv());
            stmt.setString(31, existing_maternity.getpArtCode());
            stmt.setString(32, existing_maternity.getArtCode());
            stmt.setString(33, existing_maternity.getArtNo());
            stmt.setString(34, existing_maternity.getCtxCode());
            stmt.setString(35, existing_maternity.getwSyphilis());
            stmt.setString(36, existing_maternity.getwHepatitisB());
            stmt.setString(37, existing_maternity.getpSyphilis());
            stmt.setString(38, existing_maternity.getpHepatitisB());
            stmt.setString(39, existing_maternity.getMuac());
            stmt.setInt(40, existing_maternity.getInrNo());
            stmt.setString(41, existing_maternity.getModeOfDelivery());
            stmt.setString(42, existing_maternity.getDateOfDelivery().format(dateFormatter));
            stmt.setString(43, existing_maternity.getTimeOfDelivery().format(timeFormatter));
            stmt.setBoolean(44, existing_maternity.getOxytocin());
            stmt.setBoolean(45, existing_maternity.getMisoprostol());
            stmt.setBoolean(46, existing_maternity.getErgometrine());
            stmt.setString(47, existing_maternity.getManagementProcedure());
            stmt.setString(48, existing_maternity.getOtherTreatment());
            stmt.setString(49, existing_maternity.getApgarScore());
            stmt.setString(50, existing_maternity.getSexOfBaby());
            stmt.setString(51, existing_maternity.getNotBreathing());
            stmt.setBoolean(52, existing_maternity.getImmediateSkinToSkin());
            stmt.setString(53, existing_maternity.getSourceOfWarmth());
            stmt.setBoolean(54, existing_maternity.getBreastFed());
            stmt.setBoolean(55, existing_maternity.getTeo());
            stmt.setBoolean(56, existing_maternity.getVitK());
            stmt.setBoolean(57, existing_maternity.getChlorhexidine());
            stmt.setDouble(58, existing_maternity.getWeight());
            stmt.setString(59, existing_maternity.getRiskStatus());
            stmt.setString(60, existing_maternity.getArvsAdministered());
            stmt.setInt(61, existing_maternity.getSyrupDuration());
            stmt.setString(62, existing_maternity.getBcgImmunization());
            stmt.setString(63, existing_maternity.getPolioImmunization());
            stmt.setInt(64, existing_maternity.getFamilyPlanningMethod());
            stmt.setString(65, existing_maternity.getFamilyPlanningDate().format(dateFormatter));
            stmt.setString(66, existing_maternity.getTreatmentOffered());
            stmt.setString(67, existing_maternity.getBabyFinalDiagnosis());
            stmt.setString(68, existing_maternity.getDeliveredByName());
            stmt.setString(69, existing_maternity.getDeliveredByCadre());
            stmt.setString(70, existing_maternity.getTransferredByName());
            stmt.setString(71, existing_maternity.getTransferredByWhere());
            stmt.setString(72, existing_maternity.getMotherBleeding6());
            stmt.setInt(73, existing_maternity.getMotherBp6());
            stmt.setString(74, existing_maternity.getBabyCheckedCord6());
            stmt.setString(75, existing_maternity.getBabyBreastFeeding6());
            stmt.setString(76, existing_maternity.getBabyBreathing6());
            stmt.setString(77, existing_maternity.getLlnsGiven());
            stmt.setString(78, existing_maternity.getBabyCondition());
            stmt.setString(79, existing_maternity.getMotherFinalDiagnosis());
            stmt.setString(80, existing_maternity.getMotherBleeding24());
            stmt.setInt(81, existing_maternity.getMotherBp24());
            stmt.setString(82, existing_maternity.getBabyCheckedCord24());
            stmt.setString(83, existing_maternity.getBabyBreastFeeding24());
            stmt.setString(84, existing_maternity.getBabyBreathing24());
            stmt.setString(85, existing_maternity.getIycf());
            stmt.setString(86, existing_maternity.getIycfOption());
            stmt.setString(87, existing_maternity.getCounselingDischarged());
            stmt.setString(88, existing_maternity.getMaterNutrCouns());
            stmt.setString(89, existing_maternity.getConditionOfMotherAtDischarge());
            stmt.setString(90, existing_maternity.getNameOfPersonDischarging());
            stmt.setString(91, existing_maternity.getCadreOfPersonDischarging());
            stmt.setString(92, existing_maternity.getDateOfDischarge().format(dateFormatter));
            stmt.setString(93, existing_maternity.getTimeOfDischarge().format(timeFormatter));
            stmt.setInt(94, existing_maternity.getMatId());

            stmt.executeUpdate();
            con.close();
            return true;
        } catch (Exception var4) {
            //use for debugging
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR: "+var4, "Success"));

            ErrorDAO.Error_Add(new Error("ChbDAO", "Update_Existing_HMIS", " Message: " + var4.getMessage(), now));
            return false;
        }
    }

    @SuppressWarnings("unused") //no obvious use for this function but no harm in keeping it in
    private static java.sql.Date stringToSQLDate(String startDate)
    {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date date = formatter.parse(startDate);
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
            return sqlStartDate;
        }catch (ParseException e)
        {
           e.printStackTrace();
        }
        return null;
    }

}
