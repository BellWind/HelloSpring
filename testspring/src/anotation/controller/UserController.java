package anotation.controller;

import anotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired(required = false)
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void excute() {
        System.out.println("UserController excute.");
        userService.hi();
    }
}
