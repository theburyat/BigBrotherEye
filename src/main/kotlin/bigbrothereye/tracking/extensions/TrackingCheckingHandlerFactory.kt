package bigbrothereye.tracking.extensions

import bigbrothereye.tracking.handlers.TrackingCheckinHandler
import com.intellij.openapi.vcs.CheckinProjectPanel
import com.intellij.openapi.vcs.changes.CommitContext
import com.intellij.openapi.vcs.checkin.CheckinHandler
import com.intellij.openapi.vcs.checkin.CheckinHandlerFactory

class TrackingCheckingHandlerFactory: CheckinHandlerFactory() {
    override fun createHandler(p0: CheckinProjectPanel, p1: CommitContext): CheckinHandler {
        return TrackingCheckinHandler()
    }
}