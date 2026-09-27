package main.java.classes_objects.class_problems.MiniSystem;

class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount");
            return;
        }

        amountPaid += amount;
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds) {
        this.roomNo = roomNo;
        this.beds = beds;
        occupied = 0;
    }

    void allot(String name) {
        if (occupied < beds) {
            occupied++;
            System.out.println(name + " allotted to " + roomNo);
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (HostelRoom room : rooms) {
            if (room.occupied < room.beds)
                return room;
        }
        return null;
    }

    static HostelRoom safeAllot(HostelRoom[] rooms, String name) {
        HostelRoom room = findAvailableRoom(rooms);

        if (room != null) {
            room.allot(name);
            return room;
        }

        return null;
    }
}

class SrmStudent {

    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    SrmStudent(String name, String regNo,
               HostelFeeAccount feeAccount, HostelRoom room) {

        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;

        totalStudents++;
    }

    void fullStatus() {
        String roomNumber;

        if (room == null)
            roomNumber = "unallotted";
        else
            roomNumber = room.roomNo;

        System.out.println(name + " | Due: Rs " +
                feeAccount.getDue() + " | Room: " + roomNumber);
    }

    public static void main(String[] args) {

        HostelRoom[] rooms = {
                new HostelRoom("C-214", 1),
                new HostelRoom("C-507", 1)
        };

        HostelFeeAccount fee1 =
                new HostelFeeAccount("101", 200000);

        HostelFeeAccount fee2 =
                new HostelFeeAccount("102", 180000);

        HostelFeeAccount fee3 =
                new HostelFeeAccount("103", 200000);

        SrmStudent student1 =
                new SrmStudent("Ravi", "101", fee1, null);

        SrmStudent student2 =
                new SrmStudent("Anitha", "102", fee2, null);

        SrmStudent student3 =
                new SrmStudent("Karthik", "103", fee3, null);

        student1.room = HostelRoom.safeAllot(rooms, "Ravi");
        student2.room = HostelRoom.safeAllot(rooms, "Anitha");

        fee1.pay(60000);
        fee2.pay(-5000);

        student1.fullStatus();
        student2.fullStatus();
        student3.fullStatus();

        System.out.println("Total students: " + totalStudents);
    }
}