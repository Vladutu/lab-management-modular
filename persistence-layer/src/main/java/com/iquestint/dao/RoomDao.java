package com.iquestint.dao;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Room;

import java.util.List;

/**
 * This interfaces provides methods for working with Room entity explicitly (and Room database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface RoomDao {

    /**
     * Returns the Room entity from the database which has the same name as the method parameter.
     *
     * @param name the name of the room
     * @return Room
     * @throws DaoEntityNotFoundException if the room is not found
     */
    public Room getRoomByName(String name) throws DaoEntityNotFoundException;

    /**
     * Returns all Room entities from the database.
     *
     * @return list of rooms
     */
    public List<Room> getAllRooms();
}
