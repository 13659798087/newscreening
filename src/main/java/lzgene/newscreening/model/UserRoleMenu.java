package lzgene.newscreening.model;

import java.util.List;

public class UserRoleMenu {

    private String userId;
    private String userName;
    private String password;
    private String phone;
    private String createTime;
    private String organizationId;
    private String roleId;
    private String roleName;
    private String menuId;
    private String menuName;
    private String url;
    private String parentId;
    private String icons;

    private List<UserRoleMenu> listParent;

    private List<UserRoleMenu> listSon;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<UserRoleMenu> getListParent() {
        return listParent;
    }

    public void setListParent(List<UserRoleMenu> listParent) {
        this.listParent = listParent;
    }

    public List<UserRoleMenu> getListSon() {
        return listSon;
    }

    public void setListSon(List<UserRoleMenu> listSon) {
        this.listSon = listSon;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }
}
