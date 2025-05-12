package bigbrothereye.tracking.handlers

import bigbrothereye.entities.State
import bigbrothereye.entities.enums.IdeActionType
import bigbrothereye.helpers.IdeActionCreator
import com.intellij.openapi.vcs.checkin.CheckinHandler

class TrackingCheckinHandler : CheckinHandler() {
    override fun checkinSuccessful() {
        if (State.tracking()) {
            val action = IdeActionCreator.create(IdeActionType.Commit)
            State.repository!!.add(action)
        }
    }
}