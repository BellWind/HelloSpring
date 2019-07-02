package aop;

import org.springframework.stereotype.Component;

@Component
public class Mayu implements Human {

    @Override
    public void eat() {
        System.out.println("mayu吃饭了。");
    }

    @Override
    public void sleep() {
        System.out.println("mayu睡觉了。");
    }
}
