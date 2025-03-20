import java.util.Scanner;

public class StudentGradesCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many subjects do you have? ");
        int subjectCount = scanner.nextInt();
        int totalMarks = 0;
        for (int i = 1; i <= subjectCount; i++) {
            System.out.print("Enter marks for subject " + i + " (out of 100): ");
            int marks = scanner.nextInt();
            
            while (marks < 0 || marks > 100) {
                System.out.print("Invalid input! Please enter marks between 0 and 100: ");
                marks = scanner.nextInt();
            }
            
            totalMarks += marks;
        }
        double average = (double) totalMarks / subjectCount;
        String grade;
        
        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }
        System.out.println("\nTotal Marks: " + totalMarks);
        System.out.println("Average Percentage: " + String.format("%.2f", average) + "%");
        System.out.println("Your Grade: " + grade);
        scanner.close();
    }
}
