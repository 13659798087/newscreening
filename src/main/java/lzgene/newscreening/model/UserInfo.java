package lzgene.newscreening.model;

public class UserInfo {
	
	private String userId;
	private String userName;
	private String password;
	private String phone;
	private String createTime;
	private String updateTime;
	private String organizationId;
	private String parentId;
	private String name;
	private String listRole;
	private Integer dayLoginError;//每天登陆的错误次数，账号一天登录次数超过三次就冻结账户
	private Integer firstLogin;//第一次登陆值为0，修改密码后值为1

	public Integer getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Integer firstLogin) {
		this.firstLogin = firstLogin;
	}

	public Integer getDayLoginError() {
		return dayLoginError;
	}

	public void setDayLoginError(Integer dayLoginError) {
		this.dayLoginError = dayLoginError;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getListRole() {
		return listRole;
	}

	public void setListRole(String listRole) {
		this.listRole = listRole;
	}

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
