package bigbrothereye.entities

import bigbrothereye.entities.enums.IdeActionType
import java.time.Instant
import java.util.UUID

class IdeAction(val id: UUID, val type: IdeActionType, val message: String?, val detectTime: Instant)
