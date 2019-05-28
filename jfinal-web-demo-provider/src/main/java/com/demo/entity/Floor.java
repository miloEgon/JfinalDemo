package com.demo.entity;

import com.demo.common.JPAModel;
import com.demo.common.Table;
import org.hibernate.validator.constraints.Length;

@Table(tableName = "tb_floor")
public class Floor extends JPAModel<Floor> {

    public static final Floor dao = new Floor().dao();

    @Length(max = 64)
    public String getId() { return get("id"); } //主键

    @Length(max = 64)
    public String getEstateId() { return get("estate_id"); } //房产ID

    @Length(max = 64)
    public String getName() { return get("name"); } //楼层名称

    @Length(max = 64)
    public String getMasterId() { return get("master_id"); } //第三方SID

    public Object getCreateDate() { return get("create_date"); } //创建时间

    public Object getModifyDate() { return get("modify_date"); } //修改时间

    public Floor setId(String id) { return set("id", id); }

    public Floor setEstateId(String estate_id) { return set("estate_id", estate_id); }

    public Floor setName(String name) { return set("name", name); }

    public Floor setMasterId(String master_id) { return set("master_id", master_id); }

    public Floor setCreateDate(Object create_date) { return set("create_date", create_date); }

    public Floor setModifyDate(Object modify_date) { return set("modify_date", modify_date); }



}
