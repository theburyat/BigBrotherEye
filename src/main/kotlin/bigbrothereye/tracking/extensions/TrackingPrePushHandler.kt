package bigbrothereye.tracking.extensions

import bigbrothereye.entities.State
import bigbrothereye.entities.enums.IdeActionType
import bigbrothereye.helpers.IdeActionCreator
import com.intellij.dvcs.push.PrePushHandler
import com.intellij.dvcs.push.PushInfo
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.project.Project

class TrackingPrePushHandler : PrePushHandler {
    override fun getPresentableName(): String {
        return "bigbrothereye.push"
    }

    override fun handle(
        project: Project,
        pushDetails: MutableList<PushInfo>,
        indicator: ProgressIndicator
    ): PrePushHandler.Result {
        if (State.tracking()) {
            val action = IdeActionCreator.create(IdeActionType.Push)
            State.repository!!.add(action)
        }
        return PrePushHandler.Result.OK
    }
}