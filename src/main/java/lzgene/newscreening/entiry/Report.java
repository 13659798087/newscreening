package lzgene.newscreening.entiry;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Report {
    private String pHospital;
    private String pDept;
    private Timestamp pBithday;
    private String pMName; //母亲姓名
    private Integer pMGestation;//怀孕周 +
    private Integer pMGestationD;//怀孕天
    private String pWeigh; //出生体重
    private String pLabour;//分娩分式
    private String pMMed;//母病史
    private String pCondition;//小孩状况
    private Timestamp pDate;
    private String pInNo;
    private String pBarcode;//采集卡号
    private String pHospitalQs; //医院区属
    private Integer cId;//检查id
    private Integer cPid;//关联病人Id
    private String cCardNo;
    private String cSsid;
    private Timestamp cSampleDate;//采样日期
    private String cSampleName;
    private Integer cSampleHg;
    private Integer cSampleType;//标本类型，1为筛查，2为门诊
    private Timestamp cWDate;
    private Timestamp cADate;
    private Timestamp cFaDate;
    private String cWName;
    private String cAName;
    private String checkinfoCAName;
    private String cFaName;
    private Timestamp cPrtDate;
    private String CPrtName;
    private String cBz;
    private Integer cType;//1初筛，2复查，3召回，4定检
    private Integer cFlag;
    private Timestamp cDate;
    private Timestamp cSdate;
    private String cSid;//关联resulto(结果表)的rSid
    private Integer cResFlag;//结果标志0为正常，1为阳性
    private String cResExplain;//结果解析
    private String cBgJyz;
    private String cBgShz;
    private String cBgPzz;
    private String cZhFlag;
    private String rICode;
    private String rIName;
    private String rIRef;
    private String rChr1;//结果1
    private String rChr2;//结果2
    private String rDate;//结果日期
    private String rChrPd;
    private String rChr;
    private Integer rType;

    private BigDecimal iId;
    private String iName;//检查项目名
    private String iAilment;//疾病
    private String iUnit;
    private Integer iSeq;

    private String  pBz;
    private String  pBedNo;//床号
    private String  pTel1;//联系电话
    private String  pTel2;//联系电话
    private String  pAddress;//病人地址
    private String  pApgar;
    private Integer pFetusNum;
    private String  pSex;
    private String  pAgeUnit;
    private String  pAge;

    public String getpHospital() {
        return pHospital;
    }

    public void setpHospital(String pHospital) {
        this.pHospital = pHospital;
    }

    public String getpDept() {
        return pDept;
    }

    public void setpDept(String pDept) {
        this.pDept = pDept;
    }

    public Timestamp getpBithday() {
        return pBithday;
    }

    public void setpBithday(Timestamp pBithday) {
        this.pBithday = pBithday;
    }

    public String getpMName() {
        return pMName;
    }

    public void setpMName(String pMName) {
        this.pMName = pMName;
    }

    public Integer getpMGestation() {
        return pMGestation;
    }

    public void setpMGestation(Integer pMGestation) {
        this.pMGestation = pMGestation;
    }

    public Integer getpMGestationD() {
        return pMGestationD;
    }

    public void setpMGestationD(Integer pMGestationD) {
        this.pMGestationD = pMGestationD;
    }

    public String getpWeigh() {
        return pWeigh;
    }

    public void setpWeigh(String pWeigh) {
        this.pWeigh = pWeigh;
    }

    public String getpLabour() {
        return pLabour;
    }

    public void setpLabour(String pLabour) {
        this.pLabour = pLabour;
    }

    public String getpMMed() {
        return pMMed;
    }

    public void setpMMed(String pMMed) {
        this.pMMed = pMMed;
    }

    public String getpCondition() {
        return pCondition;
    }

    public void setpCondition(String pCondition) {
        this.pCondition = pCondition;
    }

    public Timestamp getpDate() {
        return pDate;
    }

    public void setpDate(Timestamp pDate) {
        this.pDate = pDate;
    }

    public String getpInNo() {
        return pInNo;
    }

    public void setpInNo(String pInNo) {
        this.pInNo = pInNo;
    }

    public String getpBarcode() {
        return pBarcode;
    }

    public void setpBarcode(String pBarcode) {
        this.pBarcode = pBarcode;
    }

    public String getpHospitalQs() {
        return pHospitalQs;
    }

    public void setpHospitalQs(String pHospitalQs) {
        this.pHospitalQs = pHospitalQs;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getcPid() {
        return cPid;
    }

    public void setcPid(Integer cPid) {
        this.cPid = cPid;
    }

    public String getcCardNo() {
        return cCardNo;
    }

    public void setcCardNo(String cCardNo) {
        this.cCardNo = cCardNo;
    }

    public String getcSsid() {
        return cSsid;
    }

    public void setcSsid(String cSsid) {
        this.cSsid = cSsid;
    }

    public Timestamp getcSampleDate() {
        return cSampleDate;
    }

    public void setcSampleDate(Timestamp cSampleDate) {
        this.cSampleDate = cSampleDate;
    }

    public String getcSampleName() {
        return cSampleName;
    }

    public void setcSampleName(String cSampleName) {
        this.cSampleName = cSampleName;
    }

    public Integer getcSampleHg() {
        return cSampleHg;
    }

    public void setcSampleHg(Integer cSampleHg) {
        this.cSampleHg = cSampleHg;
    }

    public Integer getcSampleType() {
        return cSampleType;
    }

    public void setcSampleType(Integer cSampleType) {
        this.cSampleType = cSampleType;
    }

    public Timestamp getcWDate() {
        return cWDate;
    }

    public void setcWDate(Timestamp cWDate) {
        this.cWDate = cWDate;
    }

    public Timestamp getcADate() {
        return cADate;
    }

    public void setcADate(Timestamp cADate) {
        this.cADate = cADate;
    }

    public Timestamp getcFaDate() {
        return cFaDate;
    }

    public void setcFaDate(Timestamp cFaDate) {
        this.cFaDate = cFaDate;
    }

    public String getcWName() {
        return cWName;
    }

    public void setcWName(String cWName) {
        this.cWName = cWName;
    }

    public String getcAName() {
        return cAName;
    }

    public void setcAName(String cAName) {
        this.cAName = cAName;
    }

    public String getCheckinfoCAName() {
        return checkinfoCAName;
    }

    public void setCheckinfoCAName(String checkinfoCAName) {
        this.checkinfoCAName = checkinfoCAName;
    }

    public String getcFaName() {
        return cFaName;
    }

    public void setcFaName(String cFaName) {
        this.cFaName = cFaName;
    }

    public Timestamp getcPrtDate() {
        return cPrtDate;
    }

    public void setcPrtDate(Timestamp cPrtDate) {
        this.cPrtDate = cPrtDate;
    }

    public String getCPrtName() {
        return CPrtName;
    }

    public void setCPrtName(String CPrtName) {
        this.CPrtName = CPrtName;
    }

    public String getcBz() {
        return cBz;
    }

    public void setcBz(String cBz) {
        this.cBz = cBz;
    }

    public Integer getcType() {
        return cType;
    }

    public void setcType(Integer cType) {
        this.cType = cType;
    }

    public Integer getcFlag() {
        return cFlag;
    }

    public void setcFlag(Integer cFlag) {
        this.cFlag = cFlag;
    }

    public Timestamp getcDate() {
        return cDate;
    }

    public void setcDate(Timestamp cDate) {
        this.cDate = cDate;
    }

    public Timestamp getcSdate() {
        return cSdate;
    }

    public void setcSdate(Timestamp cSdate) {
        this.cSdate = cSdate;
    }

    public String getcSid() {
        return cSid;
    }

    public void setcSid(String cSid) {
        this.cSid = cSid;
    }

    public Integer getcResFlag() {
        return cResFlag;
    }

    public void setcResFlag(Integer cResFlag) {
        this.cResFlag = cResFlag;
    }

    public String getcResExplain() {
        return cResExplain;
    }

    public void setcResExplain(String cResExplain) {
        this.cResExplain = cResExplain;
    }

    public String getcBgJyz() {
        return cBgJyz;
    }

    public void setcBgJyz(String cBgJyz) {
        this.cBgJyz = cBgJyz;
    }

    public String getcBgShz() {
        return cBgShz;
    }

    public void setcBgShz(String cBgShz) {
        this.cBgShz = cBgShz;
    }

    public String getcBgPzz() {
        return cBgPzz;
    }

    public void setcBgPzz(String cBgPzz) {
        this.cBgPzz = cBgPzz;
    }

    public String getcZhFlag() {
        return cZhFlag;
    }

    public void setcZhFlag(String cZhFlag) {
        this.cZhFlag = cZhFlag;
    }

    public String getrICode() {
        return rICode;
    }

    public void setrICode(String rICode) {
        this.rICode = rICode;
    }

    public String getrIName() {
        return rIName;
    }

    public void setrIName(String rIName) {
        this.rIName = rIName;
    }

    public String getrIRef() {
        return rIRef;
    }

    public void setrIRef(String rIRef) {
        this.rIRef = rIRef;
    }

    public String getrChr1() {
        return rChr1;
    }

    public void setrChr1(String rChr1) {
        this.rChr1 = rChr1;
    }

    public String getrChr2() {
        return rChr2;
    }

    public void setrChr2(String rChr2) {
        this.rChr2 = rChr2;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public String getrChrPd() {
        return rChrPd;
    }

    public void setrChrPd(String rChrPd) {
        this.rChrPd = rChrPd;
    }

    public String getrChr() {
        return rChr;
    }

    public void setrChr(String rChr) {
        this.rChr = rChr;
    }

    public Integer getrType() {
        return rType;
    }

    public void setrType(Integer rType) {
        this.rType = rType;
    }

    public BigDecimal getiId() {
        return iId;
    }

    public void setiId(BigDecimal iId) {
        this.iId = iId;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getiAilment() {
        return iAilment;
    }

    public void setiAilment(String iAilment) {
        this.iAilment = iAilment;
    }

    public String getiUnit() {
        return iUnit;
    }

    public void setiUnit(String iUnit) {
        this.iUnit = iUnit;
    }

    public Integer getiSeq() {
        return iSeq;
    }

    public void setiSeq(Integer iSeq) {
        this.iSeq = iSeq;
    }

    public String getpBz() {
        return pBz;
    }

    public void setpBz(String pBz) {
        this.pBz = pBz;
    }

    public String getpBedNo() {
        return pBedNo;
    }

    public void setpBedNo(String pBedNo) {
        this.pBedNo = pBedNo;
    }

    public String getpTel1() {
        return pTel1;
    }

    public void setpTel1(String pTel1) {
        this.pTel1 = pTel1;
    }

    public String getpTel2() {
        return pTel2;
    }

    public void setpTel2(String pTel2) {
        this.pTel2 = pTel2;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getpApgar() {
        return pApgar;
    }

    public void setpApgar(String pApgar) {
        this.pApgar = pApgar;
    }

    public Integer getpFetusNum() {
        return pFetusNum;
    }

    public void setpFetusNum(Integer pFetusNum) {
        this.pFetusNum = pFetusNum;
    }

    public String getpSex() {
        return pSex;
    }

    public void setpSex(String pSex) {
        this.pSex = pSex;
    }

    public String getpAgeUnit() {
        return pAgeUnit;
    }

    public void setpAgeUnit(String pAgeUnit) {
        this.pAgeUnit = pAgeUnit;
    }

    public String getpAge() {
        return pAge;
    }

    public void setpAge(String pAge) {
        this.pAge = pAge;
    }
}
