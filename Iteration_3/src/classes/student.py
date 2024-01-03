import os
from .user import User
from .draft import Draft
import json
import logging

# LOGGING CONFIG
logger = logging.getLogger()
logger.setLevel(logging.DEBUG)
file_handler = logging.FileHandler("logfile.log")
formatter = logging.Formatter("%(asctime)s - %(levelname)s - %(message)s")
file_handler.setFormatter(formatter)
logger.addHandler(file_handler)


class Student(User):
    def __init__(
        self,
        user_id=None,
        name=None,
        password=None,
        email=None,
        transcript=None,
        year=0,
        advisor=None,
    ):
        super().__init__(user_id, name, password, email)
        self.transcript = transcript
        self.year = year
        self.advisor = advisor
        self.draft = Draft(student=self)
        self.registered_courses = []


    def register_course(self, course):
        if course not in self.registered_courses:
            self.registered_courses.append(course)
            return True
        return False

    def get_registered_courses(self):
        return self.registered_courses

    def get_student_info(self):  
        return (
            f"Name: {self.name}\n"
            f"User ID: {self.user_id}\n"
            f"Email: {self.email}\n"
            f"Year: {self.year}\n"
            f"Registered Courses: {', '.join(course.get_course_code() for course in self.registered_courses)}"
        )    

    def get_draft(self):
        return self.draft

    def set_advisor(self, advisor):
        self.advisor = advisor

    def get_advisor(self):
        return self.advisor

    def get_name(self):
        return self.name

    def get_transcript(self):
        return self.transcript

    def is_course_eligible(self, course):
        logger.info(f"is course eligable called for course {course.get_course_code()}")

        # check if already in draf
        if course in self.draft.get_courses():
            return False

        # check year prerequisite
        if course.get_course_year() > self.year:
            logger.info(
                f"course is found to NOT be eligable for {course.get_course_code()} by year"
            )
            return False

        # check if course is already taken
        if course.get_course_code() in self.transcript.get_completed_courses():
            logger.info(
                f"course is found to NOT be eligable for {course.get_course_code()} by already taken"
            )
            return False

        # check if prerequisites are completed
        if course.get_prerequisites() is not None:
            for prerequisite in course.get_prerequisites():
                if prerequisite not in self.transcript.get_completed_courses():
                    logger.info(
                        f"course is found to NOT be eligable for {course.get_course_code()} by prerequisite"
                    )
                    return False

        logger.info(f"course is found to be eligable for {course.get_course_code()}")
        return True

    def get_eligible_courses(self, all_courses):
        eligible_courses = []
        for course in all_courses:
            if self.is_course_eligible(course):
                eligible_courses.append(course)
        return eligible_courses

    def get_info(self):
        return (
            f"user_id: {self.user_id}\n"
            f"name: {self.name}\n"
            f"password: {self.password}\n"
            f"email: {self.email}\n"
            f"transcript: {self.transcript}\n"
            f"year: {self.year}\n"
            f"advisor: {self.advisor}"
        )

    def to_json_file(self):
        filename = f"database/students/{self.user_id}.json"
        with open(filename, "w") as json_file:
            json.dump(self.get_info(), json_file, indent=2)
        return filename

    @classmethod
    def from_json_file(cls, user_id):
        filename = f"{user_id}.json"
        if os.path.exists(filename):
            with open(filename, "r") as json_file:
                student_data = json.load(json_file)
                return cls(**student_data)
        else:
            raise FileNotFoundError(f"File {filename} does not exist.")
