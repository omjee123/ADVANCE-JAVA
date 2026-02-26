package org.example;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationConfig.xml");
        Student studentBean = context.getBean("studentBean", Student.class);
        studentBean.display();
    }
}
