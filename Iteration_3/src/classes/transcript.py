class Transcript:
    def __init__(self, gano=0, grades=[]):
        self.gano = gano
        self.grades = grades

    def get_grades(self):
        return self.grades

    def add_grade(self, grade):
        self.grades.append(grade)

    def get_completed_courses(self):
        completed_courses = []
        for grade in self.grades:
            if grade.is_grade_passing():
                completed_courses.append(grade.get_course_code())
        return completed_courses

    def convert_gano_to_letter_grade(self, gano):
        if 4.00 >= gano > 3.50:
            letter_grade = "AA"
        elif 3.50 >= gano > 3.00:
            letter_grade = "BA"
        elif 3.00 >= gano > 2.50:
            letter_grade = "BB"
        elif 2.50 >= gano > 2.00:
            letter_grade = "CB"
        elif 2.00 >= gano > 1.50:
            letter_grade = "CC"
        elif 1.50 >= gano > 1.00:
            letter_grade = "DC"
        elif 1.00 >= gano > 0.50:
            letter_grade = "DD"
        elif 0.50 >= gano > 0.00:
            letter_grade = "FD"
        else:
            letter_grade = "FF"

        return letter_grade

    def to_string(self):
        info_str = f"GANO: {self.gano}\n"

        completed_courses = self.get_completed_courses()
        if completed_courses:
            info_str += "Completed Courses:\n"
            for course_code in completed_courses:
                info_str += f"- {course_code}\n"
        else:
            info_str += "No completed courses.\n"

        return info_str

    def get_info(self):
        return f"gano: {self.gano}\ngrades: {self.grades}\nletter grade: {self.convert_gano_to_letter_grade(self.gano)}"
