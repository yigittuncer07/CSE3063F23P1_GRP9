
public class GradeTest {
    public static void main(String[] args) {
        testConvertHundredToLetterGrade();
        testConvertHundredToGano();
    }

    private static Grade createTestGrade() {
        Grade grade = new Grade();
        grade.setOutOfHundred(75);
        grade.setGano(3.00);
        grade.setLetterGrade("BB");
        return grade;
    }

    private static void testConvertHundredToLetterGrade() {
        Grade grade = createTestGrade();
        grade.convertHundredToLetterGrade(grade.getOutOfHundred());
        if (grade.getLetterGrade().equals(grade.getLetterGrade())) {
            System.out.println("convertHundredToLetterGrade() works properly");
        } else {
            System.out.println("convertHundredToLetterGrade() malfunctions");
        }
    }

    private static void testConvertHundredToGano() {
        Grade grade = createTestGrade();

        if (grade.getGano() == grade.convertHundredToGano(grade.getOutOfHundred())) {
            System.out.println("convertHundredToGano() works properly");
        } else {
            System.out.println("convertHundredToGano() malfunctions");
        }
    }
}
