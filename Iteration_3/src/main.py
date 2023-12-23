from classes.advisor import Advisor
from classes.course import Course
from classes.draft import Draft
from classes.grade import Grade
from classes.lecturer import Lecturer
from classes.staff import Staff
from classes.student_affairs_staff import Student_Affairs_Staff
from classes.student import Student
from classes.transcript import Transcript

print("Hello, World!")

advisor = Advisor()
course = Course()
draft = Draft()
grade = Grade()
lecturer = Lecturer()
staff = Staff()
student_affairs_staff = Student_Affairs_Staff()
student = Student()
transcript = Transcript()

course.enroll_student(student)

# Read all users into their arraylist
