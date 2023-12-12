import java.util.ArrayList;

public class Student extends User {
    private String studentId;
    private Advisor advisor;
    private Transcript transcript;
    private Draft draft = new Draft();
    private long year;

    private ArrayList<Course> registeredCourses = new ArrayList<>();
    // private ArrayList<Course> approvedCourses = new ArrayList<>();

    /*
     * For a course to be elligable, it must:
     * - not be already taken
     * - prerequisites must be complete
     * - must have required year complete
     * 
     */
    public ArrayList<Course> getEligableCourses(ArrayList<Course> allCourses) {
        ArrayList<Course> eligableCourses = new ArrayList<>();
        for (Course course : allCourses) {
            if (!isRegisteredCourse(course) && course.isPrerequisitesCompleted(course.getPrequisite()) && (course.getYear() <= year)) {
                eligableCourses.add(course);
            }
        }
        return eligableCourses;
    }

    public void sendDraftToAdvisor(Advisor studentAdvisor) {
        studentAdvisor.addDraft(draft);
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

    // public ArrayList<Course> getApprovedCourses() {
    // return approvedCourses;
    // }

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

    public void addToRegisteredCourses(Course course) {
        this.registeredCourses.add(course);
    }

    // public void setApprovedCourses(ArrayList<Course> approvedCourses) {
    // this.approvedCourses = approvedCourses;
    // }

    // public void addToApprovedCourses(Course course) {
    // this.approvedCourses.add(course);
    // }

    public String getTranscriptInformation(Transcript transcript) {
        return transcript.toString();
    }

}
