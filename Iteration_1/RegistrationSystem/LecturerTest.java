public class LecturerTest {

    public static void main(String[] args) {
        testGetProffesion();
        testSetProffesion();
        testDefaultConstructor();
    }

    private static void testGetProffesion() {
        Lecturer lecturer = new Lecturer();
        lecturer.setProffesion("Computer Science");

        if ("Computer Science".equals(lecturer.getProffesion())) {
            System.out.println("Test passed: Getting the correct profession");
        } else {
            System.out.println("Test failed: Getting the incorrect profession");
        }
    }

    private static void testSetProffesion() {
        Lecturer lecturer = new Lecturer();

        lecturer.setProffesion("Mathematics");
        if ("Mathematics".equals(lecturer.getProffesion())) {
            System.out.println("Test passed: Setting and getting the correct profession");
        } else {
            System.out.println("Test failed: Setting and getting the incorrect profession");
        }
    }

    private static void testDefaultConstructor() {
        Lecturer lecturer = new Lecturer();

        if (lecturer.getProffesion() == null) {
            System.out.println("Test passed: Default constructor initializes profession as null");
        } else {
            System.out.println("Test failed: Default constructor does not initialize profession as null");
        }
    }
}
