import java.util.ArrayList;

public class Lecturer extends Staff {
    
    private String profession;
    private ArrayList<CourseInstance> courseInstances = new ArrayList<>();
    
    public Lecturer() {

    }

    public Lecturer(String profession){
        this.profession=profession;
    }

    public void findAllCourseInstances(ArrayList<Course> courses) {

        for (int i = 0 ; i < courses.size() ; i++) {

            if (courses.get(i).getCourseLecturer().getStaffID().equals(getStaffID())) {
                CourseInstance courseInstance = new CourseInstance();
                courseInstance.setCourseCode(courses.get(i).getCourseCode());
                courseInstance.setCourseName(courses.get(i).getCourseName());
                courseInstance.setCourseLecturerID(getStaffID());
                courseInstances.add(courseInstance);
            }
        }

    }

    public ArrayList<CourseInstance> getCourseInstances() {
        return courseInstances;
    }

    public void setCourseInstances(ArrayList<CourseInstance> courseInstances) {
        this.courseInstances = courseInstances;
    }

	public String getProfession() {
		return profession;
	}
    
	public void setProfession(String proffesion) {
		profession = proffesion;
	}
}