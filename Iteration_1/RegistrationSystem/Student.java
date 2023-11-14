import java.util.ArrayList;

class Student extends User {
    private Transcript transcript;
    private String studentId;
    private Advisor advisor;
    private ArrayList<Course> registeredCourses = new ArrayList<>();
    private ArrayList<Course> draftForCourses = new ArrayList<>();

    public String getTranscriptInformation(Transcript transcript) {
        return transcript.toString();
    }

    public boolean addToDraft(Course course) {
        if (draftForCourses.size() != 0){
            if (draftForCourses.contains(course) ) {
                return false;
            }
        }
        if (draftForCourses.size() >= 5 || !course.isPrequisiteCompleted()) {
            return false;
        }
        draftForCourses.add(course);
        return true;
    }

    public boolean sendDraftForAdvisorApproval() {
        if (advisor.registrationApproval(draftForCourses)) {
            registeredCourses.addAll(draftForCourses);
            draftForCourses.clear();
        }
        return false;
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

    public ArrayList<Course> getDraftForCoursess() {
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
