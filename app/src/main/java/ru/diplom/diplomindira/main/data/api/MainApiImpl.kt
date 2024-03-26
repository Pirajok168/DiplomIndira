package ru.diplom.diplomindira.main.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import okio.FileSystem
import okio.Path.Companion.toPath
import ru.diplom.diplomindira.main.domain.model.CachedFile

class MainApiImpl(
    val client: HttpClient
): MainApi{
    override suspend fun sendAttachments(attachments: List<CachedFile>): HttpResponse {

        return client.post("uploadImage"){
            setBody(MultiPartFormDataContent(
                formData {
                    attachments.forEach {
                        val array = FileSystem.SYSTEM.read(it.sourceFile.toPath()) {
                            readByteArray()
                        }
                        append("files", array, Headers.build {
                            append(HttpHeaders.ContentType, it.type)
                            append(
                                HttpHeaders.ContentDisposition,
                                "filename=${it.name}.${it.ext}"
                            )
                        })
                    }
                }
            ))
            contentType(ContentType.Application.Json)
        }
    }
}