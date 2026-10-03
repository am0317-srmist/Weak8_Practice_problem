import java.util.Scanner;

public class DeliveryFeeCalculator {
    interface Delivery { double fee(double weight, double distance, double extra); }
    static class Standard implements Delivery {
        public double fee(double w, double d, double e) { return 5 + 0.50 * w + 0.10 * d; }
    }
    static class Express implements Delivery {
        public double fee(double w, double d, double e) { return 15 + w + 0.20 * d; }
    }
    static class International implements Delivery {
        public double fee(double w, double d, double e) { return 25 + 2 * w + 0.50 * d + e; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            double w = Double.parseDouble(p[1]), d = Double.parseDouble(p[2]);
            double extra = p.length > 3 ? Double.parseDouble(p[3]) : 0;
            Delivery delivery;
            switch (p[0]) {
                case "STANDARD": delivery = new Standard(); break;
                case "EXPRESS": delivery = new Express(); break;
                case "INTERNATIONAL": delivery = new International(); break;
                default: throw new IllegalArgumentException("Unknown delivery type");
            }
            double amount = delivery.fee(w, d, extra);
            System.out.printf("%s: %.2f%n", p[0], amount);
            total += amount;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
