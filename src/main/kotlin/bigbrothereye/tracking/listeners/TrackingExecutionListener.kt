package bigbrothereye.tracking.listeners

import bigbrothereye.entities.State
import bigbrothereye.entities.enums.IdeActionType
import bigbrothereye.helpers.IdeActionCreator
import com.intellij.execution.ExecutionListener
import com.intellij.execution.runners.ExecutionEnvironment

class TrackingExecutionListener : ExecutionListener {
    override fun processStartScheduled(executorId: String, env: ExecutionEnvironment) {
        if (State.tracking()) {
            if (env.toString() == "Build ${env.project.name}") {
                val action = IdeActionCreator.create(IdeActionType.Build)
                State.repository!!.add(action)
            } else if (env.toString() == env.runProfile.name) {
                val action = IdeActionCreator.create(IdeActionType.Run)
                State.repository!!.add(action)
            }
        }
    }
}