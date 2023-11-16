import java.util.ArrayList;

public class Advisor extends Lecturer {

    private ArrayList<ArrayList<Course>> drafts = new ArrayList<>();

    public Advisor() {
    }

    public void addDraft(ArrayList<Course> draft) {
        for (ArrayList<Course> list : drafts) {
            for (Course course : list) {
                course.getCourseCode();
            }
        }
        this.drafts.add(draft);
        System.out.println(" _ __ _ _ ");
        for (ArrayList<Course> list : drafts) {
            for (Course course : list) {
                course.getCourseCode();
            }
        }
    }

    public ArrayList<ArrayList<Course>> getDrafts() {
        return drafts;
    }

    public void setDrafts(ArrayList<ArrayList<Course>> drafts) {
        this.drafts = drafts;
    }

}
