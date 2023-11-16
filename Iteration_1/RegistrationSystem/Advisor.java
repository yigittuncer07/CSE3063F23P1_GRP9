import java.util.ArrayList;

public class Advisor extends Lecturer {

    private ArrayList<ArrayList<Course>> drafts = new ArrayList<>();

    public Advisor() {
    }    

    public void addDraft(ArrayList<Course> draft){
        this.drafts.add(draft);
    }

    public ArrayList<ArrayList<Course>> getDrafts() {
        return drafts;
    }

    public void setDrafts(ArrayList<ArrayList<Course>> drafts) {
        this.drafts = drafts;
    }

}
