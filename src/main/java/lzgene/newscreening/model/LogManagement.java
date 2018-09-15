package lzgene.newscreening.model;

public class LogManagement {

    private Integer loggerId;
    private String loggerName;
    private String type;
    private String userName;
    private String ipAddress;
    private String createTime;

    private String patients;
    private String hospital;
    private String combine;
    private String barcode;
    private String sid;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPatients() {
        return patients;
    }

    public void setPatients(String patients) {
        this.patients = patients;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getCombine() {
        return combine;
    }

    public void setCombine(String combine) {
        this.combine = combine;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getLoggerId() {
        return loggerId;
    }

    public void setLoggerId(Integer loggerId) {
        this.loggerId = loggerId;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


}
