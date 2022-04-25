package service.room;

import model.Room;
import service.IServiceGeneric;

public interface IRoomService extends IServiceGeneric {

    void edit(int id, Room room);

    void delete(int id, Room room);

    void save(Room room);
}
