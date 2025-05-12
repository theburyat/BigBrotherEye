package bigbrothereye.apiClients

import bigbrothereye.entities.IdeAction

interface ApiClient {
    fun connect(sessionId: Int, username: String): Result<Int>

    fun postIdeAction(ideAction: IdeAction): Result<Boolean>
}