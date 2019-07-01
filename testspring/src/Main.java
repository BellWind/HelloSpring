import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //1.创建Sprint的IOC容器的对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        //2.从IOC的容器中获取Bean的实例
//        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloworld");
//        //3.调用hello方法
//        helloWorld.hello();

//        Car car = (Car)ctx.getBean(Car.class);
//        System.out.println(car);

        Person p1 = (Person)ctx.getBean("p1", Person.class);
        System.out.println(p1);
    }
}
