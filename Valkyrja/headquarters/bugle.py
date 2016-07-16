from headquarters.models import LoggingInUser, Course, User, Exam, Problem

import socket
import json


# call() should be renamed to dispatchMessages()
def call(targets_ip: list, message: dict):
    print("bugle.call--->")
    for target in targets_ip:
        print("bugle.call: %s !" % target)
        sock = socket.socket()
        sock.connect((target, 3001))

        sock.sendall(json.dumps(message).encode('UTF-8'))

        sock.close()


def new_message(exam: Exam, user: User, message: str):

    course = Course.objects.get(exams__id=exam.id)

    teacher = course.teacher
    # students = course.students.all()
    students = course.students.all().exclude(student_id=user.student_id)

    records = LoggingInUser.objects.filter(user__in=students) | LoggingInUser.objects.filter(user=teacher)

    print("!!bugle.new_msg-rec#: %d" % len(records))
    if len(records) > 0:
        call([record.ip_address for record in records], {
            "type": "Chat",
            "action": "NewMessage",
            "content": {
                "name": user.name,
                "message": message
            }
        })


def student_login(student: User):
    teachers = LoggingInUser.objects.filter(user__is_admin=True)

    print("student_login-teacher#: " + str(len(teachers)))
    if len(teachers) > 0:
        call([teacher.ip_address for teacher in teachers], {
            "type": "User",
            "action": "Login",
            "content": {
                "name": student.name
            }
        })


def student_logout(student: User):
    teachers = LoggingInUser.objects.filter(user__is_admin=True)
    print("student_logout-teachers#: %d" % len(teachers))
    if len(teachers) > 0:
        call([teacher.ip_address for teacher in teachers], {
            "type": "User",
            "action": "Logout",
            "content": {
                "name": student.name
            }
        })


def student_idle(student: User):
    teachers = LoggingInUser.objects.filter(user__is_admin=True)

    call([teacher.ip_address for teacher in teachers], {
        "type": "User",
        "action": "Idle",
        "content": {
            "name": student.name
        }
    })


def student_submit(teacher: User, student: User, exam: Exam, problem: Problem):
    call(LoggingInUser.objects.get(user=teacher).ip_address, {
        "type": "Exam",
        "action": "Submit",
        "content": {
            "name": student.name,
            "examName": exam.name,
            "problemName": problem.name
        }
    })


def request_new_snapshot(student: User):
    call(LoggingInUser.objects.get(user=student).ip_address, {
        "type": "Exam",
        "action": "RequestSnapshot"
    })


def start_monitor(student: User):
    call(LoggingInUser.objects.get(user=student).ip_address, {
        "type": "Monitor",
        "action": "Start"
    })


def send_key_event(teacher: User, key_event: dict):
    call(LoggingInUser.objects.get(user=teacher).ip_address, {
        "type": "Monitor",
        "action": "KeyEvent",
        "content": key_event
    })


def stop_monitor(student: User):
    call(LoggingInUser.objects.get(user=student).ip_address, {
        "type": "Monitor",
        "action": "Stop"
    })


def pause_exam(exam: Exam):
    course_students = Course.objects.get(exams__id=exam.id).students.all()
    online_students = LoggingInUser.objects.filter(user__in=course_students)

    call([student.ip_address for student in online_students], {
        "type": "Exam",
        "action": "Pause"
    })


def resume_exam(exam: Exam):
    course_students = Course.objects.get(exams__id=exam.id).students.all()
    online_students = LoggingInUser.objects.filter(user__in=course_students)

    call([student.ip_address for student in online_students], {
        "type": "Exam",
        "action": "Resume"
    })


def halt_exam(exam: Exam):
    course_students = Course.objects.get(exams__id=exam.id).students.all()
    online_students = LoggingInUser.objects.filter(user__in=course_students)

    call([student.ip_address for student in online_students], {
        "type": "Exam",
        "action": "Halt"
    })


def extend_exam(exam: Exam, extend_time: int):
    course_students = Course.objects.get(exams__id=exam.id).students.all()
    online_students = LoggingInUser.objects.filter(user__in=course_students)

    call([student.ip_address for student in online_students], {
        "type": "Exam",
        "action": "Extend",
        "content": {
            "extend_time": extend_time
        }
    })

