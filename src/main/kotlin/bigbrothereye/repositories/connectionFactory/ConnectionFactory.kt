package bigbrothereye.repositories.connectionFactory

import java.sql.Connection

interface ConnectionFactory {
    fun getConnection(): Connection
}
