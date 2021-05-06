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
            con = DriverManager.getConnection(url, "root", "t00r");
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
    public static List<Parish> Get_Parishes_From_Subcounty(String subcountyId) throws SQLException {

        try {
            Connection con;

            Parish parish;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "t00r");
            now = LocalDateTime.now();

            PreparedStatement stmt;

            if(subcountyId == null || subcountyId.length() == 0){
                stmt = con.prepareStatement("SELECT * FROM parishes");
            } else {
                stmt = con.prepareStatement("SELECT * FROM parishes WHERE SubcountyId='" + subcountyId+ "'");
            }

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
    public static List<Village> Get_Villages_From_Parish(String parishId) throws SQLException {

        try {
            Connection con;

            Village village;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "t00r");
            now = LocalDateTime.now();

            PreparedStatement stmt;

            if(parishId == null || parishId.length() == 0){
                stmt = con.prepareStatement("SELECT * FROM village");
            } else {
                stmt = con.prepareStatement("SELECT * FROM village WHERE ParishId='" + parishId+ "'");
            }

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

    // Selects all villages with a subcounty
    public static List<Village> Get_Villages_From_Subcounty(String subcountyId) throws SQLException {

        try {
            Connection con;

            Village village;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "t00r");
            now = LocalDateTime.now();

            PreparedStatement stmt;

            if(subcountyId == null || subcountyId.length() == 0){
                stmt = con.prepareStatement("SELECT * FROM village");
            } else {
                stmt = con.prepareStatement("SELECT * FROM village WHERE ParishId IN (SELECT ParishId FROM parishes WHERE SubcountyId='" + subcountyId+ "')");
            }

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

    // Return a Parish object given the id of a village in that Parish
    public static Parish Get_Parish_From_Village(String villageId) throws SQLException{
        try{
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "t00r");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("SELECT ParishId FROM village WHERE VillageId='" + villageId + "'");

            ResultSet rs = stmt.executeQuery();
            rs.next();

            String parishId = rs.getString("ParishId");

            stmt = con.prepareStatement("SELECT * FROM parishes WHERE ParishId='" + parishId + "'");

            rs = stmt.executeQuery();
            rs.next();

            Parish parish = new Parish(parishId, rs.getString("SubcountyId"), rs.getString("ParishName"));

            con.close();

            return parish;

        } catch (Exception e){
            ErrorDAO.Error_Add(new Error("ChbDAO", "ChbDAO_Get_Parish_From_Village", " Message: " + e.getMessage(), now));
            return null;
        }
    }

    // Return a Subcounty object given the id of a parish in that subcounty
    public static Subcounty Get_Subcounty_From_Parish(String parishId) throws SQLException{
        try{
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "t00r");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("SELECT SubcountyId FROM parishes WHERE ParishId='" + parishId + "'");

            ResultSet rs = stmt.executeQuery();
            rs.next();

            String subcountyId = rs.getString("SubcountyId");

            stmt = con.prepareStatement("SELECT * FROM subcounty WHERE SubcountyId='" + subcountyId + "'");

            rs = stmt.executeQuery();
            rs.next();

            Subcounty subcounty = new Subcounty(subcountyId, rs.getString("SubcountyName"));

            con.close();

            return subcounty;

        } catch (Exception e){
            ErrorDAO.Error_Add(new Error("ChbDAO", "ChbDAO_Get_Parish_From_Village", " Message: " + e.getMessage(), now));
            return null;
        }
    }

    public static List<Vht> Get_Vht_List() throws SQLException {
        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "t00r");
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

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "t00r");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("SELECT * From maternity");

            ResultSet rs = stmt.executeQuery();
            List <Maternity> maternity_list = new ArrayList<>();

            while (rs.next()) {

                Maternity maternity = new Maternity();

                maternity.setMatId(rs.getString("matId"));
                maternity.setAdmissionNo(rs.getString("admissionNo"));
                maternity.setDateOfAdmission(rs.getDate("dateOfAdmission").toLocalDate());
                maternity.setClientGivenName(rs.getString("clientGivenName"));
                maternity.setClientSurname(rs.getString("clientSurname"));

                ResultSet villageData = con.prepareStatement(String.format("SELECT VillageName From village WHERE VillageId LIKE '%s'", rs.getString("matVillage")))
                                           .executeQuery();
                if(villageData.next()) {
                    maternity.setVillageName(villageData.getString("VillageName"));
                }
                maternity.setRecordDate(rs.getTimestamp("recordDate").toLocalDateTime());
                maternity.setDateOfAdmission(rs.getDate("dateOfAdmission").toLocalDate());
                maternity.setTimeOfAdmission(rs.getTime("timeOfAdmission").toLocalTime());
                maternity.setAdmissionNo(rs.getString("admissionNo"));
                maternity.setAncNo(rs.getString("ancNo"));
                maternity.setIpdNo(rs.getString("ipdNo"));
                maternity.setNin(rs.getString("nin"));
                maternity.setHasNin(maternity.getNin() != null);
                maternity.setClientSurname(rs.getString("clientSurname"));
                maternity.setClientGivenName(rs.getString("clientGivenName"));
                maternity.setAge(rs.getString("age"));
                maternity.setClientCategory(rs.getString("clientCategory"));
                maternity.setVillageId(rs.getString("matVillage"));
                // maternity.setVillageName(rs.getString("villageName")); TODO: Fix the corr. getter method
                maternity.setParishId(rs.getString("matParish"));
                // maternity.setParishName(rs.getString("parishName")); TODO: Fix the corr. getter method
                maternity.setSubcountyId(rs.getString("matSubcounty"));
                // maternity.setSubcountyName(rs.getString("subcountyName")); TODO: Fix the corr. getter method
                maternity.setDistrictId(rs.getString("matDistrict"));
                // maternity.setDistrictName(rs.getString("districtName")); TODO: Fix the corr. getter method
                maternity.setPhoneNumber(rs.getString("phoneNumber"));
                maternity.setGravidity(rs.getString("gravidity"));
                maternity.setParity(rs.getString("parity"));
                maternity.setGestationAge(rs.getString("gestationAge"));
                maternity.setTerm(rs.getString("term"));
                maternity.setReasonForAdmission(rs.getString("reasonForAdmission"));
                maternity.setRevisit(rs.getBoolean("revisit"));
                maternity.setWhoClinicalStage(rs.getString("whoClinicalStage"));
                maternity.setCd4Results(rs.getString("cd4Results"));
                if(rs.getDate("cd4Date") != null)
                    maternity.setCd4Date(rs.getDate("cd4Date").toLocalDate());
                maternity.setViralLoadResults(rs.getString("viralLoadResults"));
                if(rs.getDate("viralLoadDate") != null)
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
                maternity.setInrNo(rs.getString("inrNo"));
                maternity.setModeOfDelivery(rs.getString("modeOfDelivery"));
                if(rs.getDate("dateOfDelivery") != null)
                    maternity.setDateOfDelivery(rs.getDate("dateOfDelivery").toLocalDate());
                if(rs.getTime("timeOfDelivery") != null)
                    maternity.setTimeOfDelivery(rs.getTime("timeOfDelivery").toLocalTime());
                maternity.setLiveBirths(rs.getString("liveBirths"));
                maternity.setOxytocin(rs.getBoolean("oxytocin"));
                maternity.setMisoprostol(rs.getBoolean("misoprostol"));
                maternity.setErgometrine(rs.getBoolean("ergometrine"));
                maternity.setManagementProcedure(rs.getString("managementProcedure"));
                maternity.setOtherTreatment(rs.getString("otherTreatment"));
                maternity.setApgarScore(rs.getString("apgarScore"));
                maternity.setSexOfBaby(rs.getString("sexOfBaby"));
                maternity.setNotBreathing(rs.getString("notBreathing"));
                maternity.setImmediateSkinToSkin(rs.getString("immediateSkinToSkin"));
                maternity.setSourceOfWarmth(rs.getString("sourceOfWarmth"));
                maternity.setBreastFed(rs.getString("breastFed"));
                maternity.setTeo(rs.getBoolean("teo"));
                maternity.setVitK(rs.getBoolean("vitK"));
                maternity.setChlorhexidine(rs.getBoolean("chlorhexidine"));
                maternity.setWeight(rs.getString("weight"));
                maternity.setRiskStatus(rs.getString("riskStatus"));
                maternity.setArvsAdministered(rs.getString("arvsAdministered"));
                maternity.setSyrupDuration(rs.getString("syrupDuration"));
                maternity.setBcgImmunization(rs.getString("bcgImmunization"));
                maternity.setPolioImmunization(rs.getString("polioImmunization"));
                maternity.setFamilyPlanningMethod(rs.getString("familyPlanningMethod"));
                if(rs.getDate("familyPlanningDate") != null)
                    maternity.setFamilyPlanningDate(rs.getDate("familyPlanningDate").toLocalDate());
                maternity.setTreatmentOffered(rs.getString("treatmentOffered"));
                maternity.setBabyFinalDiagnosis(rs.getString("babyFinalDiagnosis"));
                maternity.setDeliveredByName(rs.getString("deliveredByName"));
                maternity.setDeliveredByCadre(rs.getString("deliveredByCadre"));
                maternity.setTransferredByName(rs.getString("transferredByName"));
                maternity.setTransferredByWhere(rs.getString("transferredByWhere"));
                maternity.setMotherBleeding6(rs.getString("motherBleeding6"));
                maternity.setMotherDias6(rs.getString("motherDias6"));
                maternity.setMotherSyst6(rs.getString("motherSyst6"));
                maternity.setBabyCheckedCord6(rs.getString("babyCheckedCord6"));
                maternity.setBabyBreastFeeding6(rs.getString("babyBreastFeeding6"));
                maternity.setBabyBreathing6(rs.getString("babyBreathing6"));
                maternity.setLlinsGiven(rs.getString("llinsGiven"));
                maternity.setBabyCondition(rs.getString("babyCondition"));
                maternity.setMotherFinalDiagnosis(rs.getString("motherFinalDiagnosis"));
                maternity.setMotherBleeding24(rs.getString("motherBleeding24"));
                maternity.setMotherDias24(rs.getString("motherDias24"));
                maternity.setMotherSyst24(rs.getString("motherSyst24"));
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
                if(rs.getDate("dateOfDischarge") != null)
                    maternity.setDateOfDischarge(rs.getDate("dateOfDischarge").toLocalDate());
                if(rs.getTime("timeOfDischarge") != null)
                    maternity.setTimeOfDischarge(rs.getTime("timeOfDischarge").toLocalTime());
                if(rs.getDate("recordDate") != null)
                    maternity.setRecordDate(rs.getTimestamp("recordDate").toLocalDateTime());
                maternity.setUserId(rs.getString("userID"));
                maternity_list.add(maternity);
            }
            con.close();
            return maternity_list;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new model.Error("ChbDAO", "Get_Maternity_List", " Message: " + ex.getMessage(), now));
            return null;
        }
    }

    public static List<Vht> Get_Village_Vht_List(String VillageId) throws SQLException {
        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "t00r");
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
            con = DriverManager.getConnection(url, "root", "t00r");

            PreparedStatement stmt = con.prepareStatement("SELECT * From maternity WHERE matVillage = ?");

            stmt.setString(1, VillageId);

            ResultSet rs = stmt.executeQuery();
            List <Maternity> maternity_list = new ArrayList <>();

            while (rs.next()) {

                Maternity maternity = new Maternity();

                maternity.setMatId(rs.getString("matId"));
                maternity.setDateOfAdmission(rs.getDate("dateOfAdmission").toLocalDate());
                maternity.setTimeOfAdmission(rs.getTime("timeOfAdmission").toLocalTime());
                maternity.setAdmissionNo(rs.getString("admissionNo"));
                maternity.setAncNo(rs.getString("ancNo"));
                maternity.setIpdNo(rs.getString("ipdNo"));
                maternity.setNin(rs.getString("nin"));
                maternity.setClientSurname(rs.getString("clientSurname"));
                maternity.setClientGivenName(rs.getString("clientGivenName"));
                maternity.setAge(rs.getString("age"));
                maternity.setClientCategory(rs.getString("clientCategory"));
                maternity.setVillageId(rs.getString("matVillage"));
                maternity.setParishId(rs.getString("matParish"));
                maternity.setSubcountyId(rs.getString("matSubcounty"));
                maternity.setDistrictId(rs.getString("matDistrict"));
                maternity.setPhoneNumber(rs.getString("phoneNumber"));
                maternity.setGravidity(rs.getString("gravidity"));
                maternity.setParity(rs.getString("parity"));
                maternity.setGestationAge(rs.getString("gestationAge"));
                maternity.setTerm(rs.getString("term"));
                maternity.setReasonForAdmission(rs.getString("reasonForAdmission"));
                maternity.setRevisit(rs.getBoolean("revisit"));
                maternity.setWhoClinicalStage(rs.getString("whoClinicalStage"));
                maternity.setCd4Results(rs.getString("cd4Results"));
                maternity.setCd4Date(rs.getDate("cd4Date").toLocalDate());
                maternity.setViralLoadResults(rs.getString("viralLoadResults"));
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
                maternity.setInrNo(rs.getString("inrNo"));
                maternity.setModeOfDelivery(rs.getString("modeOfDelivery"));
                maternity.setDateOfDelivery(rs.getDate("dateOfDelivery").toLocalDate());
                maternity.setTimeOfDelivery(rs.getTime("timeOfDelivery").toLocalTime());
                maternity.setLiveBirths(rs.getString("liveBirths"));
                maternity.setOxytocin(rs.getBoolean("oxytocin"));
                maternity.setMisoprostol(rs.getBoolean("misoprostol"));
                maternity.setErgometrine(rs.getBoolean("ergometrine"));
                maternity.setManagementProcedure(rs.getString("managementProcedure"));
                maternity.setOtherTreatment(rs.getString("otherTreatment"));
                maternity.setApgarScore(rs.getString("apgarScore"));
                maternity.setSexOfBaby(rs.getString("sexOfBaby"));
                maternity.setNotBreathing(rs.getString("notBreathing"));
                maternity.setImmediateSkinToSkin(rs.getString("immediateSkinToSkin"));
                maternity.setSourceOfWarmth(rs.getString("sourceOfWarmth"));
                maternity.setBreastFed(rs.getString("breastFed"));
                maternity.setTeo(rs.getBoolean("teo"));
                maternity.setVitK(rs.getBoolean("vitK"));
                maternity.setChlorhexidine(rs.getBoolean("chlorhexidine"));
                maternity.setWeight(rs.getString("weight"));
                maternity.setRiskStatus(rs.getString("riskStatus"));
                maternity.setArvsAdministered(rs.getString("arvsAdministered"));
                maternity.setSyrupDuration(rs.getString("syrupDuration"));
                maternity.setBcgImmunization(rs.getString("bcgImmunization"));
                maternity.setPolioImmunization(rs.getString("polioImmunization"));
                maternity.setFamilyPlanningMethod(rs.getString("familyPlanningMethod"));
                maternity.setFamilyPlanningDate(rs.getDate("familyPlanningDate").toLocalDate());
                maternity.setTreatmentOffered(rs.getString("treatmentOffered"));
                maternity.setBabyFinalDiagnosis(rs.getString("babyFinalDiagnosis"));
                maternity.setDeliveredByName(rs.getString("deliveredByName"));
                maternity.setDeliveredByCadre(rs.getString("deliveredByCadre"));
                maternity.setTransferredByName(rs.getString("transferredByName"));
                maternity.setTransferredByWhere(rs.getString("transferredByWhere"));
                maternity.setMotherBleeding6(rs.getString("motherBleeding6"));
                maternity.setMotherDias6(rs.getString("motherDias6"));
                maternity.setMotherSyst6(rs.getString("motherSyst6"));
                maternity.setBabyCheckedCord6(rs.getString("babyCheckedCord6"));
                maternity.setBabyBreastFeeding6(rs.getString("babyBreastFeeding6"));
                maternity.setBabyBreathing6(rs.getString("babyBreathing6"));
                maternity.setLlinsGiven(rs.getString("llinsGiven"));
                maternity.setBabyCondition(rs.getString("babyCondition"));
                maternity.setMotherFinalDiagnosis(rs.getString("motherFinalDiagnosis"));
                maternity.setMotherBleeding24(rs.getString("motherBleeding24"));
                maternity.setMotherDias24(rs.getString("motherDias24"));
                maternity.setMotherSyst24(rs.getString("motherSyst24"));
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
                maternity.setUserId(rs.getString("userID"));

                maternity_list.add(maternity);
            }
            con.close();
            return maternity_list;
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new model.Error("ChbDAO", "Get_Maternity_List", " Message: " + ex.getMessage(), now));
            return null;
        }
    }

    public static boolean Save_New_Vht(Vht new_vht,Integer userId) throws SQLException {
        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "t00r");
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

    public static boolean Save_New_Maternity(Maternity maternity, Integer userId) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            Connection con = DriverManager.getConnection(url, "root", "t00r");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("insert into maternity"
                    + "(dateOfAdmission, timeOfAdmission, admissionNo, ancNo, ipdNo, nin, clientSurname, "
                    + "clientGivenName, age, clientCategory, matVillage, matParish, matSubcounty, matDistrict, "
                    + "phoneNumber, gravidity, parity, gestationAge, term, reasonForAdmission, revisit, "
                    + "whoClinicalStage, cd4Results, cd4Date, viralLoadResults, viralLoadDate, wInitialResult, wTFV, "
                    + "pInitialResult, pTFV, pArtCode, artCode, artNo, ctxCode, wSyphilis, wHepatitisB, pSyphilis, pHepatitisB, muac, inrNo, "
                    + "modeOfDelivery, dateOfDelivery, timeOfDelivery, liveBirths, oxytocin, misoprostol, ergometrine, "
                    + "managementProcedure, otherTreatment, apgarScore, sexOfBaby, notBreathing, immediateSkinToSkin, "
                    + "sourceOfWarmth, breastFed, teo, vitK, chlorhexidine, weight, riskStatus, arvsAdministered, "
                    + "syrupDuration, bcgImmunization, polioImmunization, familyPlanningMethod, familyPlanningDate, "
                    + "treatmentOffered, babyFinalDiagnosis, deliveredByName, deliveredByCadre, transferredByName, "
                    + "transferredByWhere, motherBleeding6, timeOfDischarge, babyCheckedCord6, babyBreastFeeding6, "
                    + "babyBreathing6, llinsGiven, babyCondition, motherFinalDiagnosis, motherBleeding24, recordDate, "
                    + "babyCheckedCord24, babyBreastFeeding24, babyBreathing24, iycf, iycfOption, "
                    + "counselingDischarged, materNutrCouns, conditionOfMotherAtDischarge, motherTransferredWhere, nameOfPersonDischarging, "
                    + "cadreOfPersonDischarging, dateOfDischarge, motherSyst6, motherDias6, motherSyst24, motherDias24, "
                    + "userID, matID)"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?)");

            extract_maternity(stmt, maternity);
            stmt.setObject(99, userId);
            stmt.setString(100, maternity.getMatId());

            stmt.executeUpdate();
            con.close();
            return true;
        } catch (Exception var6) {
            //for debugging
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR: "+ var6.getMessage(), "Success"));
            ErrorDAO.Error_Add(new Error("ChbDAO", "Save_New_Maternity", " Message: " + var6.getMessage(), now));
            return false;
        }
    }

    private static void extract_maternity(PreparedStatement stmt, Maternity maternity) throws SQLException {
        if(maternity.getDateOfAdmission() == null)
            stmt.setNull(1, Types.DATE);
        else
            stmt.setString(1, maternity.getDateOfAdmission().format(dateFormatter));
        if(maternity.getTimeOfAdmission() == null)
            stmt.setNull(2, Types.TIME);
        else
            stmt.setString(2, maternity.getTimeOfAdmission().format(timeFormatter));
        stmt.setObject(3, maternity.getAdmissionNo());
        stmt.setString(4, maternity.getAncNo());
        stmt.setObject(5, maternity.getIpdNo());
        if(maternity.getHasNin()) {
            stmt.setString(6, maternity.getNin());
        } else {
            stmt.setString(6, null);
        }
        stmt.setString(7, maternity.getClientSurname());
        stmt.setString(8, maternity.getClientGivenName());
        stmt.setObject(9, maternity.getAge());
        stmt.setString(10, maternity.getClientCategory());
        stmt.setString(11, maternity.getVillageId());
        stmt.setString(12, maternity.getParishId());
        stmt.setString(13, maternity.getSubcountyId());
        stmt.setString(14, maternity.getDistrictId());
        stmt.setString(15, maternity.getPhoneNumber());
        stmt.setObject(16, maternity.getGravidity());
        stmt.setObject(17, maternity.getParity());
        stmt.setObject(18, maternity.getGestationAge());
        stmt.setString(19, maternity.getTerm());
        stmt.setString(20, maternity.getReasonForAdmission());
        stmt.setBoolean(21, maternity.getRevisit());
        stmt.setObject(22, maternity.getWhoClinicalStage());
        stmt.setObject(23, maternity.getCd4Results());
        if(maternity.getCd4Date()  ==  null)
            stmt.setNull(24, Types.DATE);
        else
            stmt.setDate(24, Date.valueOf(maternity.getCd4Date()));
        stmt.setObject(25, maternity.getViralLoadResults());
        if(maternity.getViralLoadDate()  ==  null)
            stmt.setNull(26, Types.DATE);
        else
            stmt.setDate(26, Date.valueOf(maternity.getViralLoadDate()));
        stmt.setString(27, maternity.getwInitialResult());
        stmt.setString(28, maternity.getwTfv());
        stmt.setString(29, maternity.getpInitialResult());
        stmt.setString(30, maternity.getpTfv());
        stmt.setString(31, maternity.getpArtCode());
        stmt.setString(32, maternity.getArtCode());
        stmt.setString(33, maternity.getArtNo());
        stmt.setString(34, maternity.getCtxCode());
        stmt.setString(35, maternity.getwSyphilis());
        stmt.setString(36, maternity.getwHepatitisB());
        stmt.setString(37, maternity.getpSyphilis());
        stmt.setString(38, maternity.getpHepatitisB());
        stmt.setString(39, maternity.getMuac());
        stmt.setObject(40, maternity.getInrNo());
        stmt.setString(41, maternity.getModeOfDelivery());
        if(maternity.getDateOfDelivery()  ==  null)
            stmt.setNull(42, Types.DATE);
        else
            stmt.setString(42, maternity.getDateOfDelivery().format(dateFormatter));
        if(maternity.getTimeOfDelivery()  ==  null)
            stmt.setNull(43, Types.TIME);
        else
            stmt.setString(43, maternity.getTimeOfDelivery().format(timeFormatter));
        stmt.setString(44, maternity.getLiveBirths());
        stmt.setBoolean(45, maternity.getOxytocin());
        stmt.setBoolean(46, maternity.getMisoprostol());
        stmt.setBoolean(47, maternity.getErgometrine());
        stmt.setString(48, maternity.getManagementProcedure());
        stmt.setString(49, maternity.getOtherTreatment());
        stmt.setString(50, maternity.getApgarScore());
        stmt.setString(51, maternity.getSexOfBaby());
        stmt.setString(52, maternity.getNotBreathing());
        stmt.setString(53, maternity.getImmediateSkinToSkin());
        stmt.setString(54, maternity.getSourceOfWarmth());
        stmt.setString(55, maternity.getBreastFed());
        stmt.setBoolean(56, maternity.getTeo());
        stmt.setBoolean(57, maternity.getVitK());
        stmt.setBoolean(58, maternity.getChlorhexidine());
        if (maternity.getWeight() == null)
            stmt.setNull(59, Types.FLOAT);
        else
            stmt.setString(59, maternity.getWeight());
        stmt.setString(60, maternity.getRiskStatus());
        stmt.setString(61, maternity.getArvsAdministered());
        stmt.setObject(62, maternity.getSyrupDuration());
        stmt.setString(63, maternity.getBcgImmunization());
        stmt.setString(64, maternity.getPolioImmunization());
        stmt.setObject(65, maternity.getFamilyPlanningMethod());
        if(maternity.getFamilyPlanningDate()  ==  null)
            stmt.setNull(66, java.sql.Types.DATE);
        else
            stmt.setString(66, maternity.getFamilyPlanningDate().format(dateFormatter));
        stmt.setString(67, maternity.getTreatmentOffered());
        stmt.setString(68, maternity.getBabyFinalDiagnosis());
        stmt.setString(69, maternity.getDeliveredByName());
        stmt.setString(70, maternity.getDeliveredByCadre());
        stmt.setString(71, maternity.getTransferredByName());
        stmt.setString(72, maternity.getTransferredByWhere());
        stmt.setString(73, maternity.getMotherBleeding6());
        if(maternity.getTimeOfDischarge()  ==  null)
            stmt.setNull(74, java.sql.Types.DATE);
        else
            stmt.setString(74, maternity.getTimeOfDischarge().format(timeFormatter));
        stmt.setString(75, maternity.getBabyCheckedCord6());
        stmt.setString(76, maternity.getBabyBreastFeeding6());
        stmt.setString(77, maternity.getBabyBreathing6());
        stmt.setString(78, maternity.getLlinsGiven());
        stmt.setString(79, maternity.getBabyCondition());
        stmt.setString(80, maternity.getMotherFinalDiagnosis());
        stmt.setString(81, maternity.getMotherBleeding24());
        stmt.setString(82, now.format(dateTimeFormatter));
        stmt.setString(83, maternity.getBabyCheckedCord24());
        stmt.setString(84, maternity.getBabyBreastFeeding24());
        stmt.setString(85, maternity.getBabyBreathing24());
        stmt.setString(86, maternity.getIycf());
        stmt.setString(87, maternity.getIycfOption());
        stmt.setString(88, maternity.getCounselingDischarged());
        stmt.setString(89, maternity.getMaterNutrCouns());
        stmt.setString(90, maternity.getConditionOfMotherAtDischarge());
        stmt.setString(91, maternity.getMotherTransferredWhere());
        stmt.setString(92, maternity.getNameOfPersonDischarging());
        stmt.setString(93, maternity.getCadreOfPersonDischarging());
        if(maternity.getDateOfDischarge()  ==  null)
            stmt.setNull(94, java.sql.Types.DATE);
        else
            stmt.setString(94, maternity.getDateOfDischarge().format(dateFormatter));
        stmt.setString(95, maternity.getMotherSyst6());
        stmt.setString(96, maternity.getMotherDias6());
        stmt.setString(97, maternity.getMotherSyst24());
        stmt.setString(98, maternity.getMotherDias24());
    }

    public static Vht Get_Existing_Vht(Integer VhtId) throws SQLException {
        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "t00r");
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

    public static Maternity Get_Existing_Maternity(String matId)  {
        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "t00r");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("SELECT * From maternity where matID=?");
            stmt.setString(1, matId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Maternity maternity = new Maternity();

                maternity.setMatId(rs.getString("matId"));
                maternity.setAdmissionNo(rs.getString("admissionNo"));
                maternity.setDateOfAdmission(rs.getDate("dateOfAdmission").toLocalDate());
                maternity.setClientGivenName(rs.getString("clientGivenName"));
                maternity.setClientSurname(rs.getString("clientSurname"));

                ResultSet villageData = con.prepareStatement(String.format("SELECT VillageName From village WHERE VillageId LIKE '%s'", rs.getString("matVillage")))
                        .executeQuery();
                if(villageData.next()) {
                    maternity.setVillageName(villageData.getString("VillageName"));
                }
                maternity.setRecordDate(rs.getTimestamp("recordDate").toLocalDateTime());
                maternity.setDateOfAdmission(rs.getDate("dateOfAdmission").toLocalDate());
                maternity.setTimeOfAdmission(rs.getTime("timeOfAdmission").toLocalTime());
                maternity.setAdmissionNo(rs.getString("admissionNo"));
                maternity.setAncNo(rs.getString("ancNo"));
                maternity.setIpdNo(rs.getString("ipdNo"));
                maternity.setNin(rs.getString("nin"));
                maternity.setHasNin(maternity.getNin() != null);
                maternity.setClientSurname(rs.getString("clientSurname"));
                maternity.setClientGivenName(rs.getString("clientGivenName"));
                maternity.setAge(rs.getString("age"));
                maternity.setClientCategory(rs.getString("clientCategory"));
                maternity.setVillageId(rs.getString("matVillage"));
                // maternity.setVillageName(rs.getString("villageName")); TODO: Fix the corr. getter method
                maternity.setParishId(rs.getString("matParish"));
                // maternity.setParishName(rs.getString("parishName")); TODO: Fix the corr. getter method
                maternity.setSubcountyId(rs.getString("matSubcounty"));
                // maternity.setSubcountyName(rs.getString("subcountyName")); TODO: Fix the corr. getter method
                maternity.setDistrictId(rs.getString("matDistrict"));
                // maternity.setDistrictName(rs.getString("districtName")); TODO: Fix the corr. getter method
                maternity.setPhoneNumber(rs.getString("phoneNumber"));
                maternity.setGravidity(rs.getString("gravidity"));
                maternity.setParity(rs.getString("parity"));
                maternity.setGestationAge(rs.getString("gestationAge"));
                maternity.setTerm(rs.getString("term"));
                maternity.setReasonForAdmission(rs.getString("reasonForAdmission"));
                maternity.setRevisit(rs.getBoolean("revisit"));
                maternity.setWhoClinicalStage(rs.getString("whoClinicalStage"));
                maternity.setCd4Results(rs.getString("cd4Results"));
                if(rs.getDate("cd4Date") != null)
                    maternity.setCd4Date(rs.getDate("cd4Date").toLocalDate());
                maternity.setViralLoadResults(rs.getString("viralLoadResults"));
                if(rs.getDate("viralLoadDate") != null)
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
                maternity.setInrNo(rs.getString("inrNo"));
                maternity.setModeOfDelivery(rs.getString("modeOfDelivery"));
                if(rs.getDate("dateOfDelivery") != null)
                    maternity.setDateOfDelivery(rs.getDate("dateOfDelivery").toLocalDate());
                if(rs.getTime("timeOfDelivery") != null)
                    maternity.setTimeOfDelivery(rs.getTime("timeOfDelivery").toLocalTime());
                maternity.setLiveBirths(rs.getString("liveBirths"));
                maternity.setOxytocin(rs.getBoolean("oxytocin"));
                maternity.setMisoprostol(rs.getBoolean("misoprostol"));
                maternity.setErgometrine(rs.getBoolean("ergometrine"));
                maternity.setManagementProcedure(rs.getString("managementProcedure"));
                maternity.setOtherTreatment(rs.getString("otherTreatment"));
                maternity.setApgarScore(rs.getString("apgarScore"));
                maternity.setSexOfBaby(rs.getString("sexOfBaby"));
                maternity.setNotBreathing(rs.getString("notBreathing"));
                maternity.setImmediateSkinToSkin(rs.getString("immediateSkinToSkin"));
                maternity.setSourceOfWarmth(rs.getString("sourceOfWarmth"));
                maternity.setBreastFed(rs.getString("breastFed"));
                maternity.setTeo(rs.getBoolean("teo"));
                maternity.setVitK(rs.getBoolean("vitK"));
                maternity.setChlorhexidine(rs.getBoolean("chlorhexidine"));
                maternity.setWeight(rs.getString("weight"));
                maternity.setRiskStatus(rs.getString("riskStatus"));
                maternity.setArvsAdministered(rs.getString("arvsAdministered"));
                maternity.setSyrupDuration(rs.getString("syrupDuration"));
                maternity.setBcgImmunization(rs.getString("bcgImmunization"));
                maternity.setPolioImmunization(rs.getString("polioImmunization"));
                maternity.setFamilyPlanningMethod(rs.getString("familyPlanningMethod"));
                if(rs.getDate("familyPlanningDate") != null)
                    maternity.setFamilyPlanningDate(rs.getDate("familyPlanningDate").toLocalDate());
                maternity.setTreatmentOffered(rs.getString("treatmentOffered"));
                maternity.setBabyFinalDiagnosis(rs.getString("babyFinalDiagnosis"));
                maternity.setDeliveredByName(rs.getString("deliveredByName"));
                maternity.setDeliveredByCadre(rs.getString("deliveredByCadre"));
                maternity.setTransferredByName(rs.getString("transferredByName"));
                maternity.setTransferredByWhere(rs.getString("transferredByWhere"));
                maternity.setMotherBleeding6(rs.getString("motherBleeding6"));
                maternity.setMotherDias6(rs.getString("motherDias6"));
                maternity.setMotherSyst6(rs.getString("motherSyst6"));
                maternity.setBabyCheckedCord6(rs.getString("babyCheckedCord6"));
                maternity.setBabyBreastFeeding6(rs.getString("babyBreastFeeding6"));
                maternity.setBabyBreathing6(rs.getString("babyBreathing6"));
                maternity.setLlinsGiven(rs.getString("llinsGiven"));
                maternity.setBabyCondition(rs.getString("babyCondition"));
                maternity.setMotherFinalDiagnosis(rs.getString("motherFinalDiagnosis"));
                maternity.setMotherBleeding24(rs.getString("motherBleeding24"));
                maternity.setMotherDias24(rs.getString("motherDias24"));
                maternity.setMotherSyst24(rs.getString("motherSyst24"));
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
                if(rs.getDate("dateOfDischarge") != null)
                    maternity.setDateOfDischarge(rs.getDate("dateOfDischarge").toLocalDate());
                if(rs.getTime("timeOfDischarge") != null)
                    maternity.setTimeOfDischarge(rs.getTime("timeOfDischarge").toLocalTime());
                if(rs.getDate("recordDate") != null)
                    maternity.setRecordDate(rs.getTimestamp("recordDate").toLocalDateTime());
                maternity.setUserId(rs.getString("userID"));
                con.close();
                return maternity;
            }

            throw new Exception(String.format("No such maternity form with MatID=%s found.", matId));
        } catch (Exception ex) {
            ErrorDAO.Error_Add(new model.Error("ChbDAO", "Get_Existing_Maternity", " Message: " + ex.getMessage(), now));
            return null;
        }
    }

    public static boolean Update_Existing_Vht(Vht existing_vht) throws SQLException {
        try {
            Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            con = DriverManager.getConnection(url, "root", "t00r");
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

    public static boolean Update_Existing_Maternity(Maternity maternity) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bwindihospital_reduced";
            Connection con = DriverManager.getConnection(url, "root", "t00r");
            now = LocalDateTime.now();

            PreparedStatement stmt = con.prepareStatement("UPDATE maternity SET " +
                    "dateOfAdmission=?, timeOfAdmission=?, admissionNo=?, ancNo=?, ipdNo=?, nin=?, clientSurname=?, " +
                    "clientGivenName=?, age=?, clientCategory=?, matVillage=?, matParish=?, matSubcounty=?, " +
                    "matDistrict=?, phoneNumber=?, gravidity=?, parity=?, gestationAge=?, term=?, reasonForAdmission=?, " +
                    "revisit=?, whoClinicalStage=?, cd4Results=?, cd4Date=?, viralLoadResults=?, viralLoadDate=?, " +
                    "wInitialResult=?, wTFV=?, pInitialResult=?, pTFV=?, pArtCode=?, artCode=?, artNo=?, ctxCode=?, " +
                    "wSyphilis=?, wHepatitisB=?, pSyphilis=?, pHepatitisB=?, muac=?, inrNo=?, modeOfDelivery=?, " +
                    "dateOfDelivery=?, timeOfDelivery=?, liveBirths=?, oxytocin=?, misoprostol=?, ergometrine=?, " +
                    "managementProcedure=?, otherTreatment=?, apgarScore=?, sexOfBaby=?, notBreathing=?, " +
                    "immediateSkinToSkin=?, sourceOfWarmth=?, breastFed=?, teo=?, vitK=?, chlorhexidine=?, " +
                    "weight=?, riskStatus=?, arvsAdministered=?, syrupDuration=?, bcgImmunization=?, " +
                    "polioImmunization=?, familyPlanningMethod=?, familyPlanningDate=?, treatmentOffered=?, " +
                    "babyFinalDiagnosis=?, deliveredByName=?, deliveredByCadre=?, transferredByName=?, " +
                    "transferredByWhere=?, motherBleeding6=?, timeOfDischarge=?, babyCheckedCord6=?, babyBreastFeeding6=?, " +
                    "babyBreathing6=?, llinsGiven=?, babyCondition=?, motherFinalDiagnosis=?, motherBleeding24=?, " +
                    "recordDate=?, babyCheckedCord24=?, babyBreastFeeding24=?, babyBreathing24=?, iycf=?, " +
                    "iycfOption=?, counselingDischarged=?, materNutrCouns=?, conditionOfMotherAtDischarge=?, " +
                    "motherTransferredWhere=?, nameOfPersonDischarging=?, cadreOfPersonDischarging=?, " +
                    "dateOfDischarge=?, motherDias6=?, motherSyst6=?, motherDias24=?, motherSyst24=?" +
                    "WHERE matId=?");

            extract_maternity(stmt, maternity);
            stmt.setString(99, maternity.getMatId());
            stmt.executeUpdate();
            con.close();
            return true;
        } catch (Exception var4) {
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
