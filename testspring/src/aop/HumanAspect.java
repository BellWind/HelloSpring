package aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect //声明该类是切面类
@Component//配置文件中启动自动扫描功能

public class HumanAspect {

    @Before("execution(* aop.*.eat(..))") //在吃饭前先洗手
    public void wash() {
        System.out.println("先洗手啦~");
    }

    @After("execute(* aop.*.eat(..))") //吃饭后刷牙
    public void brush() {
        System.out.println("然后刷牙啦~");
    }

    @Before("execute(* aop.*.sleep(..))") //睡觉前先看会书
    public void read() {
        System.out.println("先看会书~ ");
    }
}
