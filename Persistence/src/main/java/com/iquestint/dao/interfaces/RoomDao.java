package com.iquestint.dao.interfaces;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Room;

import java.util.List;

/**
 * @author vladu
 */
public interface RoomDao {

    public Room getRoomByName(String name) throws DaoEntityNotFoundException;

    public List<Room> getAllRooms();
}
