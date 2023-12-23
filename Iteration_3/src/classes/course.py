class Course:
    def __init__(
        self,
        course_code=None,
        course_name=None,
        lecturer=None,
        students=None,
        prerequisites=None,
        credits=None,
        year=None,
    ):
        self.course_code = course_code
        self.course_name = course_name
        self.lecturer = lecturer
        self.students = students if students is not None else []
        self.prerequisites = prerequisites
        self.credits = credits
        self.year = year

    def enroll_student(self, student):
        if student not in self.students:
            self.students.append(student)
            
    def get_course_code(self):
        return self.course_code

    def get_info(self):
        course_info = (
            f"Course Code: {self.course_code}\n"
            f"Course Name: {self.course_name}\n"
            f"Lecturer: {self.lecturer.get_info() if self.lecturer else 'None'}\n"
            f"Credits: {self.credits}\n"
            f"Year: {self.year}\n"
            f"Prerequisites: {', '.join([prerequisite.get_course_code() for prerequisite in self.prerequisites]) if self.prerequisites else 'None'}\n"
            f"Students Enrolled: {', '.join([student.get_name() for student in self.students]) if self.students else 'None'}"
        )
        return course_info
