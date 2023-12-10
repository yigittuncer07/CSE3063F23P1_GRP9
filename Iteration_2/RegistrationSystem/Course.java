public class Course {
	private Student student = new Student();
	private Advisor advisor = new Advisor();
	private Lecturer courseLecturer = new Lecturer();
	private Grade grade = new Grade();
	private String courseName;
	private String courseCode;
	private String prequisite;
	private long credits;
	/*
	 * TODO: NEEDS IMPLEMENTATION
	 */
	private boolean prequisiteCompleted; 
	private boolean completed;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getPrequisite() {
		return prequisite;
	}

	public void setPrequisite(String prequisite) {
		this.prequisite = prequisite;
	}

	public Advisor getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Advisor advisor) {
		this.advisor = advisor;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Lecturer getCourseLecturer() {
		return courseLecturer;
	}

	public void setCourseLecturer(Lecturer courseLecturer) {
		this.courseLecturer = courseLecturer;
	}

	public long getCredits() {
		return credits;
	}

	public void setCredits(long credits) {
		this.credits = credits;
	}

	public boolean isPrequisiteCompleted(String studentID) {
		return true;
	}

	public void setPrequisiteCompleted(boolean prequisiteCompleted) {
		this.prequisiteCompleted = prequisiteCompleted;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
