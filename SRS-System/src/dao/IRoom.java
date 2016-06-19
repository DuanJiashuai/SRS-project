package dao;

import java.util.List;

import model.Room;

public interface IRoom {
	List<Room> getAllRooms();
	Room getRoom(int roomNo);
	void addRoom(Room room);
	void deleteRoom(Room room);
}
