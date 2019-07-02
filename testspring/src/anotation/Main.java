package anotation;

import anotation.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.dir"));
        ApplicationContext ctx = new ClassPathXmlApplicationContext("configAutowire.xml");
        UserController userController = (UserController) ctx.getBean("userController");
        userController.excute();
    }
}
