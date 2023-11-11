import java.util.Scanner;

public class LoginSystem {
    public static void LoginSystem() {

        JSONFileManager data = new JSONFileManager();

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
                    System.out.println("ERROR");
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
            if (studentID.equals(data.students.get(i).getStudentId())) {
                if (password.equals(data.students.get(i).getPassword())) {
                    validInformation = true;
                } else {
                    System.out.println("Wrong Password!");
                    userFound = true;
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
                System.out.println("Welcome " data.students.);
                System.out.println("");
                System.out.println("");
                System.out.println("");
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

    public static boolean verify(int choice, String id, String password) {

        switch (choice) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
        }

        return true;
    }

}