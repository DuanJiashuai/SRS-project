package sqliteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.IRoom;
import model.Room;
import util.DBUtil;

public class RoomImpl implements IRoom {
	public List<Room> getAllRooms() {
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from Room";
		List<Room> roomList = new ArrayList<Room>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int roomNo = rs.getInt("roomNo");
				String building = rs.getString("building");
				int seatingCapacity = rs.getInt("seatingCapacity");
				Room room = new Room();
				room.setRoomNo(roomNo);
				room.setBuilding(building);
				room.setSeatingCapacity(seatingCapacity);
				roomList.add(room);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomList;
	}

	public Room getRoom(int roomNo) {
		Room room = new Room();
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from Room where roomNo='" + roomNo + "'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String building = rs.getString("building");
				int seatingCapacity = rs.getInt("seatingCapacity");
				room.setBuilding(building);
				room.setRoomNo(roomNo);
				room.setSeatingCapacity(seatingCapacity);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return room;
	}

	public void addRoom(Room room) {
		int roomNo = room.getRoomNo();
		String building = room.getBuilding();
		int seatingCapacity = room.getSeatingCapacity();

		String sql = "insert into Room values('" + roomNo + "','" + building + "','" + seatingCapacity + "')";
		Connection conn = DBUtil.getSqliteConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入教师异常：" + e.getMessage());
		}
	}

	public void deleteRoom(Room room) {
		int roomNo = room.getRoomNo();
		Connection conn = DBUtil.getSqliteConnection();
		String sql = "delete from Room where roomNo='" + roomNo + "'";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("删除教师异常：" + e.getMessage());
		}
	}
}
