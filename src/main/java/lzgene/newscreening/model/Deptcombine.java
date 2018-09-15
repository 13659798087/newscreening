package lzgene.newscreening.model;

/*
  * 医院检查项目套餐表
 */
public class Deptcombine {

    private Integer dcId;
    private Integer dcdId; //医院id对应此表的dcdid
    private Integer dcDId;
    private String dcCcode;


    public Integer getDcId() {
        return dcId;
    }

    public void setDcId(Integer dcId) {
        this.dcId = dcId;
    }

    public Integer getDcdId() {
        return dcdId;
    }

    public void setDcdId(Integer dcdId) {
        this.dcdId = dcdId;
    }

    public Integer getDcDId() {
        return dcDId;
    }

    public void setDcDId(Integer dcDId) {
        this.dcDId = dcDId;
    }

    public String getDcCcode() {
        return dcCcode;
    }

    public void setDcCcode(String dcCcode) {
        this.dcCcode = dcCcode;
    }
}
