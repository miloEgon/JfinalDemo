package com.demo.routers;

import com.demo.controller.HelloController;
import com.jfinal.config.Routes;

public class FrontRoutes extends Routes {
    public void config() {
        setBaseViewPath("/view/front");
        add("/", HelloController.class);
    }
}
