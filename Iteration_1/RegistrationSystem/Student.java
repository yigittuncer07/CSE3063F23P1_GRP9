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

    private boolean isRegisteredCourse(Course course) {
        for (Course registeredCourse : registeredCourses) {
            if (registeredCourse.getCourseCode().equals(course.getCourseCode())) {
                return true;
            }
        }
        return false;
    }

    public boolean canAddToDraft(Course course) {
        if (draftForCourses.size() != 0) {
            if (draftForCourses.contains(course)) {
                return false;
            }
        }
        for (Course course1 : registeredCourses) {
            if (course1.getCourseCode().equals(course.getCourseCode())) {
                return false;
            }
        }
        if (draftForCourses.size() >= 5 || !course.isPrequisiteCompleted(studentId)) {
            return false;
        }
        return true;
    }

    public void addToDraft(Course course) {
        this.draftForCourses.add(course);
    }

    public void sendDraftToAdvisor(Advisor studentAdvisor) {
        studentAdvisor.addDraft(draftForCourses);
    }

    public void approveDraft(ArrayList<Course> draft) {
        registeredCourses.addAll(draft);
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
