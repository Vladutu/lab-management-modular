package com.iquestint.service;

import com.iquestint.dao.interfaces.RoomDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Room;
import com.iquestint.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author vladu
 */
@Service("roomService")
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao roomDao;

    @Override
    public List<Room> getAllRooms() {
        return roomDao.getAllRooms();
    }

    @Override
    public Room getRoomByName(String name) throws DaoEntityNotFoundException {
        return roomDao.getRoomByName(name);
    }
}
