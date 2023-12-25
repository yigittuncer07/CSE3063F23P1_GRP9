from abc import ABC, abstractmethod
from .user import User

class Staff(User, ABC):
    def __init__(self, user_id=None, name=None, password=None, email=None, department=None):
        super().__init__(user_id,name,password,email)
        self.department = department

    def get_info(self):
        info_str = f"User ID: {self.user_id}\nName: {self.name}\nPassword: {self.password}\nEmail: {self.email}\nDepartment: {self.department}"
        return info_str