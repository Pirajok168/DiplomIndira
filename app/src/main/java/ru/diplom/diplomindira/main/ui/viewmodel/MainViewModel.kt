package ru.diplom.diplomindira.main.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.instance
import ru.diplom.diplomindira.main.domain.repository.MainRepository
import ru.diplom.diplomindira.main.ui.di.mainDi
import ru.diplom.diplomindira.main.ui.events.MainEvents
import ru.diplom.diplomindira.main.ui.state.MainScreenState

class MainViewModel: ViewModel() {

    var viewState by mutableStateOf(MainScreenState(emptyList()))
        private set

    private val di: DI by lazy { mainDi() }

    private val mainRepository: MainRepository by di.instance<MainRepository>()

    fun onEvent(event: MainEvents){
        when(event){
            is MainEvents.PassAttachments -> {
                val attachments = event.attachments
                val newAttachments =
                    (viewState.attachments + attachments).distinctBy { cachedFile -> cachedFile.uri }

                viewState = viewState.copy(attachments = newAttachments)
            }

            MainEvents.SendAttachments -> {
                viewModelScope.launch {
                    mainRepository.sendAttachments(attachments = viewState.attachments)
                }

            }
        }
    }
}