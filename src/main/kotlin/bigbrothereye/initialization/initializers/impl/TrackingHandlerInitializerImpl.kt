package bigbrothereye.initialization.initializers.impl

import bigbrothereye.initialization.initializers.Initializer
import bigbrothereye.tracking.handlers.TrackingBackspaceHandler
import bigbrothereye.tracking.handlers.TrackingTypedActionHandlerBase
import com.intellij.openapi.actionSystem.IdeActions
import com.intellij.openapi.editor.actionSystem.EditorActionManager

class TrackingHandlerInitializerImpl: Initializer {
    override fun initialize() {
        val e = EditorActionManager.getInstance()

        e.setActionHandler(
            IdeActions.ACTION_EDITOR_BACKSPACE,
            TrackingBackspaceHandler(e.getActionHandler(IdeActions.ACTION_EDITOR_BACKSPACE))
        )

        val t = e.typedAction
        t.setupHandler(TrackingTypedActionHandlerBase(t.handler))
    }
}
