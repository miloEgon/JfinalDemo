package com.demo.entity.gateway;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

public class RequestBean {

    @Length(max = 64)
    @NotBlank(message = "房产ID不能为空")
    private String estate_id; //房产ID

    private List<GatewaySaveBean> list; //网关List

    public String getEstate_id() {
        return estate_id;
    }

    public List<GatewaySaveBean> getList() {
        return list;
    }

    public void setEstate_id(String estate_id) {
        this.estate_id = estate_id;
    }

    public void setList(List<GatewaySaveBean> list) {
        this.list = list;
    }
}
