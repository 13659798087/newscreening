package lzgene.newscreening.entiry;

public class ApplicationSetmealTotle {

    private String setmealName;//项目名
    private Integer setmealTotal;//一条记录的总数
    private Integer entryCount;//录入
    private Integer inspection;//送检
    private Integer signInCount;//签收
    private Integer reportCount;//报告

    public String getSetmealName() {
        return setmealName;
    }

    public void setSetmealName(String setmealName) {
        this.setmealName = setmealName;
    }

    public Integer getSetmealTotal() {
        return setmealTotal;
    }

    public void setSetmealTotal(Integer setmealTotal) {
        this.setmealTotal = setmealTotal;
    }

    public Integer getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(Integer entryCount) {
        this.entryCount = entryCount;
    }

    public Integer getInspection() {
        return inspection;
    }

    public void setInspection(Integer inspection) {
        this.inspection = inspection;
    }

    public Integer getSignInCount() {
        return signInCount;
    }

    public void setSignInCount(Integer signInCount) {
        this.signInCount = signInCount;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

}
