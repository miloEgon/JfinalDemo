package com.demo.routers;

import com.demo.controller.*;
import com.jfinal.config.Routes;

public class FrontRoutes extends Routes {
    public void config() {
        setBaseViewPath("/view/front");
        add("/hello", HelloController.class);
        add("/account", AccountController.class);
        add("/user", UserController.class);
        add("/mini", MiniProController.class);
        add("/redis", RedisController.class);
        add("/house", EstateController.class);
        add("/floor", FloorController.class);
        add("/room", RoomController.class);
    }
}
