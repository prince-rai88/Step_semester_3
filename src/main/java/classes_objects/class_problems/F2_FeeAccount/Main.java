package main.java.classes_objects.class_problems.F2_FeeAccount;

public class Main {
    public static void main() {
        FeeAccount[] accounts={
                new FeeAccount("101", 150000),
                new HostelFeeAccount("102", 200000),
                new ScholarshipAccountFee("103", 180000, 20)
        };

        accounts[0].pay(150000);

        for (FeeAccount account : accounts) {
            if (account instanceof HostelFeeAccount)
                ((HostelFeeAccount) account).payInTwoInstallments(60000);

            if (account instanceof ScholarshipAccountFee)
                System.out.println("Scholarship account effective due: Rs "
                        + ((ScholarshipAccountFee) account).effectiveDue());
            else
                System.out.println("Account due: Rs " + account.getDue());
        }

    }
}
