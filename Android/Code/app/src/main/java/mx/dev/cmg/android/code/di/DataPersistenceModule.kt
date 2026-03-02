package mx.dev.cmg.android.code.di

import androidx.room.Room
import mx.dev.cmg.android.code.data.datasource.local.LocalDataSource
import mx.dev.cmg.android.code.data.datasource.local.LocalDataSourceImpl
import mx.dev.cmg.android.code.data.datasource.local.database.NoteDatabase
import mx.dev.cmg.android.code.data.repository.notes.NoteRepository
import mx.dev.cmg.android.code.data.repository.notes.NoteRepositoryImpl
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.DataPersistenceViewModel
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.NoteDetailViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val dataPersistenceModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = NoteDatabase::class.java,
            name = NoteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }
    single { get<NoteDatabase>().noteDao() }
    singleOf(::LocalDataSourceImpl) { bind<LocalDataSource>() }
    singleOf(::NoteRepositoryImpl) { bind<NoteRepository>() }
    viewModelOf(::DataPersistenceViewModel)
    viewModelOf(::NoteDetailViewModel)
}
