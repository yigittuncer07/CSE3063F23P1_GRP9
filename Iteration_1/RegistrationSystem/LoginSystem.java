import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class LoginSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final JSONFileManager jsonFileManager = new JSONFileManager();
    private Timer timer = new Timer();

    public void startSystem() {

        timer.schedule(new ExitTask(), 300 * 1000);

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

        Student student = (Student) getUserWithId(studentID, "Student");

        if (student == null) {
            System.out.println("\nStudent with StudentID " + studentID + " is not found!");
            return;
        }

        if (isPasswordCorrect(student, password)) {
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
                        break;
                    case 3:
                        System.out.println("\nYou have logged out succesfully.");
                        return;
                    default:
                        System.out.println("\nInvalid input!\n");
                }
            }
        } else {
            System.out.println("\nWrong Password!");
            return;
        }
    }

    private void lecturerLogin() {

        System.out.println("\nEnter your StaffID: ");
        String staffID = scanner.nextLine();
        System.out.println("Enter your Password: ");
        String password = scanner.nextLine();

        Lecturer lecturer = (Lecturer) getUserWithId(staffID, "Lecturer");

        if (lecturer == null) {
            System.out.println("\nLecturer with StaffID " + staffID + " is not found!");
            return;
        }

        if (isPasswordCorrect(lecturer, password)) {
            System.out.println("\nWelcome " + lecturer.getName() + " " + lecturer.getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see your proffesion.");
                System.out.println("    Enter 2 to logout.");
                System.out.print("Enter action : ");

                int choice = -1;// switch will go to default if integer cannot be parsed
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (Exception ignore) {
                }

                switch (choice) {
                    case 1:
                        System.out.println(lecturer.getProfession());
                        break;
                    case 2:
                        System.out.println("\nYou have logged out succesfully.");
                        return;
                    default:
                        System.out.println("\nInvalid input!\n");
                }
            }
        } else {
            System.out.println("\nWrong Password!");
            return;
        }

    }

    private void advisorLogin() {

        System.out.println("\nEnter your StaffID: ");
        String staffID = scanner.nextLine();
        System.out.println("Enter your Password: ");
        String password = scanner.nextLine();

        Advisor advisor = (Advisor) getUserWithId(staffID, "Advisor");

        if (advisor == null) {
            System.out.println("\nAdvisor with StaffID " + staffID + " is not found!");
            return;
        }

        if (isPasswordCorrect(advisor, password)) {
            System.out.println("\nWelcome " + advisor.getName() + " " + advisor.getLastName());
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
                        System.out.println(advisor.getProfession());
                        break;
                    case 2:
                        draftApprovalProcess(advisor);
                        break;
                    case 3:
                        System.out.println("\nYou have logged out succesfully.");
                        return;
                    default:
                        System.out.println("\nInvalid input!\n");
                }
            }
        } else {
            System.out.println("\nWrong Password!");
            return;
        }

    }

    private void studentAffairsStaffLogin() {

        System.out.println("\nEnter your StaffID: ");
        String staffID = scanner.nextLine();
        System.out.println("Enter your Password: ");
        String password = scanner.nextLine();

        StudentAffairsStaff studentAffairsStaff = (StudentAffairsStaff) getUserWithId(staffID, "StudentAffairsStaff");

        if (studentAffairsStaff == null) {
            System.out.println("\nStudent affairs staff with StaffID " + staffID + " is not found!");
            return;
        }

        if (isPasswordCorrect(studentAffairsStaff, password)) {
            System.out.println("\nWelcome " + studentAffairsStaff.getName() + " " + studentAffairsStaff.getLastName());
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
                System.out.println("Sent for approval\n");
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

    private boolean isPasswordCorrect(User user, String password) {
        return user.getPassword().equals(password);
    }

    private User getUserWithId(String ID, String className) {
        switch (className) {
            case "Student":
                for (Student student : jsonFileManager.getStudents()) {
                    if (student.getStudentId().equals(ID)) {
                        return student;
                    }
                }
                break;
            case "Lecturer":
                for (Lecturer lecturer : jsonFileManager.getLecturers()) {
                    if (lecturer.getStaffID().equals(ID)) {
                        return lecturer;
                    }
                }
                break;
            case "Advisor":
                for (Advisor advisor : jsonFileManager.getAdvisors()) {
                    if (advisor.getStaffID().equals(ID)) {
                        return advisor;
                    }
                }
                break;
            case "StudentAffairs":
                for (StudentAffairsStaff studentAffairsStaff : jsonFileManager.getStudentAffairsStaffs()) {
                    if (studentAffairsStaff.getStaffID().equals(ID)) {
                        return studentAffairsStaff;
                    }
                }
                break;

            default:
                break;
        }
        return null;
    }

    private void draftApprovalProcess(Advisor advisor) {
        if (advisor.getDrafts().isEmpty()) {
            System.out.println("No drafts to approve currently");
            return;
        }
        System.out.println("\nPlease proceed with this draft:.\n");
        for (ArrayList<Course> draft : advisor.getDrafts()) {
            
            //Print all draft courses
            for (Course course : draft) {
                System.out.println(course.getCourseName() + " " + course.getCourseCode());
            }

            System.out.println("Do you approve this draft? yes/no");
            String isApprovedByAdvisor = scanner.nextLine();
            Student draftStudent = (Student) getUserWithId(draft.get(0).getStudent().getStudentId(), "Student");

            if (isApprovedByAdvisor.equals("yes")) {
                draftStudent.approveDraft(draft);
            } else if (isApprovedByAdvisor.equals("no")) {
                draftStudent.clearDraft();
            }
        }

        advisor.clearDrafts();
    }
}