package bigbrothereye.entities.dtos

import bigbrothereye.entities.enums.ErrorCode
import kotlinx.serialization.Serializable

@Serializable
class ErrorDto(val code: ErrorCode, val message: String?)