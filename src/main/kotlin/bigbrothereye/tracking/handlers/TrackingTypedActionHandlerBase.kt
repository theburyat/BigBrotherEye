package bigbrothereye.tracking.handlers

import bigbrothereye.entities.State
import bigbrothereye.entities.enums.IdeActionType
import bigbrothereye.helpers.IdeActionCreator
import com.intellij.codeInsight.template.impl.editorActions.TypedActionHandlerBase
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.TypedActionHandler

class TrackingTypedActionHandlerBase(originalHandler: TypedActionHandler) : TypedActionHandlerBase(originalHandler) {
    override fun execute(p0: Editor, p1: Char, p2: DataContext) {
        if (State.tracking()) {
            val action = IdeActionCreator.create(IdeActionType.Type, p1.toString())
            State.repository!!.add(action)
        }
        super.myOriginalHandler?.execute(p0, p1, p2)
    }
}