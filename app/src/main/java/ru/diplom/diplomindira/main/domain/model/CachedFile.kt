package ru.diplom.diplomindira.main.domain.model

data class CachedFile(
    val sourceFile: String,
    val uri: String,
    val name: String,
    val ext: String,
    val size: Long,
    val type: String
)