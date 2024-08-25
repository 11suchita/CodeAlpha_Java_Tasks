import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }
}

public class StudentGrades {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of students:");
        int numberOfStudents = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Enter the student's name:");
            String name = scanner.nextLine();

            System.out.println("Enter the student's grade:");
            int grade = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            students.add(new Student(name, grade));
        }

        int total = 0;
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        for (Student student : students) {
            int grade = student.getGrade();
            total += grade;
            if (grade > highest) {
                highest = grade;
            }
            if (grade < lowest) {
                lowest = grade;
            }
        }

        double average = (double) total / students.size();

        System.out.println("\nResults:");
        System.out.println("Average score: " + average);
        System.out.println("Highest score: " + highest);
        System.out.println("Lowest score: " + lowest);

        scanner.close();
    }
}
