## 参考文献

配合下文一起食用[[跟着刚哥学习Spring框架](https://www.cnblogs.com/hzg110/p/6760653.html)]。



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

在main中，`getBean`方法传递的参数信息是`“helloworld”`，在xml文件中查阅到`bean id`值为为`“helloworld”`时，`HelloWorld`类的`name`成员变量的初始化函数的参数赋值为`"Spring"`。



### 4.代码样例地址

[HelloSpring](https://github.com/BellWind/HelloSpring)



