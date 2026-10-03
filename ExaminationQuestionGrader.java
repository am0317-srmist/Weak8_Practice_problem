import java.util.Scanner;

public class ExaminationQuestionGrader {
    interface Question { double score(String correct, String answer, double points); }
    static class MCQ implements Question {
        public double score(String c, String a, double p) { return c.equalsIgnoreCase(a) ? p : 0; }
    }
    static class TF implements Question {
        public double score(String c, String a, double p) { return c.equalsIgnoreCase(a) ? p : 0; }
    }
    static class Essay implements Question {
        public double score(String correct, String answer, double points) {
            String lowerAnswer = answer.toLowerCase();
            int found = 0;
            for (String keyword : correct.split(",")) {
                String k = keyword.trim().toLowerCase();
                if (!k.isEmpty() && lowerAnswer.contains(k)) found++;
            }
            if (found >= 2) return points * 0.75;
            if (found == 1) return points * 0.50;
            return 0;
        }
    }

    // Input fields are expected to be double-quoted when they contain spaces.
    private static String[] parseQuotedFields(String line) {
        java.util.ArrayList<String> fields = new java.util.ArrayList<>();
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(line);
        while (m.find()) fields.add(m.group(1) != null ? m.group(1) : m.group(2));
        return fields.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] p = parseQuotedFields(sc.nextLine());
            String type = p[0], correct = p[2], answer = p[3];
            double points = Double.parseDouble(p[4]);
            Question q;
            switch (type) {
                case "MCQ": q = new MCQ(); break;
                case "TF": q = new TF(); break;
                case "ESSAY": q = new Essay(); break;
                default: throw new IllegalArgumentException("Unknown question type");
            }
            double score = q.score(correct, answer, points);
            System.out.printf("%s: %.2f%n", type, score);
            total += score;
        }
        System.out.printf("Total Score: %.2f%n", total);
        sc.close();
    }
}
