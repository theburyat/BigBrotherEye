package bigbrothereye.initialization.initializers.impl

import bigbrothereye.constants.Constants
import bigbrothereye.entities.State
import bigbrothereye.entities.enums.Status
import bigbrothereye.initialization.initializers.Initializer
import bigbrothereye.repositories.IdeActionsRepository
import bigbrothereye.repositories.connectionFactory.ConnectionFactory
import bigbrothereye.repositories.connectionFactory.impl.ConnectionFactoryImpl
import bigbrothereye.repositories.impl.IdeActionsRepositoryImpl
import com.github.benmanes.caffeine.cache.Caffeine
import java.io.File
import java.time.Duration

class PluginInitializerImpl: Initializer {
    override fun initialize() {
        State.repository = getRepository()
        State.copiedTextCache = Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(Duration.ofHours(1))
            .build()
        State.status = Status.INACTIVE
    }

    private fun getRepository(): IdeActionsRepository {
        val userHomeDir = System.getProperty("user.home")

        val pluginDir = File(userHomeDir, Constants.PluginDirectoryName)
        // todo error window
        pluginDir.mkdir()
        val pluginDbFile = File(pluginDir, Constants.PluginDbFileName)

        val connectionString = "jdbc:sqlite:$pluginDbFile"
        val connectionFactory: ConnectionFactory = ConnectionFactoryImpl(connectionString)
        val ideActionsRepository: IdeActionsRepository = IdeActionsRepositoryImpl(connectionFactory)

        return ideActionsRepository
    }
}
