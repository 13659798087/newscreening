package lzgene.newscreening.model;

import java.sql.Timestamp;

public class ApplyinfoCombine {


    private String a_id;//申请单ID，与申请单表ID关联
    private String a_combine_code;//组别项目代码，与组别项目代码关联
    private Timestamp a_combine_name;//组别名称

    private String  a_dp_rbc;// 地贫RBC值
    private String  a_dp_hb;//地贫hb值
    private String  a_dp_mch;//地贫mch值
    private String  a_dp_mchc;//地贫mchc值
    private String  a_dp_hba;//地贫hba值
    private String  a_dp_hbf;//地贫hbf值
    private String  a_dp_hba2;//地贫hb a2值
    private String  a_dp_yc;//地贫异常血红蛋白
    private String  a_dp_fe;//地贫铁蛋白
    private String  a_dp_ycs;// 地贫铁蛋白生育史 1有、2无、0未知

    private Integer a_wcts_hyfs;//无创唐筛共用怀孕方式，0为自然、1为辅助生殖
    private String  a_wc_hyfs_type;//辅助生生殖类型
    private Integer a_wc_hyfs_yy;//原因，夫妻一方为染色体平衡易位，1是，0否
    private Integer a_wc_xzlc;//先兆流产
    private String  a_wc_xzlc_yz;//先兆流产发生时孕周
    private Integer a_wc_ywjt;//有无减胎
    private String  a_wc_ywjt_yz;//有无减胎发生时孕周
    private String  a_wc_tety;//胎儿停育
    private String  a_wc_tety_yz;//胎儿停育发生时孕周

    private String  a_el_grs;//耳聋个人史
    private String  a_el_sys;//耳聋生育史
    private String  a_el_jzs;//耳聋家族史

    private Timestamp a_ts_qldate;//唐筛取卵日期
    private Timestamp a_ts_zrdate;//唐筛植入日期
    private Timestamp a_ts_bcdate;//唐筛B超日期
    private String  a_ts_bpd;//唐筛BPD
    private String  a_ts_bpd2;//唐筛BPD2
    private String  a_ts_crl;//唐筛CRL
    private String  a_ts_crl2;//唐筛CRL2
    private String  a_ts_nt;//唐筛NT
    private String  a_ts_nt2;//唐筛NT2
    private String  a_ts_nb;//唐筛鼻骨
    private String  a_ts_nb2;//唐筛鼻骨2
    private String  a_ts_bcweek;//唐筛B超孕周
    private String  a_ts_bcday;//唐筛B超孕天


    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public String getA_combine_code() {
        return a_combine_code;
    }

    public void setA_combine_code(String a_combine_code) {
        this.a_combine_code = a_combine_code;
    }

    public Timestamp getA_combine_name() {
        return a_combine_name;
    }

    public void setA_combine_name(Timestamp a_combine_name) {
        this.a_combine_name = a_combine_name;
    }

    public String getA_dp_rbc() {
        return a_dp_rbc;
    }

    public void setA_dp_rbc(String a_dp_rbc) {
        this.a_dp_rbc = a_dp_rbc;
    }

    public String getA_dp_hb() {
        return a_dp_hb;
    }

    public void setA_dp_hb(String a_dp_hb) {
        this.a_dp_hb = a_dp_hb;
    }

    public String getA_dp_mch() {
        return a_dp_mch;
    }

    public void setA_dp_mch(String a_dp_mch) {
        this.a_dp_mch = a_dp_mch;
    }

    public String getA_dp_mchc() {
        return a_dp_mchc;
    }

    public void setA_dp_mchc(String a_dp_mchc) {
        this.a_dp_mchc = a_dp_mchc;
    }

    public String getA_dp_hba() {
        return a_dp_hba;
    }

    public void setA_dp_hba(String a_dp_hba) {
        this.a_dp_hba = a_dp_hba;
    }

    public String getA_dp_hbf() {
        return a_dp_hbf;
    }

    public void setA_dp_hbf(String a_dp_hbf) {
        this.a_dp_hbf = a_dp_hbf;
    }

    public String getA_dp_hba2() {
        return a_dp_hba2;
    }

    public void setA_dp_hba2(String a_dp_hba2) {
        this.a_dp_hba2 = a_dp_hba2;
    }

    public String getA_dp_yc() {
        return a_dp_yc;
    }

    public void setA_dp_yc(String a_dp_yc) {
        this.a_dp_yc = a_dp_yc;
    }

    public String getA_dp_fe() {
        return a_dp_fe;
    }

    public void setA_dp_fe(String a_dp_fe) {
        this.a_dp_fe = a_dp_fe;
    }

    public String getA_dp_ycs() {
        return a_dp_ycs;
    }

    public void setA_dp_ycs(String a_dp_ycs) {
        this.a_dp_ycs = a_dp_ycs;
    }

    public Integer getA_wcts_hyfs() {
        return a_wcts_hyfs;
    }

    public void setA_wcts_hyfs(Integer a_wcts_hyfs) {
        this.a_wcts_hyfs = a_wcts_hyfs;
    }

    public String getA_wc_hyfs_type() {
        return a_wc_hyfs_type;
    }

    public void setA_wc_hyfs_type(String a_wc_hyfs_type) {
        this.a_wc_hyfs_type = a_wc_hyfs_type;
    }

    public Integer getA_wc_hyfs_yy() {
        return a_wc_hyfs_yy;
    }

    public void setA_wc_hyfs_yy(Integer a_wc_hyfs_yy) {
        this.a_wc_hyfs_yy = a_wc_hyfs_yy;
    }

    public Integer getA_wc_xzlc() {
        return a_wc_xzlc;
    }

    public void setA_wc_xzlc(Integer a_wc_xzlc) {
        this.a_wc_xzlc = a_wc_xzlc;
    }

    public String getA_wc_xzlc_yz() {
        return a_wc_xzlc_yz;
    }

    public void setA_wc_xzlc_yz(String a_wc_xzlc_yz) {
        this.a_wc_xzlc_yz = a_wc_xzlc_yz;
    }

    public Integer getA_wc_ywjt() {
        return a_wc_ywjt;
    }

    public void setA_wc_ywjt(Integer a_wc_ywjt) {
        this.a_wc_ywjt = a_wc_ywjt;
    }

    public String getA_wc_ywjt_yz() {
        return a_wc_ywjt_yz;
    }

    public void setA_wc_ywjt_yz(String a_wc_ywjt_yz) {
        this.a_wc_ywjt_yz = a_wc_ywjt_yz;
    }

    public String getA_wc_tety() {
        return a_wc_tety;
    }

    public void setA_wc_tety(String a_wc_tety) {
        this.a_wc_tety = a_wc_tety;
    }

    public String getA_wc_tety_yz() {
        return a_wc_tety_yz;
    }

    public void setA_wc_tety_yz(String a_wc_tety_yz) {
        this.a_wc_tety_yz = a_wc_tety_yz;
    }

    public String getA_el_grs() {
        return a_el_grs;
    }

    public void setA_el_grs(String a_el_grs) {
        this.a_el_grs = a_el_grs;
    }

    public String getA_el_sys() {
        return a_el_sys;
    }

    public void setA_el_sys(String a_el_sys) {
        this.a_el_sys = a_el_sys;
    }

    public String getA_el_jzs() {
        return a_el_jzs;
    }

    public void setA_el_jzs(String a_el_jzs) {
        this.a_el_jzs = a_el_jzs;
    }

    public Timestamp getA_ts_qldate() {
        return a_ts_qldate;
    }

    public void setA_ts_qldate(Timestamp a_ts_qldate) {
        this.a_ts_qldate = a_ts_qldate;
    }

    public Timestamp getA_ts_zrdate() {
        return a_ts_zrdate;
    }

    public void setA_ts_zrdate(Timestamp a_ts_zrdate) {
        this.a_ts_zrdate = a_ts_zrdate;
    }

    public Timestamp getA_ts_bcdate() {
        return a_ts_bcdate;
    }

    public void setA_ts_bcdate(Timestamp a_ts_bcdate) {
        this.a_ts_bcdate = a_ts_bcdate;
    }

    public String getA_ts_bpd() {
        return a_ts_bpd;
    }

    public void setA_ts_bpd(String a_ts_bpd) {
        this.a_ts_bpd = a_ts_bpd;
    }

    public String getA_ts_bpd2() {
        return a_ts_bpd2;
    }

    public void setA_ts_bpd2(String a_ts_bpd2) {
        this.a_ts_bpd2 = a_ts_bpd2;
    }

    public String getA_ts_crl() {
        return a_ts_crl;
    }

    public void setA_ts_crl(String a_ts_crl) {
        this.a_ts_crl = a_ts_crl;
    }

    public String getA_ts_crl2() {
        return a_ts_crl2;
    }

    public void setA_ts_crl2(String a_ts_crl2) {
        this.a_ts_crl2 = a_ts_crl2;
    }

    public String getA_ts_nt() {
        return a_ts_nt;
    }

    public void setA_ts_nt(String a_ts_nt) {
        this.a_ts_nt = a_ts_nt;
    }

    public String getA_ts_nt2() {
        return a_ts_nt2;
    }

    public void setA_ts_nt2(String a_ts_nt2) {
        this.a_ts_nt2 = a_ts_nt2;
    }

    public String getA_ts_nb() {
        return a_ts_nb;
    }

    public void setA_ts_nb(String a_ts_nb) {
        this.a_ts_nb = a_ts_nb;
    }

    public String getA_ts_nb2() {
        return a_ts_nb2;
    }

    public void setA_ts_nb2(String a_ts_nb2) {
        this.a_ts_nb2 = a_ts_nb2;
    }

    public String getA_ts_bcweek() {
        return a_ts_bcweek;
    }

    public void setA_ts_bcweek(String a_ts_bcweek) {
        this.a_ts_bcweek = a_ts_bcweek;
    }

    public String getA_ts_bcday() {
        return a_ts_bcday;
    }

    public void setA_ts_bcday(String a_ts_bcday) {
        this.a_ts_bcday = a_ts_bcday;
    }
}
