package com.iquestint.configuration;

import com.iquestint.dao.interfaces.LaboratoryDao;
import com.iquestint.dao.interfaces.StudentDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Laboratory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author vladu
 */
@Component
public class App {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private LaboratoryDao laboratoryDao;

    public static void main(String[] args) throws DaoEntityNotFoundException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceConfig.class);

        App app = ctx.getBean(App.class);

        app.doIt();
    }

    private void doIt() throws DaoEntityNotFoundException {
        Laboratory laboratory = laboratoryDao.findLaboratoryById(2);

    }
}
