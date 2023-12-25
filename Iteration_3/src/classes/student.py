from .user import User
import json


class Student(User):
    def __init__(self, user_id=None, name=None, password=None, email=None, transcript=None, year=0):
        super().__init__(user_id,name,password,email)
        self.transcript = transcript
        self.year = year

    def get_name(self):
        return self.name
    
    def get_transcript(self):
        return self.transcript
    
    def get_info(self):
        user_info = super().get_info()
        return f"user_id: {self.user_id}\nname: {self.name}\nemail: {self.email}"
            
    def is_course_eligible(self, course):
        
        # check year prerequisite
        if course.get_course_year() < self.year:
            return false
        
        # check if course is already taken 
        if course.get_course_code() in self.transcript.get_completed_courses():
            return false
        
        # check if prerequisites are completed 
        for prerequisite in course.get_prerequisites():
            if prerequisite not in self.transcript.get_completed_courses():
                return false

        return true
    
    def get_eligible_courses(self, all_courses):
        eligible_courses = []
        for course in all_courses:
            if self.is_course_eligible(course):
                eligible_courses.append(course)
        return eligible_courses
    
    def to_json_file(self):
        filename = f"database/students/{self.user_id}.json"
        with open(filename, 'w') as json_file:
            json.dump(self.get_info(), json_file, indent=2)
        return filename

    @classmethod
    def from_json_file(cls, user_id):
        filename = f"{user_id}.json"
        if os.path.exists(filename):
            with open(filename, 'r') as json_file:
                student_data = json.load(json_file)
                return cls(**student_data)
        else:
            raise FileNotFoundError(f"File {filename} does not exist.")