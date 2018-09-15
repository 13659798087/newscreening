package lzgene.newscreening.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/*
套餐信息
 */
public class Setmeal {
    private String s_code;
    private String s_name;
    private BigDecimal s_price;//价格
    private Integer s_order_no;//排序
    private Integer s_flag;//标识
    private Timestamp create_time;
    private Timestamp update_time;

    public String getS_code() {
        return s_code;
    }

    public void setS_code(String s_code) {
        this.s_code = s_code;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public BigDecimal getS_price() {
        return s_price;
    }

    public void setS_price(BigDecimal s_price) {
        this.s_price = s_price;
    }

    public Integer getS_order_no() {
        return s_order_no;
    }

    public void setS_order_no(Integer s_order_no) {
        this.s_order_no = s_order_no;
    }

    public Integer getS_flag() {
        return s_flag;
    }

    public void setS_flag(Integer s_flag) {
        this.s_flag = s_flag;
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
