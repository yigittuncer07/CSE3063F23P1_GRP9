from abc import ABC, abstractmethod
import json

class User(ABC):
    def __init__(self, user_id=None, name=None, password=None, email=None):
        self.user_id = user_id
        self.name = name
        self.password = password
        self.email = email

    @abstractmethod
    def get_info(self):
        pass

    @abstractmethod    
    def to_json_file(self):
        pass

    @abstractmethod
    def from_json_file(cls, user_id):
        pass

