package org.fblood.demo.client.controller;

import org.fblood.consumer.annotation.RemoteInject;
import org.fblood.demo.service.UserService;
import org.springframework.stereotype.Controller;

/**
 * Created by coder on 15/5/10.
 */
@Controller
public class UserController {

    @RemoteInject
    private UserService userService;





}
