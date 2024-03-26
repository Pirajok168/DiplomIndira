package ru.diplom.diplomindira.main.domain.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.diplom.diplomindira.main.data.api.MainApi
import ru.diplom.diplomindira.main.data.api.MainApiImpl
import ru.diplom.diplomindira.main.data.di.dataDiModule
import ru.diplom.diplomindira.main.domain.repository.MainRepository
import ru.diplom.diplomindira.main.domain.repository.MainRepositoryImpl

fun domainDiModule() = DI.Module("DomainDiModule"){
    importOnce(dataDiModule())
    bind<MainRepository>() with singleton {
        MainRepositoryImpl(
            instance()
        )
    }
}