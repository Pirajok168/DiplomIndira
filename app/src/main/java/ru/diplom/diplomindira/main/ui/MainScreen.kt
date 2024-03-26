package ru.diplom.diplomindira.main.ui

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.diplom.diplomindira.main.ui.component.NewAttachment
import ru.diplom.diplomindira.main.ui.events.MainEvents
import ru.diplom.diplomindira.main.ui.ext.cacheToFile
import ru.diplom.diplomindira.main.ui.viewmodel.MainViewModel

@Composable
fun MainScreen() {
    val mainViewModel: MainViewModel = viewModel()
    val state = mainViewModel.viewState
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            val context = LocalContext.current
            val fileLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        val resultUris = result.data?.run {
                            when (val uris = clipData) {
                                null ->
                                    listOfNotNull(data)

                                else ->
                                    (0 until uris.itemCount).mapNotNull { index -> uris.getItemAt(index).uri }
                            }
                        } ?: return@rememberLauncherForActivityResult

                        val files = resultUris.mapNotNull { it.cacheToFile(context) }

                        mainViewModel.onEvent(MainEvents.PassAttachments(files))
                    }
                }
            Button(onClick = {
                fileLauncher.launch(Intent(Intent.ACTION_GET_CONTENT).apply {
                    type = "*/*"
                    putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                })
            }) {
                Text(text = "Загрузить изображение")
            }

            LazyRow{
                items(state.attachments){
                    NewAttachment(
                        title = it.name,
                        uri =  it.uri,
                    )
                }
            }

            Button(onClick = {
                mainViewModel.onEvent(MainEvents.SendAttachments)
            }) {
                Text(text = "Отправить")
            }


        }
    }
}