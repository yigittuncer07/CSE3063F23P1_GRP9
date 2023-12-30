from .lecturer import Lecturer
import json


class Advisor(Lecturer):
    def __init__(
        self,
        user_id=None,
        name=None,
        password=None,
        email=None,
        department=None,
        field=None,
        students=None,
        drafts=[],
    ):
        super().__init__(user_id, name, password, email, department, field)
        self.students = students
        self.drafts = drafts

    # returns true if draft was appended
    def add_draft(self, draft_sent):
        for draft in self.drafts:
            if draft_sent.get_student() == draft.get_student():
                draft = draft_sent
                return True
        self.drafts.append(draft_sent)
        return False

    @staticmethod
    def get_list_info(list):
        all_info = ""
        for item in list:
            all_info += item.get_info()
        return all_info

    def get_info(self):
        user_info = super().get_info()
        advisor_info = f"\nField: {self.field}\nStudents: {self.get_list_info(self.students)}\nDrafts: {self.get_list_info(self.students)}"
        return user_info + advisor_info

    def to_json_file(self):
        filename = f"database/advisors/{self.user_id}.json"
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
