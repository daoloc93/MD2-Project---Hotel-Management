package view;

import model.*;
import service.receipt.ReceiptServiceIMPL;
import service.room.RoomServiceIMPL;
import service.user_principal.UserPrincipalServiceIMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RoomView {

    Scanner sc = new Scanner(System.in);

    List<Room> rooms = RoomServiceIMPL.rooms;
    List<Receipt> receipts = ReceiptServiceIMPL.receipts;

    RoomServiceIMPL roomServiceIMPL = new RoomServiceIMPL();
    ReceiptServiceIMPL receiptServiceIMPL = new ReceiptServiceIMPL();

    public RoomView() {
        addToRoomList();
//        ReceiptView receiptView = new ReceiptView();
//        receiptView.addToReceiptList();

        List<UserPrincipal> userPrincipalList = UserPrincipalServiceIMPL.userPrincipalList;
        boolean checkLogin = false;
        Set<Role> roleSet = userPrincipalList.get(0).getRoleSet();
//        System.out.println(roleSet);
        List<Role> roleList = new ArrayList<>(roleSet);
        System.out.println("roleList ===> " + roleList.get(0));
        boolean checkAdmin = (roleList.get(0).getName() == RoleName.ADMIN);


        if (userPrincipalList.size() != 0) {
            checkLogin = true;
        } else {
            checkLogin = false;
        }


        System.out.println("============ ROOM MANAGEMENT MENU ============");
        System.out.println("CHOOSE ANY KEY TO PROCEED:" +
                "\n1. SHOW ALL ROOMS");
        if (checkAdmin) {
            System.out.println("2. ADD ROOM" +
                    "\n3. DELETE ROOM" +
                    "\n4. EDIT ROOM INFORMATION");
        }
        System.out.println("5. SEARCH ROOM BY PRICE RANGE" +
                "\n6. CHECK ROOM AVAILABILITY" +
                "\n7. BACK TO MENU");

        int selection = sc.nextInt();
        switch (selection) {
            case 1:
                roomServiceIMPL.findAll();

                String leftAlignFormat = "| %-11s | %-15d | %-10s | %-17d | %-16d |%n";

                System.out.format("+-------------+-----------------+------------+-------------------+------------------+%n");
                System.out.format("| Room Number | Price per Night |   Status   | Number Of Bedroom | Number Of Toilet |%n");
                System.out.format("+-------------+-----------------+------------+-------------------+------------------+%n");

                for (int i = 0; i < rooms.size(); i++) {
                    System.out.format(leftAlignFormat, rooms.get(i).getRoomNumber(), rooms.get(i).getPricePerNight(), rooms.get(i).getStatus().name(), rooms.get(i).getNoOfBedroom(), rooms.get(i).getNoOfToilet());
                }
                System.out.format("+-------------+-----------------+------------+-------------------+------------------+%n");
                backToMenu();
                break;
            case 2:
                if (checkAdmin) {
                    addRoom();
                    break;
                } else {
                    System.err.println("Unauthorized Access, back to MAIN MENU");
                    new Main();
                }
            case 3:
                if (checkAdmin) {
                    System.out.print("Enter room number you would like to delete: ");
                    int roomNo = sc.nextInt();
                    boolean flag = false;
                    for (int i = 0; i < rooms.size(); i++) {
                        if (roomNo == rooms.get(i).getRoomNumber()) {
                            flag = true;
                            rooms.remove(rooms.get(i));
                        }
                    }
                    if (flag == true) {
                        System.out.println("ROOM DELETED SUCCESSFULLY!");
                        backToMenu();
                    } else {
                        System.out.println("Room number not exist!");
                        backToMenu();
                    }
                } else {
                    System.err.println("Unauthorized Access, back to MAIN MENU");
                    new Main();
                }
            case 4:
                if (checkAdmin) {
                    editRoomInfo();
                    break;
                } else {
                    System.err.println("Unauthorized Access, back to MAIN MENU");
                    new Main();
                }
                break;
            case 5:
                searchRoomByPriceRange();
                break;
            case 6:
                checkRoomAvailability();
                break;
            case 7:
                new Main();
                break;
        }
    }

    public void addRoom() {
        System.out.println("======== ADD ROOM MENU ========");
        System.out.print("Enter room number: ");
        int roomNumber = sc.nextInt();
        System.out.print("Enter price per night: ");
        int pricePerNight = sc.nextInt();
        System.out.println("Please note that all new rooms are in READY condition");
        System.out.print("Enter number of bedroom: ");
        int noOfBedroom = sc.nextInt();
        System.out.print("Enter number of toilet: ");
        int noOfToilet = sc.nextInt();
        System.out.println("New room is successfully created!");

        Room room = new Room(roomNumber, pricePerNight, RoomStatus.READY, noOfBedroom, noOfToilet);
        roomServiceIMPL.save(room);
        for (Room element : rooms) {
            if (element.getRoomNumber() == roomNumber) {
                System.out.println(element);
            }
        }
        roomServiceIMPL.findAll();
        backToMenu();
    }

    public void editRoomInfo() {
        System.out.println("======== EDIT ROOM INFO MENU ========");
        System.out.print("Enter room number you want to edit: ");
        int roomToEdit = sc.nextInt();

        boolean flag = false;
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNumber() == roomToEdit) {
                flag = true;
                System.out.print("Enter new room number: ");
                int roomNo = sc.nextInt();
                System.out.print("Enter price per night: ");
                int pricePerNight = sc.nextInt();
                System.out.print("Enter room status, 1=READY, 2=EMPTY, 3=UNAVAILABLE: ");
                int statusNo = sc.nextInt();
                RoomStatus status = null;
                switch (statusNo) {
                    case 1:
                        status = RoomStatus.READY;
                        break;
                    case 2:
                        status = RoomStatus.EMPTY;
                        break;
                    case 3:
                        status = RoomStatus.AVAILABLE;

                }
                System.out.print("Enter number of bedroom: ");
                int noOfBedroom = sc.nextInt();
                System.out.println("Enter number of toilet: ");
                int noOfToilet = sc.nextInt();

                Room newRoom = new Room(roomNo, pricePerNight, status, noOfBedroom, noOfToilet);

                rooms.set(i, newRoom);

                System.out.println("ROOM EDIT SUCCESSFUL!");
                System.out.println(rooms);

            }
        }
        if (flag == false) {
            System.out.println("Invalid room number, please try again!");
            editRoomInfo();
        }
        backToMenu();
    }

    public void searchRoomByPriceRange() {
        System.out.println("========== SEARCH ROOM MENU ==========");
        System.out.print("Enter lower limit: ");
        int lowerLimit = sc.nextInt();
        System.out.print("Enter upper limit: ");
        int upperLimit = sc.nextInt();

        ArrayList<Room> matchRoomList = new ArrayList<Room>();

        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getPricePerNight() >= lowerLimit && rooms.get(i).getPricePerNight() <= upperLimit) {
                matchRoomList.add(rooms.get(i));
            }
        }
        System.out.println("List of rooms matching price range of " + lowerLimit + " to " + upperLimit + ":");

        String leftAlignFormat = "| %-11s | %-15d | %-10s | %-17d | %-16d |%n";

        System.out.format("+-------------+-----------------+------------+-------------------+------------------+%n");
        System.out.format("| Room Number | Price per Night |   Status   | Number Of Bedroom | Number Of Toilet |%n");
        System.out.format("+-------------+-----------------+------------+-------------------+------------------+%n");

        for (int i = 0; i < matchRoomList.size(); i++) {
            System.out.format(leftAlignFormat, matchRoomList.get(i).getRoomNumber(), matchRoomList.get(i).getPricePerNight(), matchRoomList.get(i).getStatus().name(), matchRoomList.get(i).getNoOfBedroom(), matchRoomList.get(i).getNoOfToilet());
        }
        System.out.format("+-------------+-----------------+------------+-------------------+------------------+%n");

//        System.out.println(matchRoomList);
        backToMenu();
    }

    public void checkRoomAvailability() {
        System.out.println("========== CHECKING ROOM AVAILABILITY ==========");
        System.out.print("Enter room number you would like to check: ");
        int roomNumber = sc.nextInt();
        boolean flag = false;
        ArrayList<Receipt> temporaryList = new ArrayList<>();

        for (int i = 0; i < receipts.size(); i++) {

            if (receipts.get(i).getRoomNumber() == roomNumber) {
                flag = true;
                temporaryList.add(receipts.get(i));
            }
        }

        if (flag = true) {
            String leftAlignFormat = "| %-10d | %-11d | %-13s | %-10s | %-15s | %-14s | %-11d |%n";

            System.out.format("+------------+-------------+---------------+------------+-----------------+----------------+-------------+%n");
            System.out.format("| Receipt ID | Room Number | Customer Name | Staff Name |  Check-In Date  | Check-Out Date | Total Price |%n");
            System.out.format("+------------+-------------+---------------+------------+-----------------+----------------+-------------+%n");


            for (int i = 0; i < temporaryList.size(); i++) {
                System.out.format(leftAlignFormat, temporaryList.get(i).getReceiptID(), temporaryList.get(i).getRoomNumber(), temporaryList.get(i).getCustomerName(), temporaryList.get(i).getStaffName(), temporaryList.get(i).getCheckIn(), temporaryList.get(i).getCheckOut(), temporaryList.get(i).getTotalPrice());
            }
            System.out.format("+------------+-------------+---------------+------------+-----------------+----------------+-------------+%n");
        } else {
            System.out.println("Room number not exist!");
        }
        backToMenu();
    }

    public void addToRoomList() {
        if (rooms.size() == 0) {

            Room[] roomList = new Room[12];
            roomList[0] = new Room(101, 50, RoomStatus.READY, 1, 1);
            roomList[1] = new Room(102, 50, RoomStatus.READY, 1, 1);
            roomList[2] = new Room(201, 60, RoomStatus.READY, 1, 1);
            roomList[3] = new Room(202, 60, RoomStatus.READY, 1, 1);
            roomList[4] = new Room(301, 70, RoomStatus.READY, 2, 1);
            roomList[5] = new Room(302, 70, RoomStatus.READY, 2, 1);
            roomList[6] = new Room(401, 80, RoomStatus.READY, 2, 1);
            roomList[7] = new Room(402, 80, RoomStatus.READY, 2, 1);
            roomList[8] = new Room(501, 90, RoomStatus.READY, 2, 2);
            roomList[9] = new Room(502, 90, RoomStatus.READY, 2, 2);
            roomList[10] = new Room(601, 100, RoomStatus.READY, 3, 2);
            roomList[11] = new Room(602, 100, RoomStatus.READY, 3, 2);


            for (Room room : roomList) {
                roomServiceIMPL.save(room);
            }
        }
    }

    public void backToMenu() {
        System.out.println("Press 1 to back to MENU");
        int no1 = sc.nextInt();
        switch (no1) {
            case 1:
                new Main();
        }
    }
}
