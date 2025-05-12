package bigbrothereye.entities.enums

enum class ErrorCode {
    GroupNotFound,
    GroupAlreadyExists,

    SessionNotFound,
    SessionWasNotStarted,
    SessionWasNotFinished,
    SessionIsNotActive,

    UserNotFound,
    UserAlreadyExists,
    NotEnoughUsersForAnalysis,

    ScoreNotFound,
    InvalidScore,

    Unknown
}