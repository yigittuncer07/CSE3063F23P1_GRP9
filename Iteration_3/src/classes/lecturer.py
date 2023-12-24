from .staff import Staff
import json


class Lecturer(Staff):
    def __init__(self, user_id=None):
        self.user_id = user_id
        

    def get_info(self):
        return self.user_id
    
    def to_json_file(self):
        filename = f"database/lecturers/{self.user_id}.json"
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