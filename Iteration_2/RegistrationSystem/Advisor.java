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

    
    public void approveDraft(Student student, Draft draft) {
        student.getRegisteredCourses().addAll(draft.getCourses());
        draft.clearDraft();
    }

}
