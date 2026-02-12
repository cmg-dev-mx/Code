package mx.dev.cmg.android.code.di

import mx.dev.cmg.android.code.repository.feature.FeatureRepository
import mx.dev.cmg.android.code.repository.feature.FeatureRepositoryImpl
import mx.dev.cmg.android.code.source.remoteconfig.RemoteConfigSource
import mx.dev.cmg.android.code.source.remoteconfig.RemoteConfigSourceImpl
import mx.dev.cmg.android.code.ui.feature.main.viewmodel.MainViewModel
import mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel.NameListViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    // Sources
    singleOf(::RemoteConfigSourceImpl) { bind<RemoteConfigSource>() }

    // Repositories
    singleOf(::FeatureRepositoryImpl) { bind<FeatureRepository>() }

    // ViewModels
    viewModelOf(::MainViewModel)
    viewModelOf(::NameListViewModel)
}