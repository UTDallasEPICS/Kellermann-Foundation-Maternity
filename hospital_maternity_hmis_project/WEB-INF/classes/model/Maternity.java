/*
 * Model for maternity form, contains data structure for Maternity
 */

package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Maternity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer matId;
    private LocalDate dateOfAdmission;
    private LocalTime timeOfAdmission;
    private Integer admissionNo;
    private String ancNo;
    private Integer ipdNo;
    private String nin;
    private String clientSurname;
    private String clientGivenName;
    private Integer age;
    private String clientCategory;
    private String villageId;
    private String villageName;
    private String parishId;
    private String parishName;
    private String subcountyId;
    private String subcountyName;
    private String districtId;
    private String districtName;
    private String phoneNumber;
    private Integer gravidity;
    private Integer parity;
    private Integer gestationAge;
    private String term;
    private String reasonForAdmission;
    private Boolean revisit;
    private Integer whoClinicalStage;
    private Integer cd4Results;
    private LocalDate cd4Date;
    private Integer viralLoadResults;
    private LocalDate viralLoadDate;
    private String wInitialResult;
    private String wTfv;
    private String pInitialResult;
    private String pTfv;
    private String pArtCode;
    private String artCode;
    private String artNo;
    private String ctxCode;
    private String wSyphilis;
    private String wHepatitisB;
    private String pSyphilis;
    private String pHepatitisB;
    private String muac;
    private Integer inrNo;
    private String modeOfDelivery;
    private LocalDate dateOfDelivery;
    private LocalTime timeOfDelivery;
    private Boolean oxytocin;
    private Boolean misoprostol;
    private Boolean ergometrine;
    private String managementProcedure;
    private String otherTreatment;
    private String apgarScore;
    private String sexOfBaby;
    private String notBreathing;
    private Boolean immediateSkinToSkin;
    private String sourceOfWarmth;
    private Boolean breastFed;
    private Boolean teo;
    private Boolean vitK;
    private Boolean chlorhexidine;
    private Float weight;
    private String riskStatus;
    private String arvsAdministered;
    private Integer syrupDuration;
    private String bcgImmunization;
    private String polioImmunization;
    private Integer familyPlanningMethod;
    private LocalDate familyPlanningDate;
    private String treatmentOffered;
    private String babyFinalDiagnosis;
    private String deliveredByName;
    private String deliveredByCadre;
    private String transferredByName;
    private String transferredByWhere;
    private String motherBleeding6;
    private Integer motherBp6;
    private String babyCheckedCord6;
    private String babyBreastFeeding6;
    private String babyBreathing6;
    private String llnsGiven;
    private String babyCondition;
    private String motherFinalDiagnosis;
    private String motherBleeding24;
    private Integer motherBp24;
    private String babyCheckedCord24;
    private String babyBreastFeeding24;
    private String babyBreathing24;
    private String iycf;
    private String iycfOption;
    private String counselingDischarged;
    private String materNutrCouns;
    private String conditionOfMotherAtDischarge;
    private String motherTransferredWhere;
    private String nameOfPersonDischarging;
    private String cadreOfPersonDischarging;
    private LocalDate dateOfDischarge;
    private LocalTime timeOfDischarge;
    private LocalDateTime recordDate;
    private Integer userId;

    public Integer getMatId() {
        return matId;
    }

    public void setMatId(Integer matId) {
        this.matId = matId;
    }

    public LocalDate getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(LocalDate dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public LocalTime getTimeOfAdmission() {
        return timeOfAdmission;
    }

    public void setTimeOfAdmission(LocalTime timeOfAdmission) {
        this.timeOfAdmission = timeOfAdmission;
    }

    public Integer getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(Integer admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getAncNo() {
        return ancNo;
    }

    public void setAncNo(String ancNo) {
        this.ancNo = ancNo;
    }

    public Integer getIpdNo() {
        return ipdNo;
    }

    public void setIpdNo(Integer ipdNo) {
        this.ipdNo = ipdNo;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientGivenName() {
        return clientGivenName;
    }

    public void setClientGivenName(String clientGivenName) {
        this.clientGivenName = clientGivenName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClientCategory() {
        return clientCategory;
    }

    public void setClientCategory(String clientCategory) {
        this.clientCategory = clientCategory;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getParishId() {
        return parishId;
    }

    public void setParishId(String parishId) {
        this.parishId = parishId;
    }

    public String getParishName() {
        return parishName;
    }

    public void setParishName(String parishName) {
        this.parishName = parishName;
    }

    public String getSubcountyId() {
        return subcountyId;
    }

    public void setSubcountyId(String subcountyId) {
        this.subcountyId = subcountyId;
    }

    public String getSubcountyName() {
        return subcountyName;
    }

    public void setSubcountyName(String subcountyName) {
        this.subcountyName = subcountyName;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getGravidity() {
        return gravidity;
    }

    public void setGravidity(Integer gravidity) {
        this.gravidity = gravidity;
    }

    public Integer getParity() {
        return parity;
    }

    public void setParity(Integer parity) {
        this.parity = parity;
    }

    public Integer getGestationAge() {
        return gestationAge;
    }

    public void setGestationAge(Integer gestationAge) {
        this.gestationAge = gestationAge;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getReasonForAdmission() {
        return reasonForAdmission;
    }

    public void setReasonForAdmission(String reasonForAdmission) {
        this.reasonForAdmission = reasonForAdmission;
    }

    public Boolean getRevisit() {
        return revisit;
    }

    public void setRevisit(Boolean revisit) {
        this.revisit = revisit;
    }

    public Integer getWhoClinicalStage() {
        return whoClinicalStage;
    }

    public void setWhoClinicalStage(Integer whoClinicalStage) {
        this.whoClinicalStage = whoClinicalStage;
    }

    public Integer getCd4Results() {
        return cd4Results;
    }

    public void setCd4Results(Integer cd4Results) {
        this.cd4Results = cd4Results;
    }

    public LocalDate getCd4Date() {
        return cd4Date;
    }

    public void setCd4Date(LocalDate cd4Date) {
        this.cd4Date = cd4Date;
    }

    public Integer getViralLoadResults() {
        return viralLoadResults;
    }

    public void setViralLoadResults(Integer viralLoadResults) {
        this.viralLoadResults = viralLoadResults;
    }

    public LocalDate getViralLoadDate() {
        return viralLoadDate;
    }

    public void setViralLoadDate(LocalDate viralLoadDate) {
        this.viralLoadDate = viralLoadDate;
    }

    public String getwInitialResult() {
        return wInitialResult;
    }

    public void setwInitialResult(String wInitialResult) {
        this.wInitialResult = wInitialResult;
    }

    public String getwTfv() {
        return wTfv;
    }

    public void setwTfv(String wTfv) {
        this.wTfv = wTfv;
    }

    public String getpInitialResult() {
        return pInitialResult;
    }

    public void setpInitialResult(String pInitialResult) {
        this.pInitialResult = pInitialResult;
    }

    public String getpTfv() {
        return pTfv;
    }

    public void setpTfv(String pTfv) {
        this.pTfv = pTfv;
    }

    public String getpArtCode() {
        return pArtCode;
    }

    public void setpArtCode(String pArtCode) {
        this.pArtCode = pArtCode;
    }

    public String getArtCode() {
        return artCode;
    }

    public void setArtCode(String artCode) {
        this.artCode = artCode;
    }

    public String getArtNo() {
        return artNo;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo;
    }

    public String getCtxCode() {
        return ctxCode;
    }

    public void setCtxCode(String ctxCode) {
        this.ctxCode = ctxCode;
    }

    public String getwSyphilis() {
        return wSyphilis;
    }

    public void setwSyphilis(String wSyphilis) {
        this.wSyphilis = wSyphilis;
    }

    public String getwHepatitisB() {
        return wHepatitisB;
    }

    public void setwHepatitisB(String wHepatitisB) {
        this.wHepatitisB = wHepatitisB;
    }

    public String getpSyphilis() {
        return pSyphilis;
    }

    public void setpSyphilis(String pSyphilis) {
        this.pSyphilis = pSyphilis;
    }

    public String getpHepatitisB() {
        return pHepatitisB;
    }

    public void setpHepatitisB(String pHepatitisB) {
        this.pHepatitisB = pHepatitisB;
    }

    public String getMuac() {
        return muac;
    }

    public void setMuac(String muac) {
        this.muac = muac;
    }

    public Integer getInrNo() {
        return inrNo;
    }

    public void setInrNo(Integer inrNo) {
        this.inrNo = inrNo;
    }

    public String getModeOfDelivery() {
        return modeOfDelivery;
    }

    public void setModeOfDelivery(String modeOfDelivery) {
        this.modeOfDelivery = modeOfDelivery;
    }

    public LocalDate getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(LocalDate dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public LocalTime getTimeOfDelivery() {
        return timeOfDelivery;
    }

    public void setTimeOfDelivery(LocalTime timeOfDelivery) {
        this.timeOfDelivery = timeOfDelivery;
    }

    public Boolean getOxytocin() {
        return oxytocin;
    }

    public void setOxytocin(Boolean oxytocin) {
        this.oxytocin = oxytocin;
    }

    public Boolean getMisoprostol() {
        return misoprostol;
    }

    public void setMisoprostol(Boolean misoprostol) {
        this.misoprostol = misoprostol;
    }

    public Boolean getErgometrine() {
        return ergometrine;
    }

    public void setErgometrine(Boolean ergometrine) {
        this.ergometrine = ergometrine;
    }

    public String getManagementProcedure() {
        return managementProcedure;
    }

    public void setManagementProcedure(String managementProcedure) {
        this.managementProcedure = managementProcedure;
    }

    public String getOtherTreatment() {
        return otherTreatment;
    }

    public void setOtherTreatment(String otherTreatment) {
        this.otherTreatment = otherTreatment;
    }

    public String getApgarScore() {
        return apgarScore;
    }

    public void setApgarScore(String apgarScore) {
        this.apgarScore = apgarScore;
    }

    public String getSexOfBaby() {
        return sexOfBaby;
    }

    public void setSexOfBaby(String sexOfBaby) {
        this.sexOfBaby = sexOfBaby;
    }

    public String getNotBreathing() {
        return notBreathing;
    }

    public void setNotBreathing(String notBreathing) {
        this.notBreathing = notBreathing;
    }

    public Boolean getImmediateSkinToSkin() {
        return immediateSkinToSkin;
    }

    public void setImmediateSkinToSkin(Boolean immediateSkinToSkin) {
        this.immediateSkinToSkin = immediateSkinToSkin;
    }

    public String getSourceOfWarmth() {
        return sourceOfWarmth;
    }

    public void setSourceOfWarmth(String sourceOfWarmth) {
        this.sourceOfWarmth = sourceOfWarmth;
    }

    public Boolean getBreastFed() {
        return breastFed;
    }

    public void setBreastFed(Boolean breastFed) {
        this.breastFed = breastFed;
    }

    public Boolean getTeo() {
        return teo;
    }

    public void setTeo(Boolean teo) {
        this.teo = teo;
    }

    public Boolean getVitK() {
        return vitK;
    }

    public void setVitK(Boolean vitK) {
        this.vitK = vitK;
    }

    public Boolean getChlorhexidine() {
        return chlorhexidine;
    }

    public void setChlorhexidine(Boolean chlorhexidine) {
        this.chlorhexidine = chlorhexidine;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getRiskStatus() {
        return riskStatus;
    }

    public void setRiskStatus(String riskStatus) {
        this.riskStatus = riskStatus;
    }

    public String getArvsAdministered() {
        return arvsAdministered;
    }

    public void setArvsAdministered(String arvsAdministered) {
        this.arvsAdministered = arvsAdministered;
    }

    public Integer getSyrupDuration() {
        return syrupDuration;
    }

    public void setSyrupDuration(Integer syrupDuration) {
        this.syrupDuration = syrupDuration;
    }

    public String getBcgImmunization() {
        return bcgImmunization;
    }

    public void setBcgImmunization(String bcgImmunization) {
        this.bcgImmunization = bcgImmunization;
    }

    public String getPolioImmunization() {
        return polioImmunization;
    }

    public void setPolioImmunization(String polioImmunization) {
        this.polioImmunization = polioImmunization;
    }

    public Integer getFamilyPlanningMethod() {
        return familyPlanningMethod;
    }

    public void setFamilyPlanningMethod(Integer familyPlanningMethod) {
        this.familyPlanningMethod = familyPlanningMethod;
    }

    public LocalDate getFamilyPlanningDate() {
        return familyPlanningDate;
    }

    public void setFamilyPlanningDate(LocalDate familyPlanningDate) {
        this.familyPlanningDate = familyPlanningDate;
    }

    public String getTreatmentOffered() {
        return treatmentOffered;
    }

    public void setTreatmentOffered(String treatmentOffered) {
        this.treatmentOffered = treatmentOffered;
    }

    public String getBabyFinalDiagnosis() {
        return babyFinalDiagnosis;
    }

    public void setBabyFinalDiagnosis(String babyFinalDiagnosis) {
        this.babyFinalDiagnosis = babyFinalDiagnosis;
    }

    public String getDeliveredByName() {
        return deliveredByName;
    }

    public void setDeliveredByName(String deliveredByName) {
        this.deliveredByName = deliveredByName;
    }

    public String getDeliveredByCadre() {
        return deliveredByCadre;
    }

    public void setDeliveredByCadre(String deliveredByCadre) {
        this.deliveredByCadre = deliveredByCadre;
    }

    public String getTransferredByName() {
        return transferredByName;
    }

    public void setTransferredByName(String transferredByName) {
        this.transferredByName = transferredByName;
    }

    public String getTransferredByWhere() {
        return transferredByWhere;
    }

    public void setTransferredByWhere(String transferredByWhere) {
        this.transferredByWhere = transferredByWhere;
    }

    public String getMotherBleeding6() {
        return motherBleeding6;
    }

    public void setMotherBleeding6(String motherBleeding6) {
        this.motherBleeding6 = motherBleeding6;
    }

    public Integer getMotherBp6() {
        return motherBp6;
    }

    public void setMotherBp6(Integer motherBp6) {
        this.motherBp6 = motherBp6;
    }

    public String getBabyCheckedCord6() {
        return babyCheckedCord6;
    }

    public void setBabyCheckedCord6(String babyCheckedCord6) {
        this.babyCheckedCord6 = babyCheckedCord6;
    }

    public String getBabyBreastFeeding6() {
        return babyBreastFeeding6;
    }

    public void setBabyBreastFeeding6(String babyBreastFeeding6) {
        this.babyBreastFeeding6 = babyBreastFeeding6;
    }

    public String getBabyBreathing6() {
        return babyBreathing6;
    }

    public void setBabyBreathing6(String babyBreathing6) {
        this.babyBreathing6 = babyBreathing6;
    }

    public String getLlnsGiven() {
        return llnsGiven;
    }

    public void setLlnsGiven(String llnsGiven) {
        this.llnsGiven = llnsGiven;
    }

    public String getBabyCondition() {
        return babyCondition;
    }

    public void setBabyCondition(String babyCondition) {
        this.babyCondition = babyCondition;
    }

    public String getMotherFinalDiagnosis() {
        return motherFinalDiagnosis;
    }

    public void setMotherFinalDiagnosis(String motherFinalDiagnosis) {
        this.motherFinalDiagnosis = motherFinalDiagnosis;
    }

    public String getMotherBleeding24() {
        return motherBleeding24;
    }

    public void setMotherBleeding24(String motherBleeding24) {
        this.motherBleeding24 = motherBleeding24;
    }

    public Integer getMotherBp24() {
        return motherBp24;
    }

    public void setMotherBp24(Integer motherBp24) {
        this.motherBp24 = motherBp24;
    }

    public String getBabyCheckedCord24() {
        return babyCheckedCord24;
    }

    public void setBabyCheckedCord24(String babyCheckedCord24) {
        this.babyCheckedCord24 = babyCheckedCord24;
    }

    public String getBabyBreastFeeding24() {
        return babyBreastFeeding24;
    }

    public void setBabyBreastFeeding24(String babyBreastFeeding24) {
        this.babyBreastFeeding24 = babyBreastFeeding24;
    }

    public String getBabyBreathing24() {
        return babyBreathing24;
    }

    public void setBabyBreathing24(String babyBreathing24) {
        this.babyBreathing24 = babyBreathing24;
    }

    public String getIycf() {
        return iycf;
    }

    public void setIycf(String iycf) {
        this.iycf = iycf;
    }

    public String getIycfOption() {
        return iycfOption;
    }

    public void setIycfOption(String iycfOption) {
        this.iycfOption = iycfOption;
    }

    public String getCounselingDischarged() {
        return counselingDischarged;
    }

    public void setCounselingDischarged(String counselingDischarged) {
        this.counselingDischarged = counselingDischarged;
    }

    public String getMaterNutrCouns() {
        return materNutrCouns;
    }

    public void setMaterNutrCouns(String materNutrCouns) {
        this.materNutrCouns = materNutrCouns;
    }

    public String getConditionOfMotherAtDischarge() {
        return conditionOfMotherAtDischarge;
    }

    public void setConditionOfMotherAtDischarge(String conditionOfMotherAtDischarge) {
        this.conditionOfMotherAtDischarge = conditionOfMotherAtDischarge;
    }

    public String getMotherTransferredWhere() {
        return motherTransferredWhere;
    }

    public void setMotherTransferredWhere(String motherTransferredWhere) {
        this.motherTransferredWhere = motherTransferredWhere;
    }

    public String getNameOfPersonDischarging() {
        return nameOfPersonDischarging;
    }

    public void setNameOfPersonDischarging(String nameOfPersonDischarging) {
        this.nameOfPersonDischarging = nameOfPersonDischarging;
    }

    public String getCadreOfPersonDischarging() {
        return cadreOfPersonDischarging;
    }

    public void setCadreOfPersonDischarging(String cadreOfPersonDischarging) {
        this.cadreOfPersonDischarging = cadreOfPersonDischarging;
    }

    public LocalDate getDateOfDischarge() {
        return dateOfDischarge;
    }

    public void setDateOfDischarge(LocalDate dateOfDischarge) {
        this.dateOfDischarge = dateOfDischarge;
    }

    public LocalTime getTimeOfDischarge() {
        return timeOfDischarge;
    }

    public void setTimeOfDischarge(LocalTime timeOfDischarge) {
        this.timeOfDischarge = timeOfDischarge;
    }

    public LocalDateTime getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Maternity() {}

    public Maternity(Integer matId, LocalDate dateOfAdmission, LocalTime timeOfAdmission, Integer admissionNo,
                     String ancNo, Integer ipdNo, String nin, String clientSurname, String clientGivenName,
                     Integer age, String clientCategory, String villageId, String villageName, String parishId,
                     String parishName, String subcountyId, String subcountyName, String districtId,
                     String districtName, String phoneNumber, Integer gravidity, Integer parity,
                     Integer gestationAge, String term, String reasonForAdmission, Boolean revisit,
                     Integer whoClinicalStage, Integer cd4Results, LocalDate cd4Date, Integer viralLoadResults,
                     LocalDate viralLoadDate, String wInitialResult, String wTfv, String pInitialResult,
                     String pTfv, String pArtCode, String artCode, String artNo, String ctxCode, String wSyphilis,
                     String wHepatitisB, String pSyphilis, String pHepatitisB, String muac, Integer inrNo,
                     String modeOfDelivery, LocalDate dateOfDelivery, LocalTime timeOfDelivery, Boolean oxytocin,
                     Boolean misoprostol, Boolean ergometrine, String managementProcedure, String otherTreatment,
                     String apgarScore, String sexOfBaby, String notBreathing, Boolean immediateSkinToSkin,
                     String sourceOfWarmth, Boolean breastFed, Boolean teo, Boolean vitK, Boolean chlorhexidine,
                     Float weight, String riskStatus, String arvsAdministered, Integer syrupDuration,
                     String bcgImmunization, String polioImmunization, Integer familyPlanningMethod,
                     LocalDate familyPlanningDate, String treatmentOffered, String babyFinalDiagnosis,
                     String deliveredByName, String deliveredByCadre, String transferredByName,
                     String transferredByWhere, String motherBleeding6, Integer motherBp6, String babyCheckedCord6,
                     String babyBreastFeeding6, String babyBreathing6, String llnsGiven, String babyCondition,
                     String motherFinalDiagnosis, String motherBleeding24, Integer motherBp24,
                     String babyCheckedCord24, String babyBreastFeeding24, String babyBreathing24, String iycf,
                     String iycfOption, String counselingDischarged, String materNutrCouns,
                     String conditionOfMotherAtDischarge, String nameOfPersonDischarging,
                     String cadreOfPersonDischarging, LocalDate dateOfDischarge, LocalTime timeOfDischarge,
                     LocalDateTime recordDate, Integer userId) {
        this.matId = matId;
        this.ipdNo = ipdNo;
        this.recordDate = recordDate;
        this.dateOfAdmission = dateOfAdmission;
        this.timeOfAdmission = timeOfAdmission;
        this.admissionNo = admissionNo;
        this.ancNo = ancNo;
        this.nin = nin;
        this.clientSurname = clientSurname;
        this.clientGivenName = clientGivenName;
        this.age = age;
        this.clientCategory = clientCategory;
        this.villageId = villageId;
        this.villageName = villageName;
        this.parishId = parishId;
        this.parishName = parishName;
        this.subcountyId = subcountyId;
        this.subcountyName = subcountyName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.phoneNumber = phoneNumber;
        this.gravidity = gravidity;
        this.parity = parity;
        this.gestationAge = gestationAge;
        this.term = term;
        this.reasonForAdmission = reasonForAdmission;
        this.revisit = revisit;
        this.whoClinicalStage = whoClinicalStage;
        this.cd4Results = cd4Results;
        this.cd4Date = cd4Date;
        this.viralLoadResults = viralLoadResults;
        this.viralLoadDate = viralLoadDate;
        this.wInitialResult = wInitialResult;
        this.wTfv = wTfv;
        this.pInitialResult = pInitialResult;
        this.pTfv = pTfv;
        this.pArtCode = pArtCode;
        this.artCode = artCode;
        this.artNo = artNo;
        this.ctxCode = ctxCode;
        this.wSyphilis = wSyphilis;
        this.wHepatitisB = wHepatitisB;
        this.pSyphilis = pSyphilis;
        this.pHepatitisB = pHepatitisB;
        this.muac = muac;
        this.inrNo = inrNo;
        this.modeOfDelivery = modeOfDelivery;
        this.dateOfDelivery = dateOfDelivery;
        this.timeOfDelivery = timeOfDelivery;
        this.oxytocin = oxytocin;
        this.misoprostol = misoprostol;
        this.ergometrine = ergometrine;
        this.managementProcedure = managementProcedure;
        this.otherTreatment = otherTreatment;
        this.apgarScore = apgarScore;
        this.sexOfBaby = sexOfBaby;
        this.notBreathing = notBreathing;
        this.immediateSkinToSkin = immediateSkinToSkin;
        this.sourceOfWarmth = sourceOfWarmth;
        this.breastFed = breastFed;
        this.teo = teo;
        this.vitK = vitK;
        this.chlorhexidine = chlorhexidine;
        this.weight = weight;
        this.riskStatus = riskStatus;
        this.arvsAdministered = arvsAdministered;
        this.syrupDuration = syrupDuration;
        this.bcgImmunization = bcgImmunization;
        this.polioImmunization = polioImmunization;
        this.familyPlanningMethod = familyPlanningMethod;
        this.familyPlanningDate = familyPlanningDate;
        this.treatmentOffered = treatmentOffered;
        this.babyFinalDiagnosis = babyFinalDiagnosis;
        this.deliveredByName = deliveredByName;
        this.deliveredByCadre = deliveredByCadre;
        this.transferredByName = transferredByName;
        this.transferredByWhere = transferredByWhere;
        this.motherBleeding6 = motherBleeding6;
        this.motherBp6 = motherBp6;
        this.babyCheckedCord6 = babyCheckedCord6;
        this.babyBreastFeeding6 = babyBreastFeeding6;
        this.babyBreathing6 = babyBreathing6;
        this.llnsGiven = llnsGiven;
        this.babyCondition = babyCondition;
        this.motherFinalDiagnosis = motherFinalDiagnosis;
        this.motherBleeding24 = motherBleeding24;
        this.motherBp24 = motherBp24;
        this.babyCheckedCord24 = babyCheckedCord24;
        this.babyBreastFeeding24 = babyBreastFeeding24;
        this.babyBreathing24 = babyBreathing24;
        this.iycf = iycf;
        this.iycfOption = iycfOption;
        this.counselingDischarged = counselingDischarged;
        this.materNutrCouns = materNutrCouns;
        this.conditionOfMotherAtDischarge = conditionOfMotherAtDischarge;
        this.nameOfPersonDischarging = nameOfPersonDischarging;
        this.cadreOfPersonDischarging = cadreOfPersonDischarging;
        this.dateOfDischarge = dateOfDischarge;
        this.timeOfDischarge = timeOfDischarge;
        this.userId = userId;
    }
}
