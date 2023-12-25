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
    print_commands("1-> see transcript\n2->register to a course\n3->")

        
  
def staff_login():
    pass
    
def init():
    
    advisor = Advisor()
    lecturer = Lecturer()
    student0 = Student(user_id="150111", name="Yigit Tuncer", password="111", email="yigittuncer@marun.edu.tr")
    student1 = Student(user_id="150222", name="Kerem Ozkan", password="111", email="keremozkan@marun.edu.tr")
    student2 = Student(user_id="150333", name="Ceren Ozge", password="111", email="cerenozge@marun.edu.tr")
    student3 = Student(user_id="150444", name="Cem Mazlum", password="111", email="cemmazlum@marun.edu.tr")
    
    students = []
    students.append(student0)
    students.append(student1)
    students.append(student2)
    students.append(student3)

    course0 = Course("CSE101", "Intro to Rust Programming")
    course1 = Course("CSE201", "Intermediate Rust Programming")

    prerequisites = []
    prerequisites.append(course0)
    prerequisites.append(course1)

    course2 = Course(
        "CSE301", "Advanced Rust Programming", lecturer, students, prerequisites, 4, 2
    )

    courses.append(course0)
    courses.append(course1)
    courses.append(course2)

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
print_info("WELCOME TO BYS")
print_commands("1-> student login\n2-> staff login\n3-> exit")
user_input = input("==> ")

while (not user_input in ["1","2","3"]):
    print_error("invalid input!")
    print_commands("1-> student login\n2-> staff login\n3-> exit")
    user_input = input("==> ")

if user_input == "1":
    student_login()
elif user_input == "2":
    advisor_login()
elif user_input ==  "3":
    save_users_to_json() 
    save_courses_to_json()
    exit()