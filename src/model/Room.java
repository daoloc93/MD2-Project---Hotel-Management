package model;

public class Room {
    int roomNumber;
    int pricePerNight;
    RoomStatus status;
    int noOfBedroom;
    int noOfToilet;

    public Room() {
    }

    public Room(int roomNumber, int pricePerNight, RoomStatus status, int noOfBedroom, int noOfToilet) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.status = status;
        this.noOfBedroom = noOfBedroom;
        this.noOfToilet = noOfToilet;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public int getNoOfBedroom() {
        return noOfBedroom;
    }

    public void setNoOfBedroom(int noOfBedroom) {
        this.noOfBedroom = noOfBedroom;
    }

    public int getNoOfToilet() {
        return noOfToilet;
    }

    public void setNoOfToilet(int noOfToilet) {
        this.noOfToilet = noOfToilet;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", pricePerNight=" + pricePerNight +
                ", status=" + status +
                ", noOfBedroom=" + noOfBedroom +
                ", noOfToilet=" + noOfToilet +
                '}';
    }
}
