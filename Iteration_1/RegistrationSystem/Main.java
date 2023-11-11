import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        LoginSystem newLogin = new LoginSystem();

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
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                   
                    break;
                case 4:
                    
                    System.exit(0);
                default:
                    System.out.println("ERROr");
            }
        }
    }
}
