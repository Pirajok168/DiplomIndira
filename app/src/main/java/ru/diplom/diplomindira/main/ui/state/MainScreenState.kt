package ru.diplom.diplomindira.main.ui.state

import ru.diplom.diplomindira.main.domain.model.CachedFile

data class MainScreenState(
    val attachments: List<CachedFile>
)
