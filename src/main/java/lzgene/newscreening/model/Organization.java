package lzgene.newscreening.model;

public class Organization {

    private String id;
    private String name;
    private String parentId;
    private String level; //层级
    private double isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(double isDelete) {
        this.isDelete = isDelete;
    }
}
