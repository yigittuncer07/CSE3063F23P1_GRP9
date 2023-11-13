import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONFileManager {
    ArrayList<Student> students = new ArrayList<>();
    // ArrayList<StudentAffairsStaff> studentAffairsStaffs = new ArrayList<>();
    // ArrayList<Lecturer> lecturers = new ArrayList<>();
    ArrayList<Advisor> advisors = new ArrayList<>();

    public void getAllStudentsData() {
        String folderPath = "database/students";

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                // Sadece JSON dosyalarını işle
                if (file.isFile() && file.getName().endsWith(".json")) {
                    try {
                        // JSON dosyasını oku
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
                                    ", Credits: " + registeredCourse.getCredits() 
                                   );
                        }


                        

                        


                        // Student objesini ArrayList'e ekle
                        students.add(student);

                        // Dosyayı kapat
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

}
