package com.demo.entity;

import com.demo.common.JPAModel;
import com.demo.common.Table;
import org.hibernate.validator.constraints.Length;

@Table(tableName="tb_estate")
public class Estate extends JPAModel<Estate> {
    public static final Estate dao = new Estate().dao();

    @Length(max = 64)
    public String getId() { return get("id"); } //主键

    @Length(max = 100)
    public String getName() { return get("name"); } //房源名称

    @Length(max = 6)
    public String getAreaCode() { return get("area_code"); } //6位地区编号

    @Length(max = 300)
    public String getAddress() { return get("address"); } //房源地址

    @Length(max = 64)
    public String getMasterId() { return get("master_id"); } //第三方SID

/*    public java.sql.Timestamp getCreateDate() { return get("create_date"); } //创建时间

    public java.sql.Timestamp getModifyDate() { return get("modify_date"); } //修改时间*/

    public Object getCreateDate() { return get("create_date"); } //创建时间

    public Object getModifyDate() { return get("modify_date"); } //修改时间

    public Estate setId(String id) { return set("id", id); }

    public Estate setName(String name) { return set("name", name); }

    public Estate setAreaCode(String area_code) { return set("area_code", area_code); }

    public Estate setAddress(String address) { return set("address", address); }

    public Estate setMasterId(String master_id) { return set("master_id", master_id); }

    public Estate setCreateDate(Object create_date) { return set("create_date", create_date); }

    public Estate setModifyDate(Object modify_date) { return set("modify_date", modify_date); }

    /*private String createTime;

    private String modifyTime;

    public String getCreateTime() {
        return createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }*/
}
