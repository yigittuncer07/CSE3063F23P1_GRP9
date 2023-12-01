import java.util.ArrayList;

public class Student extends User {
    private Transcript transcript;
    private String studentId;
    private Advisor advisor;
    private ArrayList<Course> registeredCourses = new ArrayList<>();
    private Draft draft = new Draft();

    public String getTranscriptInformation(Transcript transcript) {
        return transcript.toString();
    }

    /*
     * For a course to be elligable, it must:
     * Not be already taken
     * prerequisites must be complete
     */
    public ArrayList<Course> getEligableCourses(ArrayList<Course> allCourses) {
        ArrayList<Course> eligableCourses = new ArrayList<>();
        for (Course course : allCourses) {
            if (!isRegisteredCourse(course) && course.isPrequisiteCompleted(studentId)) {
                eligableCourses.add(course);
            }
        }
        return eligableCourses;
    }

    public boolean canAddToDraft(Course course) {
        if (isRegisteredCourse(course)) {
            return false;
        }
        if (draft.getNumberOfClasses() >= 5 || !course.isPrequisiteCompleted(studentId) || draft.hasCourse(course)) {
            return false;
        }
        return true;
    }

    public void sendDraftToAdvisor(Advisor studentAdvisor) {
        studentAdvisor.addDraft(draft);
    }

    public void approveDraft(Draft draft) {
        registeredCourses.addAll(draft.getCourses());
        draft.clearDraft();
    }

    private boolean isRegisteredCourse(Course course) {
        for (Course registeredCourse : registeredCourses) {
            if (registeredCourse.getCourseCode().equals(course.getCourseCode())) {
                return true;
            }
        }
        return false;
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

    public Draft getDraft() {
        return draft;
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
