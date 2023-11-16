import java.util.ArrayList;

public class Transcript {
    private ArrayList<Course> courses;
    private Grade grade;
    private double GPA;

    public Transcript() {
        this.courses = new ArrayList<>();
        this.GPA = 0.0;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
        updateGPA();
    }

    public double getGPA() {
        return GPA;
    }

    private void updateGPA() {
        double totalCredits = 0.0;
        double weightedSum = 0.0;

        for (Course course : courses) {
            totalCredits += course.getCredits();
            weightedSum += course.getCredits() * course.getGrade().getOutOfHundred();
        }

        if (totalCredits > 0) {
            GPA = weightedSum / totalCredits;
        } else {
            GPA = 0.0;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Transcript: \n");

        for (Course course : courses) {
            result.append("Course Code: ").append(course.getCourseCode())
                    .append(", Course Name: ").append(course.getCourseName())
                    .append(", Credit: ").append(course.getCredits())
                    .append(", Letter Grade: ").append(course.grade.getLetterGrade())
                    .append("\n");
        }

        result.append("GPA: ").append(GPA);
        return result.toString();
    }
}
