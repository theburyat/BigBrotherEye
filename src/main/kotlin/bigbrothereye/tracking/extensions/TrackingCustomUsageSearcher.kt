package bigbrothereye.tracking.extensions

import bigbrothereye.entities.State
import bigbrothereye.entities.enums.IdeActionType
import bigbrothereye.helpers.IdeActionCreator
import com.intellij.find.findUsages.CustomUsageSearcher
import com.intellij.find.findUsages.FindUsagesOptions
import com.intellij.psi.PsiElement
import com.intellij.usages.Usage
import com.intellij.util.Processor

class TrackingCustomUsageSearcher : CustomUsageSearcher() {
    override fun processElementUsages(p0: PsiElement, p1: Processor<in Usage>, p2: FindUsagesOptions) {
        if (State.tracking()) {
            val action = IdeActionCreator.create(IdeActionType.FindUsages)
            State.repository!!.add(action)
        }
    }
}