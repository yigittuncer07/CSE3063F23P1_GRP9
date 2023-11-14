import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import json.JSONArray;
import json.JSONObject;
import json.parser.JSONParser;
import json.parser.ParseException;

public class JSONFileManager {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<StudentAffairsStaff> studentAffairsStaffs = new ArrayList<>();
    private ArrayList<Lecturer> lecturers = new ArrayList<>();
    private ArrayList<Advisor> advisors = new ArrayList<>();

    public JSONFileManager(){
        getAllAdvisorData();
        getAllLecturersData();
        getAllStudentAffairsStaffsData();
        getAllStudentsData();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<StudentAffairsStaff> getStudentAffairsStaffs() {
        return studentAffairsStaffs;
    }

    public void setStudentAffairsStaffs(ArrayList<StudentAffairsStaff> studentAffairsStaffs) {
        this.studentAffairsStaffs = studentAffairsStaffs;
    }

    
    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    
    public ArrayList<Advisor> getAdvisors() {
        return advisors;
    }

    public void setAdvisors(ArrayList<Advisor> advisors) {
        this.advisors = advisors;
    }



    private void getAllAdvisorData() {
        String folderPath = "database/advisors";

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    try {
                        FileReader fileReader = new FileReader(file);
                        JSONParser jsonParser = new JSONParser();
                        Object obj = jsonParser.parse(fileReader);
                        JSONObject jsonObject = (JSONObject) obj;

                        Advisor advisor = new Advisor();
                        advisor.setName((String) jsonObject.get("firstName"));
                        advisor.setLastName((String) jsonObject.get("lastName"));
                        advisor.setBirthDate((String) jsonObject.get("birthDate"));
                        advisor.setAddress((String) jsonObject.get("address"));
                        advisor.setSsn((String) jsonObject.get("ssn"));
                        advisor.setEmail((String) jsonObject.get("email"));
                        advisor.setPassword((String) jsonObject.get("password"));
                        advisor.setStaffID((String) jsonObject.get("advisorId"));

                        ArrayList<Course> registrations = new ArrayList<>();

                        JSONArray registrationsJSON = (JSONArray) jsonObject.get("registrations");
                        System.out.println("Registrations :");

                        for (Object courseObj : registrationsJSON) {
                            System.out.println("--------------------------------------------");

                            JSONObject courseJSON = (JSONObject) courseObj;

                            Course registration = new Course();

                            registration.setCourseName((String) courseJSON.get("courseName"));
                            registration.setCourseCode((String) courseJSON.get("courseCode"));

                            Lecturer lecturer = new Lecturer();
                            lecturer.setStaffID((String) courseJSON.get("courseLecturer"));
                            registration.setCourseLecturer(lecturer);

                            Student student = new Student();
                            student.setStudentId((String) courseJSON.get("studentId"));
                            student.setStudentId((String) courseJSON.get("studentName"));
                            registration.setStudent(student);

                            registration.setCredits((long) courseJSON.get("credits"));

                            Advisor courseAdvisor = new Advisor();
                            courseAdvisor.setStaffID((String) courseJSON.get("advisor"));

                            registration.setPrequisiteCompleted(false);

                            registrations.add(registration);

                            System.out.println("--------------------------------------------");
                        }

                        advisor.setRegistrations(registrations);

                        /*
                         * for (Course registration : advisor.getRegistrations()) {
                         * System.out.println("Name: " + registration.getCourseName() +
                         * ", Code: " + registration.getCourseCode() +
                         * ", Credits: " + registration.getCredits()
                         * );
                         * }
                         */

                        advisors.add(advisor);

                        fileReader.close();
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (Advisor advisor : advisors) {
                System.out.println("Name: " + advisor.getName() +
                        ", Last Name: " + advisor.getLastName() +
                        ", Birth Date: " + advisor.getBirthDate() +
                        ", Address: " + advisor.getAddress() +
                        ", SSN: " + advisor.getSsn() +
                        ", Email: " + advisor.getEmail() +
                        ", Password: " + advisor.getPassword() +
                        ", Student ID: " + advisor.getStaffID());

                for (Course registration : advisor.getRegistrations()) {
                    System.out.println("Name: " + registration.getCourseName() +
                            ", Code: " + registration.getCourseCode() +
                            ", Credits: " + registration.getCredits());
                }
            }

        } else {
            System.out.println("No file in database.");
        }

    }

    private void getAllStudentsData() {
        String folderPath = "database/students";

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    try {
                        FileReader fileReader = new FileReader(file);
                        JSONParser jsonParser = new JSONParser();
                        Object obj = jsonParser.parse(fileReader);
                        JSONObject jsonObject = (JSONObject) obj;

                        Student student = new Student();
                        student.setName((String) jsonObject.get("firstName"));
                        student.setLastName((String) jsonObject.get("lastName"));
                        student.setBirthDate((String) jsonObject.get("birthDate"));
                        student.setAddress((String) jsonObject.get("address"));
                        student.setSsn((String) jsonObject.get("ssn"));
                        student.setEmail((String) jsonObject.get("email"));
                        student.setPassword((String) jsonObject.get("password"));
                        student.setStudentId((String) jsonObject.get("studentId"));

                        ArrayList<Course> registeredCoursesList = new ArrayList<>();

                        JSONArray registeredCoursesJSON = (JSONArray) jsonObject.get("registeredCourses");
                        System.out.println("Registered Courses:");

                        for (Object courseObj : registeredCoursesJSON) {
                            System.out.println("--------------------------------------------");

                            JSONObject courseJSON = (JSONObject) courseObj;

                            Course registeredCourse = new Course();

                            registeredCourse.setCourseName((String) courseJSON.get("courseName"));
                            registeredCourse.setCourseCode((String) courseJSON.get("courseCode"));

                            Lecturer lecturer = new Lecturer();
                            lecturer.setStaffID((String) courseJSON.get("courseLecturer"));
                            registeredCourse.setCourseLecturer(lecturer);

                            registeredCourse.setCredits((long) courseJSON.get("credits"));

                            // ADD GRADE
                            Grade grade = new Grade();
                            grade.setOutOfHundred((long) courseJSON.get("grade"));

                            registeredCourse.setGrade(grade);

                            // ADD prerequisite

                            registeredCourse.setPrequisiteCompleted(true);
                            registeredCoursesList.add(registeredCourse);

                            System.out.println("--------------------------------------------");
                        }

                        Transcript transcript = new Transcript();

                        transcript.setCourses(registeredCoursesList);

                        student.setTranscript(transcript);

                        student.setRegisteredCourses(registeredCoursesList);

                        Advisor advisor = new Advisor();

                        advisor.setStaffID((String) jsonObject.get("advisorId"));

                        student.setAdvisor(advisor);

                        for (Course registeredCourse : student.getRegisteredCourses()) {
                            System.out.println("Name: " + registeredCourse.getCourseName() +
                                    ", Code: " + registeredCourse.getCourseCode() +
                                    ", Credits: " + registeredCourse.getCredits());
                        }

                        students.add(student);

                        fileReader.close();
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (Student student : students) {
                System.out.println("Name: " + student.getName() +
                        ", Last Name: " + student.getLastName() +
                        ", Birth Date: " + student.getBirthDate() +
                        ", Address: " + student.getAddress() +
                        ", SSN: " + student.getSsn() +
                        ", Email: " + student.getEmail() +
                        ", Password: " + student.getPassword() +
                        ", Student ID: " + student.getStudentId());
            }

        } else {
            System.out.println("No file in database.");
        }

    }



    private void getAllLecturersData() {
        String folderPath = "database/lecturers";

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    try {
                        FileReader fileReader = new FileReader(file);
                        JSONParser jsonParser = new JSONParser();
                        Object obj = jsonParser.parse(fileReader);
                        JSONObject jsonObject = (JSONObject) obj;

                        Lecturer lecturer = new Advisor();
                        lecturer.setName((String) jsonObject.get("firstName"));
                        lecturer.setLastName((String) jsonObject.get("lastName"));
                        lecturer.setBirthDate((String) jsonObject.get("birthDate"));
                        lecturer.setAddress((String) jsonObject.get("address"));
                        lecturer.setSsn((String) jsonObject.get("ssn"));
                        lecturer.setEmail((String) jsonObject.get("email"));
                        lecturer.setPassword((String) jsonObject.get("password"));
                        lecturer.setStaffID((String) jsonObject.get("lecturerId"));
                        lecturer.setDepartment((String) jsonObject.get("lecturerId"));
                        lecturer.setProffesion((String) jsonObject.get("profession"));
                        

                        lecturers.add(lecturer);

                        fileReader.close();
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (Lecturer lecturer : lecturers) {
                System.out.println("Name: " + lecturer.getName() +
                        ", Last Name: " + lecturer.getLastName() +
                        ", Birth Date: " + lecturer.getBirthDate() +
                        ", Address: " + lecturer.getAddress() +
                        ", SSN: " + lecturer.getSsn() +
                        ", Email: " + lecturer.getEmail() +
                        ", Password: " + lecturer.getPassword() +
                        ", Student ID: " + lecturer.getStaffID());

            }

        } else {
            System.out.println("No file in database.");
        }

    }

    private void getAllStudentAffairsStaffsData() {
        String folderPath = "database/studentAffairsStaffs";

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    try {
                        FileReader fileReader = new FileReader(file);
                        JSONParser jsonParser = new JSONParser();
                        Object obj = jsonParser.parse(fileReader);
                        JSONObject jsonObject = (JSONObject) obj;

                        StudentAffairsStaff studentAffairsStaff = new StudentAffairsStaff();
                        studentAffairsStaff.setName((String) jsonObject.get("firstName"));
                        studentAffairsStaff.setLastName((String) jsonObject.get("lastName"));
                        studentAffairsStaff.setBirthDate((String) jsonObject.get("birthDate"));
                        studentAffairsStaff.setAddress((String) jsonObject.get("address"));
                        studentAffairsStaff.setSsn((String) jsonObject.get("ssn"));
                        studentAffairsStaff.setEmail((String) jsonObject.get("email"));
                        studentAffairsStaff.setPassword((String) jsonObject.get("password"));
                        studentAffairsStaff.setStaffID((String) jsonObject.get("lecturerId"));
                        studentAffairsStaff.setDepartment((String) jsonObject.get("lecturerId"));
                        studentAffairsStaff.setWorkingField((String) jsonObject.get("workingField"));
                        

                        studentAffairsStaffs.add(studentAffairsStaff);

                        fileReader.close();
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (StudentAffairsStaff studentAffairsStaff : studentAffairsStaffs) {
                System.out.println("Name: " + studentAffairsStaff.getName() +
                        ", Last Name: " + studentAffairsStaff.getLastName() +
                        ", Birth Date: " + studentAffairsStaff.getBirthDate() +
                        ", Address: " + studentAffairsStaff.getAddress() +
                        ", SSN: " + studentAffairsStaff.getSsn() +
                        ", Email: " + studentAffairsStaff.getEmail() +
                        ", Password: " + studentAffairsStaff.getPassword() +
                        ", Student ID: " + studentAffairsStaff.getStaffID());

            }

        } else {
            System.out.println("No file in database.");
        }

    }

}
