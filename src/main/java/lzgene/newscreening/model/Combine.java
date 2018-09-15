package lzgene.newscreening.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/*
  组别表
 */
public class Combine {
    private String c_code; //代码 id
    private String c_name; //名称
    private BigDecimal c_price; //价格
    private Integer c_order_no; //排序
    private Integer c_flag; //0为在用，1为删除
    private String c_rpt;  //对应报表名称
    private String c_rpt2;// 对应报表名称2
    private String c_rpt3;// 对应报表名称3
    private String c_rpt4;// 对应报表名称4
    private String c_rpt_title;// 报告单标题
    private String c_rpt_bz1;// 报告单备注1
    private String c_rpt_bz2;// 报告单备注2
    private Timestamp create_time;
    private Timestamp update_time;
    private String paper_size;// 纸张类型

    public String getPaper_size() {
        return paper_size;
    }

    public void setPaper_size(String paper_size) {
        this.paper_size = paper_size;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public BigDecimal getC_price() {
        return c_price;
    }

    public void setC_price(BigDecimal c_price) {
        this.c_price = c_price;
    }

    public Integer getC_order_no() {
        return c_order_no;
    }

    public void setC_order_no(Integer c_order_no) {
        this.c_order_no = c_order_no;
    }

    public Integer getC_flag() {
        return c_flag;
    }

    public void setC_flag(Integer c_flag) {
        this.c_flag = c_flag;
    }

    public String getC_rpt() {
        return c_rpt;
    }

    public void setC_rpt(String c_rpt) {
        this.c_rpt = c_rpt;
    }

    public String getC_rpt2() {
        return c_rpt2;
    }

    public void setC_rpt2(String c_rpt2) {
        this.c_rpt2 = c_rpt2;
    }

    public String getC_rpt3() {
        return c_rpt3;
    }

    public void setC_rpt3(String c_rpt3) {
        this.c_rpt3 = c_rpt3;
    }

    public String getC_rpt4() {
        return c_rpt4;
    }

    public void setC_rpt4(String c_rpt4) {
        this.c_rpt4 = c_rpt4;
    }

    public String getC_rpt_title() {
        return c_rpt_title;
    }

    public void setC_rpt_title(String c_rpt_title) {
        this.c_rpt_title = c_rpt_title;
    }

    public String getC_rpt_bz1() {
        return c_rpt_bz1;
    }

    public void setC_rpt_bz1(String c_rpt_bz1) {
        this.c_rpt_bz1 = c_rpt_bz1;
    }

    public String getC_rpt_bz2() {
        return c_rpt_bz2;
    }

    public void setC_rpt_bz2(String c_rpt_bz2) {
        this.c_rpt_bz2 = c_rpt_bz2;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }
}
