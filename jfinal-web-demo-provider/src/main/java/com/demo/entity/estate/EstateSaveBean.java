package com.demo.entity.estate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;

public class EstateSaveBean {

    @Length(max=64)
    private String id;

    @Length(max=100)
    @NotBlank(message = "房源名不可为空")
    private String name;

    @Length(max=6)
    private String area_code;

    @Length(max=300)
    @NotBlank(message = "房源地址不可为空")
    private String address;

    @Length(max=64)
    @NotBlank(message = "第三方SID不可为空")
    private String master_id;

    private Timestamp create_date;

    private Timestamp modify_date;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMaster_id(String master_id) {
        this.master_id = master_id;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public void setModify_date(Timestamp modify_date) {
        this.modify_date = modify_date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArea_code() {
        return area_code;
    }

    public String getAddress() {
        return address;
    }

    public String getMaster_id() {
        return master_id;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public Timestamp getModify_date() {
        return modify_date;
    }
}
