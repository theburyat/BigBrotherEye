package bigbrothereye.tracking.handlers

import bigbrothereye.entities.State
import bigbrothereye.entities.enums.IdeActionType
import bigbrothereye.helpers.IdeActionCreator
import com.intellij.codeInsight.editorActions.BackspaceHandler
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler

class TrackingBackspaceHandler(originalHandler: EditorActionHandler?) : BackspaceHandler(originalHandler) {
    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        if (State.tracking()) {
            val action = IdeActionCreator.create(IdeActionType.Delete)
            State.repository!!.add(action)
        }
        super.doExecute(editor, caret, dataContext)
    }
}