import java.util.Scanner;

public class LoginSystem {
    public static void LoginSystem() {

        JSONFileManager data = new JSONFileManager();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the LOGIN SYSTEM:");
            System.out.println("    Enter 1 if you are a Student.");
            System.out.println("    Enter 2 if you are a Lecturer.");
            System.out.println("    Enter 3 if you are an Advisor.");
            System.out.println("    Enter 4 if you are a Student Affairs Staff.");
            System.out.println("    Enter 5 exit.");

            System.out.print("Enter action : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    studentLogin(data);
                    break;
                case 2:
                    lecturerLogin(data);
                    break;
                case 3:
                    advisorLogin(data);
                    break;
                case 4:
                    studentAffairsStaffLogin(data);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid input!");
            }

            System.out.println("");

        }
    }

    public static void studentLogin(JSONFileManager data) {
        System.out.println("Student");

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your StudentID: ");
        String studentID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

        Student student1 = new Student();
        data.students.add(student1);
        data.students.get(0).setStudentId("150121991");
        data.students.get(0).setPassword("123");

        boolean validInformation = false;
        boolean userFound = false;
        int indexInDatabase = -1;
        for (int i = 0; i < data.students.size(); i++) {
            if (studentID.compareTo(data.students.get(i).getStudentId()) == 0) {
                System.out.println("TESTING!!!");
                if (password.compareTo(data.students.get(i).getPassword()) == 0) {
                    indexInDatabase = i;
                    validInformation = true;
                    userFound = true;
                    break;
                } else {
                    System.out.println("Wrong Password!");
                    return;
                }
            }
        }

        if (!userFound) {
            System.out.println("Student with StudentID " + studentID + " is not found!");
            return;
        }

        if (validInformation) {
            while (true) {
                System.out.println("Welcome " + data.students.get(indexInDatabase).getName() + " "
                        + data.students.get(indexInDatabase).getLastName());
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see transcript.");
                System.out.println("    Enter 2 to register to a course.");
                System.out.println("    Enter 3 to logout.");
                System.out.print("Enter action : ");

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("This is your transcript");
                        break;
                    case 2:
                        System.out.println("Welcome to the registration");
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid input!");
                }
            }
        }
    }

    public static void lecturerLogin(JSONFileManager data) {
        System.out.println("Lecturer");

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your LecturerID: ");
        String studentID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

    }

    public static void advisorLogin(JSONFileManager data) {
        System.out.println("Advisor");

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your AdvisorID: ");
        String studentID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

    }

    public static void studentAffairsStaffLogin(JSONFileManager data) {
        System.out.println("Student Affairs Staff");

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your StudentAffairsStaffID: ");
        String studentID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

    }

}