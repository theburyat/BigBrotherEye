package bigbrothereye.tracking.extensions

import bigbrothereye.entities.State
import bigbrothereye.entities.enums.IdeActionType
import bigbrothereye.helpers.IdeActionCreator
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiFile

class TrackingEnterHandlerDelegate : EnterHandlerDelegate {
    override fun preprocessEnter(
        p0: PsiFile,
        p1: Editor,
        p2: Ref<Int>,
        p3: Ref<Int>,
        p4: DataContext,
        p5: EditorActionHandler?
    ): EnterHandlerDelegate.Result {
        return EnterHandlerDelegate.Result.Continue
    }

    override fun postProcessEnter(p0: PsiFile, p1: Editor, p2: DataContext): EnterHandlerDelegate.Result {
        if (State.tracking()) {
            val action = IdeActionCreator.create(IdeActionType.Enter)
            State.repository!!.add(action)

            val existed = State.repository!!.get()
            existed.forEach {
                println("${it.type} ${it.message} ${it.detectTime}")
            }
        }
        return EnterHandlerDelegate.Result.Continue
    }
}