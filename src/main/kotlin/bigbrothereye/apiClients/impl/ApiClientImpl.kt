package bigbrothereye.apiClients.impl

import bigbrothereye.apiClients.ApiClient
import bigbrothereye.entities.IdeAction
import bigbrothereye.entities.dtos.ErrorDto
import bigbrothereye.entities.enums.ErrorCode
import bigbrothereye.entities.exceptions.BadRequestException
import bigbrothereye.helpers.IdeActionCreator
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class ApiClientImpl(private val serverUrl: String) : ApiClient {
    override fun connect(sessionId: Int, username: String): Result<Int> {
        val (_, response, result) = "$serverUrl/api/connections/sessions/$sessionId?username=$username"
            .httpPost()
            .responseString()
        return when (result) {
            is com.github.kittinunf.result.Result.Success -> {
                Result.success(result.value.toInt())
            }
            is com.github.kittinunf.result.Result.Failure -> {
                return try {
                    val error = Json.decodeFromString<ErrorDto>(response.body().toByteArray().decodeToString())
                    Result.failure(BadRequestException(error.code, error.message ?: ""))
                } catch (ex: Exception) {
                    println(ex.message)
                    Result.failure(BadRequestException(ErrorCode.Unknown, ""))
                }
            }
        }
    }

    override fun postIdeAction(ideAction: IdeAction): Result<Boolean> {
        val dto = IdeActionCreator.toDto(ideAction)

        val (_, response, result) = "$serverUrl/api/ideActions"
            .httpPost()
            .jsonBody(Json.encodeToString(dto))
            .timeout(10_000)
            .timeoutRead(10_000)
            .header("Content-Type", "application/json")
            .response()

        return when (result) {
            is com.github.kittinunf.result.Result.Success -> {
                Result.success(true)
            }

            is com.github.kittinunf.result.Result.Failure -> {
                try {
                    val error = Json.decodeFromString<ErrorDto>(response.body().toByteArray().decodeToString())
                    Result.failure(BadRequestException(error.code, error.message ?: ""))
                } catch (ex: Exception) {
                    println(ex.message)
                    Result.failure(BadRequestException(ErrorCode.Unknown, ""))
                }
            }
        }
    }

}