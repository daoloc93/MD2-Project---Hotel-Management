package model;

import java.util.Date;

public class Receipt {
    int receiptID;
    int roomNumber;
    String customerName;
    String staffName;
    String checkIn;
    String checkOut;
    int totalPrice;

    public Receipt() {
    }

    public Receipt(int receiptID, int roomNumber, String customerName, String staffName, String checkIn, String checkOut, int totalPrice) {
        this.receiptID = receiptID;
        this.roomNumber = roomNumber;
        this.customerName = customerName;
        this.staffName = staffName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptID=" + receiptID +
                ", roomNumber=" + roomNumber +
                ", customerName='" + customerName + '\'' +
                ", staffName='" + staffName + '\'' +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}