package ru.diplom.diplomindira.main.ui.events

import ru.diplom.diplomindira.main.domain.model.CachedFile

sealed class MainEvents {
    data class PassAttachments(val attachments: List<CachedFile>) : MainEvents()

    data object SendAttachments : MainEvents()
}