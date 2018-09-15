package lzgene.newscreening.model;

import java.math.BigDecimal;

/*
送检单位名称、医院信息表
 */
public class Dept {

    private BigDecimal d_id;//医院id
    private String d_name;
    private String d_code;
    private Integer d_t_flag;
    private String d_type;
    private String d_py_code;
    private Integer d_hk_city;
    private Integer d_bd;
    private String d_hospital;
    private String d_address;
    private Integer d_sjdq;//关联区属表
    private String d_his_name;
    private Integer d_order_no;
    private String d_lxr;
    private String d_lxr_dh;
    private String d_lxr_sj;
    private String d_lxr_yx;
    private String d_ct_sum;
    private String d_dl_psw;
    private Integer d_create_bc;


    public BigDecimal getD_id() {
        return d_id;
    }

    public void setD_id(BigDecimal d_id) {
        this.d_id = d_id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_code() {
        return d_code;
    }

    public void setD_code(String d_code) {
        this.d_code = d_code;
    }

    public Integer getD_t_flag() {
        return d_t_flag;
    }

    public void setD_t_flag(Integer d_t_flag) {
        this.d_t_flag = d_t_flag;
    }

    public String getD_type() {
        return d_type;
    }

    public void setD_type(String d_type) {
        this.d_type = d_type;
    }

    public String getD_py_code() {
        return d_py_code;
    }

    public void setD_py_code(String d_py_code) {
        this.d_py_code = d_py_code;
    }

    public Integer getD_hk_city() {
        return d_hk_city;
    }

    public void setD_hk_city(Integer d_hk_city) {
        this.d_hk_city = d_hk_city;
    }

    public Integer getD_bd() {
        return d_bd;
    }

    public void setD_bd(Integer d_bd) {
        this.d_bd = d_bd;
    }

    public String getD_hospital() {
        return d_hospital;
    }

    public void setD_hospital(String d_hospital) {
        this.d_hospital = d_hospital;
    }

    public String getD_address() {
        return d_address;
    }

    public void setD_address(String d_address) {
        this.d_address = d_address;
    }

    public Integer getD_sjdq() {
        return d_sjdq;
    }

    public void setD_sjdq(Integer d_sjdq) {
        this.d_sjdq = d_sjdq;
    }

    public String getD_his_name() {
        return d_his_name;
    }

    public void setD_his_name(String d_his_name) {
        this.d_his_name = d_his_name;
    }

    public Integer getD_order_no() {
        return d_order_no;
    }

    public void setD_order_no(Integer d_order_no) {
        this.d_order_no = d_order_no;
    }

    public String getD_lxr() {
        return d_lxr;
    }

    public void setD_lxr(String d_lxr) {
        this.d_lxr = d_lxr;
    }

    public String getD_lxr_dh() {
        return d_lxr_dh;
    }

    public void setD_lxr_dh(String d_lxr_dh) {
        this.d_lxr_dh = d_lxr_dh;
    }

    public String getD_lxr_sj() {
        return d_lxr_sj;
    }

    public void setD_lxr_sj(String d_lxr_sj) {
        this.d_lxr_sj = d_lxr_sj;
    }

    public String getD_lxr_yx() {
        return d_lxr_yx;
    }

    public void setD_lxr_yx(String d_lxr_yx) {
        this.d_lxr_yx = d_lxr_yx;
    }

    public String getD_ct_sum() {
        return d_ct_sum;
    }

    public void setD_ct_sum(String d_ct_sum) {
        this.d_ct_sum = d_ct_sum;
    }

    public String getD_dl_psw() {
        return d_dl_psw;
    }

    public void setD_dl_psw(String d_dl_psw) {
        this.d_dl_psw = d_dl_psw;
    }

    public Integer getD_create_bc() {
        return d_create_bc;
    }

    public void setD_create_bc(Integer d_create_bc) {
        this.d_create_bc = d_create_bc;
    }
}
