package com.iquestint.configuration;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Group;
import com.iquestint.model.Section;
import com.iquestint.model.Student;
import com.iquestint.model.Subgroup;
import com.iquestint.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Hello world!
 */
@Component
public class App {

    @Autowired
    StudentService studentService;

    public static void main(String[] args) throws DaoEntityNotFoundException {
        ApplicationContext ctx =
            new AnnotationConfigApplicationContext(PersistenceConfig.class);

        App app = (App) ctx.getBean(App.class);
        app.persist();

    }

    public void update() {
        studentService.deleteStudent(10);
    }

    public void persist() {
        Student student = new Student();
        student.setFirstName("te2453451st1");
        student.setLastName("tes543345345t2");
        student.setSection(new Section("43002"));
        student.setGroup(new Group("54003v"));
        student.setSubgroup(new Subgroup("0D"));

        studentService.saveStudent(student);
    }

    public void getById() throws DaoEntityNotFoundException {
        studentService.findStudentById(10);
    }
}
