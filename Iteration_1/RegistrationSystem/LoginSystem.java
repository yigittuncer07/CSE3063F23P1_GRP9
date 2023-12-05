import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoginSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final JSONFileManager jsonFileManager = new JSONFileManager();
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private int incorrectAttemptsStudent = 0;
    private int incorrectAttemptsLecturer = 0;
    private int incorrectAttemptsAdvisor = 0;
    private int incorrectAttemptsStaff = 0;
    private final int maxAttempts = 3;
    private final int timeoutSeconds = 20;

    public void startSystem() {

        init();

        executorService.schedule(() -> {
            System.out.println("Program will exit after 100 seconds!");
        }, 100, TimeUnit.SECONDS);

        executorService.schedule(() -> {
            System.out.println("The system has timed out, please log in again.");
            System.exit(0);
        }, 200, TimeUnit.SECONDS);

        connectStudentsWithCourseInstances(jsonFileManager);

        while (true) {
            System.out.println(
                    "Welcome to the LOGIN SYSTEM:\n\tEnter 1 if you are a Student.\n\tEnter 2 if you are a Lecturer.\n\tEnter 3 if you are an Advisor.\n\tEnter 4 if you are a Student Affairs Staff.\n\tEnter 5 exit.\nEnter action : ");

            int choice = intInput();

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

        if (student.getPassword().equals(password)) {

            System.out.println("\nWelcome " + student.getName() + " " + student.getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see transcript.");
                System.out.println("    Enter 2 to register to a course.");
                System.out.println("    Enter 3 to logout.");
                System.out.print("Enter action : ");

                int choice = intInput();

                switch (choice) {
                    case 1:
                        System.out.println("\nThis is your transcript.\n");
                        System.out.println(student.getTranscript());
                        break;
                    case 2:
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
            incorrectAttemptsStudent++;
            System.out.println("\nWrong Password!");

            if (incorrectAttemptsStudent == maxAttempts) {
                System.out.println("Too many incorrect attempts. Account locked for " + timeoutSeconds + " seconds.");
                try {
                    Thread.sleep(timeoutSeconds * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Reset attempts after timeout
                incorrectAttemptsStudent = 0;
            }
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

        if (lecturer.getPassword().equals(password)) {

            System.out.println("\nWelcome " + lecturer.getName() + " " + lecturer.getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see your proffesion.");
                System.out.println("    Enter 2 to see and control the courses you teach.");
                System.out.println("    Enter 3 to logout.");
                System.out.print("Enter action : ");

                int choice = intInput();

                switch (choice) {
                    case 1:
                        System.out.println(lecturer.getProfession());
                        break;
                    case 2:
                        controlYourCourses(lecturer);
                        break;
                    case 3:
                        System.out.println("\nYou have logged out succesfully.");
                        return;
                    default:
                        System.out.println("\nInvalid input!\n");
                }
            }
        } else {
            incorrectAttemptsLecturer++;
            System.out.println("\nWrong Password!");

            if (incorrectAttemptsLecturer == maxAttempts) {
                System.out.println("Too many incorrect attempts. Account locked for " + timeoutSeconds + " seconds.");
                try {
                    Thread.sleep(timeoutSeconds * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Reset attempts after timeout
                incorrectAttemptsLecturer = 0;
            }
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


        if (advisor.getPassword().equals(password)) {

            System.out.println("\nWelcome " + advisor.getName() + " " + advisor.getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see your proffesion.");
                System.out.println("    Enter 2 to approve student registrations.");
                System.out.println("    Enter 3 to logout.");
                System.out.print("Enter action : ");

                int choice = intInput();

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
            incorrectAttemptsAdvisor++;
            System.out.println("\nWrong Password!");

            if (incorrectAttemptsAdvisor == maxAttempts) {
                System.out.println("Too many incorrect attempts. Account locked for " + timeoutSeconds + " seconds.");
                try {
                    Thread.sleep(timeoutSeconds * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Reset attempts after timeout
                incorrectAttemptsAdvisor = 0;
            }
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


        if (studentAffairsStaff.getPassword().equals(password)) {

            System.out.println("\nWelcome " + studentAffairsStaff.getName() + " " + studentAffairsStaff.getLastName());
            while (true) {
                System.out.println("Choose your action you will like to perform.");
                System.out.println("    Enter 1 to see the information that needs to be updated in the system.");
                System.out.println("    Enter 2 to logout.");
                System.out.print("Enter action : ");

                int choice = intInput();

                switch (choice) {
                    case 1:
                        System.out.println(studentAffairsStaff.getWorkingField());
                        printApprovedCourses();
                        break;
                    case 2:
                        System.out.println("\nYou have logged out succesfully.");
                        return;
                    default:
                        System.out.println("\nInvalid input!\n");
                }
            }
        } else {
            incorrectAttemptsStaff++;
            System.out.println("\nWrong Password!");

            if (incorrectAttemptsStaff == maxAttempts) {
                System.out.println("Too many incorrect attempts. Account locked for " + timeoutSeconds + " seconds.");
                try {
                    Thread.sleep(timeoutSeconds * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Reset attempts after timeout
                incorrectAttemptsStaff = 0;
            }
            return;
        }
    }

    private void registrationProcess(Student student) {
        System.out.println("\nWelcome to the registration.\n");
        ArrayList<Course> eligableCourses = student.getEligableCourses(jsonFileManager.getCourses());
        Advisor advisor = (Advisor) getUserWithId(student.getAdvisor().getStaffID(), "Advisor");
        while (true) {
            System.out.println(
                    "Enter a course code to register to said course, enter \"submit\" to submit for approval and \"exit\" to scrap draft and exit:");

            if (eligableCourses.isEmpty()) {
                System.out.println("You cannot take any courses!\n");
                return;
            }
            for (Course course : eligableCourses) {
                System.out.println(course.getCourseCode() + " : " + course.getCourseName());
            }

            String input = scanner.nextLine();

            if (input.equals("submit")) {
                student.sendDraftToAdvisor(advisor);
                System.out.println("Sent for approval\n");
                return;

            } else if (input.equals("exit")) {
                System.out.println("\nDraft cleared and exited.\n");
                student.getDraft().clearDraft();
                return;
            } else {
                boolean courseFound = false;
                for (Course course : eligableCourses) {
                    if (input.equals(course.getCourseCode())) {
                        courseFound = true;
                        boolean canAddToDraft = student.canAddToDraft(course);
                        if (canAddToDraft) {
                            course.setStudent(student);
                            student.getDraft().addClass(course);
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
            for (Course course : student.getDraft().getCourses()) {
                System.out.print(course.getCourseCode() + " ");
            }
            System.out.println("\n ");
        }
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
            case "StudentAffairsStaff":
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

    private int intInput() {
        boolean isInt = false;
        int input = 0;
        while (!isInt) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                isInt = true;
            } catch (Exception ignore) {
                System.out.println("Please input a number:");
            }
        }
        return input;
    }
    private void printApprovedCourses() {
        System.out.println("\nApproved Courses:");
        for (Student student : jsonFileManager.getStudents()) {
            System.out.println(student.getName() + " " + student.getLastName() + "'s Approved Courses:");
            for (Course course : student.getApprovedCourses()) {
                System.out.println(course.getCourseName() + " " + course.getCourseCode());
            }
            System.out.println();
        }
    }
    

    private void draftApprovalProcess(Advisor advisor) {
        if (advisor.getDrafts().isEmpty()) {
            System.out.println("No drafts to approve currently");
            return;
        }
        System.out.println("\nPlease proceed with this draft:.\n");
        for (Draft draft : advisor.getDrafts()) {

            System.out.println("Student Info:\nStudentID: " + draft.get(0).getStudent().getStudentId() + "\n"
                    + draft.get(0).getStudent().getInfo() + "\n\nCourses:");

            // Print all draft courses
            for (Course course : draft.getCourses()) {
                System.out.println(course.getCourseName() + " " + course.getCourseCode());
            }

            System.out.println("\nDo you approve this draft? yes/no");
            String isApprovedByAdvisor = scanner.nextLine();

            if (isApprovedByAdvisor.equals("yes")) {

                ((Student) getUserWithId(draft.getStudent().getStudentId(), "Student")).approveDraft(draft);



            } else if (isApprovedByAdvisor.equals("no")) {
                draft.clearDraft();
            }
        }

        advisor.clearDrafts();
    }

    private void init(){
        for (Student student: jsonFileManager.getStudents()){
            student.getDraft().setStudent(student);
        }
    }

    // This method is used for teachers to find all students that attent to their courses.
    private void connectStudentsWithCourseInstances(JSONFileManager data) {

        for (int i = 0; i < data.getLecturers().size(); i++) {
            for (int j = 0; j < data.getLecturers().get(i).getCourseInstances().size(); j++) {
                for (int k = 0; k < data.getStudents().size(); k++) {
                    for (int m = 0; m < data.getStudents().get(k).getRegisteredCourses().size() ; m++) {
                        if (data.getLecturers().get(i).getCourseInstances().get(j).getCourseCode().equals(data.getStudents().get(k).getRegisteredCourses().get(m).getCourseCode())) {
                            data.getLecturers().get(i).getCourseInstances().get(j).getRegisteredStudents().add(data.getStudents().get(k));
                        }
                    }
                }
            }
        }

    }

    // This is where a lecturer can control their courses
    private void controlYourCourses(Lecturer lecturer) {

        while (true) {
            System.out.println("\nThese are the courses you give.");
            int i = 0;
            for (i = 0 ; i < lecturer.getCourseInstances().size() ; i++) {
                System.out.println("\t" + i + " -> " + lecturer.getCourseInstances().get(i).getCourseCode() + ": " + lecturer.getCourseInstances().get(i).getCourseName());
            }
            System.out.println("\t" + i + " -> cancel");
            System.out.println("Choose a number to perform an action to one of your courses");

            int choice = scanner.nextInt();

            if (choice == i) {
                return;
            }
            else if (choice >= 0 && choice < i) {
                System.out.println("You have chosen the course -> " + lecturer.getCourseInstances().get(choice).getCourseCode() + ": " + lecturer.getCourseInstances().get(choice).getCourseName());
                System.out.println("\tEnter 1 to get the course information.");
                System.out.println("\tEnter 2 to grade students.");
                System.out.println("\tEnter 3 to pass/fail a student.");
                System.out.println("\tEnter 4 to choose another course");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("test1");
                        break;
                    case 2:
                        System.out.println("test2");
                        break;
                    case 3:
                        System.out.println("test3");
                        break;
                    case 4:
                        System.out.println("\nYou have returned succesfully.");
                        return;
                    default:
                        System.out.println("\nInvalid input!\n");
                }

            }
            else {
                System.out.println("\nInvalid input!\n");
            }
        }
    }

}