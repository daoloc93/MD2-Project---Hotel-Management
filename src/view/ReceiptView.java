package view;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.Receipt;
import model.Role;
import model.Room;
import model.RoomStatus;
import service.receipt.ReceiptServiceIMPL;
import service.room.RoomServiceIMPL;
import service.user.UserServiceIMPL;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReceiptView {
    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Scanner sc = new Scanner(System.in);

    List<Room> rooms = RoomServiceIMPL.rooms;
    List<Receipt> receipts = ReceiptServiceIMPL.receipts;

    ReceiptServiceIMPL receiptServiceIMPL = new ReceiptServiceIMPL();
    RoomServiceIMPL roomServiceIMPL = new RoomServiceIMPL();

    public ReceiptView() {

        addToReceiptList();

        System.out.println("======== RECEIPT MANAGEMENT MENU ========");
        System.out.println("CHOOSE ANY KEY TO PROCEED:" +
                "\n1. SHOW ALL RECEIPTS" +
                "\n2. CREATE RECEIPT" +
                "\n3. DELETE RECEIPT" +
                "\n4. EDIT RECEIPT" +
                "\n5. SHOW MONTHLY REVENUE" +
                "\n6. BACK TO MENU");

        int selection = sc.nextInt();
        switch (selection) {
            case 1:
                receiptServiceIMPL.findAll();

                String leftAlignFormat = "| %-10d | %-11d | %-13s | %-10s | %-15s | %-14s | %-11d |%n";

                System.out.format("+------------+-------------+---------------+------------+-----------------+----------------+-------------+%n");
                System.out.format("| Receipt ID | Room Number | Customer Name | Staff Name |  Check-In Date  | Check-Out Date | Total Price |%n");
                System.out.format("+------------+-------------+---------------+------------+-----------------+----------------+-------------+%n");


                for (int i = 0; i < receipts.size(); i++) {
                    System.out.format(leftAlignFormat, receipts.get(i).getReceiptID(), receipts.get(i).getRoomNumber(), receipts.get(i).getCustomerName(), receipts.get(i).getStaffName(), receipts.get(i).getCheckIn(), receipts.get(i).getCheckOut(), receipts.get(i).getTotalPrice());
                }
                System.out.format("+------------+-------------+---------------+------------+-----------------+----------------+-------------+%n");
                backToMenu();
                break;
            case 2:
                createReceipt();
                break;
            case 3:
                System.out.print("Enter receipt ID you would like to delete: ");
                int receiptID = sc.nextInt();
                boolean flag = false;
                for (int i = 0; i < receipts.size(); i++) {
                    if (receiptID == receipts.get(i).getReceiptID()) {
                        flag = true;
                        receipts.remove(receipts.get(i));
                    }
                }

                if (flag == true) {
                    System.out.println("RECEIPT DELETED SUCCESSFULLY!");
                    backToMenu();
                } else {
                    System.out.println("Receipt ID not exist!");
                    backToMenu();
                }
            case 4:
                editReceipt();
                break;
            case 5:
                System.out.print("Enter number of month: ");
                int month = sc.nextInt();
                showMonthlyRevenue(month);
                break;
            case 6:
                new Main();
                break;
        }
    }

    public void createReceipt() {
        System.out.println("======== CREATE RECEIPT MENU ========");

        int id;
        if (receipts.size() == 0) {
            id = 1;
        } else {
            id = receipts.get(receipts.size() - 1).getReceiptID() + 1;
        }

        System.out.print("Enter room number: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();
        System.out.print("Enter staff name: ");
        String staffName = sc.nextLine();
        System.out.print("Enter check-in date (dd/MM/yyyy): ");
        String checkIn = sc.nextLine();
        System.out.print("Enter check-out date (dd/MM/yyyy): ");
        String checkOut = sc.nextLine();
        int totalPrice = 0;
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNumber() == roomNumber) {
                totalPrice = rooms.get(i).getPricePerNight() * calculateDaysDiff(checkIn, checkOut);
            }
        }

        System.out.println("New receipt is successfully created!");

        Receipt receipt = new Receipt(id, roomNumber, customerName, staffName, checkIn, checkOut, totalPrice);
        receiptServiceIMPL.save(receipt);
//        receiptServiceIMPL.findAll();


        for (Receipt element : receipts) {
            if (element.getReceiptID() == id) {
                System.out.println(element);
            }
        }
        backToMenu();
    }

    public void backToMenu() {
        System.out.println("Press 1 to back to MENU");
        int no1 = sc.nextInt();
        switch (no1) {
            case 1:
                new Main();
        }
    }

    public void editReceipt() {
        System.out.println("========== EDIT RECEIPT MENU ==========");
        System.out.print("Enter receipt ID you want to edit: ");
        int receiptID = sc.nextInt();
        boolean flag = false;
        for (int i = 0; i < receipts.size(); i++) {
            if (receipts.get(i).getReceiptID() == receiptID) {
                System.out.println("Founded receipt with ID number of " + receiptID + ":");
                System.out.println(receipts.get(i));
                flag = true;
                System.out.println("Please enter edit information: ");
                System.out.print("Enter room number: ");
                int roomNumber = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter customer name: ");
                String customerName = sc.nextLine();
                System.out.print("Enter staff name: ");
                String staffName = sc.nextLine();
                System.out.println("Enter check-in time (dd/MM/yyyy): ");
                String checkIn = sc.nextLine();
                System.out.println("Enter check-out time (dd/MM/yyyy): ");
                String checkOut = sc.nextLine();
                System.out.println("Enter total price: ");
                int totalPrice = sc.nextInt();

                Receipt newReceipt = new Receipt(receiptID, roomNumber, customerName, staffName, checkIn, checkOut, totalPrice);
                receipts.set(i, newReceipt);
                System.out.println("RECEIPT EDIT SUCCESSFUL!");
            }
        }
        if (flag == false) {
            System.out.println("Receipt number not exist, please try again!");
            editReceipt();
        }
        backToMenu();
    }

    ;

    public void showMonthlyRevenue(int month) {
        String monthName = null;
        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Integer> totalPrice = new ArrayList<Integer>();
        int monthlyRevenue = 0;
        for (int i = 0; i < receipts.size(); i++) {
            try {
                Date date = sdf.parse(receipts.get(i).getCheckIn());
                int getMonth = date.getMonth() + 1;
                if (getMonth == month) {
                    totalPrice.add(receipts.get(i).getTotalPrice());
                    for (int element : totalPrice) {
                        monthlyRevenue += element;
                    }
                }

            } catch (ParseException pe) {
                pe.getMessage();
            }
        }
        System.out.println("Monthly revenue of " + monthName + " is: " + monthlyRevenue);

        backToMenu();
    }

    ;

    public int calculateDaysDiff(String start, String end) {
        int dayDiffs = 0;
        try {
            long dayDiff = 0;
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            long diffInMillies = (endDate.getTime() - startDate.getTime());
            dayDiff = (diffInMillies / (1000 * 60 * 60 * 24)) % 365;
            dayDiffs = (int) dayDiff;
        } catch (ParseException pe) {
            System.err.println("Wrong input format");
            pe.getMessage();
        } finally {
            return dayDiffs;
        }
    }

    public void addToReceiptList() {
        if (receipts.size() == 0) {
            Receipt[] receiptList = new Receipt[12];

            receiptList[0] = new Receipt(1, 102, "Customer1", "Staff1", "21/3/2022", "23/3/2022", 100);
            receiptList[1] = new Receipt(2, 202, "Customer2", "Staff2", "23/3/2022", "26/3/2022", 180);
            receiptList[2] = new Receipt(3, 302, "Customer3", "Staff3", "28/3/2022", "31/3/2022", 210);
            receiptList[3] = new Receipt(4, 401, "Customer4", "Staff4", "01/4/2022", "3/4/2022", 160);
            receiptList[4] = new Receipt(5, 502, "Customer5", "Staff5", "3/4/2022", "5/4/2022", 180);
            receiptList[5] = new Receipt(6, 601, "Customer6", "Staff6", "6/4/2022", "8/4/2022", 200);
            receiptList[6] = new Receipt(7, 101, "Customer7", "Staff1", "8/4/2022", "11/4/2022", 150);
            receiptList[7] = new Receipt(8, 201, "Customer8", "Staff2", "12/4/2022", "15/4/2022", 180);
            receiptList[8] = new Receipt(9, 301, "Customer9", "Staff3", "16/4/2022", "18/4/2022", 140);
            receiptList[9] = new Receipt(10, 402, "Customer10", "Staff4", "18/4/2022", "20/4/2022", 160);
            receiptList[10] = new Receipt(11, 501, "Customer11", "Staff5", "21/4/2022", "23/3/2022", 270);
            receiptList[11] = new Receipt(12, 602, "Customer12", "Staff6", "24/4/2022", "26/4/2022", 200);

            for (Receipt receipt : receiptList) {
                receipts.add(receipt);
            }
        }
    }
    public int calculateTotalPrice(int roomNumber, int nightStay) {
        return roomNumber * nightStay;
    }
}
