package main.java.classes_objects.class_problems.F2_FeeAccount;

public class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo,double totalFee){
        this.regNo=regNo;
        this.totalFee=totalFee;
        this.amountPaid=0;
    }

    public FeeAccount() {
    }

    void pay(double amount){
        if(amount<0){
            System.out.println("Invalid Amount");
            return;
        }
        this.amountPaid+=amount;
    }
    double getDue(){
        return totalFee-amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount{
    HostelFeeAccount(String regNo,double totalFee){
        super(regNo,totalFee);
    }
    void payInTwoInstallments(double amount){
        pay(amount);
        pay(amount);
    }
}
class ScholarshipAccountFee extends FeeAccount {
    private double scholarshipPercent;
    ScholarshipAccountFee(String regNo, double totalFee, double scholarshipPercent) {
        super(regNo, totalFee);
        this.scholarshipPercent=scholarshipPercent;
    }
    double effectiveDue(){
        return getDue()-(scholarshipPercent/100 * getDue());
    }

}

