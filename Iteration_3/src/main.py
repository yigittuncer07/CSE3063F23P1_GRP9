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

# GLOBAL VARIABLES
users = []
courses = []

# LOGGING CONFIG
logger = logging.getLogger()
logger.setLevel(logging.DEBUG)
file_handler = logging.FileHandler('logfile.log')
formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(message)s')
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
    
def student_login():
    
    print_title("STUDENT LOGIN")
    student_id_input = input("enter student id: ")
    student = get_user_with_id(student_id_input)
    
    while (student == None or not isinstance(student, Student)):
        print_error(f"no student with studentID  {student_id_input}")
        student_id_input = input("enter student id: ")
        student = get_user_with_id(student_id_input)

    student_password_input = input("enter password: ")
    
    if (student.get_password() != student_password_input):
        print_error("\033[91mWrong Password!\033[0m")
        return
    
    print_info(f"Welcome {student.get_name()}")
    
    while True:
        print_commands("1-> see transcript\n2-> register to a course\n3-> exit")
        user_input = input("==> ")
    
        while (not user_input in ["1","2","3"]):
            print_error("invalid input!")
            print_commands("1-> see transcript\n2-> register to a course\n3-> exit")
            user_input = input("==> ")

        if user_input == "1":
            print_title("TRANSCRIPT")
            print_info(student.get_transcript().get_info())
        elif user_input == "2":
            
            print_title("COURSE REGISTRATION")
            eligable_courses = student.get_eligible_courses(courses)
            
            while True:
                print_info("Eligable Courses:")
                for course in eligable_courses:
                    print(course.get_course_code() + " " + course.get_course_name())
                    
                print_commands("add \"course\" -> add to draft\nremove \"course\" -> remove from draft\nsubmit -> send draft to advisor for approval\nexit -> save draft and exit")
                user_input = input("==> ")
                
                if (user_input.startswith("add")):
                    pass
                elif (user_input.startswith("remove")):
                    pass
                elif (user_input.startswith("submit")):
                    pass
                elif (user_input.startswith("exit")):
                    print_info("draft saved!")
                    return
                else:
                    print_error("Invalid Input!")

            
            
        elif user_input == "3":
            return
  
  
def staff_login():
    print_title("STAFF LOGIN")
    
def init():
    
    grade0 = Grade("CSE101", "AA", "98", True)
    grade1 = Grade("CSE201", "AA", "99", True)
    grade2 = Grade("CSE301", "AA", "100", True)
    
    grades = []
    grades.append(grade0)
    grades.append(grade1)
    grades.append(grade2)
    
    transcript = Transcript(3.98, grades)
    
    advisor = Advisor()
    lecturer = Lecturer()
    student0 = Student(user_id="150111", name="Yigit Tuncer", password="111", email="yigittuncer@marun.edu.tr")
    student1 = Student(user_id="150222", name="Kerem Ozkan", password="111", email="keremozkan@marun.edu.tr")
    student2 = Student(user_id="150333", name="Ceren Ozge", password="111", email="cerenozge@marun.edu.tr", transcript=transcript, year=3)
    student3 = Student(user_id="150444", name="Cem Mazlum", password="111", email="cemmazlum@marun.edu.tr")
    
    students = []
    students.append(student0)
    students.append(student1)
    students.append(student2)
    students.append(student3)
    
    course0 = Course("CSE101", "Intro to Rust Programming")
    course1 = Course("CSE201", "Intermediate Rust Programming")
    course3 = Course("IAC", "Interior Architecture with AI")
    course4 = Course("CSE401", "Final Project")


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


    draft = Draft()
    grade = Grade()
    transcript = Transcript()
    student_affairs_staff = Student_Affairs_Staff()

    users.append(student0)
    users.append(student1)
    users.append(student2)
    users.append(student3)
    users.append(lecturer)
    users.append(advisor)
    users.append(student_affairs_staff)

# MAIN PROCESS
init()
while True:
    print_title("BYS")
    print_commands("1-> student login\n2-> staff login\n3-> exit")
    user_input = input("==> ")

    while (not user_input in ["1","2","3"]):
        print_error("invalid input!")
        print_commands("1-> student login\n2-> staff login\n3-> exit")
        user_input = input("==> ")

    if user_input == "1":
        student_login()
    elif user_input == "2":
        staff_login()
    elif user_input ==  "3":
        system_exit()