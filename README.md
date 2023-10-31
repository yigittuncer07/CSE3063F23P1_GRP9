# CSE3063 TERM PROJECT

## 1. GENERAL INFORMATION

We will employ iterative evolutionary software development processes such as Unified Process, SCRUM, or XP. We'll divide our development into 3 iterations, with each iteration being three weeks long and time-boxed. All four main activities; requirement analysis, design, implementation, and testing must be done in each iteration.

At the end of each iteration, you must submit a functional, working software. In the first iteration, you should analyze, design, and implement the core architecture with limited functionality, and test it. Do not attempt to analyze, design, and implement all the requirements in the first iteration! In the second iteration, you can analyze and acquire additional requirements, update and extend your design, update and extend your code, and test it. In the third iteration, you need to switch your design and implementation to the Python programming language. After that, you need to analyze, design, implement, and test additional requirements and corresponding functionality.

## 2. SCHEDULE

**Iteration 1:** Week 3 – Week 7
- Midterm break at Week 8
- Demo at Week 9

**Iteration 2:** Week 9 – Week 11
- Demo at Week 12

**Iteration 3:** Week 11 – Week 13
- Demo at Week 14

## 3. DEVELOPMENT SETUP

I. Elect one of your teammates as "team leader" by voting.
II. If you don’t have a GitHub account, get one. Make sure your username reflects your real name.
III. Team leader must open a private GitHub repository named `CSE3063F23P1_GRPX` (X is your group number, for group 1, the repo name should be `CSE3063F23P1_GRP1`) and add all team members to the repo as collaborators. Also, add `mcganiz` and `cseoosd`.
IV. Use the issues section for simple issue tracking. You may also use the Projects section to open a Kanban board with at least three lists such as TO DO, IN PROGRESS, DONE. You should use the Kanban board and issues to distribute workload to individual team members.

Please note that using an issue tracking system is very important. The quantity and quality of issues completed by each team member, linked to the code or an artifact, is extremely important to prove your effort in the project. Your individual grade will depend on this.

## 4. TEAM WORK

- You do not have the right to kick a student out of the group. You have to keep existing members during the iterations.
- You need to distribute the workload mainly in a voluntary way, like in Scrum self-organizing teams, everybody in the team can choose tasks that are best fit to them. If you have a problem with this approach, then you may elect a team leader by popular vote, and she/he can assign the tasks to the members.
- Please note that a student may not only do non-coding jobs. Every student must code, therefore coding tasks should be distributed to all students.
- I recommend you to assign tasks in an environment like Jira, Trello, or GitHub issues so that you can keep track of the performance of members and show it to me if there are any conflicts.

## 5. ITERATION ACTIVITIES AND ARTIFACTS

### 1. REQUIREMENT ANALYSIS

Prepare a simple Requirement Analysis Document (RAD) including:

a. A title page including group number, names and numbers of all group members

b. A brief description of the project in a couple of paragraphs,

c. A glossary of important concepts,

d. List of functional and non-functional requirements,

e. A domain model showing real-world objects and important concepts in your domain along with their simple relations and features.

f. Use cases. You should have at least one use case in detailed format (refer to your textbooks for the format).

g. System Sequence Diagram (SSD)

File name example: `CSE3063F23P1_RAD_GRP1_iteration1.pdf`
This must be pushed into your GitHub repository's corresponding iteration folder (e.g., `iteration1`) by the deadline.

### 2. SOFTWARE DESIGN

a. Prepare a Design Class Diagram (DCD): A UML class diagram showing your domain classes. It must be a fully-dressed UML Class diagram showing all the details of classes, attributes, and methods, including access identifiers, types of attributes, types of input parameters, and output parameters of the methods. Also explicitly state abstract members, interfaces, inheritance, etc. (File name example: `CSE3063F23P1_DCD_GRP1_iteration1.pdf)`. This must be pushed into your GitHub repository's corresponding iteration folder by the deadline.

b. Prepare Design Sequence Diagrams (DSD): UML Sequence Diagram(s) showing interactions between software objects of your system. (File name example: `CSE3063F23P1_DSD_GRP1_iteration1.pdf`). This must be pushed into your GitHub repository's corresponding iteration folder by the deadline.

### 3. IMPLEMENTATION / JAVA CODE

This includes classes. Each class must be in a separate .java file named with the name of the class. Please make sure that only the java files, json files, and necessary libraries are in the repo. Do not include any binaries or executables! Your repo size should not exceed 10mb. During your demonstrations, me or your TA should be able to get your code from GitHub to any computer with a Java compiler and run your Java code without errors.

This must be pushed into your GitHub repository's corresponding iteration folder by the deadline.

### 4. TESTING

This includes at least 5 unit tests for at least 3 different classes.

### 5. DELIVERY

- Please make a compressed zip archive of all these artifacts and upload it to Google Classroom by the deadline.
- The best way to do this is to take a snapshot of your GitHub repo and zip it. The file size should not exceed 10mb. This must be done ONLY by the team leader. Only one student must upload the delivery to the Google Classroom. (File name example: `CSE3063F23P1_GRP1_iteration1.zip)

### 6. PROJECT TOPIC AND INITIAL REQUIREMENTS

For your Java project, I want you to create a small but functional course registration system for our department. There will be two types of users; student and advisor. We may add more in the future like department head, admin, and student affairs. Users will enter the system by their assigned username and password. You will basically use rules and regulations of our department for the registration process. For details, you need to ask in the classroom so that I can clarify the requirements.

- You must have several classes such as Course, CourseSection, Staff, Lecturer, Advisor, Student, Grade, Registration, Transcript, CourseRegistrationSystem (Controller pattern), etc.

- Majority of your classes must have responsibilities, e.g., meaningful operations (methods) for implementing business logic. Avoid seeing classes as data holders only. A data holder only has get and set methods. We don't like that.

- You implement inheritance. One idea is to have an abstract Person class and inherit Staff and Student from it. Lecturer and StudentAffairsStaff can inherit from Staff. Advisor can be an interface enforcing certain behavior set or a subclass of Lecturer. You may have better ideas.

- Try to implement polymorphism based on abstract operations in abstract classes or interfaces.

- I MUST see the aggregation relationships between your classes clearly in attribute types and/or method parameter types.

- You must read the transcripts of these students each from a JSON file such as "150119055.json" and update these files at the end of your run. Try to create different student profiles with a different number of courses taken, some of them passed, some failed among these 10 different courses.

- You must have a parameters.json file to read system parameters from.

- Avoid using user interfaces in your domain classes. One class which is called a controller (CourseRegistrationSimulation?) should be responsible for sending messages to the screen.

### 5. ITERATION 1

- There must be at least 10 students all 3rd-year students, 2 advisors, lecturers, and at least 10 different courses, at least 3 of them having prerequisites, and 2 of them are fourth-year courses, in your first iteration. For the first iteration, a student can take a maximum of 5 courses.

- You must use a command-line user interface. You cannot use a graphical user interface such as web or windows.

- You cannot use an RDBMS, simply use JSON files to store data.
