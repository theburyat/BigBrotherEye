package bigbrothereye.tracking.listeners

import bigbrothereye.entities.State
import bigbrothereye.entities.enums.IdeActionType
import bigbrothereye.helpers.IdeActionCreator
import com.intellij.openapi.command.CommandEvent
import com.intellij.openapi.command.CommandListener

class TrackingLookupCommandListener : CommandListener {
    override fun commandFinished(event: CommandEvent) {
        if (State.tracking()) {
            if (event.commandName.lowercase().startsWith("undo")) {
                val action = IdeActionCreator.create(IdeActionType.Undo)
                State.repository!!.add(action)
            }
        }
    }
}
