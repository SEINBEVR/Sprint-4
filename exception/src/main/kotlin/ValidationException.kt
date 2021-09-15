class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_LENGTH(101, "Недопустимая длина"),
    INVALID_SNILS_NUMBER(102, "Неверный номер СНИЛС"),
    CANNOT_BE_EMPTY(103, "Не может быть пустым")
}