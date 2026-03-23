package mx.dev.cmg.android.code.di

import mx.dev.cmg.android.code.data.datasource.remoteconfig.RemoteConfigDataSource
import mx.dev.cmg.android.code.data.datasource.remoteconfig.RemoteConfigDataSourceImpl
import mx.dev.cmg.android.code.data.repository.color.ColorRepository
import mx.dev.cmg.android.code.data.repository.color.ColorRepositoryImpl
import mx.dev.cmg.android.code.data.repository.feature.FeatureRepository
import mx.dev.cmg.android.code.data.repository.feature.FeatureRepositoryImpl
import mx.dev.cmg.android.code.ui.theme.ThemeViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val remoteConfigModule = module {
    singleOf(::RemoteConfigDataSourceImpl) { bind<RemoteConfigDataSource>() }
    singleOf(::FeatureRepositoryImpl) { bind<FeatureRepository>() }
    singleOf(::ColorRepositoryImpl) { bind<ColorRepository>() }
    viewModelOf(::ThemeViewModel)
}
