package bigbrothereye.actions

import bigbrothereye.constants.Constants
import bigbrothereye.entities.State
import bigbrothereye.entities.enums.Status
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class FinishTrackingAction: AnAction() {
    override fun update(e: AnActionEvent) {
        val project = e.project
        e.presentation.isVisible = project != null && project.isOpen && State.status == Status.ACTIVE
        e.presentation.isEnabled = project != null && project.isOpen && State.status == Status.ACTIVE
    }

    override fun actionPerformed(p0: AnActionEvent) {

        State.status = Status.SAVING_RESULTS

        val commitedActions = State.repository!!.get()
        for (action in commitedActions) {
            println("${action.type} - ${action.message} - ${action.detectTime}")
            val result = State.httpClient!!.postIdeAction(action)
            result.onSuccess {
                State.repository!!.delete(action)
            }
            result.onFailure {
                Messages.showErrorDialog(Constants.ErrorFailedToPostActionsToServer, Constants.TitleConnectionFailure)
            }
            if (result.isFailure) {
                break
            }
        }

        State.reset()
    }
}