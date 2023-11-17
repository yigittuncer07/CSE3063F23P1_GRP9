public class StudentTest {

    public static void main(String[] args) {
        testAddToDraft();
        testSendDraftToAdvisor();
    }

    private static void testAddToDraft() {
        Student student = createTestStudent();
        Course validCourse = new Course();

        // Test adding a valid course to draft
        if (student.addToDraft(validCourse)) {
            System.out.println("Test passed: Adding a valid course to draft");
        } else {
            System.out.println("Test failed: Adding a valid course to draft");
        }

        // Test adding the same course to draft again
        if (!student.canAddToDraft(validCourse)) {
            System.out.println("Test passed: Adding the same course to draft again correctly returns false");
        } else {
            System.out.println("Test failed: Adding the same course to draft again incorrectly returns true");
        }

        if (student.getDraftForCourses().size() == 1) {
            System.out.println("Test passed: Draft contains one course");
        } else {
            System.out.println("Test failed: Draft does not contain one course");
        }
    }

    private static void testSendDraftToAdvisor() {
        Student student = createTestStudent();
        Advisor advisor = createTestAdvisor();
        student.setAdvisor(advisor);

        Course validCourse = new Course();

        student.addToDraft(validCourse);
        student.sendDraftToAdvisor(advisor);

        // Test advisor's draft size after sending the draft
        if (advisor.getDrafts().size() == 1) {
            System.out.println("Test passed: Advisor received one draft");
        } else {
            System.out.println("Test failed: Advisor did not receive one draft");
        }
    }

    private static Student createTestStudent() {
        Student student = new Student();
        student.setStudentId("12345");
        student.setAdvisor(createTestAdvisor());
        return student;
    }

    private static Advisor createTestAdvisor() {
        return new Advisor();
    }
}
