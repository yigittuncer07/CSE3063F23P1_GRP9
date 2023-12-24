from abc import ABC, abstractmethod
from .user import User

class Staff(User, ABC):
    pass
