from enum import Enum, unique


@unique
class UserState(Enum):
    Login = 0
    Logout = 1
    InExam = 2
    Idle = 3
