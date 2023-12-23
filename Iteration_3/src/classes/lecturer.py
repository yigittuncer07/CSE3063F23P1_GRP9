from .staff import Staff

class Lecturer(Staff):
    def __init__(self, user_id=None):
        self.user_id = user_id
        

    def get_info(self):
        return self.user_id