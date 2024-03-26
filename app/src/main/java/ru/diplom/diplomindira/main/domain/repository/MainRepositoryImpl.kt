package ru.diplom.diplomindira.main.domain.repository

import ru.diplom.diplomindira.main.data.api.MainApi
import ru.diplom.diplomindira.main.domain.model.CachedFile

class MainRepositoryImpl(
    val api: MainApi
): MainRepository{

    override suspend fun sendAttachments(attachments: List<CachedFile>) {
        api.sendAttachments(attachments)
    }
}