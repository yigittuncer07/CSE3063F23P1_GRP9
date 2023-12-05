import java.util.ArrayList;

public class Draft {
    private ArrayList<Course> courses = new ArrayList<>();
    private Student student;
    
    public boolean hasCourse(Course course) {
        for (Course draftCourse : courses) {
            if (draftCourse.getCourseCode().equals(course.getCourseCode())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public boolean isEmpty(){
        return courses.isEmpty();
    }
    public int getNumberOfClasses(){
        return courses.size();
    }

    public void clearDraft(){
        courses.clear();
    }

    public void addClass(Course course){
        courses.add(course);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
