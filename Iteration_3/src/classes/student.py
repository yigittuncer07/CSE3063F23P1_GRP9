from .user import User
import json


class Student(User):
    def __init__(self, user_id, name, password, email):
        super().__init__(user_id,name,password,email)

    def get_name(self):
        return self.name
    
    def get_info(self):
        user_info = super().get_info()
        return f"user_id: {self.user_id}\nname: {self.name}\nemail: {self.email}"

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