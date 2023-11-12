import java.util.Scanner;

public class LoginSystem {

    public static void startSystem() {

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
                    System.out.println("\nSystem closed succesfully.\n");
                    System.exit(0);
                default:
                    System.out.println("Invalid input!");
            }

            System.out.println("");

        }

    }

    public static void studentLogin(JSONFileManager data) {

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your StudentID: ");
        String studentID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

        boolean validInformation = false;
        boolean userFound = false;
        int indexInDatabase = -1;
        for (int i = 0; i < data.students.size(); i++) {
            if (studentID.compareTo(data.students.get(i).getStudentId()) == 0) {
                if (password.compareTo(data.students.get(i).getPassword()) == 0) {
                    indexInDatabase = i;
                    validInformation = true;
                    userFound = true;
                    break;
                } else {
                    System.out.println("\nWrong Password!");
                    return;
                }
            }
        }

        if (!userFound) {
            System.out.println("\nStudent with StudentID " + studentID + " is not found!");
            return;
        }

        if (validInformation) {
            System.out.println("\nWelcome " + data.students.get(indexInDatabase).getName() + " "
                    + data.students.get(indexInDatabase).getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see transcript.");
                System.out.println("    Enter 2 to register to a course.");
                System.out.println("    Enter 3 to logout.");
                System.out.print("Enter action : ");

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("\nThis is your transcript.\n");
                        break;
                    case 2:
                        System.out.println("\nWelcome to the registration.\n");
                        break;
                    case 3:
                        System.out.println("\nYou have logged out succesfully.");
                        return;
                    default:
                        System.out.println("\nInvalid input!\n");
                }
            }
        }
    }

    public static void lecturerLogin(JSONFileManager data) {

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your StaffID: ");
        String staffID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

        boolean validInformation = false;
        boolean userFound = false;
        int indexInDatabase = -1;
        for (int i = 0; i < data.lecturer.size(); i++) {
            if (staffID.compareTo(data.lecturer.get(i).getStaffID()) == 0) {
                if (password.compareTo(data.lecturer.get(i).getPassword()) == 0) {
                    indexInDatabase = i;
                    validInformation = true;
                    userFound = true;
                    break;
                } else {
                    System.out.println("\nWrong Password!");
                    return;
                }
            }
        }

        if (!userFound) {
            System.out.println("\nLecturer with StaffID " + staffID + " is not found!");
            return;
        }

        if (validInformation) {
            System.out.println("\nWelcome " + data.lecturer.get(indexInDatabase).getName() + " "
                    + data.lecturer.get(indexInDatabase).getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see your proffesion.");
                System.out.println("    Enter 2 to logout.");
                System.out.print("Enter action : ");

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("\nThis is your proffesion.\n");
                        break;
                    case 2:
                        System.out.println("\nYou have logged out succesfully.");
                        return;
                    default:
                        System.out.println("\nInvalid input!\n");
                }
            }
        }

    }

    public static void advisorLogin(JSONFileManager data) {

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your StaffID: ");
        String staffID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

        boolean validInformation = false;
        boolean userFound = false;
        int indexInDatabase = -1;
        for (int i = 0; i < data.advisors.size(); i++) {
            if (staffID.compareTo(data.advisors.get(i).getStaffID()) == 0) {
                if (password.compareTo(data.advisors.get(i).getPassword()) == 0) {
                    indexInDatabase = i;
                    validInformation = true;
                    userFound = true;
                    break;
                } else {
                    System.out.println("\nWrong Password!");
                    return;
                }
            }
        }

        if (!userFound) {
            System.out.println("\nAdvisor with StaffID " + staffID + " is not found!");
            return;
        }

        if (validInformation) {
            System.out.println("\nWelcome " + data.advisors.get(indexInDatabase).getName() + " "
                    + data.advisors.get(indexInDatabase).getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see your proffesion.");
                System.out.println("    Enter 2 to approve student registrations.");
                System.out.println("    Enter 3 to logout.");
                System.out.print("Enter action : ");

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("\nThis is your proffesion.\n");
                        break;
                    case 2:
                        System.out.println("\nThese are the student registrations.\n");
                        break;
                    case 3:
                        System.out.println("\nYou have logged out succesfully.");
                        return;
                    default:
                        System.out.println("\nInvalid input!\n");
                }
            }
        }

    }

    public static void studentAffairsStaffLogin(JSONFileManager data) {

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your StaffID: ");
        String staffID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

        boolean validInformation = false;
        boolean userFound = false;
        int indexInDatabase = -1;
        for (int i = 0; i < data.studentAffairsStaffs.size(); i++) {
            if (staffID.compareTo(data.studentAffairsStaffs.get(i).getStaffID()) == 0) {
                if (password.compareTo(data.studentAffairsStaffs.get(i).getPassword()) == 0) {
                    indexInDatabase = i;
                    validInformation = true;
                    userFound = true;
                    break;
                } else {
                    System.out.println("\nWrong Password!");
                    return;
                }
            }
        }

        if (!userFound) {
            System.out.println("\nStudent affairs staff with StaffID " + staffID + " is not found!");
            return;
        }

        if (validInformation) {
            System.out.println("\nWelcome " + data.studentAffairsStaffs.get(indexInDatabase).getName() + " "
                    + data.studentAffairsStaffs.get(indexInDatabase).getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see your working field.");
                System.out.println("    Enter 2 to logout.");
                System.out.print("Enter action : ");

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("\nThis is your working field.\n");
                        break;
                    case 2:
                        System.out.println("\nYou have logged out succesfully.");
                        return;
                    default:
                        System.out.println("\nInvalid input!\n");
                }
            }
        }
    }

}