import java.time.LocalDate;
import java.util.Scanner;

public class LibraryItemDueDateCalculator {
    interface LibraryItem { int loanDays(); }
    static class Book implements LibraryItem { public int loanDays() { return 14; } }
    static class DVD implements LibraryItem { public int loanDays() { return 7; } }
    static class Magazine implements LibraryItem { public int loanDays() { return 3; } }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        LocalDate today = LocalDate.parse("2023-10-26");
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            int firstSpace = line.indexOf(' ');
            String type = line.substring(0, firstSpace);
            String title = line.substring(firstSpace + 1).trim();
            if (title.startsWith("\"") && title.endsWith("\""))
                title = title.substring(1, title.length() - 1);
            LibraryItem item;
            switch (type) {
                case "BOOK": item = new Book(); break;
                case "DVD": item = new DVD(); break;
                case "MAGAZINE": item = new Magazine(); break;
                default: throw new IllegalArgumentException("Unknown item type");
            }
            System.out.println(title + ": " + today.plusDays(item.loanDays()));
        }
        sc.close();
    }
}
