import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class Tracker {
    static ArrayList<Student> studentList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== Student Grade Tracker =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Calculate Average Grade");
            System.out.println("4. Show Highest Grade");
            System.out.println("5. Show Lowest Grade");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    calculateAverageGrade();
                    break;
                case 4:
                    showHighestGrade();
                    break;
                case 5:
                    showLowestGrade();
                    break;
                case 6:
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        } while (choice != 6);
    }

    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student grade (0-100): ");
        double grade = scanner.nextDouble();
        studentList.add(new Student(name, grade));
        System.out.println("Student added successfully!");
    }

    static void viewAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n--- Student Records ---");
        for (Student s : studentList) {
            System.out.println("Name: " + s.name + " | Grade: " + s.grade);
        }
    }

    static void calculateAverageGrade() {
        if (studentList.isEmpty()) {
            System.out.println("No student records to calculate average.");
            return;
        }

        double total = 0;
        for (Student s : studentList) {
            total += s.grade;
        }

        double average = total / studentList.size();
        System.out.printf("Average Grade: %.2f\n", average);
    }

    static void showHighestGrade() {
        if (studentList.isEmpty()) {
            System.out.println("No student records to evaluate.");
            return;
        }

        Student highest = studentList.get(0);
        for (Student s : studentList) {
            if (s.grade > highest.grade) {
                highest = s;
            }
        }

        System.out.println("Highest Grade: " + highest.grade + " by " + highest.name);
    }

    static void showLowestGrade() {
        if (studentList.isEmpty()) {
            System.out.println("No student records to evaluate.");
            return;
        }

        Student lowest = studentList.get(0);
        for (Student s : studentList) {
            if (s.grade < lowest.grade) {
                lowest = s;
            }
        }

        System.out.println("Lowest Grade: " + lowest.grade + " by " + lowest.name);
    }
}
