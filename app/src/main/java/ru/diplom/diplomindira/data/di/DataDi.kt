package ru.diplom.diplomindira.data.di

import org.kodein.di.DI
import org.kodein.di.LazyDI
import ru.diplom.diplomindira.data.web.webDi

val dataDi: DI = LazyDI {
    DI {
        importOnce(webDi)
    }
}