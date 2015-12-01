package com.iquestint.dao.impl;

import com.iquestint.dao.RoomDao;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class implements RoomDao interface;
 *
 * @author Georgian Vladutu
 */
@Repository("roomDao")
public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {

    @Override
    public Room getRoomByName(String name) throws DaoEntityNotFoundException {
        return getByName(name);
    }

    @Override
    public List<Room> getAllRooms() {
        return getAll();
    }
}
