package bigbrothereye.tracking.extensions

import bigbrothereye.entities.State
import bigbrothereye.entities.enums.IdeActionType
import bigbrothereye.helpers.IdeActionCreator
import com.intellij.codeInsight.editorActions.CopyPastePreProcessor
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.RawText
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

class TrackingCopyPastePreProcessor : CopyPastePreProcessor {
    override fun preprocessOnCopy(
        file: PsiFile?,
        startOffsets: IntArray?,
        endOffsets: IntArray?,
        text: String?
    ): String? {
        if (text != null && State.tracking()) {
            State.copiedTextCache!!.put(text.hashCode(), text)

            val action = IdeActionCreator.create(IdeActionType.Copy, text)
            State.repository!!.add(action)
        }

        return text
    }

    override fun preprocessOnPaste(
        project: Project?,
        file: PsiFile?,
        editor: Editor?,
        text: String?,
        rawText: RawText?
    ): String {
        if (text != null && State.tracking()) {
            val isTextCopiedLocally = State.copiedTextCache!!.getIfPresent(text.hashCode()) != null
            if (isTextCopiedLocally) {
                val action = IdeActionCreator.create(IdeActionType.Paste, text)
                State.repository!!.add(action)
            } else {
                val action = IdeActionCreator.create(IdeActionType.PasteFromExternal, text)
                State.repository!!.add(action)
            }
        }

        return text ?: ""
    }
}