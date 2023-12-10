import java.util.ArrayList;

/**
 * The Student class represents a student with information such as a transcript,
 * student ID, advisor, registered courses, draft, approved courses, and the current year.
 * It extends the User class and includes methods for managing courses, drafts, and year information.
 *
 * @author yigit_tuncer
 * @version 1.0
 * @since 2023-12-10
 */
public class Student extends User {

    private Transcript transcript;
    private String studentId;
    private Advisor advisor;
    private int year;
    private ArrayList<Course> registeredCourses = new ArrayList<>();
    private Draft draft = new Draft();
    private ArrayList<Course> approvedCourses = new ArrayList<>();

    /**
     * Retrieves the information for the student's transcript.
     *
     * @return A string representation of the transcript information.
     */
    public String getTranscriptInformation() {
        return this.transcript.toString();
    }

    /**
     * Retrieves a list of eligible courses for the student.
     * Eligible courses are those not already taken and whose prerequisites are completed.
     *
     * @param allCourses The list of all available courses.
     * @return The list of eligible courses.
     */
    public ArrayList<Course> getEligibleCourses(ArrayList<Course> allCourses) {
        ArrayList<Course> eligibleCourses = new ArrayList<>();
        for (Course course : allCourses) {
            if (!isRegisteredCourse(course) && course.isPrequisiteCompleted(studentId)) {
                eligibleCourses.add(course);
            }
        }
        return eligibleCourses;
    }

    /**
     * Checks if a course can be added to the draft.
     * A course can be added if it's not already registered, prerequisites are completed,
     * the draft has fewer than 5 courses, and the course is not already in the draft.
     *
     * @param course The course to check for draft eligibility.
     * @return True if the course can be added to the draft, false otherwise.
     */
    public boolean canAddToDraft(Course course) {
        if (isRegisteredCourse(course) || draft.getNumberOfClasses() >= 5 ||
                !course.isPrequisiteCompleted(studentId) || draft.hasCourse(course)) {
            return false;
        }
        return true;
    }

    /**
     * Sends the current draft to the advisor for review.
     *
     * @param studentAdvisor The advisor to whom the draft is sent.
     */
    public void sendDraftToAdvisor(Advisor studentAdvisor) {
        studentAdvisor.addDraft(draft);
    }

    /**
     * Approves the courses in the provided draft and adds them to the registered courses.
     *
     * @param draft The draft to be approved.
     */
    public void approveDraft(Draft draft) {
        registeredCourses.addAll(draft.getCourses());
        draft.clearDraft();
    }

    /**
     * Checks if a course is already registered by the student.
     *
     * @param course The course to check for registration status.
     * @return True if the course is already registered, false otherwise.
     */
    private boolean isRegisteredCourse(Course course) {
        for (Course registeredCourse : registeredCourses) {
            if (registeredCourse.getCourseCode().equals(course.getCourseCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the student's transcript.
     *
     * @return The student's transcript.
     */
    public Transcript getTranscript() {
        return transcript;
    }

    /**
     * Retrieves the student's ID.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Retrieves the student's advisor.
     *
     * @return The student's advisor.
     */
    public Advisor getAdvisor() {
        return advisor;
    }

    /**
     * Retrieves the list of courses registered by the student.
     *
     * @return The list of registered courses.
     */
    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    /**
     * Retrieves the current draft of the student.
     *
     * @return The student's draft.
     */
    public Draft getDraft() {
        return draft;
    }

    /**
     * Retrieves the list of courses approved by the student.
     *
     * @return The list of approved courses.
     */
    public ArrayList<Course> getApprovedCourses() {
        return approvedCourses;
    }

    /**
     * Retrieves the current year of the student.
     *
     * @return The current year of the student.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the student's transcript.
     *
     * @param transcript The new transcript to set.
     */
    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    /**
     * Sets the student's ID.
     *
     * @param studentId The new student ID to set.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Sets the student's advisor.
     *
     * @param advisor The new advisor to set.
     */
    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    /**
     * Sets the list of courses registered by the student.
     *
     * @param registeredCourses The new list of registered courses.
     */
    public void setRegisteredCourses(ArrayList<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    /**
     * Sets the list of courses approved by the student.
     *
     * @param approvedCourses The new list of approved courses.
     */
    public void setApprovedCourses(ArrayList<Course> approvedCourses) {
        this.approvedCourses = approvedCourses;
    }

    /**
     * Sets the current year of the student.
     *
     * @param year The new year to set.
     */
    public void setYear(int year) {
        this.year = year;
    }
}
