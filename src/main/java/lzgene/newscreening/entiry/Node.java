package lzgene.newscreening.entiry;

public class Node {

    /**
     * 节点id
     */
    private String id;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 父节点id
     */
    private String parentId;

    public Node(String id, String nodeName, String parentId) {
        this.id = id;
        this.nodeName = nodeName;
        this.parentId = parentId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


}
