package bigbrothereye.repositories.impl

import bigbrothereye.entities.IdeAction
import bigbrothereye.entities.enums.IdeActionType
import bigbrothereye.repositories.IdeActionsRepository
import bigbrothereye.repositories.connectionFactory.ConnectionFactory
import java.time.Instant
import java.util.UUID

class IdeActionsRepositoryImpl(private val connectionFactory: ConnectionFactory) : IdeActionsRepository {

    private val TableName: String = "IdeActions"

    init {
        connectionFactory.getConnection().use { connection ->
            val statement = connection.createStatement()
            statement.execute("CREATE TABLE IF NOT EXISTS $TableName (id TEXT, type TEXT, message TEXT, detectTime TEXT);")
        }
    }

    override fun isEmpty(): Boolean {
        var count = 0
        connectionFactory.getConnection().use { connection ->
            connection.createStatement().use { statement ->
                statement.executeQuery("SELECT COUNT(*) FROM $TableName;").use { resultSet ->
                    while (resultSet.next()) {
                        count = resultSet.getString("COUNT(*)").toInt()
                    }
                }
            }
        }
        return count == 0
    }

    override fun get(limit: Int?): Iterable<IdeAction> {
        val result = mutableListOf<IdeAction>()
        connectionFactory.getConnection().use { connection ->
            connection.createStatement().use { statement ->
                statement.executeQuery("SELECT * FROM $TableName;").use { resultSet ->
                    while (resultSet.next()) {
                        val id = UUID.fromString(resultSet.getString("id"))
                        val type = IdeActionType.valueOf(resultSet.getString("type"))
                        val message: String? = resultSet.getString("message")
                        val detectTime = Instant.parse(resultSet.getString("detectTime"))

                        result.add(IdeAction(id, type, message, detectTime))
                    }
                }
            }
        }
        return result
    }

    override fun add(ideAction: IdeAction) {
        connectionFactory.getConnection().use { connection ->
            connection.prepareStatement("INSERT INTO $TableName (id, type, message, detectTime) VALUES (?,?,?,?);").use { statement ->
                statement.setString(1, ideAction.id.toString())
                statement.setString(2, ideAction.type.toString())
                statement.setString(3, ideAction.message)
                statement.setString(4, ideAction.detectTime.toString())
                statement.execute()
            }
        }
    }

    override fun delete(ideAction: IdeAction) {
        connectionFactory.getConnection().use { connection ->
            connection.prepareStatement("DELETE FROM $TableName WHERE id = ?;").use { statement ->
                statement.setString(1, ideAction.id.toString())
                statement.execute()
            }
        }
    }

    override fun clear() {
        connectionFactory.getConnection().use { connection ->
            connection.prepareStatement("DELETE FROM $TableName;").use { statement ->
                statement.execute()
            }
        }
    }
}
