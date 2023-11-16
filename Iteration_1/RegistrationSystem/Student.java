import java.util.ArrayList;

public class Student extends User {
    private Transcript transcript;
    private String studentId;
    private Advisor advisor;
    private ArrayList<Course> registeredCourses = new ArrayList<>();
    private ArrayList<Course> draftForCourses = new ArrayList<>();

    public String getTranscriptInformation(Transcript transcript) {
        return transcript.toString();
    }

    public boolean addToDraft(Course course) {
        for (Course course1 : registeredCourses) {
            System.out.println(course1.getCourseCode());
        }
        if (draftForCourses.size() != 0) {
            if (draftForCourses.contains(course)) {
                return false;
            }
        }
        for (Course course1: registeredCourses){
            if (course1.getCourseCode().equals(course.getCourseCode())){
                return false;
            }
        }
        if (draftForCourses.size() >= 5 || !course.isPrequisiteCompleted()) {
            return false;
        }
        draftForCourses.add(course);
        return true;
    }

    public void sendDraftToAdvisor(){
        advisor.addDraft(draftForCourses);
    }

    public void approveDraft(){
        registeredCourses.addAll(draftForCourses);
        clearDraft();
    }

    public void clearDraft() {
        draftForCourses.clear();
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public String getStudentId() {
        return studentId;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public ArrayList<Course> getDraftForCourses() {
        return draftForCourses;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public void setRegisteredCourses(ArrayList<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }
}
