import java.util.Scanner;

public class conditional_statements {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks (0-100): ");
        int marks = sc.nextInt();

        System.out.println("\n----- RESULT -----");
        System.out.println("Student Name : " + name);
        System.out.println("Marks        : " + marks);

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid Marks!");
        } else {

            if (marks >= 90) {
                System.out.println("Grade : A+");
            } else if (marks >= 80) {
                System.out.println("Grade : A");
            } else if (marks >= 70) {
                System.out.println("Grade : B");
            } else if (marks >= 60) {
                System.out.println("Grade : C");
            } else if (marks >= 50) {
                System.out.println("Grade : D");
            } else {
                System.out.println("Grade : F");
            }

            if (marks >= 85) {
                System.out.println("Scholarship : Eligible");
            } else {
                System.out.println("Scholarship : Not Eligible");
            }

            if (marks >= 35) {
                System.out.println("Result : PASS");
            } else {
                System.out.println("Result : FAIL");
            }
        }

        sc.close();
    }
}
/*output
Enter Student Name: Anusha
Enter Marks (0-100): 95

----- RESULT -----
Student Name : Anusha
Marks        : 95
Grade : A+
Scholarship : Eligible
Result : PASS */