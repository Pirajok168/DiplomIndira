package ru.diplom.diplomindira.main.data.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.diplom.diplomindira.main.data.api.MainApi
import ru.diplom.diplomindira.main.data.api.MainApiImpl

fun dataDiModule() = DI.Module("dataDiModule"){
    bind<MainApi>() with singleton {
        MainApiImpl(
            instance()
        )
    }
}