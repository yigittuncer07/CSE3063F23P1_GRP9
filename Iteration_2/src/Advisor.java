import java.util.ArrayList;

public class Advisor extends Lecturer {

    private ArrayList<Draft> drafts;
    private ArrayList<Student> students;

    public void addDraft(Draft draft) {
        this.drafts.add(draft);
    }

    public ArrayList<Draft> getDrafts() {
        return drafts;
    }

    public void clearDrafts(){
        drafts.clear();
    }

    public void setDrafts(ArrayList<Draft> drafts) {
        this.drafts = drafts;
    }

    public ArrayList<Student> getStudents(){
        return students;
    }
    
    public void addStudent(Student student){
        students.add(student);
    }

    //Adds approved courses to students registered courses
    public void processDraft(Student student) {
        Draft draft = student.getDraft();
        for (Course course : draft.getCourses()){
            if (course.isApproved()){
                student.addToApprovedCourses(course);
                draft.removeCourse(course.getCourseCode());
            }   
        }
    }
}
