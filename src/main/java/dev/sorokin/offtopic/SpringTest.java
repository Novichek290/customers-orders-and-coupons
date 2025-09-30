package dev.sorokin.offtopic;

import dev.sorokin.contract.Music;
import dev.sorokin.controller.DBPrintout;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class SpringTest {
    private static DBPrintout dbPrintout = new DBPrintout();
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("applicationContext.xml")) {
            Computer computer = context.getBean("computer", Computer.class);
            System.out.println(computer);
        }
    }
}

