package lzgene.newscreening.model;

/*
模板信息表
 */
public class Mb {

    private String mb_id;
    private String mb_code;
    private String mb_name;
    private String mb_order_no;
    private Integer mb_flag; //0为在用，1为删除
    private Integer mb_type; //同一类型为同一信息
    private String mb_lb_code;//类别代码
    private String mb_lb_name;//类别名称
    public String getMb_id() {
        return mb_id;
    }

    public void setMb_id(String mb_id) {
        this.mb_id = mb_id;
    }

    public String getMb_code() {
        return mb_code;
    }

    public void setMb_code(String mb_code) {
        this.mb_code = mb_code;
    }

    public String getMb_name() {
        return mb_name;
    }

    public void setMb_name(String mb_name) {
        this.mb_name = mb_name;
    }

    public String getMb_order_no() {
        return mb_order_no;
    }

    public void setMb_order_no(String mb_order_no) {
        this.mb_order_no = mb_order_no;
    }

    public Integer getMb_flag() {
        return mb_flag;
    }

    public void setMb_flag(Integer mb_flag) {
        this.mb_flag = mb_flag;
    }

    public Integer getMb_type() {
        return mb_type;
    }

    public void setMb_type(Integer mb_type) {
        this.mb_type = mb_type;
    }

    public String getMb_lb_code() {
        return mb_lb_code;
    }

    public void setMb_lb_code(String mb_lb_code) {
        this.mb_lb_code = mb_lb_code;
    }

    public String getMb_lb_name() {
        return mb_lb_name;
    }

    public void setMb_lb_name(String mb_lb_name) {
        this.mb_lb_name = mb_lb_name;
    }

}
