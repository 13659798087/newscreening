package lzgene.newscreening.entiry;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SendApplication {

    private String c_id;
    private String p_name;//孕妇姓名
    private String c_in_no;//病历号
    private Timestamp p_bithday;//出生日期
    private BigDecimal c_ts_ycq_age; //预产年龄
    private String c_ts_yzjs;//孕周计算基于 1-B超,2-末次月经,3-预产日期,4-IVF,5-CRL,6-BPD
    private Integer c_tes;//胎儿数
    private String c_hospital; //送检单位
    private String c_dept;//送检科室
    private String c_doctor;//送检医生
    private Timestamp c_lmp_date;//末次月经
    private String p_tel1;//联系电话
    private String p_tel2;//联系电话2
    private String c_sid;//样本编号
    private String c_barcode; //条码号
    private String c_weigh;//体重
    private Timestamp c_ts_bcdate;//B超日期
    private String c_ts_bpd; //bpd
    private String c_ts_crl;//crl
    private String c_ts_crl2;//crl2
    private String c_ts_nt; //nt
    private String c_ts_nt2;//nt2
    private Timestamp c_sample_date;//采样日期
    private String pregnancy;//评估孕周
    private String r_i_code;//项目
    private String r_chr1;//结果
    private String r_i_code_unit;//单位
    private String r_chr2;//中位数倍数
    private String r_i_ref;//参考范围
    private Timestamp c_a_date;//报告日期
    private String c_w_name;//检验者
    private String c_a_name;//审核者
    private String c_res_ts;//结果解释
    private String c_combine_code;//组合项目
    private Integer c_rpt_flag;//是否打印 1-未打印,2-已打印

    public Integer getC_rpt_flag() {
        return c_rpt_flag;
    }

    public void setC_rpt_flag(Integer c_rpt_flag) {
        this.c_rpt_flag = c_rpt_flag;
    }

    public String getC_combine_code() {
        return c_combine_code;
    }

    public void setC_combine_code(String c_combine_code) {
        this.c_combine_code = c_combine_code;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getC_in_no() {
        return c_in_no;
    }

    public void setC_in_no(String c_in_no) {
        this.c_in_no = c_in_no;
    }

    public Timestamp getP_bithday() {
        return p_bithday;
    }

    public void setP_bithday(Timestamp p_bithday) {
        this.p_bithday = p_bithday;
    }

    public BigDecimal getC_ts_ycq_age() {
        return c_ts_ycq_age;
    }

    public void setC_ts_ycq_age(BigDecimal c_ts_ycq_age) {
        this.c_ts_ycq_age = c_ts_ycq_age;
    }

    public String getC_ts_yzjs() {
        return c_ts_yzjs;
    }

    public void setC_ts_yzjs(String c_ts_yzjs) {
        this.c_ts_yzjs = c_ts_yzjs;
    }

    public Integer getC_tes() {
        return c_tes;
    }

    public void setC_tes(Integer c_tes) {
        this.c_tes = c_tes;
    }

    public String getC_hospital() {
        return c_hospital;
    }

    public void setC_hospital(String c_hospital) {
        this.c_hospital = c_hospital;
    }

    public String getC_dept() {
        return c_dept;
    }

    public void setC_dept(String c_dept) {
        this.c_dept = c_dept;
    }

    public String getC_doctor() {
        return c_doctor;
    }

    public void setC_doctor(String c_doctor) {
        this.c_doctor = c_doctor;
    }

    public Timestamp getC_lmp_date() {
        return c_lmp_date;
    }

    public void setC_lmp_date(Timestamp c_lmp_date) {
        this.c_lmp_date = c_lmp_date;
    }

    public String getP_tel1() {
        return p_tel1;
    }

    public void setP_tel1(String p_tel1) {
        this.p_tel1 = p_tel1;
    }

    public String getP_tel2() {
        return p_tel2;
    }

    public void setP_tel2(String p_tel2) {
        this.p_tel2 = p_tel2;
    }

    public String getC_sid() {
        return c_sid;
    }

    public void setC_sid(String c_sid) {
        this.c_sid = c_sid;
    }

    public String getC_barcode() {
        return c_barcode;
    }

    public void setC_barcode(String c_barcode) {
        this.c_barcode = c_barcode;
    }

    public String getC_weigh() {
        return c_weigh;
    }

    public void setC_weigh(String c_weigh) {
        this.c_weigh = c_weigh;
    }

    public Timestamp getC_ts_bcdate() {
        return c_ts_bcdate;
    }

    public void setC_ts_bcdate(Timestamp c_ts_bcdate) {
        this.c_ts_bcdate = c_ts_bcdate;
    }

    public String getC_ts_bpd() {
        return c_ts_bpd;
    }

    public void setC_ts_bpd(String c_ts_bpd) {
        this.c_ts_bpd = c_ts_bpd;
    }

    public String getC_ts_crl() {
        return c_ts_crl;
    }

    public void setC_ts_crl(String c_ts_crl) {
        this.c_ts_crl = c_ts_crl;
    }

    public String getC_ts_crl2() {
        return c_ts_crl2;
    }

    public void setC_ts_crl2(String c_ts_crl2) {
        this.c_ts_crl2 = c_ts_crl2;
    }

    public String getC_ts_nt() {
        return c_ts_nt;
    }

    public void setC_ts_nt(String c_ts_nt) {
        this.c_ts_nt = c_ts_nt;
    }

    public String getC_ts_nt2() {
        return c_ts_nt2;
    }

    public void setC_ts_nt2(String c_ts_nt2) {
        this.c_ts_nt2 = c_ts_nt2;
    }

    public Timestamp getC_sample_date() {
        return c_sample_date;
    }

    public void setC_sample_date(Timestamp c_sample_date) {
        this.c_sample_date = c_sample_date;
    }

    public String getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(String pregnancy) {
        this.pregnancy = pregnancy;
    }

    public String getR_i_code() {
        return r_i_code;
    }

    public void setR_i_code(String r_i_code) {
        this.r_i_code = r_i_code;
    }

    public String getR_chr1() {
        return r_chr1;
    }

    public void setR_chr1(String r_chr1) {
        this.r_chr1 = r_chr1;
    }

    public String getR_i_code_unit() {
        return r_i_code_unit;
    }

    public void setR_i_code_unit(String r_i_code_unit) {
        this.r_i_code_unit = r_i_code_unit;
    }

    public String getR_chr2() {
        return r_chr2;
    }

    public void setR_chr2(String r_chr2) {
        this.r_chr2 = r_chr2;
    }

    public String getR_i_ref() {
        return r_i_ref;
    }

    public void setR_i_ref(String r_i_ref) {
        this.r_i_ref = r_i_ref;
    }

    public Timestamp getC_a_date() {
        return c_a_date;
    }

    public void setC_a_date(Timestamp c_a_date) {
        this.c_a_date = c_a_date;
    }

    public String getC_w_name() {
        return c_w_name;
    }

    public void setC_w_name(String c_w_name) {
        this.c_w_name = c_w_name;
    }

    public String getC_a_name() {
        return c_a_name;
    }

    public void setC_a_name(String c_a_name) {
        this.c_a_name = c_a_name;
    }

    public String getC_res_ts() {
        return c_res_ts;
    }

    public void setC_res_ts(String c_res_ts) {
        this.c_res_ts = c_res_ts;
    }
}
