package bigbrothereye.actions

import bigbrothereye.apiClients.impl.ApiClientImpl
import bigbrothereye.constants.Constants
import bigbrothereye.dialogs.ActivateTrackingDialogWrapper
import bigbrothereye.entities.SessionInfo
import bigbrothereye.entities.State
import bigbrothereye.entities.enums.ErrorCode
import bigbrothereye.entities.enums.Status
import bigbrothereye.entities.exceptions.BadRequestException
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class StartTrackingAction: AnAction() {
    override fun update(e: AnActionEvent) {
        val project = e.project
        e.presentation.isVisible = project != null && project.isOpen && State.status == Status.INACTIVE
        e.presentation.isEnabled = project != null && project.isOpen && State.status == Status.INACTIVE
    }

    override fun actionPerformed(p0: AnActionEvent) {
        val dialog = ActivateTrackingDialogWrapper()
        if (!dialog.showAndGet()) {
            return
        }

        val serverUrl = dialog.getServerUrl()
        val client = ApiClientImpl(serverUrl)

        val username = dialog.getUsername()
        val session = dialog.getSession()

        val connectResult = client.connect(session, username)
        connectResult.onSuccess {
            State.httpClient = client
            State.sessionInfo = SessionInfo(serverUrl, session, it)

            State.status = Status.ACTIVE

            if (State.repository!!.isEmpty()) {
                return
            }

            val saveOldActionsResponse = Messages.showYesNoDialog(p0.project, Constants.QuestionSaveOldActions,
                Constants.TitleConfirmation, Messages.getQuestionIcon())
            if (saveOldActionsResponse == Messages.NO) {
                State.repository!!.clear()
            }
        }
        connectResult.onFailure {
            if (it is BadRequestException) {
                when (it.code) {
                    ErrorCode.SessionNotFound -> Messages.showErrorDialog(if (it.message.isNullOrBlank()) Constants.ErrorSessionNotFound else it.message,
                        Constants.TitleConnectionFailure)
                    ErrorCode.SessionIsNotActive -> Messages.showErrorDialog(if (it.message.isNullOrBlank()) Constants.ErrorSessionIsNotActive else it.message,
                        Constants.TitleConnectionFailure)
                    else -> Messages.showErrorDialog(if (it.message.isNullOrBlank()) Constants.ErrorConnectionFailure else it.message,
                        Constants.TitleConnectionFailure)
                }
            } else {
                Messages.showErrorDialog(it?.message ?: Constants.ErrorConnectionFailure,
                    Constants.TitleConnectionFailure)
            }
        }
    }
}
