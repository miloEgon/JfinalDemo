package com.demo.utils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.Date;
import java.util.Map;

/**
 * @author hyh
 * @date 2018/12/22
 * @description 该Util用于封装DB+Record的形式,
 *              用于抛弃Mode映射表的做法,
 *              但是数据库表的主键名是id，该工具类的save及update方法只支持单表操作
 */
public class DBUtil {

    /**
     * 使用Record进行保存，保存时需要传入数据库中的字段名作为k，该字段名作为v，
     * 以此作为键值对的形式进行数据库保存操作，保存时自动保存创建时间及更新时间
     * 同时需要传入将保存数据的表名
     * @param params 需要保存的参数
     * @return 返回boolean型保存结果
     */
    public static boolean save(Map<String,Object> params) {

        String tablename = null;

        Record record = new Record();

        boolean flag = false;

        for(Map.Entry<String, Object> entry : params.entrySet()) {

          if("id".equals(entry.getKey())){
              flag = true;
          }

          if("tablename".equals(entry.getKey())) {
              tablename = (String) entry.getValue();
              continue;
          }

          record.set(entry.getKey(), entry.getValue());

        }

        if(tablename == null || "".equals(tablename)){//没有数据表名
            return false;
        }

        if(!flag){
            record.set("id", KCode.GUID());
        }

        record.set("create_date", new Date())
                .set("modify_date", new Date());

        return Db.save(tablename, record);
    }

    /**
     * 使用Record进行更新，更新时需要传入数据库中的字段名作为k，该字段名作为v，
     * 以此作为键值对的形式进行数据库更新操作，更新时自动update更新时间，
     * 同时需要传入将更新数据的表名
     * @param params 需要更新的参数
     * @param record 需要更新的Record对象
     * @return 返回boolean型更新结果
     */
    public static boolean update(Map<String,Object> params, Record record) {

        if(record == null) {
            return false;
        }

        String tablename = null;

        for(Map.Entry<String, Object> entry : params.entrySet()) {

            if("tablename".equals(entry.getKey())) {
                tablename = (String) entry.getValue();
                continue;
            }

            record.set(entry.getKey(), entry.getValue());

        }

        if(tablename == null || "".equals(tablename)){//没有数据表名
            return false;
        }

        record.set("modify_date", new Date());

        return Db.update(tablename, record);

    }

}
