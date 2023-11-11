import java.util.Scanner;

public class LoginSystem {
    public static void LoginSystem() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the LOGIN SYSTEM:");
            System.out.println("    Press 1 if you are a Student.");
            System.out.println("    Press 2 if you are a Lecturer.");
            System.out.println("    Press 3 if you are an Advisor.");
            System.out.println("    Press 4 if you are a Student Affairs Staff.");
            System.out.println("    Press 5 exit.");

            System.out.print("Press any button : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    studentLogin();
                    break;
                case 2:
                    lecturerLogin();
                    break;
                case 3:
                    advisorLogin();
                    break;
                case 4:
                    studentAffairsStaffLogin();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("ERROR");
            }

            System.out.println("");

        }
    }

    public static void studentLogin() {
        System.out.println("Student");

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your StudentID: ");
        String studentID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

    }

    public static void lecturerLogin() {
        System.out.println("Lecturer");
        
        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your LecturerID: ");
        String studentID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

    }

    public static void advisorLogin() {
        System.out.println("Advisor");

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your AdvisorID: ");
        String studentID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

    }

    public static void studentAffairsStaffLogin() {
        System.out.println("Student Affairs Staff");

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your StudentAffairsStaffID: ");
        String studentID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

    }
}