package aop;
import aop.Mayu;
import aop.Lulu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("configAop.xml");
        Mayu m = (Mayu)context.getBean(Mayu.class);
        m.eat();
        m.eat();
        Lulu l = (Lulu)context.getBean(Lulu.class);
        l.eat();
        l.sleep();
    }
}
