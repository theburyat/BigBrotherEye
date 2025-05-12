package bigbrothereye.tracking.extensions

import bigbrothereye.entities.State
import bigbrothereye.entities.enums.IdeActionType
import bigbrothereye.helpers.IdeActionCreator
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.impl.source.codeStyle.PreFormatProcessor

class TrackingPreFormatProcessor : PreFormatProcessor {
    override fun process(p0: ASTNode, p1: TextRange): TextRange {
        if (State.tracking()) {
            val action = IdeActionCreator.create(IdeActionType.FormatCode)
            State.repository!!.add(action)
        }
        return p1
    }
}