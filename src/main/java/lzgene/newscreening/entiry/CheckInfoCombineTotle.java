package lzgene.newscreening.entiry;

public class CheckInfoCombineTotle {

    private String combineName;//项目名
    private Integer combineTotal;//一条记录的总数
    private Integer noPrintCount;//未打印
    private Integer printCount;//打印

    public String getCombineName() {
        return combineName;
    }

    public void setCombineName(String combineName) {
        this.combineName = combineName;
    }

    public Integer getCombineTotal() {
        return combineTotal;
    }

    public void setCombineTotal(Integer combineTotal) {
        this.combineTotal = combineTotal;
    }

    public Integer getPrintCount() {
        return printCount;
    }

    public void setPrintCount(Integer printCount) {
        this.printCount = printCount;
    }

    public Integer getNoPrintCount() {
        return noPrintCount;
    }

    public void setNoPrintCount(Integer noPrintCount) {
        this.noPrintCount = noPrintCount;
    }
}
