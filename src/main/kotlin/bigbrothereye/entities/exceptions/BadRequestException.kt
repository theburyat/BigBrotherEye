package bigbrothereye.entities.exceptions

import bigbrothereye.entities.enums.ErrorCode

class BadRequestException(val code: ErrorCode, message: String): Exception(message)