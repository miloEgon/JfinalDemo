package com.demo.common;

import com.demo.entity.estate.Estate;
import com.demo.entity.floor.Floor;
import com.demo.entity.room.Room;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * 添加数据库表与Model的映射关系，
 * 在指定表的主键时，该插件默认主键为id，
 * 如果数据表的主键不是id需要手动指定
 */
public class MappingModel {

    public static void mapping(ActiveRecordPlugin arp) {

//        arp.addMapping("tb_lock_auth", "id", TenantLockAuth.class);

        arp.addMapping("tb_estate","id", Estate.class);

//        arp.addMapping("tb_estate","id", EstateSaveBean.class);

        arp.addMapping("tb_floor","id", Floor.class);

        arp.addMapping("tb_room","id", Room.class);
//
//        arp.addMapping("tb_device_log","id",DeviceLogBean.class);
//
//        arp.addMapping("tb_information_records", "id", PushRecord.class);
//
//        arp.addMapping("water_electric_meter_token", "id", WaterElectricMeterBean.class);
//
//        arp.addMapping("water_electric_meter_task_push", "id", WaterElectricMeterTask.class);
    }

}
