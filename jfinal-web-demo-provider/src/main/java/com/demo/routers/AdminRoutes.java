package com.demo.routers;

import com.demo.controller.AdminController;
import com.jfinal.config.Routes;

public class AdminRoutes extends Routes {

    public void config() {
        setBaseViewPath("/view/admin");
        add("/admin", AdminController.class);
    }
}
