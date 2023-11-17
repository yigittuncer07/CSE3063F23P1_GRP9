public class TranscriptTest {

    public static void main(String[] args) {
        TranscriptTest transcriptTest = new TranscriptTest();
        transcriptTest.startTest();
    }

    Transcript transcript = new Transcript();

    public void startTest() {
        createTestDataBase();

        System.out.println("Test class for the class Transcript!\n");

        System.out.println("Transcript before adding a new class using the toString method:\n");
        System.out.println(transcript.toString() + "\n");

        System.out.println(
                "Transcript after adding a new class using the addCourse and updateGPA method and then printing it with the toString method:\n");
        Course course = new Course();
        course.setCourseName("Object-Oriented Software Design");
        course.setCourseCode("CSE3063");
        course.setCredits(6);
        course.setGrade(new Grade(36, 1.44, "DC"));
        transcript.addCourse(course);
        System.out.println(transcript.toString() + "\n");
    }

    public void createTestDataBase() {
        Course course1 = new Course();
        Course course2 = new Course();
        Course course3 = new Course();

        course1.setCourseName("Database Systems");
        course1.setCourseCode("CSE3055");
        course1.setCredits(7);
        course1.setGrade(new Grade(82, 3.28, "BA"));

        course2.setCourseName("Digital Logic Design");
        course2.setCourseCode("CSE3215");
        course2.setCredits(5);
        course2.setGrade(new Grade(37, 1.48, "DC"));

        course3.setCourseName("Operating Systems");
        course3.setCourseCode("CSE3033");
        course3.setCredits(6);
        course3.setGrade(new Grade(55, 2.2, "CB"));

        transcript.addCourse(course1);
        transcript.addCourse(course2);
        transcript.addCourse(course3);
    }

}
