package bigbrothereye.tracking.listeners

import bigbrothereye.entities.State
import bigbrothereye.entities.enums.IdeActionType
import bigbrothereye.helpers.IdeActionCreator
import com.intellij.codeInsight.lookup.Lookup
import com.intellij.codeInsight.lookup.LookupManagerListener

class TrackingLookupManagerListener : LookupManagerListener {
    override fun activeLookupChanged(oldLookup: Lookup?, newLookup: Lookup?) {
        if (State.tracking()) {
            if (oldLookup != null && (oldLookup.isSelectionTouched)) {
                val action = IdeActionCreator.create(IdeActionType.CompleteCode)
                State.repository!!.add(action)
            }
        }
    }
}