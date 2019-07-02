## 参考文献

配合此文一起食用[[跟着刚哥学习Spring框架](https://www.cnblogs.com/hzg110/p/6760653.html)]。



## 创建Hello Word项目

### 1.提前需要了解的名词

**非侵入式设计**：不需要继承框架自身的类，这样更换框架也可以使用原来的代码。

**IOC（控制反转）**：面向对象编程中的一种设计原则，可以用来减低计算机代码之间的耦合度。其中最常见的方式叫做依赖注入（Dependency Injection，简称DI）。
举例：Class A中用到了Class B的对象b，一般情况下，需要在A的代码中显式的new一个B的对象。采用依赖注入技术之后，A的代码只需要定义一个私有的B对象，不需要直接new来获得这个对象，而是通过相关的容器控制程序来将B对象在外部new出来并注入到A类里的引用中。而具体获取的方法、对象被获取时的状态由配置文件（如XML）来指定。

### 2.创建Spring配置文件applicationContext.xml

测试小项目中的xml模板：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
        http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd">

    <bean id="helloworld" class="HelloWorld">
        <property name="name" value="Spring"></property>
    </bean>

</beans>
```

只有中间的`<bean id>`标签是需要自主编写的：

```xml
<bean id="helloworld" class="HelloWorld">
    <property name="name" value="Spring"></property>
</bean>
```

### 3.过程

在main函数中，getBean方法传递的参数信息是“helloworld”，在xml文件中查询到bean id值为为“helloworld”时，HelloWorld类的name成员变量的初始化函数的参数赋值为"Spring"。

### 4.代码样例地址

[HelloSpring](https://github.com/BellWind/HelloSpring)



## Spring容器

### 1.启动Spring容器

**Bean**：在Spring中，那些组成应用程序的主体，及由Spring IoC容器所管理的对象，都被称之为bean。

ps：Spring Ioc容器至少包含一个bean定义，但大多数情况下会有多个bean定义。bean定义与应用程序中实际使用的对象一一对应。

### 2.两种启动方式

**BeanFactory**：IoC容器的顶级接口，是IoC容器的最基础实现，也是访问Spring容器的根接口，负责对bean的创建，访问等工作。

举例：

Object getBean(String name)：返回Spring容器中id为name的Bean实例。

**ApplicationContext**：

大部分使用Spring框架的开发者所使用的方式。

两个实现类的使用方式：

**(1)ClassPathXmlApplicationContext**：从类路径下加载配置文件

```java
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
```

**(2)FileSystemXmlApplicationContext**:从文件系统中加载配置文件

```java
String ctxConfig = "文件绝对路径";
ApplicationContext ctx = new FileSystemXmlApplicationContext(ctxConfig);
```



## 通过XML方式配置Bean

### 1.Bean的配置方式

```xml
<bean id="helloworld" class="HelloWorld">    
    <property name="name" value="Spring"></property>
</bean>
```

通过id（**唯一**）和class指定。

ps：要求Bean中必须有无参的构造器。

### 2.依赖注入的方式

**(1)属性注入**：通过**setter**方法注入属性值。

```xml
<bean id="helloworld" class="HelloWorld">    
    <property name="name" value="Spring"></property>
</bean>
```

属性注入使用Property元素，使用name指定Bean的属性名称，使用value指定Bean的属性的值。

**此方法必须要有setter！**

**(2)构造器注册**：通过构造方法注入Bean的属性和值.

创建一个Car类：

```java
public class Car {    
    private String brand;    
    private int speed;    
    private String color;    
    public Car(String brand, int speed, String color) {        
        this.brand = brand;        
        this.speed = speed;        
        this.color = color;    
    }    
    @Override    
    public String toString() {        
        return "Car [brand=" + brand +                
            ", speed=" + speed +                
            ", color=" + color + "]";    
    }
}
```

在main函数中加入：

```java
Car car = (Car)ctx.getBean(Car.class);
System.out.println(car);
```

在配置文件中加入：

```xml
<bean id="car" class="Car">        
    <constructor-arg value="Audi"></constructor-arg>        
    <constructor-arg value="120"></constructor-arg>        
    <constructor-arg value="black"></constructor-arg>
</bean>
```

或者使用index属性：

```xml
<constructor-arg value="Audi" index="0"></constructor-arg>
```

或者使用type属性：

```xml
<constructor-arg value="Audi" type="java.lang.String"></constructor-arg>
```

### 3.引用其他Bean

**(1)引用外部Bean**：

```xml
<!--引用外部Bean-->
<bean id="p1" class="Person">    
    <property name="name" value="mayu"></property>    
    <property name="age" value="18"></property>    
    <!--使用 property的ref属性建立Bean之间的引用关系-->    
    <property name="car" ref="car"></property>
</bean>
```

被引用的外部Bean为：

```xml
<bean id="car" class="Car">        
    <constructor-arg value="Audi"></constructor-arg>        
    <constructor-arg value="120"></constructor-arg>        
    <constructor-arg value="black"></constructor-arg>
</bean>
```

**通过ref的值为"car"，检索bean id也为"car"的bean类**。

使用 property的ref属性建立Bean之间的引用关系.

**(2)引用内部类**：

```xml
<bean id="p2" class="Person">    
    <property name="name" value="mayu"></property>    
    <property name="age" value="18"></property>    
    <!--内部Bean-->    
    <property name="car">        
        <bean class="Car">            
            <constructor-arg value="Benz"></constructor-arg>            
            <constructor-arg value="60"></constructor-arg>            
            <constructor-arg value="white"></constructor-arg>        
        </bean>    
    </property>
</bean>
```

### 4.级联属性赋值

```xml
<bean id="p1" class="Person">    
    <property name="name" value="mayu"></property>    
    <property name="age" value="18"></property>    
    <!--引用外部Bean-->    
    <property name="car" ref="car"></property>    
    <!--级联属性赋值-->    
    <property name="car.price" value="300000"></property>
</bean>
```

**要注意的点**：

1.在Car类中记得给car.price设置setter；

2.在Person类中要给car成员变量设置getter（调用的car.price为私有变量，public变量可以不用设置getter）；

3.在Bean类中要先初始car。

### 5.集合属性赋值

```xml
<bean id="p3" class="Person">    
    <property name="name" value="mayu"></property>    
    <property name="age" value="18"></property>    
    <!--集合属性，使用ref来配置子节点信息-->    
    <property name="cars">        
        <list>            
            <ref bean="car1"></ref>            
            <ref bean="car2"></ref>            
            <ref bean="car3"></ref>        
        </list>    
    </property>
</bean>
```

### 6.Q&A

*Q：为什么在bean id为p1的bean中赋值price为300000会影响到它上面的bean id为car1的bean类？*

*A：请看第8点：Bean的作用域。默认情况下Spring返回的实例都是单例的：一旦容器中注册了实例对象，应用程序需要的时候，就直接给予，不会重复创建。*

### 7.使用命名空间赋值

```xml
<!--通过P命名空间为Bean属性赋值，相对于传统的配置方式更加简洁 -->
<bean id="p4" class="Person" p:name="mayu" p:age="18" p:car-ref="car1"></bean>
```

### 8.Bean的作用域

```java
Person p1 = (Person)ctx.getBean("p1", Person.class);
Person p2 = (Person)ctx.getBean("p1", Person.class);
System.out.println(p1 == p2);
```

输出结果为True。

说明默认为单例模式的，这个看scope属性。使用Bean的scope属性来配置Bean的作用域的，scope有两个重要的属性值。

**singleton**：**默认值**，容器初始化时创建Bean实例，在整个容器的生命周期内只创建一个Bean，单例的。

**prototype**：原型的，容器初始化时不创建Bean的实例，而在每次请求时都创建一个新的Bean的实例，并返回。

### 9.使用外部属性文件

使用property-placeholder 属性占位符。

外部文件db.properties内容：

```text
name=lulu
age=20
```

xml文件：

```xml
<context:property-placeholder location="db.properties">
</context:property-placeholder>
<bean id="p5" class="Person">    
    <property name="name" value="${name}"></property>    
    <property name="age" value="${age}"></property>
</bean>
```

### 10.Bean的生命周期

### ![img](https://cdn.nlark.com/lark/0/2019/png/224206/1562055842874-d910fd03-0eed-4cb6-9ee9-24c7de6c9a20.png?OSSAccessKeyId=LTAIX2KDHwZymFhr&Expires=1562057773&Signature=3qX%2BL5YHl5dwz5HzdwDubqOi4Ng%3D)



## 通过注解方式配置Bean

### 1.需要预先加载的jar包

首先再导入一个jar包，名为：spring-aop-4.3.6.RELEASE.jar。

然后创建如下包和类、接口：

```
+-- anotation
  +-- controller
    |-- UserController
  +-- repository
    |-- UserRepository(接口)
    |-- UserRepositoryImlp
  +-- service
  	|-- UserService
  |--Main
```

### 2.具体代码

[具体代码](https://github.com/BellWind/HelloSpring/tree/master/testspring/src/anotation)

组件扫描**：spring自动扫描classpath，侦测和实例化具有特定注解的组件。

### 3.特定组件

1.**@Componenet**：基本注解；

2.**@Respository**：标识持久层组件；

3.**@Service**：标识业务层组件；

4.**@Controller**：标识表现层组件。

**这四个注解目前的功能都是一样的**，注解名的不同为了能够让标记类本身的用途更加清晰。

### 4.其他注意的点

**Spring 有默认的命名策略**: 使用非限定类名, 第一个字母小写. 也可以在注解中通过 value 属性值标识组件的名称。

比如在此Demo中，UserController类名前的`@Component`等同于于`@Component(value="userController")`。使用`getBean("userController")`可以实例化一个该类对象。

常用：

`@Component(value = "car") `相当于在配置文件中`<bean id="car" class="……"></bean`。

`@Scope(value = "prototype") `配置创建对象是否是以单列模式进行创建。

之后还需要在配置xml文件configAutowire.xml中加上一句：

```xml
<context:component-scan base-package="anotation" />
```

需要扫描多个包时, 可以使用逗号分隔。

Main函数内容：

```java
ApplicationContext ctx = new ClassPathXmlApplicationContext("configAutowire.xml");
UserController userController = (UserController) ctx.getBean("userController");
userController.excute();
```

会依次构造UserController、UserService和UserRepositoryImlp实例。

### 5.Q&A

*Q：注解方式配置Bean，是不是没办法给类成员变量赋值？*

*A：...*



## AOP

参考此文一起食用[Spring AOP（面向切面示例）](https://www.cnblogs.com/hq233/p/6637488.html)。

### 1.首先需要了解的名词

**切面(aspect)**：横切关注点被模块化的特殊对象。

**通知(advice)**：切面必须要完成的工作。切面中的每个方向称之为通知。通知是在切面对象中的。

**目标(target)**：被通知的对象。

**代理(proxy)**：向目标对象应用通知后创建的对象。

**连接点（joinpoint）**：目标对象的程序执行的某个特定位置。如某个方法调用前，调用后的位置。包括两个信息：目标程序的哪个方法？方法执行 前还是执行后？

**切点(pointcut)**：每个类会有多个连接点，AOP通过切点定位到特定的边接点。类比，连接点相当于数所成方圆 中的记录，而切点相当于查询条件。一个切点匹配多个连接点。

### 2.利用aop和注解来实例化的示例

接口：

```java
public interface Human {    
	public void eat();    
	public void sleep();
}
```

类：

```java
@Componentpublic 
class Mayu implements Human {    
	@Override    
	public void eat() {        
		System.out.println("mayu吃饭了。");    
	}    
	@Override    
	public void sleep() {        
		System.out.println("mayu睡觉了。");    
	}
}
```

切面类：

```java
@Aspect //声明该类是切面类
@Component//配置文件中启动自动扫描功能
public class HumanAspect {    

	@Before("execution(* aop.*.eat(..))") //在吃饭前先洗手    
	public void wash() {        
		System.out.println("先洗手啦~");    
	}    
	
	@After("execution(* aop.*.eat(..))") //吃饭后刷牙    
	public void brush() {        
		System.out.println("然后刷牙啦~");    
	}    
	
	@Before("execution(* aop.*.sleep(..))") //睡觉前先看会书    
	public void read() {        
		System.out.println("先看会书~ ");    
	}
}
```

xml配置中加入：

```xml
<context:component-scan base-package="aop" />
<!-- 配置文件中启动AspectJ的注解功能 ,默认是false，要将其改为true -->
<aop:aspectj-autoproxy proxy-target-class="true" />
```

Main函数构造一个Mayu类的Bean实例：

```java
ApplicationContext context = new ClassPathXmlApplicationContext("configAop.xml");
Mayu m = (Mayu)context.getBean(Mayu.class);
m.eat();
m.sleep();
```

输出结果：

```
先洗手啦~
mayu吃饭了。
然后刷牙啦~
先看会书~ 
mayu睡觉了。
```

### 3.需要注意的点

`"execution(* aop.*.eat(..))"`该行代码含义：

1.**第一个*号**：表示返回类型，*号表示所有的类型；

2.**包名**：表示需要拦截的包名；

3.**第二个*号**：通配符；

4.**`eat(..)`中的..**：..代表所有形参，不管有多少。

### 4.Q&A

*Q：在xml配置中加入`<!--<bean class="aop.Mayu" />-->`之后，出现编译错误：*

*`No qualifying bean of type 'aop.Mayu' available: expected single matching bean but found 2: mayu,aop.Mayu#0`*

*这是为何？*

*A：...*