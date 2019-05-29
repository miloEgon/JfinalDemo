package com.demo.entity.floor;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;

public class FloorSaveBean {

    @Length(max=64)
    private String id;

    @Length(max = 64)
    @NotBlank(message = "房源ID不为空")
    private String estate_id;

    @Length(max = 64)
    @NotBlank(message = "楼层名称不为空")
    private String name;

    @Length(max = 64)
    private String master_id;

    private Timestamp create_date;

    private Timestamp modify_date;

    public void setId(String id) {
        this.id = id;
    }

    public void setEstate_id(String estate_id) {
        this.estate_id = estate_id;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEstate_id() {
        return estate_id;
    }

    public String getName() {
        return name;
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
