package bigbrothereye.entities

import bigbrothereye.apiClients.ApiClient
import bigbrothereye.entities.enums.Status
import bigbrothereye.repositories.IdeActionsRepository
import com.github.benmanes.caffeine.cache.Cache
import io.ktor.client.*

object State {
    @Volatile
    var status: Status = Status.NONE

    @Volatile
    var copiedTextCache: Cache<Int, String>? = null

    @Volatile
    var repository: IdeActionsRepository? = null

    @Volatile
    var httpClient: ApiClient? = null

    @Volatile
    var sessionInfo: SessionInfo? = null

    fun tracking(): Boolean {
        return status == Status.ACTIVE
                && copiedTextCache != null
                && repository != null
                && httpClient != null
                && sessionInfo != null
    }

    fun reset() {
        status = Status.INACTIVE
        copiedTextCache!!.invalidateAll()
        httpClient = null
        sessionInfo = null
    }
}
