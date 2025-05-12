package bigbrothereye.helpers

import bigbrothereye.entities.IdeAction
import bigbrothereye.entities.State
import bigbrothereye.entities.dtos.IdeActionDto
import bigbrothereye.entities.enums.IdeActionType
import java.time.Instant
import java.util.UUID

object IdeActionCreator {
    fun create(type: IdeActionType, message: String? = ""): IdeAction {
        return IdeAction(UUID.randomUUID(), type, message, Instant.now())
    }

    fun toDto(ideAction: IdeAction): IdeActionDto {
        return IdeActionDto(ideAction.type, ideAction.message, ideAction.detectTime.toString(), State.sessionInfo!!.sessionId, State.sessionInfo!!.userId)
    }
}
