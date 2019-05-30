package com.demo.service;

import java.lang.annotation.*;

/**
 * Created by mr.j on 2017/11/21.
 */
@Inherited
@Retention( RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
public @interface RpcService {
  String topic();
}
