from abc import ABC, abstractmethod

class User(ABC):
    def __init__(self, user_id=None):
        self.user_id = user_id
        
    @abstractmethod
    def get_info(self):
        pass