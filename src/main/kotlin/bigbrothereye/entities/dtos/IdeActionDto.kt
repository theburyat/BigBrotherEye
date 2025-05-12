package bigbrothereye.entities.dtos

import bigbrothereye.entities.enums.IdeActionType
import kotlinx.serialization.Serializable

@Serializable
class IdeActionDto(val type: IdeActionType, val message: String?, val detectTime: String, val sessionId: Int, val userId: Int)
