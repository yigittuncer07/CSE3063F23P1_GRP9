from classes.advisor import Advisor
from classes.course import Course
from classes.draft import Draft
from classes.grade import Grade
from classes.lecturer import Lecturer
from classes.staff import Staff
from classes.student_affairs_staff import Student_Affairs_Staff
from classes.student import Student
from classes.transcript import Transcript

def student_login():
    student_input = input("enter student id: ")
    student_input = input("enter password: ")
    
def staff_login():
    student_input = input("enter staff id: ")
    student_input = input("enter password: ")
    


advisor = Advisor()
lecturer = Lecturer()
student0 = Student("Yigit Tuncer")
student1 = Student("Kerem Ozkan")
student2 = Student("Cem Mazlum")
student3 = Student("Ceren Ozge")
student4 = Student("Buse Hanim")
student5 = Student("Lucider Michealson")
student6 = Student("Emre Eldek")
student7 = Student("Talip Demirel")

students = []
students.append(student0)
students.append(student1)
students.append(student2)
students.append(student3)
students.append(student4)

course0 = Course("CSE101", "Intro to Rust Programming")
course1 = Course("CSE201", "Intermediate Rust Programming")

prerequisites = []
prerequisites.append(course0)
prerequisites.append(course1)

course2 = Course(
    "CSE301", "Advanced Rust Programming", lecturer, students, prerequisites, 4, 2
)

draft = Draft()
grade = Grade()
staff = Staff()
transcript = Transcript()
student_affairs_staff = Student_Affairs_Staff()

course2.enroll_student(student5)
course2.enroll_student(student7)



print("\033[94mWELCOME TO BYS\033[0m")
print("\033[93m1-> student login\n2-> staff login\033[0m")

user_input = input("")

while (not user_input in ["1","2"]):
    user_input = input("\033[93m1-> student login 2-> staff login\n\033[0m")


if user_input == "1":
    student_login()
elif user_input == "2":
    advisor_login()
else:
    printf("invalid input!")
    



# print(course2.get_info())
