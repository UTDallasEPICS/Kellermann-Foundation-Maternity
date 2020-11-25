/*
 * Model for antenatal form, contains data structure for Antenatal
 */

package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Antenatal implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer antId;
    private Integer serialNo;
    private String clientNo;
    private Integer nin;
    private String clientName;
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
    private Integer ancVisit;
    private Integer gravida;
    private Integer parity;
    private Integer gestationAge;
    private Integer anc1;
    private LocalDate expectedDate;
    private Float weight;
    private Float height;
    private String muac;
    private Integer inrNo;
    private Integer bp;
    private String wInitial;
    private String wStk;
    private Integer pAge;
    private String pFacility;
    private String pLinked;
    private String pTfv;
    private String pLinkedWhere;
    private Integer pArtNo;
    private Integer pClientId;
    private String diagnosis;
    private Boolean revisit;
    private Integer whoClinicalStage;
    private Integer cd4Results;
    private LocalDate cd4Date;
    private Integer viralLoadResults;
    private LocalDate viralLoadDate;
    private String riskAssessment;
    private String artCode;
    private Integer linkageArtNo;
    private String infantArv;
    private String iycf;
    private String materNutrCouns;
    private String tbStatus;
    private Integer woaScan;
    private String phy;
    private String sx;
    private Integer hblevel;
    private String bloodGroup;
    private String rhFactor;
    private String sickleCell;
    private String protein;
    private String glucose;
    private String wSyphilis;
    private String wHepB;
    private String pSyphilis;
    private String pHepB;
    private String familyPlanning;
    private String td;
    private Integer iptDose;
    private String ctxCode;
    private String llin;
    private String mebendazole;
    private Integer ironSulphate;
    private Integer folicAcid;
    private Integer combined;
    private Integer misoprostol;
    private String otherTreatments;
    private String refInOut;
    private String complications;
    private LocalDateTime recordDate;
    private Integer userId;

    public Integer getAntId() {
        return antId;
    }

    public void setAntId(Integer antId) {
        this.antId = antId;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public Integer getNin() {
        return nin;
    }

    public void setNin(Integer nin) {
        this.nin = nin;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    public Integer getAncVisit() {
        return ancVisit;
    }

    public void setAncVisit(Integer ancVisit) {
        this.ancVisit = ancVisit;
    }

    public Integer getGravida() {
        return gravida;
    }

    public void setGravida(Integer gravida) {
        this.gravida = gravida;
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

    public Integer getAnc1() {
        return anc1;
    }

    public void setAnc1(Integer anc1) {
        this.anc1 = anc1;
    }

    public LocalDate getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(LocalDate expectedDate) {
        this.expectedDate = expectedDate;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
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

    public Integer getBp() {
        return bp;
    }

    public void setBp(Integer bp) {
        this.bp = bp;
    }

    public String getwInitial() {
        return wInitial;
    }

    public void setwInitial(String wInitial) {
        this.wInitial = wInitial;
    }

    public String getwStk() {
        return wStk;
    }

    public void setwStk(String wStk) {
        this.wStk = wStk;
    }

    public Integer getpAge() {
        return pAge;
    }

    public void setpAge(Integer pAge) {
        this.pAge = pAge;
    }

    public String getpFacility() {
        return pFacility;
    }

    public void setpFacility(String pFacility) {
        this.pFacility = pFacility;
    }

    public String getpLinked() {
        return pLinked;
    }

    public void setpLinked(String pLinked) {
        this.pLinked = pLinked;
    }

    public String getpTfv() {
        return pTfv;
    }

    public void setpTfv(String pTfv) {
        this.pTfv = pTfv;
    }

    public String getpLinkedWhere() {
        return pLinkedWhere;
    }

    public void setpLinkedWhere(String pLinkedWhere) {
        this.pLinkedWhere = pLinkedWhere;
    }

    public Integer getpArtNo() {
        return pArtNo;
    }

    public void setpArtNo(Integer pArtNo) {
        this.pArtNo = pArtNo;
    }

    public Integer getpClientId() {
        return pClientId;
    }

    public void setpClientId(Integer pClientId) {
        this.pClientId = pClientId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
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

    public String getRiskAssessment() {
        return riskAssessment;
    }

    public void setRiskAssessment(String riskAssessment) {
        this.riskAssessment = riskAssessment;
    }

    public String getArtCode() {
        return artCode;
    }

    public void setArtCode(String artCode) {
        this.artCode = artCode;
    }

    public Integer getLinkageArtNo() {
        return linkageArtNo;
    }

    public void setLinkageArtNo(Integer linkageArtNo) {
        this.linkageArtNo = linkageArtNo;
    }

    public String getInfantArv() {
        return infantArv;
    }

    public void setInfantArv(String infantArv) {
        this.infantArv = infantArv;
    }

    public String getIycf() {
        return iycf;
    }

    public void setIycf(String iycf) {
        this.iycf = iycf;
    }

    public String getMaterNutrCouns() {
        return materNutrCouns;
    }

    public void setMaterNutrCouns(String materNutrCouns) {
        this.materNutrCouns = materNutrCouns;
    }

    public String getTbStatus() {
        return tbStatus;
    }

    public void setTbStatus(String tbStatus) {
        this.tbStatus = tbStatus;
    }

    public Integer getWoaScan() {
        return woaScan;
    }

    public void setWoaScan(Integer woaScan) {
        this.woaScan = woaScan;
    }

    public String getPhy() {
        return phy;
    }

    public void setPhy(String phy) {
        this.phy = phy;
    }

    public String getSx() {
        return sx;
    }

    public void setSx(String sx) {
        this.sx = sx;
    }

    public Integer getHblevel() {
        return hblevel;
    }

    public void setHblevel(Integer hblevel) {
        this.hblevel = hblevel;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getRhFactor() {
        return rhFactor;
    }

    public void setRhFactor(String rhFactor) {
        this.rhFactor = rhFactor;
    }

    public String getSickleCell() {
        return sickleCell;
    }

    public void setSickleCell(String sickleCell) {
        this.sickleCell = sickleCell;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getGlucose() {
        return glucose;
    }

    public void setGlucose(String glucose) {
        this.glucose = glucose;
    }

    public String getwSyphilis() {
        return wSyphilis;
    }

    public void setwSyphilis(String wSyphilis) {
        this.wSyphilis = wSyphilis;
    }

    public String getwHepB() {
        return wHepB;
    }

    public void setwHepB(String wHepB) {
        this.wHepB = wHepB;
    }

    public String getpSyphilis() {
        return pSyphilis;
    }

    public void setpSyphilis(String pSyphilis) {
        this.pSyphilis = pSyphilis;
    }

    public String getpHepB() {
        return pHepB;
    }

    public void setpHepB(String pHepB) {
        this.pHepB = pHepB;
    }

    public String getFamilyPlanning() {
        return familyPlanning;
    }

    public void setFamilyPlanning(String familyPlanning) {
        this.familyPlanning = familyPlanning;
    }

    public String getTd() {
        return td;
    }

    public void setTd(String td) {
        this.td = td;
    }

    public Integer getIptDose() {
        return iptDose;
    }

    public void setIptDose(Integer iptDose) {
        this.iptDose = iptDose;
    }

    public String getCtxCode() {
        return ctxCode;
    }

    public void setCtxCode(String ctxCode) {
        this.ctxCode = ctxCode;
    }

    public String getLlin() {
        return llin;
    }

    public void setLlin(String llin) {
        this.llin = llin;
    }

    public String getMebendazole() {
        return mebendazole;
    }

    public void setMebendazole(String mebendazole) {
        this.mebendazole = mebendazole;
    }

    public Integer getIronSulphate() {
        return ironSulphate;
    }

    public void setIronSulphate(Integer ironSulphate) {
        this.ironSulphate = ironSulphate;
    }

    public Integer getFolicAcid() {
        return folicAcid;
    }

    public void setFolicAcid(Integer folicAcid) {
        this.folicAcid = folicAcid;
    }

    public Integer getCombined() {
        return combined;
    }

    public void setCombined(Integer combined) {
        this.combined = combined;
    }

    public Integer getMisoprostol() {
        return misoprostol;
    }

    public void setMisoprostol(Integer misoprostol) {
        this.misoprostol = misoprostol;
    }

    public String getOtherTreatments() {
        return otherTreatments;
    }

    public void setOtherTreatments(String otherTreatments) {
        this.otherTreatments = otherTreatments;
    }

    public String getRefInOut() {
        return refInOut;
    }

    public void setRefInOut(String refInOut) {
        this.refInOut = refInOut;
    }

    public String getComplications() {
        return complications;
    }

    public void setComplications(String complications) {
        this.complications = complications;
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

    public Integer setUserId()

    public Antenatal() {}

    public Antenatal(Integer antId, Integer serialNo, String clientNo, Integer nin, String clientName, Integer age,
                     String clientCategory, String villageId, String villageName, String parishId, String parishName,
                     String subcountyId, String subcountyName, String districtId, String districtName,
                     String phoneNumber, Integer ancVisit, Integer gravida, Integer parity, Integer gestationAge,
                     Integer anc1, LocalDate expectedDate, Float weight, Float height, String muac, Integer inrNo,
                     Integer bp, String wInitial, String wStk, Integer pAge, String pFacility, String pLinked,
                     String pTfv, String pLinkedWhere, Integer pArtNo, Integer pClientId, String diagnosis,
                     Boolean revisit, Integer whoClinicalStage, Integer cd4Results, LocalDate cd4Date,
                     Integer viralLoadResults, LocalDate viralLoadDate, String riskAssessment, String artCode,
                     Integer linkageArtNo, String infantArv, String iycf, String materNutrCouns, String tbStatus,
                     Integer woaScan, String phy, String sx, Integer hblevel, String bloodGroup, String rhFactor,
                     String sickleCell, String protein, String glucose, String wSyphilis, String wHepB,
                     String pSyphilis, String pHepB, String familyPlanning, String td, Integer iptDose, String ctxCode,
                     String llin, String mebendazole, Integer ironSulphate, Integer folicAcid, Integer combined,
                     Integer misoprostol, String otherTreatments, String refInOut, String complications) {
        this.antId = antId;
        this.serialNo = serialNo;
        this.clientNo = clientNo;
        this.nin = nin;
        this.clientName = clientName;
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
        this.ancVisit = ancVisit;
        this.gravida = gravida;
        this.parity = parity;
        this.gestationAge = gestationAge;
        this.anc1 = anc1;
        this.expectedDate = expectedDate;
        this.weight = weight;
        this.height = height;
        this.muac = muac;
        this.inrNo = inrNo;
        this.bp = bp;
        this.wInitial = wInitial;
        this.wStk = wStk;
        this.pAge = pAge;
        this.pFacility = pFacility;
        this.pLinked = pLinked;
        this.pTfv = pTfv;
        this.pLinkedWhere = pLinkedWhere;
        this.pArtNo = pArtNo;
        this.pClientId = pClientId;
        this.diagnosis = diagnosis;
        this.revisit = revisit;
        this.whoClinicalStage = whoClinicalStage;
        this.cd4Results = cd4Results;
        this.cd4Date = cd4Date;
        this.viralLoadResults = viralLoadResults;
        this.viralLoadDate = viralLoadDate;
        this.riskAssessment = riskAssessment;
        this.artCode = artCode;
        this.linkageArtNo = linkageArtNo;
        this.infantArv = infantArv;
        this.iycf = iycf;
        this.materNutrCouns = materNutrCouns;
        this.tbStatus = tbStatus;
        this.woaScan = woaScan;
        this.phy = phy;
        this.sx = sx;
        this.hblevel = hblevel;
        this.bloodGroup = bloodGroup;
        this.rhFactor = rhFactor;
        this.sickleCell = sickleCell;
        this.protein = protein;
        this.glucose = glucose;
        this.wSyphilis = wSyphilis;
        this.wHepB = wHepB;
        this.pSyphilis = pSyphilis;
        this.pHepB = pHepB;
        this.familyPlanning = familyPlanning;
        this.td = td;
        this.iptDose = iptDose;
        this.ctxCode = ctxCode;
        this.llin = llin;
        this.mebendazole = mebendazole;
        this.ironSulphate = ironSulphate;
        this.folicAcid = folicAcid;
        this.combined = combined;
        this.misoprostol = misoprostol;
        this.otherTreatments = otherTreatments;
        this.refInOut = refInOut;
        this.complications = complications;
    }
}
