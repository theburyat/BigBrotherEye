package bigbrothereye.constants

object Constants {
    const val PluginDbFileName = "bigbrother.db"
    const val PluginDirectoryName = ".bigbrothereye"

    const val LabelServerUri = "Big Brother server URI:"
    const val LabelSessionId = "Session identifier:"
    const val LabelUsername = "Username:"

    const val ErrorMustNotBeEmpty = "Must not be empty"
    const val ErrorInvalidFormat = "Invalid format"
    const val ErrorInvalidUri = "Invalid URI"
    const val ErrorConnectionFailure = "Failed to connect to Big Brother server"
    const val ErrorSessionNotFound = "Session was not found"
    const val ErrorSessionIsNotActive = "Session is not active"
    const val ErrorFailedToPostActionsToServer = "Failed to send tracked actions to Big Brother server"

    const val QuestionSaveOldActions = "Actions from last work session were found. Do you want to save it?"

    const val TitleConnectionFailure = "Connection failure"
    const val TitleConfirmation = "Confirmation"
}
