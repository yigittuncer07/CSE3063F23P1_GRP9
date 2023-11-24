import java.util.ArrayList;
import java.util.Scanner;

public class LoginSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final JSONFileManager jsonFileManager = new JSONFileManager();

    public void startSystem() {

        while (true) {
            System.out.println(
                    "Welcome to the LOGIN SYSTEM:\n\tEnter 1 if you are a Student.\n\tEnter 2 if you are a Lecturer.\n\tEnter 3 if you are an Advisor.\n\tEnter 4 if you are a Student Affairs Staff.\n\tEnter 5 exit.\nEnter action : ");

            int choice = -1;// This will go to the default value if it is not writen in the try catch block
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception ignore) {
            }

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
                    System.out.println("\nSystem closed succesfully.\n");
                    jsonFileManager.writeAllDataToJSON();
                    System.exit(0);
                default:
                    System.out.println("Invalid input!");
            }
            System.out.println("");
        }
    }

    private void studentLogin() {
        System.out.println("\nEnter your StudentID: ");
        String studentID = scanner.nextLine();
        System.out.println("Enter your Password: ");
        String password = scanner.nextLine();

        boolean validInformation = false;
        boolean userFound = false;
        int indexInDatabase = -1;
        for (int i = 0; i < jsonFileManager.getStudents().size(); i++) {
            if (studentID.compareTo(jsonFileManager.getStudents().get(i).getStudentId()) == 0) {
                if (password.compareTo(jsonFileManager.getStudents().get(i).getPassword()) == 0) {
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

        Student student = jsonFileManager.getStudents().get(indexInDatabase);

        if (validInformation) {
            System.out.println("\nWelcome " + student.getName() + " " + student.getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see transcript.");
                System.out.println("    Enter 2 to register to a course.");
                System.out.println("    Enter 3 to logout.");
                System.out.print("Enter action : ");

                int choice = -1;// switch will go to default if integer cannot be parsed
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (Exception ignore) {
                }

                switch (choice) {
                    case 1:
                        System.out.println("\nThis is your transcript.\n");
                        System.out.println(student.getTranscript());
                        break;
                    case 2:
                        System.out.println("\nWelcome to the registration.\n");
                        registrationProcess(student);
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
                for (Advisor advisor : jsonFileManager.getAdvisors()) {
                    if (advisor.getStaffID().equals(student.getAdvisor().getStaffID())) {
                        student.sendDraftToAdvisor(advisor);
                    }
                }
                System.out.println("Sent for approval");
                return;
            } else {
                boolean courseFound = false;
                for (Course course : courses) {
                    if (input.equals(course.getCourseCode())) {
                        courseFound = true;
                        boolean canAddToDraft = student.canAddToDraft(course);
                        if (canAddToDraft) {
                            course.setStudent(student);
                            student.addToDraft(course);
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

    private void lecturerLogin() {

        System.out.println("\nEnter your StaffID: ");
        String staffID = scanner.nextLine();
        System.out.println("Enter your Password: ");
        String password = scanner.nextLine();

        boolean validInformation = false;
        boolean userFound = false;
        int indexInDatabase = -1;
        for (int i = 0; i < jsonFileManager.getLecturers().size(); i++) {
            if (staffID.compareTo(jsonFileManager.getLecturers().get(i).getStaffID()) == 0) {
                if (password.compareTo(jsonFileManager.getLecturers().get(i).getPassword()) == 0) {
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
            System.out.println("\nWelcome " + jsonFileManager.getLecturers().get(indexInDatabase).getName() + " "
                    + jsonFileManager.getLecturers().get(indexInDatabase).getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see your proffesion.");
                System.out.println("    Enter 2 to logout.");
                System.out.print("Enter action : ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println(jsonFileManager.getLecturers().get(indexInDatabase).getProffesion());
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

    private void advisorLogin() {

        System.out.println("\nEnter your StaffID: ");
        String staffID = scanner.nextLine();
        System.out.println("Enter your Password: ");
        String password = scanner.nextLine();

        boolean validInformation = false;
        boolean userFound = false;
        int indexInDatabase = -1;
        for (int i = 0; i < jsonFileManager.getAdvisors().size(); i++) {
            if (staffID.compareTo(jsonFileManager.getAdvisors().get(i).getStaffID()) == 0) {
                if (password.compareTo(jsonFileManager.getAdvisors().get(i).getPassword()) == 0) {
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
            System.out.println("\nWelcome " + jsonFileManager.getAdvisors().get(indexInDatabase).getName() + " "
                    + jsonFileManager.getAdvisors().get(indexInDatabase).getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see your proffesion.");
                System.out.println("    Enter 2 to approve student registrations.");
                System.out.println("    Enter 3 to logout.");
                System.out.print("Enter action : ");

                int choice = -1;// switch will go to default if integer cannot be parsed
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (Exception ignore) {
                }

                switch (choice) {
                    case 1:
                        System.out.println(jsonFileManager.getAdvisors().get(indexInDatabase).getProffesion());
                        break;
                    case 2:
                        System.out.println("\nPlease proceed with this draft:.\n");
                        for (ArrayList<Course> draft : jsonFileManager.getAdvisors().get(indexInDatabase).getDrafts()) {
                            for (Course course : draft) {
                                System.out.println(course.getCourseName() + " " + course.getCourseCode());
                            }
                            System.out.println("Do you approve this draft? yes/no");
                            String advisorInput = scanner.nextLine();

                            if (advisorInput.equals("yes")) {
                                for (Student student : jsonFileManager.getStudents()) {
                                    if (student.getStudentId().equals(draft.get(0).getStudent().getStudentId())) {
                                        student.approveDraft(draft);
                                    }
                                }
                            } else if (advisorInput.equals("no")) {
                                for (Student student : jsonFileManager.getStudents()) {
                                    if (student.getStudentId().equals(draft.get(0).getStudent().getStudentId())) {
                                        student.clearDraft();
                                    }
                                }
                            }
                        }
                        jsonFileManager.getAdvisors().get(indexInDatabase).clearDrafts();
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

    private void studentAffairsStaffLogin() {

        System.out.println("\nEnter your StaffID: ");
        String staffID = scanner.nextLine();
        System.out.println("Enter your Password: ");
        String password = scanner.nextLine();

        boolean validInformation = false;
        boolean userFound = false;
        int indexInDatabase = -1;
        for (int i = 0; i < jsonFileManager.getStudentAffairsStaffs().size(); i++) {
            if (staffID.compareTo(jsonFileManager.getStudentAffairsStaffs().get(i).getStaffID()) == 0) {
                if (password.compareTo(jsonFileManager.getStudentAffairsStaffs().get(i).getPassword()) == 0) {
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
            System.out.println(
                    "\nWelcome " + jsonFileManager.getStudentAffairsStaffs().get(indexInDatabase).getName() + " "
                            + jsonFileManager.getStudentAffairsStaffs().get(indexInDatabase).getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see your working field.");
                System.out.println("    Enter 2 to logout.");
                System.out.print("Enter action : ");

                int choice = -1;// switch will go to default if integer cannot be parsed
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (Exception ignore) {
                }

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