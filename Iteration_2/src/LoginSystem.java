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
                System.out.println("    Enter 1 to open approved courses that needs to be updated in the system.");
                System.out.println("    Enter 2 to update student information");
                System.out.println("    Enter 3 to open student informations");
                System.out.println("    Enter 4 to logout.");
                System.out.print("Enter action : ");

                int choice = intInput();
                
                switch (choice) {
                    case 1:
                        printApprovedCourses();
                        System.out.println();
                        break;
                    case 2:
                    System.out.println("\nEnter StudentID: ");
                    String studentID1 = scanner.nextLine();
                    Student student1= (Student) getUserWithId(studentID1, "Student");
                    if (student1 == null) {
                    System.out.println("\nStudent with StudentID " + studentID1 + " is not found!");
                     }
                    else {
                   System.out.println("\nEnter new StudentID: ");
                      String updateStudentID = scanner.nextLine();
                     student1.setStudentId(updateStudentID);;
                    }
                      break;
                    case 3:
                    System.out.println("\nEnter StudentID: ");
                    String studentID = scanner.nextLine();
                    Student student = (Student) getUserWithId(studentID, "Student");
                     if (student == null) {
                     System.out.println("\nStudent with StudentID " + studentID + " is not found!");
                     }
                    else {
                     System.out.println(student.getInfo());
                     System.out.println();
                    }
                    break;
                    case 4:
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

        System.out.println("\nWelcome to registration!");

        System.out.println(
                "\nCommands:\n-\"add\" coursecode to add to draft\n-\"remove\" coursecode to remove from draft\n-\"submit\" to submit for approval\n-\"exit\" to save draft and exit\n-\"clear\" to clear draft and exit\n-\"commands\" to see commands\n");

        ArrayList<Course> eligableCourses = student.getEligableCourses(jsonFileManager.getCourses());
        Advisor advisor = (Advisor) getUserWithId(student.getAdvisor().getStaffID(), "Advisor");
        Draft draft = student.getDraft();

        while (true) {

            if (eligableCourses.isEmpty()) {
                System.out.println("You cannot take any courses!\n");
                return;
            }
            if (!draft.isEmpty()) {
                System.out.print("\nCurrent draft: ");
                for (Course course : draft.getCourses()) {
                    System.out.print(course.getCourseCode() + " ");
                }
            }

            System.out.print("\nAvailable Courses: ");
            for (Course course : eligableCourses) {
                // Check if already added to draft
                if (!draft.getCourses().contains(course)) {
                    System.out.print(course.getCourseCode() + " ");
                }
            }

            System.out.print("\nenter a command: ");
            String studentInput = scanner.nextLine();

            if (studentInput.substring(0, Math.min(studentInput.length(), 3)).equals("add")) {

                String[] studentInputArray = studentInput.split("\\s+");
                String courseCode = studentInputArray[1];

                if (draft.hasCourse(courseCode)) {
                    System.out.println("Draft already has course!");
                } else {
                    boolean isCourseFound = false;
                    for (Course course : eligableCourses) {
                        if (course.getCourseCode().equals(courseCode)) {
                            course.setStudent(student);
                            draft.addCourse(course);
                            isCourseFound = true;
                        }
                    }

                    if (!isCourseFound) {
                        System.out.println("No such eligable course!");
                    }
                }

            } else if (studentInput.substring(0, Math.min(studentInput.length(), 6)).equals("remove")) {

                String[] studentInputArray = studentInput.split("\\s+");
                String courseCode = studentInputArray[1];

                if (draft.removeCourse(courseCode)) {
                    System.out.println("Course with course code  " + courseCode + " removed.");
                } else {
                    System.out.println("Course with course code " + courseCode + " is not in draft.");

                }

            } else if (studentInput.equals("submit")) {
                student.sendDraftToAdvisor(advisor);
                System.out.println("\nDraft sent for advisor approval\n");
                return;
            } else if (studentInput.equals("commands")) {
                System.out.println(
                        "\nCommands:\n-\"add\" coursecode to add to draft\n-\"remove\" coursecode to remove from draft\\n-\"submit\" to submit for approval\n-\"exit\" to save draft and exit\n-\"clear\" to clear draft and exit\n-\"commands\" to see commands\n");
            } else if (studentInput.equals("exit")) {
                System.out.println("\nDraft saved and exited.\n");
                return;
            } else if (studentInput.equals("clear")) {
                draft.clearDraft();
                System.out.println("\nDraft cleared.\n");
                return;
            } else {
                System.out.println("INVALID INPUT");
            }
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

    private double doubleInput() {
        boolean isDouble = false;
        double input = 0;
        while (!isDouble) {
            try {
                input = Double.parseDouble(scanner.nextLine());
                isDouble = true;
            } catch (Exception ignore) {
                System.out.println("Please input a double:");
            }
        }
        return input;
    }

     private void printApprovedCourses() {
      for(Course course : jsonFileManager.getCourses()){
        for (Student student : jsonFileManager.getStudents()) {
             if (course.isApproved() &&student.isApproved()) {
                System.out.println("\nApproved Courses for \n" + student.getName() + " " +student.getLastName());
                System.out.println("Course name to be added to the system =" +
                    course.getCourseName() + " Course Code= "
                    + course.getCourseCode());
                    }
                }
            }
        }    

    private void draftApprovalProcess(Advisor advisor) {
        Student student;
        if (advisor.getDrafts().isEmpty()) {
            System.out.println("No drafts to approve currently");
            return;
        }
        System.out.println("\nPlease proceed with this draft:.\n");
        for (Draft draft : advisor.getDrafts()) {
            student = (Student) getUserWithId(draft.getStudent().getStudentId(), "Student");
            System.out.println(student.getInfo() + "\nStudentID: " + student.getStudentId() + "\n" + "\n\nCourses:");

            // Print all draft courses
            for (Course course : draft.getCourses()) {
                System.out.println(course.getCourseName() + " " + course.getCourseCode());
                System.out.print("Do you approve of this course? (yes/no): ");
                String advisorInput = scanner.nextLine();

                if (advisorInput.equals("yes")) {
                    course.approve();
                    student.approve();
                } else if (advisorInput.equals("no")) {
                    // Dont do anything here
                } else {
                    System.out.println("Unkown input!");
                }
            }

            System.out.println("draft for student " + student.getStudentId() + " complete");
        }
        advisor.processDrafts();
        // advisor.clearDrafts();
    }

    // This is where a lecturer can control their courses
    private void controlYourCourses(Lecturer lecturer) {

        assignStudentsToCourses();

        while (true) {
            System.out.println("\nThese are the courses you give.");
            int i = 0;
            for (i = 0; i < lecturer.getCourseInstances().size(); i++) {
                System.out.println("\t" + i + " -> " + lecturer.getCourseInstances().get(i).getCourseCode() + ": "
                        + lecturer.getCourseInstances().get(i).getCourseName());
            }
            System.out.println("\t" + i + " -> cancel");
            System.out.println("Choose a number to perform an action to one of your courses:");

            int choice = intInput();

            if (choice == i) {
                return;
            } else if (choice >= 0 && choice < i) {
                System.out.println(
                        "You have chosen the course -> " + lecturer.getCourseInstances().get(choice).getCourseCode()
                                + ": " + lecturer.getCourseInstances().get(choice).getCourseName());
                System.out.println("\tEnter 1 to get the course information.");
                System.out.println("\tEnter 2 to grade students.");
                System.out.println("\tEnter 3 to pass/fail a student.");
                System.out.println("\tEnter 4 to choose another course or return.");
                System.out.println("Choose a number:");
                int actionChoice = intInput();

                switch (actionChoice) {
                    case 1:
                        getCourseInformationFromLecturer(lecturer, lecturer.getCourseInstances().get(choice));
                        break;
                    case 2:
                        gradeStudent(lecturer.getCourseInstances().get(choice));
                        break;
                    case 3:
                        passOrFailStudent(lecturer.getCourseInstances().get(choice));
                        break;
                    case 4:
                        System.out.println("\nYou have returned succesfully.");
                        return;
                    default:
                        System.out.println("\nInvalid input!\n");
                }
            } else {
                System.out.println("\nInvalid input!\n");
            }
        }
    }

    // This method is used for passing or failing a student.
    private void passOrFailStudent(CourseInstance course) {

        while (true) {
            System.out.println("\nCourse Name: " + course.getCourseName() + "\tCourse Code: " + course.getCourseCode());
            int i = 0;
            ArrayList<Student> tempStudents = course.getRegisteredStudents();
            for (i = 0; i < tempStudents.size(); i++) {
                System.out.println("\t" + i + " -> StudentID" + tempStudents.get(i).getStudentId() + " Name:"
                        + tempStudents.get(i).getName() + " " + tempStudents.get(i).getLastName());
            }

            System.out.println("\t" + i + " -> to return.");
            System.out.println("Choose a number:");
            int choice = intInput();
            if (choice == tempStudents.size()) {
                return;
            } else if (choice >= 0 && choice < tempStudents.size()) {
                Course markCourse = new Course();
                int index = 0;
                for (int j = 0; j < tempStudents.get(choice).getRegisteredCourses().size(); j++) {
                    if (tempStudents.get(choice).getRegisteredCourses().get(j).getCourseCode()
                            .equals(course.getCourseCode())) {
                        markCourse = tempStudents.get(choice).getRegisteredCourses().get(j);
                        index = j;
                    }
                }

                System.out.println("StudentID: " + tempStudents.get(choice).getStudentId() + " Name: "
                        + tempStudents.get(choice).getName() + " " + tempStudents.get(choice).getLastName());
                System.out.println("Enter \"pass\" to pass the student, or \"fail\" to fail the student");
                while (true) {
                    String passingCondition = scanner.nextLine();
                    if (passingCondition.equals("pass")) {
                        System.out.println(
                                "Enter \"confirm\" to confirm your decision, or \"cancel\" to cancel your decision");
                        while (true) {
                            String confirmation = scanner.nextLine();
                            if (confirmation.equals("confirm")) {
                                markCourse.setIsCompleted(true);
                                course.getRegisteredStudents().remove(choice);
                                System.out.println(
                                        "Your decision has been confirmed succesfully and is not being a part of this course anymore!");
                                break;
                            } else if (confirmation.equals("cancel")) {
                                System.out.println("Your decision has been canceled succesfully!");
                                break;
                            } else {
                                System.out.println("\nInvalid input!\n");
                            }
                        }
                        break;
                    } else if (passingCondition.equals("fail")) {
                        System.out.println(
                                "Enter \"confirm\" to confirm your decision, or \"cancel\" to cancel your decision");
                        while (true) {
                            String confirmation = scanner.nextLine();
                            if (confirmation.equals("confirm")) {
                                markCourse.setIsCompleted(false);
                                tempStudents.get(choice).getRegisteredCourses().remove(index);
                                course.getRegisteredStudents().remove(choice);
                                System.out.println(
                                        "Your decision has been confirmed succesfully and is not being a part of this course anymore!");
                                break;
                            } else if (confirmation.equals("cancel")) {
                                System.out.println("Your decision has been canceled succesfully!");
                                break;
                            } else {
                                System.out.println("\nInvalid input!\n");
                            }
                        }
                        break;
                    } else {
                        System.out.println("\nInvalid input!\n");
                    }
                }
            } else {
                System.out.println("\nInvalid input!\n");
            }
        }
    }

    // Method for the lecturer so it can grade students of a particular course.
    private void gradeStudent(CourseInstance course) {

        while (true) {
            System.out.println("\nCourse Name: " + course.getCourseName() + "\tCourse Code: " + course.getCourseCode());
            int i = 0;
            for (i = 0; i < course.getRegisteredStudents().size(); i++) {
                System.out.println("\t" + i + " -> StudentID" + course.getRegisteredStudents().get(i).getStudentId()
                        + " Name:" + course.getRegisteredStudents().get(i).getName() + " "
                        + course.getRegisteredStudents().get(i).getLastName());
            }

            System.out.println("\t" + i + " -> to return.");
            System.out.println("Choose a number:");
            int choice = intInput();
            if (choice == course.getRegisteredStudents().size()) {
                return;
            } else if (choice >= 0 && choice < course.getRegisteredStudents().size()) {
                int index = 0;

                for (int j = 0; j < course.getRegisteredStudents().get(choice).getRegisteredCourses().size(); j++) {
                    if (course.getRegisteredStudents().get(choice).getRegisteredCourses().get(j).getCourseCode()
                            .equals(course.getCourseCode())) {
                        index = j;
                    }
                }

                System.out.println("StudentID: " + course.getRegisteredStudents().get(choice).getStudentId() + " Name: "
                        + course.getRegisteredStudents().get(choice).getName() + " "
                        + course.getRegisteredStudents().get(choice).getLastName());
                System.out.println("Current grade: " + course.getRegisteredStudents().get(choice).getRegisteredCourses()
                        .get(index).getGrade().getOutOfHundred());

                while (true) {
                    System.out.println("Enter a grade with a value between 0.00-100.00: ");
                    double givenGrade = doubleInput();
                    if (givenGrade >= 0.0 && givenGrade <= 100.0) {
                        course.getRegisteredStudents().get(choice).getRegisteredCourses().get(index).getGrade()
                                .setOutOfHundred(givenGrade);
                        course.getRegisteredStudents().get(choice).getRegisteredCourses().get(index).getGrade()
                                .convertHundredToGano(givenGrade);
                        course.getRegisteredStudents().get(choice).getRegisteredCourses().get(index).getGrade()
                                .convertHundredToLetterGrade(givenGrade);
                        System.out.println("Grade updated succesfully! GANO and letter grade updated automatically!");
                        break;
                    } else {
                        System.out.println("\nInvalid input!\n");
                    }
                }

            } else {
                System.out.println("\nInvalid input!\n");
            }
        }

    }

    // Method for the lecturer so it can see information about the a course.
    // Including course info, lecturer info, and students that take the course.
    private void getCourseInformationFromLecturer(Lecturer lecturer, CourseInstance course) {
        System.out.println("\nCourse Name: " + course.getCourseName() + "\tCourse Code: " + course.getCourseCode());
        System.out.println("Course Lecturer -> \n\t\t   Name: " + lecturer.getName() + " " + lecturer.getLastName());
        System.out.println("                   Department: " + lecturer.getDepartment());
        System.out.println("                   Profession: " + lecturer.getProfession());
        System.out.println("                   Email: " + lecturer.getEmail());
        System.out.println("Course Students -> ");
        for (int i = 0; i < course.getRegisteredStudents().size(); i++) {
            System.out.println("                   Name: " + course.getRegisteredStudents().get(i).getName() + " "
                    + course.getRegisteredStudents().get(i).getLastName() + " ID: "
                    + course.getRegisteredStudents().get(i).getStudentId() + " Email: "
                    + course.getRegisteredStudents().get(i).getEmail());
        }
    }

    private void assignStudentsToCourses() {

        for (Lecturer lecturer : jsonFileManager.getLecturers()) {
            for (CourseInstance courseInstance : lecturer.getCourseInstances()) {
                for (Student student : jsonFileManager.getStudents()) {
                    for (Course course : student.getRegisteredCourses()) {
                        if (courseInstance.getCourseCode().equals(course.getCourseCode()) && !course.isCompleted() && !hasStudent(courseInstance.getRegisteredStudents(), student)) {
                            courseInstance.getRegisteredStudents().add(student);
                        }
                    }
                }
            }
        }

    }

    private boolean hasStudent(ArrayList<Student> students, Student student) {
        for (Student student1 : students ) {
            if (student1.getStudentId().equals(student.getStudentId())) {
                return true;
            }
        }
        return false;
    }

    private void init() {
        // This loop is used for teachers to find all students that attent to their
        // courses.
        assignStudentsToCourses();

        for (Student student : jsonFileManager.getStudents()) {

            // Sets the student of the draft
            student.getDraft().setStudent(student);

            // Add student to advisor group
            student.getAdvisor().addStudent(student);

            // Sets the student of the enrolled courses
            for (Course course : student.getRegisteredCourses()) {
                course.setStudent(student);
            }
        }
    }

}
