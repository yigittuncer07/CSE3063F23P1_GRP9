import java.util.ArrayList;
import java.util.Scanner;

public class LoginSystem {

    private Scanner scanner = new Scanner(System.in);
    private JSONFileManager jsonFileManager = new JSONFileManager();

    public void startSystem() {

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
                    studentLogin(jsonFileManager);
                    break;
                case 2:
                    lecturerLogin(jsonFileManager);
                    break;
                case 3:
                    advisorLogin(jsonFileManager);
                    break;
                case 4:
                    studentAffairsStaffLogin(jsonFileManager);
                    break;
                case 5:
                    System.out.println("\nSystem closed succesfully.\n");
                    jsonFileManager.writeAllDataToJSON();
                    System.exit(0);
                default:
                    System.out.println("Invalid input!");
            }

            System.out.println("");

        }

    }

    private void studentLogin(JSONFileManager data) {

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your StudentID: ");
        String studentID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

        boolean validInformation = false;
        boolean userFound = false;
        int indexInDatabase = -1;
        for (int i = 0; i < data.getStudents().size(); i++) {
            if (studentID.compareTo(data.getStudents().get(i).getStudentId()) == 0) {
                if (password.compareTo(data.getStudents().get(i).getPassword()) == 0) {
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
            System.out.println("\nWelcome " + data.getStudents().get(indexInDatabase).getName() + " "
                    + data.getStudents().get(indexInDatabase).getLastName());
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
                        System.out.println(data.getStudents().get(indexInDatabase).getTranscript());
                        break;
                    case 2:
                        System.out.println("\nWelcome to the registration.\n");
                        registrationProcess(data.getStudents().get(indexInDatabase));
                        jsonFileManager.writeAllDataToJSON();
                        System.exit(0);// If this is removed we get an error with the scanner
                    case 3:
                        System.out.println("\nYou have logged out succesfully.");
                        return;
                    default:
                        System.out.println("\nInvalid input!\n");
                }
            }
        }
    }

    private void registrationProcess(Student student) {
        ArrayList<Course> courses = jsonFileManager.getCourses();
        while (true) {
            System.out.println(
                    "Enter a course code to register to said course, or enter \"submit\" to submit for approval:");
            for (Course course : courses) {
                System.out.println(course.getCourseCode() + " : " + course.getCourseName());
            }
            String input = scanner.nextLine();
            if (input.equals("submit")) {
                if (student.getDraftForCourses().isEmpty()) {
                    System.out.println("Cannot submit empty draft");
                } else {
                    student.sendDraftToAdvisor();
                    System.out.println("Sent for approval");
                    return;
                }
            } else {
                boolean courseFound = false;
                for (Course course : courses) {
                    if (input.equals(course.getCourseCode())) {
                        courseFound = true;
                        if (student.addToDraft(course)) {
                            System.out.println("Course added succesfully!");
                        } else {
                            System.out.println("Course couldnt be added!");
                        }
                        continue;
                    }
                }
                if (!courseFound) {
                    System.out.println("no such course!");
                }
                courseFound = false;
            }
            System.out.print("Current draft: ");
            for (Course course : student.getDraftForCourses()) {
                System.out.print(course.getCourseCode() + " ");
            }
            System.out.println("\n ");
        }
    }

    private void lecturerLogin(JSONFileManager data) {

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your StaffID: ");
        String staffID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

        boolean validInformation = false;
        boolean userFound = false;
        int indexInDatabase = -1;
        for (int i = 0; i < data.getLecturers().size(); i++) {
            if (staffID.compareTo(data.getLecturers().get(i).getStaffID()) == 0) {
                if (password.compareTo(data.getLecturers().get(i).getPassword()) == 0) {
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
            System.out.println("\nWelcome " + data.getLecturers().get(indexInDatabase).getName() + " "
                    + data.getLecturers().get(indexInDatabase).getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see your proffesion.");
                System.out.println("    Enter 2 to logout.");
                System.out.print("Enter action : ");

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println(data.getLecturers().get(indexInDatabase).getProffesion());
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

    private void advisorLogin(JSONFileManager data) {

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your StaffID: ");
        String staffID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

        boolean validInformation = false;
        boolean userFound = false;
        int indexInDatabase = -1;
        for (int i = 0; i < data.getAdvisors().size(); i++) {
            if (staffID.compareTo(data.getAdvisors().get(i).getStaffID()) == 0) {
                if (password.compareTo(data.getAdvisors().get(i).getPassword()) == 0) {
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
            System.out.println("\nWelcome " + data.getAdvisors().get(indexInDatabase).getName() + " "
                    + data.getAdvisors().get(indexInDatabase).getLastName());
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
                        System.out.println(data.getAdvisors().get(indexInDatabase).getProffesion());
                        break;
                    case 2:
                    System.out.println("\nPlease proceed with this draft:.\n");
                    for (ArrayList<Course> draft : data.getAdvisors().get(indexInDatabase).getDrafts()) {
                        for (Course course : draft){
                            System.out.println(course.getCourseName() + " " + course.getCourseCode());
                        }
                        System.out.println("Do you approve this draft? yes/no");
                        String advisorInput = scanner.nextLine();
                        if (advisorInput.equals("yes")) {
                            draft.get(0).getStudent().approveDraft();
                        } else if (advisorInput.equals("no")) {
                            draft.get(0).getStudent().clearDraft();
                        }
                    }
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

    private void studentAffairsStaffLogin(JSONFileManager data) {

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter your StaffID: ");
        String staffID = input.nextLine();
        System.out.println("Enter your Password: ");
        String password = input.nextLine();

        boolean validInformation = false;
        boolean userFound = false;
        int indexInDatabase = -1;
        for (int i = 0; i < data.getStudentAffairsStaffs().size(); i++) {
            if (staffID.compareTo(data.getStudentAffairsStaffs().get(i).getStaffID()) == 0) {
                if (password.compareTo(data.getStudentAffairsStaffs().get(i).getPassword()) == 0) {
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
            System.out.println("\nWelcome " + data.getStudentAffairsStaffs().get(indexInDatabase).getName() + " "
                    + data.getStudentAffairsStaffs().get(indexInDatabase).getLastName());
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