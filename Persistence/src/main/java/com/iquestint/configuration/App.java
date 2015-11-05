package com.iquestint.configuration;

import com.iquestint.dao.GroupDao;
import com.iquestint.dao.SectionDao;
import com.iquestint.exception.DaoEntityAlreadyExists;
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

import java.util.List;

/**
 * Hello world!
 */
@Component
public class App {

    @Autowired
    StudentService studentService;

    @Autowired
    GroupDao groupDao;

    @Autowired
    SectionDao sectionDao;

    public static void main(String[] args) throws DaoEntityNotFoundException, DaoEntityAlreadyExists {
        ApplicationContext ctx =
            new AnnotationConfigApplicationContext(PersistenceConfig.class);

        App app = (App) ctx.getBean(App.class);

        app.deleteStudent();

    }

    public void selectGroup() {
        Group g = groupDao.getGroupByName("10305S");
        System.out.println(g.getName());
        System.out.println(g.getId());
    }

    public void selectSection() {
        Section s = sectionDao.getSectionByName("C.E.");
        System.out.println(s.getName());
        System.out.println(s.getId());
    }

    public void selectAllGroups() {
        List<Group> groups = groupDao.getAllGroups();
        for (Group g : groups) {
            System.out.println(g.getName());
        }
    }

    public void insertStudent() throws DaoEntityAlreadyExists {
        Student student = new Student();
        student.setFirstName("Georgian");
        student.setLastName("Vladutu");
        student.setSection(new Section("C.E."));
        student.setGroup(new Group("10305H"));
        student.setSubgroup(new Subgroup("A"));

        studentService.saveStudent(student);
    }

    public void getStudent() throws DaoEntityNotFoundException {
        Student s = studentService.getStudentByName("Geordgian", "Vladutu");
        System.out.println(4);
    }

    public void getAllStudent() {
        List<Student> students = studentService.findAllStudents();
    }

    public void deleteStudent() throws DaoEntityNotFoundException {
        studentService.deleteStudent(43);
    }

}
