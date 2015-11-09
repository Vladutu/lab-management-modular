package com.iquestint.service;

import com.iquestint.dao.SectionDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class implements the SectionService interface.
 *
 * @author Georgian Vladutu
 */
@Service("sectionService")
@Transactional
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionDao sectionDao;

    @Override
    public List<Section> getAllSections() {
        return sectionDao.getAllSections();
    }

    @Override
    public Section getSectionByName(String name) throws ServiceEntityNotFoundException {
        try {
            return sectionDao.getSectionByName(name);
        }
        catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
