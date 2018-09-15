package lzgene.newscreening.model;

import java.sql.Timestamp;

public class Signpic {
    private String id;
    private String sp_name;
    private byte[] sp_pic;
    private Timestamp create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSp_name() {
        return sp_name;
    }

    public void setSp_name(String sp_name) {
        this.sp_name = sp_name;
    }

    public byte[] getSp_pic() {
        return sp_pic;
    }

    public void setSp_pic(byte[] sp_pic) {
        this.sp_pic = sp_pic;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
