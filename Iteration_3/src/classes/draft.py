class Draft:
    def __init__(self, student= [], courses= []):
        self.student = student
        self.courses = courses
        
    def add_to_draft(self, course):
        if course not in self.courses:
            self.courses.append(course)
            return True
        return False
    
    def remove_from_draft(self, course):
        if course in self.courses:
            self.courses.remove(course)
            return True
        return False
            
    def clear_draft(self):
        self.courses.clear()

    def get_courses(self):
        return self.courses
    
    def get_student(self):
        return self.student