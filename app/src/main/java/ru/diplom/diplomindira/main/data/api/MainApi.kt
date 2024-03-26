package ru.diplom.diplomindira.main.data.api

import io.ktor.client.statement.HttpResponse
import ru.diplom.diplomindira.main.domain.model.CachedFile

interface MainApi {
    suspend fun sendAttachments(
        attachments: List<CachedFile>
    ): HttpResponse
}