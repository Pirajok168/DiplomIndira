package ru.diplom.diplomindira.main.ui.di

import org.kodein.di.DI
import ru.diplom.diplomindira.data.di.dataDi
import ru.diplom.diplomindira.main.domain.di.domainDiModule

fun mainDi() = DI {
    extend(dataDi)
    importOnce(domainDiModule())
}