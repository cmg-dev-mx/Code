package mx.dev.cmg.android.code.di

import mx.dev.cmg.android.code.data.datasource.remoteconfig.RemoteConfigDataSource
import mx.dev.cmg.android.code.data.datasource.remoteconfig.RemoteConfigDataSourceImpl
import mx.dev.cmg.android.code.data.repository.feature.FeatureRepository
import mx.dev.cmg.android.code.data.repository.feature.FeatureRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val remoteConfigModule = module {
    singleOf(::RemoteConfigDataSourceImpl) { bind<RemoteConfigDataSource>() }
    singleOf(::FeatureRepositoryImpl) { bind<FeatureRepository>() }
}
