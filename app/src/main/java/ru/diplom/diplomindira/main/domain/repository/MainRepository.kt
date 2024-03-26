package ru.diplom.diplomindira.main.domain.repository

import ru.diplom.diplomindira.main.domain.model.CachedFile

interface MainRepository {

    suspend fun sendAttachments(
        attachments: List<CachedFile>
    )
}