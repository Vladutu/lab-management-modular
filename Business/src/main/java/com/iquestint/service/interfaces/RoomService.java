package com.iquestint.service.interfaces;

import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Room;

import java.util.List;

/**
 * @author vladu
 */
public interface RoomService {

    List<Room> getAllRooms();

    Room getRoomByName(String name) throws DaoEntityNotFoundException;
}
