import java.util.ArrayList;

public class Advisor extends Lecturer {

    private ArrayList<Draft> drafts = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();

    public void addDraft(Draft draft) {
        this.drafts.add(draft);
    }

    public ArrayList<Draft> getDrafts() {
        return drafts;
    }

    public void setDrafts(ArrayList<Draft> drafts) {
        this.drafts = drafts;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void processDrafts() {
        for (Draft draft : drafts) {
            for (Course course : draft.getCourses()) {
                if (course.isApproved()) {
                    System.out.println("\t\tSTUDENT NAME IN DRAFT: " + draft.getStudent().getName());
                    draft.getStudent().addToRegisteredCourses(course);
                }
            }
        }
        drafts.clear();
    }
}
