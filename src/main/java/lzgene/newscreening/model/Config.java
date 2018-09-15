package lzgene.newscreening.model;

public class Config {

    private String cf_code;  //代码
    private Integer cf_flag;// 标志。1为启用，0为不启用
    private String cf_val; //值
    private String cf_explain; //意义

    public String getCf_code() {
        return cf_code;
    }

    public void setCf_code(String cf_code) {
        this.cf_code = cf_code;
    }

    public Integer getCf_flag() {
        return cf_flag;
    }

    public void setCf_flag(Integer cf_flag) {
        this.cf_flag = cf_flag;
    }

    public String getCf_val() {
        return cf_val;
    }

    public void setCf_val(String cf_val) {
        this.cf_val = cf_val;
    }

    public String getCf_explain() {
        return cf_explain;
    }

    public void setCf_explain(String cf_explain) {
        this.cf_explain = cf_explain;
    }
}
