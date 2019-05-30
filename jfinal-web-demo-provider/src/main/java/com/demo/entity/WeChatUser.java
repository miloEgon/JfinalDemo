package com.demo.entity;

import com.demo.common.JPAModel;
import com.demo.common.Table;
import org.hibernate.validator.constraints.Length;

/**
 * 微信小程序第三方SID与所有者/安装师傅手机号绑定关系
 */
@Table(tableName="tb_wechat_user")
public class WeChatUser extends JPAModel<WeChatUser> {

    @Length(max = 64)
    public String getId() { return get("id"); }

    @Length(max = 64)//对接方身份id
    public String getSid() { return get("sid"); }

    @Length(max = 11)//用户手机号
    public String getPhone() { return get("phone"); }

    //是否有效 根据该关联表过滤用户可以看到的有效房产
    public Boolean getIsValid() {  return get("is_valid"); }

    public String getOpenId() { return get("open_id"); }

    public java.sql.Timestamp getCreateDate() { return get("create_date"); }

    public java.sql.Timestamp getModifyDate() { return get("modify_date"); }

    public WeChatUser setId(String id) { return set("id", id); }

    public WeChatUser setSid(String sid) { return set("sid", sid); }

    public WeChatUser setPhone(String phone) { return set("phone", phone); }

    public WeChatUser setIsValid(Boolean is_valid) { return set("is_valid", is_valid); }

    public WeChatUser setOpenId(String open_id) { return set("open_id", open_id); }

    public WeChatUser setCreateDate(java.sql.Timestamp create_date) { return set("create_date", create_date); }

    public WeChatUser setModifyDate(java.sql.Timestamp modify_date) { return set("modify_date", modify_date); }

}
