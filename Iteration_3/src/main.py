from classes.advisor import Advisor
from classes.course import Course
from classes.draft import Draft
from classes.grade import Grade
from classes.lecturer import Lecturer
from classes.staff import Staff
from classes.student_affairs_staff import Student_Affairs_Staff
from classes.student import Student
from classes.transcript import Transcript
import json
import logging
import time
import os

# GLOBAL VARIABLES
users = []
courses = []
incorrect_attempts = 0

# LOGGING CONFIG
logger = logging.getLogger()
logger.setLevel(logging.DEBUG)
file_handler = logging.FileHandler("logfile.log")
formatter = logging.Formatter("%(asctime)s - %(levelname)s - %(message)s")
file_handler.setFormatter(formatter)
logger.addHandler(file_handler)


def system_exit():
    save_users_to_json()
    save_courses_to_json()
    exit()


def print_title(message):
    print("\n\033[1;94m" + message + "\033[0m")


def print_info(message):
    print("\n\033[92m" + message + "\033[0m")


def print_error(message):
    print("\n\033[91m" + message + "\033[0m")


def print_commands(message):
    print("\n\033[93m" + message + "\033[0m")


def save_users_to_json():
    logger.info("save students to file called")
    for item in users:
        item.to_json_file()


def save_courses_to_json():
    logger.info("save courses to file called")
    for item in courses:
        item.to_json_file()


def get_user_with_id(id):
    for item in users:
        if item.get_user_id() == id:
            return item
    return None



def get_users_from_json():
    print("get all from json called")

    #Students JSON
    for filename in os.listdir('database/students'):
        if filename.endswith('.json'):
            file_path = os.path.join('database/students', filename)

            with open(file_path, 'r') as file:
                data = json.load(file)

                gradeJSON = data["grades"]
                grade_array = []


                for grade in gradeJSON:
                    grade_object = Grade(course_code=grade["courseCode"],
                                  number_grade=grade["numberGrade"],
                                  letter_grade=grade["letterGrade"],
                                  is_passed=grade["isPassed"])
                    grade_array.append(grade_object)

                #draftJSON = data["draft"]
                #draft_course_array = []

                #for draft_course in draftJSON:
                #    draft_course_object = Course(course_code=draft_course)
                #    draft_course_array.append(draft_course_object)




                student = Student(user_id=data["studentId"],  
                                advisor= Advisor(user_id=data["advisorId"]),
                                name=data["Name"],
                                password=data["password"],
                                year= data["year"],
                                email= data["email"],
                                transcript = Transcript(
                                    gano=data["gano"],
                                    grades= grade_array
                                ),

                                #draft = Draft(
                                #    student=Student(user_id= data["studentId"],
                                #            courses= draft_course_array)
                                #)
                )

                users.append(student)

    #Lecturer JSON
    for filename in os.listdir('database/lecturers'):
        if filename.endswith('.json'):
            file_path = os.path.join('database/lecturers', filename)

            with open(file_path, 'r') as file:
                data = json.load(file)

                lecturer = Lecturer(user_id=data["lecturerId"],
                                    field=data["field"],
                                    name=data["Name"],
                                    password=data["password"],
                                    department=data["department"],
                                    email=data["email"]
                )

                users.append(lecturer)

    #StdAffStaff JSON
    for filename in os.listdir('database/student_affairs_staff'):
        if filename.endswith('.json'):
            file_path = os.path.join('database/student_affairs_staff', filename)

            with open(file_path, 'r') as file:
                data = json.load(file)

                studentJSON = data["students"]
                student_array = []


                for std in studentJSON:
                    student_object = Student(user_id=std)
                    
                    student_array.append(student_object)


                


                student_affair_staff = Student_Affairs_Staff(user_id=data["lecturerId"],
                                    name=data["Name"],
                                    password=data["password"],
                                    department=data["department"],
                                    email=data["email"],
                                    students= student_array
                )

                users.append(student_affair_staff)

#Advisor JSON
for filename in os.listdir('database/advisors'):
        if filename.endswith('.json'):
            file_path = os.path.join('database/advisors', filename)

            with open(file_path, 'r') as file:
                data = json.load(file)

                studentJSON = data["students"]
                student_array = []


                for std in studentJSON:
                    student_object = Student(user_id=std)
                    
                    student_array.append(student_object)

                draftJSON = data["drafts"]
                draft_array = []

                for drft in draftJSON:
                    draft_courseJSON = drft["courseCode"]
                    draft_course_array = []


                    for dcj in draft_courseJSON:
                        draft_course_object = Course(course_code=dcj)
                        
                        draft_course_array.append(draft_course_object)
                    
                    draft_object = Draft(student=drft["studentId"],courses=draft_course_array)
                    draft_array.append(draft_object)


                advisor = Advisor(user_id=data["lecturerId"],
                                    field=data["field"],
                                    name=data["Name"],
                                    password=data["password"],
                                    department=data["department"],
                                    email=data["email"],
                                    students= student_array,
                                    drafts=draft_array

                )

                users.append(advisor)


def get_courses_from_json():
    print("get all courses from json called")
    for filename in os.listdir('database/courses'):
        if filename.endswith('.json'):
            file_path = os.path.join('database/courses', filename)

            with open(file_path, 'r') as file:
                data = json.load(file)

                prerequisitesJSON = data["prerequisite"]
                prerequisites_array = []


                for prerequisite in prerequisitesJSON:
                    prerequisite_course = Course(course_code=prerequisite)
                    prerequisites_array.append(prerequisite_course)

                studentsJSON = data["students"]
                student_array = []
                for student in studentsJSON:
                    course_student = Student(user_id=student)
                    student_array.append(course_student)




                course = Course(
                    course_code=data["courseCode"],
                    course_name=data["courseName"],
                    
                    lecturer=Lecturer(
                        user_id=data["courseLecturer"]
                    ),
                    
                    students= student_array,
                    prerequisites=prerequisites_array,
                    credits=data["credits"],
                    year=data["year"],
                )

                courses.append(course)





def student_login():
    print_title("STUDENT LOGIN")
    student_id_input = input("enter student id: ")
    student = get_user_with_id(student_id_input)

    while student == None or not isinstance(student, Student):
        print_error(f"no student with studentID  {student_id_input}")
        student_id_input = input("enter student id: ")
        if student_id_input == "":
            return
        student = get_user_with_id(student_id_input)

    student_password_input = input("enter password: ")
    global incorrect_attempts
    if student.get_password() != student_password_input:
        print_error("Wrong Password!")
        incorrect_attempts += 1
        if incorrect_attempts == 3:
            print_error(
                "Maximum password attempts reached. Please wait for 10 seconds."
            )
            time.sleep(10)  # Pause the program for 10 seconds
            incorrect_attempts = 0  # Reset the incorrect attempts counter

        if incorrect_attempts <= 3:
            return

    print_title("STUDENT INTERFACE")
    print_info(f"Welcome {student.get_name()}")

    while True:
        print_commands("1-> see transcript\n2-> register to a course\n3-> exit")
        user_input = input("==> ")

        while not user_input in ["1", "2", "3"]:
            print_error("invalid input!")
            print_commands("1-> see transcript\n2-> register to a course\n3-> exit")
            user_input = input("==> ")

        if user_input == "1":
            print_title("TRANSCRIPT")
            if student.get_transcript() == None:
                print_error("transcript not found! contact student affairs")
            else:
                print_info(student.get_transcript().to_string())
        elif user_input == "2":
            print_title("COURSE REGISTRATION")

            eligable_courses = student.get_eligible_courses(courses)
            registered_courses = student.get_registered_courses(courses)
            
            for course in registered_courses:
                if course in eligable_courses:
                    eligable_courses.remove(course)

            draft = student.get_draft()
            advisor = student.get_advisor()

            while True:
                print_info("Eligable Courses:")
                if len(eligable_courses) == 0:
                    print("no eligable courses")
                else:
                    for course in eligable_courses:
                        print(course.get_course_code() + " " + course.get_course_name())

                print_info("Current Draft:")
                for course in draft.get_courses():
                    print(course.get_course_code())

                print_commands(
                    'add "course" -> add to draft\nremove "course" -> remove from draft\nsubmit -> send draft to advisor for approval\nexit -> save draft and exit'
                )
                user_input = input("==> ")
                user_input = user_input.split()
                if user_input[0] == "add":
                    course_found = False
                    for course in eligable_courses:
                        if course.get_course_code() == user_input[1]:
                            if draft.add_to_draft(course):
                                print_info("course added")
                                eligable_courses.remove(course)
                            else:
                                print_error("course couldnt be added!")
                            course_found = True

                    if not course_found:
                        print_error("no such course!")

                elif user_input[0] == "remove":
                    course_found = False

                    for course in draft.get_courses():
                        if course.get_course_code() == user_input[1]:
                            course_found = True
                            if draft.remove_from_draft(course):
                                print_info("course removed")
                                eligable_courses.append(course)
                            else:
                                print_error("course couldnt be removed")

                    if not course_found:
                        print_error("no such course in draft!")

                elif user_input[0] == "submit":
                    if len(draft.get_courses()) == 0:
                        print_error("cannot send empty draft!")
                    else:
                        if advisor.add_draft(draft):
                            print_info("draft updated")
                        else:
                            print_info("draft sent for approval")

                elif user_input[0] == "exit":
                    print_info("draft saved!")
                    return
                else:
                    print_error("Invalid Input!")

        elif user_input == "3":
            return
        
def get_lecturer_courses(lecturer):
    lecturer_courses = []
    for course in courses:
        if lecturer.get_user_id() == course.get_lecturer().get_user_id():
            lecturer_courses.append(course)
    return lecturer_courses

def get_course_with_id(id):
    for course in courses:
        if course.get_course_code() == id:
            return course
    return None

def staff_login():
    print_title("STAFF LOGIN")
    staff_id_input = input("enter staff id: ")
    staff = get_user_with_id(staff_id_input)

    while staff == None or not isinstance(staff, Staff):
        print_error(f"no staff with staffId  {staff_id_input}")
        staff_id_input = input("enter staff id: ")
        if staff_id_input == "":
            return
        staff = get_user_with_id(staff_id_input)

    staff_password_input = input("enter password: ")
    global incorrect_attempts
    if staff.get_password() != staff_password_input:
        print_error("Wrong Password!")
        incorrect_attempts += 1
        if incorrect_attempts == 3:
            print_error(
                "Maximum password attempts reached. Please wait for 10 seconds."
            )
            time.sleep(10)  # Pause the program for 10 seconds
            incorrect_attempts = 0  # Reset the incorrect attempts counter

        if incorrect_attempts <= 3:
            return

    if isinstance(staff, Lecturer):
        if isinstance(staff, Advisor):
            # Implement Advisor interface
            print_title("ADVISOR INTERFACE")
            print_info(f"Welcome {staff.get_name()}")
            while True:
                print_commands("1-> student registrations\n2-> exit")
                user_input = input("==> ")

                while not user_input in ["1", "2"]:
                    print_error("invalid input!")
                    print_commands("1->exit")
                    user_input = input("==> ")

                if user_input == "2":
                    return

                if user_input == "1":
                    print_title("STUDENT REGISTRATIONS")
                    draft_approval_process(staff)

        else:
            print_title("LECTURER INTERFACE")
            print_info(f"Welcome {staff.get_name()}")
            while True: 
                print_commands("1-> see your information\n2-> evaluate students in courses you teach\n3-> exit")
                user_input = input("==> ")

                while not user_input in ["1","2","3"]:  
                    print_error("invalid input!")
                    print_commands("1-> see your information\n2-> evaluate students in courses you teach\n3-> exit")
                    user_input = input("==> ")

                if user_input == "1":
                    print_title("LECTURER INFO")  
                    print_info(staff.get_info())
                elif (user_input == "2"): 
                    print_title("EVALUATE COURSES")
                    lecturer_courses = get_lecturer_courses(staff)
                    if len(lecturer_courses) == 0:
                        print_error("no courses!")
                        break
                    while True:  
                        print_info("Choose a course code you want to evaluate or enter \"exit\" to return") 
                        for course in lecturer_courses:
                            print(course.get_course_code() + " " + course.get_course_name())
                                
                        user_input = input("==> ")
                        
                        chosen_course = get_course_with_id(user_input)
                        
                        if (user_input == "exit"):
                            break
                        elif chosen_course == None:
                            print_error("No such course!")
                            break
                        
                        while True:
                            print_title(chosen_course.get_course_code())
                            print_commands("0-> get course information\n1-> grade a student\n2-> return")
                            user_input_2 = input("==> ")

                            while not user_input_2 in ["0","1","2"]:
                                print_error("invalid input!")
                                print_commands("0-> get course information\n1-> grade a student\n2-> return")
                                user_input_2 = input("==> ")

                            if (user_input_2 == "0"):
                                print(chosen_course.get_course_information())
                                
                            elif (user_input_2 == "1"):
                                course_students = chosen_course.get_students()
                                if len(course_students) == 0:
                                        print_error("no students to grade!")
                                        break
                                while True:
                                    
                                    print_info("Choose a student id you want to grade or enter \"exit\" to return:")
                                    
                                    for student in course_students:
                                        print_info(student.get_user_id() + " " + student.get_name())
                                        
                                    user_input_3 = input("==> ")
                                    
                                    student = get_user_with_id(user_input_3)
                                    
                                    if user_input_3 == "exit":
                                        print_error("exiting")
                                        break 
                                    elif student == None:
                                        print_error("no such student!")
                                        break
                                        
                                    grade_input = input("Enter a grade between 0-100: ")

                                    while (not grade_input.isdigit() or not (0 <= int(grade_input) <= 100)):
                                        print_error("invalid grade!")
                                        grade_input = input("Enter a grade between 0-100: ")
                                    
                                    grade = Grade(course_code=chosen_course.get_course_code(), number_grade=grade_input, is_passed=True)
                                    student.add_grade_to_transcript(grade)
                                    
                                    print_info(student.get_name() + "'s grade set to " + grade_input)
                                    course.remove_student(student)
                                    
                            elif (user_input_2 == "2"):
                                break
                elif user_input == "3":
                    return

    elif isinstance(staff, Student_Affairs_Staff):
        # Implement Student Affairs Staff interface
        print_title("STUDENT AFFAIRS INTERFACE")
        print_info(f"Welcome {staff.get_name()}")

        while True:
            print_commands("1-> See Registered Courses for Students\n2-> Change student number\n3-> Exit")
            user_input = input(" Please choose your action ==>")

            while not user_input in ["1","2","3"]:
                print_error("invalid input!")
                print_commands("1-> See Registered Courses for Students\n2-> Change student number\n3-> Exit")
                user_input = input(" Please choose your action ==>")

            if user_input == "1":
                print_title("REGISTERED COURSES FOR STUDENTS")
                for student in users:
                    if isinstance(student, Student):
                        print_info(f"\nStudent ID: {student.get_user_id()}\nName: {student.get_name()}")
                        registered_courses = student.get_registered_courses(courses)
                        if len(registered_courses) != 0:
                            print("Registered Courses:")
                            for course in registered_courses:
                                print(f"{course.get_course_code()}: {course.get_course_name()}")
                        else:
                            print("No registered courses.")

            elif user_input== "2":
                # Yeni öğrenci numarasını belirleme işlemi
                student_id = input("Enter the new student ID: ")
                student = get_user_with_id(student_id)

                while student is not None:
                    print_error(f"Student ID {student_id} is already in use. Please choose another one.")
                    student_id = input("Enter the new student ID: ")
                    student = get_user_with_id(student_id)

            # Önceki öğrenci numarasını güncelleme işlemi
                previous_student_id = input("Enter the previous student ID to update: ")
                previous_student = get_user_with_id(previous_student_id)

                if previous_student is not None and isinstance(previous_student, Student):
                    previous_student.user_id = student_id  # Öğrenci numarasını doğrudan güncelle
                    print_info(f"Student ID updated. New student ID: {student_id}")
                else:
                    print_error(f"No student found with the previous ID: {previous_student_id}")

            
            elif user_input == "3":
                return



def init():
    grade0 = Grade("CSE101", "AA", "98", True)
    grade1 = Grade("CSE201", "AA", "99", True)
    grade2 = Grade("CSE301", "AA", "100", True)

    grades = []
    grades.append(grade0)
    grades.append(grade1)
    grades.append(grade2)

    transcript = Transcript(3.98, grades)

    lecturer = Lecturer(
        user_id="160111",
        name="Talip Demirel",
        password="111",
        email="talipdemirel@marun.edu.tr",
        department="science",
        field="applied mathematics",
    )
    student0 = Student(
        user_id="150111",
        name="Yigit Tuncer",
        password="111",
        email="yigittuncer@marun.edu.tr",
        transcript=Transcript(),
        year=2,
    )
    student1 = Student(
        user_id="150031",
        name="Kerem Demirel",
        password="111",
        email="keremozkan@marun.edu.tr",
        transcript=Transcript(),
        year=3,
    )
    student2 = Student(
        user_id="150333",
        name="Ceren Ozge",
        password="111",
        email="cerenozge@marun.edu.tr",
        transcript=transcript,
        year=3,
    )
    student3 = Student(
        user_id="150444",
        name="Cem Mazlum",
        password="111",
        transcript=Transcript(),
        email="cemmazlum@marun.edu.tr",
        year=3,
    )

    students = []
    students.append(student0)
    students.append(student1)
    students.append(student2)
    students.append(student3)

    drafts = []
    advisor = Advisor(
        user_id="150555",
        name="Nazmi Emir Arslan",
        password="111",
        email="emirarslan@marun.edu.tr",
        department="science",
        field="statistician",
        students=students,
        drafts=drafts,
    )

    for student in students:
        student.set_advisor(advisor)

    course0 = Course(
        course_code="CSE101", course_name="Intro to Rust Programming", lecturer=lecturer
    )
    course1 = Course(
        course_code="CSE201",
        course_name="Intermediate Rust Programming",
        lecturer=lecturer,
    )
    course3 = Course(
        course_code="IAC",
        course_name="Interior Architecture with AI",
        lecturer=lecturer,
    )
    course3.enroll_student(student2)
    course4 = Course(course_code="CSE401", course_name="Final Project", lecturer=lecturer)

    prerequisites = []
    prerequisites.append(course0)
    prerequisites.append(course1)

    course2 = Course(
        "CSE301", "Advanced Rust Programming", lecturer, students, prerequisites, 12, 2
    )

    courses.append(course0)
    courses.append(course1)
    courses.append(course2)
    courses.append(course3)
    courses.append(course4)

    draft = Draft(student=student2)

    student_affairs_staff = Student_Affairs_Staff(
        user_id="160222",
        name="Hasan Pekedis",
        password="111",
        email="hasanpekedis@marun.edu.tr",
        department="engineering",
        students=students,
    )

    users.append(student0)
    users.append(student1)
    users.append(student2)
    users.append(student3)
    users.append(lecturer)
    users.append(advisor)
    users.append(student_affairs_staff)

    for student in students:
        student.set_advisor(advisor)


def draft_approval_process(advisor):
    if not advisor.get_drafts():
        print_error("No drafts to approve currently")
        return

    print_info("\nPlease proceed with this draft:\n")

    for draft in advisor.get_drafts():
        student = draft.get_student()
        print(student.get_name() + "\n" + student.get_user_id() + "\n\nCourses:")

        for course in draft.get_courses():
            print(course.course_name + " " + course.course_code)
            advisor_input = input("Do you approve of this course? (yes/no): ")

            if advisor_input.lower() == "yes":
                course.enroll_student(student)
                print_info("student enrolled to course")
            elif advisor_input.lower() == "no":
                print_info("course rejected")
            else:
                print_error("Invalid input entered.")

        print_info("Draft completed")


# MAIN PROCESS
init()
while True:
    print_title("BYS")
    print_commands("1-> student login\n2-> staff login\n3-> exit")
    user_input = input("==> ")

    while not user_input in ["1", "2", "3"]:
        print_error("invalid input!")
        print_commands("1-> student login\n2-> staff login\n3-> exit")
        user_input = input("==> ")

    if user_input == "1":
        student_login()
    elif user_input == "2":
        staff_login()
    elif user_input == "3":
        system_exit()
