import java.util.Scanner;

public class PaymentSystemFeeCalculation {
    interface Payment { double adjusted(double amount); }
    static class Card implements Payment { public double adjusted(double a) { return a * 1.02; } }
    static class Wallet implements Payment { public double adjusted(double a) { return a * 1.01; } }
    static class BankTransfer implements Payment { public double adjusted(double a) { return a; } }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            Payment payment;
            switch (p[0]) {
                case "CARD": payment = new Card(); break;
                case "WALLET": payment = new Wallet(); break;
                case "BANKTRANSFER": payment = new BankTransfer(); break;
                default: throw new IllegalArgumentException("Unknown payment type");
            }
            double amount = payment.adjusted(Double.parseDouble(p[1]));
            System.out.printf("%s: %.2f%n", p[0], amount);
            total += amount;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
