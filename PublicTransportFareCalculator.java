import java.util.Scanner;

public class PublicTransportFareCalculator {
    interface Transport { double fare(double distance, double factor); }
    static class Bus implements Transport {
        public double fare(double d, double f) { return Math.min(10.0, 2.0 + 0.10 * d); }
    }
    static class Train implements Transport {
        public double fare(double d, double f) { return 3.0 + 0.15 * d; }
    }
    static class Metro implements Transport {
        public double fare(double d, double f) { return (1.50 + 0.20 * d) * f; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            double distance = Double.parseDouble(p[1]);
            double factor = p.length > 2 ? Double.parseDouble(p[2]) : 1.0;
            Transport t;
            switch (p[0]) {
                case "BUS": t = new Bus(); break;
                case "TRAIN": t = new Train(); break;
                case "METRO": t = new Metro(); break;
                default: throw new IllegalArgumentException("Unknown transport type");
            }
            double fare = t.fare(distance, factor);
            System.out.printf("%s: %.2f%n", p[0], fare);
            total += fare;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
