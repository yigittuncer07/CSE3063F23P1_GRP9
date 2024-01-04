import os
from .staff import Staff
import json


class Student_Affairs_Staff(Staff):
    def __init__(
        self,
        user_id=None,
        name=None,
        password=None,
        email=None,
        department=None,
        students=None,
    ):
        super().__init__(user_id, name, password, email, department)
        self.students = students

    @staticmethod
    def get_list_info(list):
        if len(list) == 0:
            return ""
        all_info = ""
        for item in list:
            all_info += item.get_info()
        return all_info

    def get_info(self):
        return (
            f"user_id: {self.user_id}\n"
            f"name: {self.name}\n"
            f"password: {self.password}\n"
            f"email: {self.email}\n"
            f"department: {self.department}\n"
            f"students: {self.students}"
        )
        
    def get_students(self):
        return self.students

    def to_json_file(self):
        filename = f"database/student_affairs_staff/{self.user_id}.json"
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
