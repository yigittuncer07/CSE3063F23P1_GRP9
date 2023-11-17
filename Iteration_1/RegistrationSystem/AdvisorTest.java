public class AdvisorTest {

    public static void main(String[] args) {
        testAddDraft();
        testClearDrafts();
    }

    private static void testAddDraft() {
        Student student = createTestStudent();
        Course validCourse = new Course();

        if (student.addToDraft(validCourse)) {
            System.out.println("Test passed: Adding a valid course to draft");
        } else {
            System.out.println("Test failed: Adding a valid course to draft");
        }
    }

    private static void testClearDrafts() {
        Student student = createTestStudent();
        Course validCourse = new Course();

        student.addToDraft(validCourse);

        student.clearDraft();

        if (student.getDraftForCourses().size() == 0) {
            System.out.println("Test passed: Clearing drafts");
        } else {
            System.out.println("Test failed: Clearing drafts");
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
