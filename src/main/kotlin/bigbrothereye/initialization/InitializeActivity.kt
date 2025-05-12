package bigbrothereye.initialization

import bigbrothereye.initialization.initializers.Initializer
import bigbrothereye.initialization.initializers.impl.PluginInitializerImpl
import bigbrothereye.initialization.initializers.impl.TrackingHandlerInitializerImpl
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class InitializeActivity: StartupActivity {
    override fun runActivity(project: Project) {
        val initializers = arrayOf(PluginInitializerImpl(), TrackingHandlerInitializerImpl())
        initializers.forEach {
            it.initialize()
        }
    }
}
