class Grade:
    
    def __init__(self, course_code=None, number_grade=None, letter_grade=None, is_passed=None):
        self.course_code = course_code
        self.number_grade = number_grade
        self.letter_grade = letter_grade
        self.is_passed = is_passed
        
    def get_course_code(self):
        return self.course_code

    def is_grade_passing(self):
        return self.is_passed