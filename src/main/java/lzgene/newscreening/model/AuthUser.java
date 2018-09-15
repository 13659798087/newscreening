package lzgene.newscreening.model;

/**
 * 授权单位信息表
 */
public class AuthUser {
    private String au_code;//代码,id
    private String au_name;//医院名称
    private String au_address;//地址
    private String au_linkman;//联系人
    private String au_tel;//联系人电话
    private String au_position;//联系人职位
    private String au_registration_code;//注册码
    private String au_remarks;//备注
    private String au_key;//实验室key，接口验证


    public String getAu_key() {
        return au_key;
    }

    public void setAu_key(String au_key) {
        this.au_key = au_key;
    }

    public String getAu_code() {
        return au_code;
    }

    public void setAu_code(String au_code) {
        this.au_code = au_code;
    }

    public String getAu_name() {
        return au_name;
    }

    public void setAu_name(String au_name) {
        this.au_name = au_name;
    }

    public String getAu_address() {
        return au_address;
    }

    public void setAu_address(String au_address) {
        this.au_address = au_address;
    }

    public String getAu_linkman() {
        return au_linkman;
    }

    public void setAu_linkman(String au_linkman) {
        this.au_linkman = au_linkman;
    }

    public String getAu_tel() {
        return au_tel;
    }

    public void setAu_tel(String au_tel) {
        this.au_tel = au_tel;
    }

    public String getAu_position() {
        return au_position;
    }

    public void setAu_position(String au_position) {
        this.au_position = au_position;
    }

    public String getAu_registration_code() {
        return au_registration_code;
    }

    public void setAu_registration_code(String au_registration_code) {
        this.au_registration_code = au_registration_code;
    }

    public String getAu_remarks() {
        return au_remarks;
    }

    public void setAu_remarks(String au_remarks) {
        this.au_remarks = au_remarks;
    }
}
