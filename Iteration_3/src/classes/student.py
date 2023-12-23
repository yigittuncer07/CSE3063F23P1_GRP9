from .user import User

class Student(User):
    def __init__(self, student_id=None):
        self.student_id = student_id
