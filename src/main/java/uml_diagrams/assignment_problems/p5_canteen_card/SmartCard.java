package uml_diagrams.assignment_problems.p5_canteen_card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class SmartCard {
    private static final double MIN_TOPUP = 100.0;
    private static final double MAX_BALANCE = 5000.0;

    private final String cardId;
    private final PricingPlan plan;
    private final List<Transaction> transactions;
    private boolean active;

    public SmartCard(String cardId, PricingPlan plan) {
        this.cardId = cardId;
        this.plan = plan;
        this.transactions = new ArrayList<>();
        this.active = true;
    }

    public String getCardId() {
        return cardId;
    }

    public PricingPlan getPlan() {
        return plan;
    }

    public boolean isActive() {
        return active;
    }

    public void block() {
        this.active = false;
    }

    public void unblock() {
        this.active = true;
    }

    // Invariant: Balance is always derived from the sum of recorded transactions
    public double getBalance() {
        double sum = 0.0;
        for (Transaction t : transactions) {
            sum += t.getAmount();
        }
        return sum;
    }

    public boolean topUp(double amount) {
        if (!active) {
            System.out.println("Top-up rejected: Card is blocked.");
            return false;
        }

        if (amount < MIN_TOPUP) {
            System.out.printf(Locale.US, "Top-up rejected: Minimum top-up amount is ₹%.2f.%n", MIN_TOPUP);
            return false;
        }

        if (getBalance() + amount > MAX_BALANCE) {
            System.out.printf(Locale.US, "Top-up rejected: Exceeds maximum allowed balance of ₹%.2f.%n", MAX_BALANCE);
            return false;
        }

        transactions.add(new Transaction("Top-up", amount, false));
        System.out.printf(Locale.US, "%s topped up with ₹%.2f. Balance: ₹%.2f.%n",
                cardId, amount, getBalance());
        return true;
    }

    public boolean purchase(String itemName, double originalPrice) {
        if (!active) {
            System.out.println("Purchase rejected: Card is blocked.");
            return false;
        }

        double finalPrice = plan.calculateDiscountedPrice(originalPrice);
        double currentBalance = getBalance();

        if (finalPrice > currentBalance) {
            System.out.printf(Locale.US, "Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).%n",
                    finalPrice, currentBalance);
            return false;
        }

        transactions.add(new Transaction(itemName, -finalPrice, true));
        System.out.printf(Locale.US, "%s purchased for ₹%.2f. Balance: ₹%.2f.%n",
                itemName, finalPrice, getBalance());
        return true;
    }

    public boolean refund(String itemName) {
        if (!active) {
            System.out.println("Refund rejected: Card is blocked.");
            return false;
        }

        Transaction targetPurchase = null;
        for (Transaction t : transactions) {
            if (t.isPurchase() && t.getDescription().equalsIgnoreCase(itemName)) {
                targetPurchase = t;
                break;
            }
        }

        if (targetPurchase == null) {
            System.out.println("Refund rejected: No purchase found for " + itemName + ".");
            return false;
        }

        if (targetPurchase.isRefunded()) {
            System.out.println("Refund rejected: " + itemName + " has already been refunded.");
            return false;
        }

        double refundAmount = Math.abs(targetPurchase.getAmount());
        targetPurchase.setRefunded(true);
        transactions.add(new Transaction("Refund: " + itemName, refundAmount, false));

        System.out.printf(Locale.US, "Refund of ₹%.2f for %s processed. Balance: ₹%.2f.%n",
                refundAmount, itemName, getBalance());
        return true;
    }

    public void printMiniStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mini-statement for ").append(cardId).append(": ");
        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);
            if (t.getAmount() >= 0) {
                sb.append(String.format(Locale.US, "+%.2f", t.getAmount()));
            } else {
                sb.append(String.format(Locale.US, "-%.2f", Math.abs(t.getAmount())));
            }
            if (i < transactions.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(String.format(Locale.US, " = ₹%.2f.", getBalance()));
        System.out.println(sb);
    }
}
