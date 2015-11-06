package com.iquestint;

import com.iquestint.configuration.BusinessConfig;
import com.iquestint.model.Student;
import com.iquestint.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Hello world!
 */
@Component
public class App {

    @Autowired
    StudentService studentService;

    public static void main(String[] args) {
        ApplicationContext ctx =
            new AnnotationConfigApplicationContext(BusinessConfig.class);

        App app = (App) ctx.getBean(App.class);
        app.printAllStudents();
    }

    public void printAllStudents() {
        List<Student> students = studentService.findAllStudents();

        for (Student s : students) {
            System.out.println(s.getId());
            System.out.println(s.getFirstName());
            System.out.println(s.getLastName());
            System.out.println(s.getSection().getName());
            System.out.println(s.getGroup().getName());
            System.out.println(s.getSubgroup().getName());
        }
    }
}
