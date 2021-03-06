package com.demo.config;

import com.demo.common.Dict;
import com.demo.interceptor.GlobalActionInterceptor;
import com.demo.interceptor.GlobalServiceInterceptor;
import com.demo.routers.AdminRoutes;
import com.demo.routers.FrontRoutes;
import com.jfinal.config.*;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class DemoConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants constants) {
        constants.setUrlParaSeparator("&");
        constants.setViewType(ViewType.JFINAL_TEMPLATE);
        constants.setBaseDownloadPath("D:/downloads");
        constants.setBaseUploadPath("files");
        constants.setInjectDependency(true);

        loadPropertyFile("play.properties");
        constants.setDevMode(getPropertyToBoolean(Dict.CONFIG_JFINAL_MODE, false));
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add(new FrontRoutes());
        routes.add(new AdminRoutes());
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        //配置Mysql数据库连接相关配置
        PluginFactory.startActiveRecordPlugin(plugins);
        //配置Redis缓存相关配置
        PluginFactory.startRedisPlugin(plugins, "bbs");
    }

    @Override
    public void configInterceptor(Interceptors me) {
        // 添加控制层全局拦截器
        me.addGlobalActionInterceptor(new GlobalActionInterceptor());
        // 添加业务层全局拦截器
        me.addGlobalServiceInterceptor(new GlobalServiceInterceptor());
    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
