public class CourseTest {

	public static void main(String[] args) {
		
        createTestCourse();
	}
	private static Course createTestCourse() {
        Course course = new Course();
        Advisor advisor = new Advisor();
        Grade grade = new Grade();
        Student student = new Student();
        Lecturer courseLecturer = new Lecturer();
        course.setAdvisor(advisor);
        course.setCompleted(true);
        course.setCourseCode("CSE102");
        course.setCourseLecturer(courseLecturer);
        course.setCourseName("Data Structurer");
        course.setCredits(6);
        course.setGrade(grade);
        course.setPrequisiteCompleted(false);
        course.setStudent(student);
        if(course.getCourseName()=="Data Structurer") {
          System.out.println("Test passed");
        }
        else 
        	System.out.println("Test not passed");
          return course;
       
	
	}
}
