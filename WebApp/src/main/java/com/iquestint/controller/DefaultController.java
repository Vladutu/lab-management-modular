package com.iquestint.controller;

import com.iquestint.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author vladu
 */
@Controller
@RequestMapping("/")
public class DefaultController {

    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
        model.addAttribute("greeting", "Hello World from Spring 4 MVC");
        return "welcome";
    }

    @RequestMapping(value = "/helloagain", method = RequestMethod.GET)
    public String sayHelloAgain(ModelMap model) {
        model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "welcome";
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String getStudent(ModelMap model) {
        model.addAttribute("students", studentService.getAllStudents());

        return "students";
    }
}
