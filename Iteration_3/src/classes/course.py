class Course:
    def __init__(
        self,
        course_code=None,
        instructor=None,
        course_name=None,
        students=None,
        prerequisites=None,
        credits=None,
        year=None,
    ):
        self.course_code = course_code
        self.instructor = instructor
        self.course_name = course_name
        self.students = students if students is not None else []
        self.prerequisites = prerequisites
        self.credits = credits
        self.year = year

    def enroll_student(self, Student):
        print("affection")
