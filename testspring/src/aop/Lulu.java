package aop;

import org.springframework.stereotype.Component;

@Component
public class Lulu implements Human{

    @Override
    public void eat() {
        System.out.println("lulu吃饭了。");
    }

    @Override
    public void sleep() {
        System.out.println("lulu睡觉了。");
    }
}
