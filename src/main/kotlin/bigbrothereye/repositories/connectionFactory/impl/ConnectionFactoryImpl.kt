package bigbrothereye.repositories.connectionFactory.impl

import bigbrothereye.repositories.connectionFactory.ConnectionFactory
import java.sql.Connection
import java.sql.DriverManager

class ConnectionFactoryImpl(private val connectionString: String) : ConnectionFactory {
    override fun getConnection(): Connection {
        Class.forName("org.sqlite.JDBC")
        return DriverManager.getConnection(connectionString)
    }
}
