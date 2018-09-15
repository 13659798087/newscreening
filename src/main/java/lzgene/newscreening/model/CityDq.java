package lzgene.newscreening.model;

/**
送检单位区属信息表。
 */
public class CityDq {
    private Integer dq_id;//关联dept表的dSjdq
    private String  dq_name;
    private Integer dq_flag;
    private String  dq_pym;

    public Integer getDq_id() {
        return dq_id;
    }

    public void setDq_id(Integer dq_id) {
        this.dq_id = dq_id;
    }

    public String getDq_name() {
        return dq_name;
    }

    public void setDq_name(String dq_name) {
        this.dq_name = dq_name;
    }

    public Integer getDq_flag() {
        return dq_flag;
    }

    public void setDq_flag(Integer dq_flag) {
        this.dq_flag = dq_flag;
    }

    public String getDq_pym() {
        return dq_pym;
    }

    public void setDq_pym(String dq_pym) {
        this.dq_pym = dq_pym;
    }
}