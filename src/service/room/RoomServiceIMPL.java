package service.room;

import config.ConfigReadAndWriteFile;
import model.Room;

import java.util.List;

public class RoomServiceIMPL implements IRoomService {

    public static String PATH_ROOM = ConfigReadAndWriteFile.PATH + "room.txt";

    public static List<Room> rooms = new ConfigReadAndWriteFile<Room>().readFromFile(PATH_ROOM);


    @Override
    public void edit(int id, Room room) {

    }

    @Override
    public void delete(int id, Room room) {

    }

    @Override
    public void edit(int id, Object o) {

    }

    @Override
    public void delete(int id, Object o) {

    }

    @Override
    public List<Room> findAll() {
        new ConfigReadAndWriteFile<Room>().writeToFile(PATH_ROOM, rooms);
        return rooms;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void save(Room room) {
        rooms.add(room);
    }

    @Override
    public Room findById(int id) {
        for (int i = 0; i < rooms.size(); i++) {
            if (id == rooms.get(i).getRoomNumber()) {
                return rooms.get(i);
            }
        }
        return null;
    }
}