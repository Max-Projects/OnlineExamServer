package org.mislab.api;

public enum ErrorCode {
    OK,
    UnknownState,
    NetworkError,
    JsonParseError,
    InvalidUserNameOrPassword,
    UserNotLoggedIn,
    CourseNotFound,
    TooFewArgument,
    StudentNotFound,
    ExamNotFound,
    ExamAlreadyFinishScoring,
    WrongArgumentType,
    AnswerSheetNotFound,
    SomethingNotFound,
    ProblemNotFound,
    UserNotFound,
    DuplicatedRegistration
}
