package com.demo.config;

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
        constants.setDevMode(true);
        constants.setUrlParaSeparator("&");
        constants.setViewType(ViewType.JFINAL_TEMPLATE);
        /*constants.setBaseDownloadPath("files");*/
        constants.setBaseDownloadPath("D:/downloads");
        constants.setBaseUploadPath("files");
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

    }

    @Override
    public void configInterceptor(Interceptors me) {
        // 添加控制层全局拦截器
        me.addGlobalActionInterceptor(new GlobalActionInterceptor());
        // 添加业务层全局拦截器
        me.addGlobalServiceInterceptor(new GlobalServiceInterceptor());
        /*// 为兼容老版本保留的方法，功能与addGlobalActionInterceptor完全一样
        me.add(new GlobalActionInterceptor());*/
    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
