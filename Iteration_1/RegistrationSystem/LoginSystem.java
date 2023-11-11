import java.util.Scanner;

public class LoginSystem {
    public static void LoginSystem() {

        Scanner scanner = new Scanner(System.in);
        

        while (true) {
            System.out.println("Press 1 for Student Login System.");
            System.out.println("Press 2 for Advisor Login System.");
            System.out.println("Press 3 for Lecturer Login System.");
            System.out.println("Press 4 exit.");

            System.out.print("Press any button : ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    StudentLogin();
                    break;
                case 2:
                    AdvisorLogin();
                    break;
                case 3:
                    LecturerLogin();
                    break;
                case 4: 
                    System.exit(0);
                
                default:
                    System.out.println("ERROR");
            }
        }
    }

    public static void StudentLogin() {
        System.out.println("Student");
    }
    public static void LecturerLogin() {
        
    }
    public static void AdvisorLogin() {
        
    }
}