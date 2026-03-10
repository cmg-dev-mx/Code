package mx.dev.cmg.android.code.di

import mx.dev.cmg.android.code.data.datasource.api.ApiDataSource
import mx.dev.cmg.android.code.data.datasource.api.ApiDataSourceImpl
import mx.dev.cmg.android.code.data.datasource.api.ApiService
import mx.dev.cmg.android.code.data.datasource.api.RetrofitClient
import mx.dev.cmg.android.code.data.repository.api.RestRepository
import mx.dev.cmg.android.code.data.repository.api.RestRepositoryImpl
import mx.dev.cmg.android.code.ui.feature.rest.viewmodel.RestViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val restModule = module {
    single<ApiService> {
        RetrofitClient.client.create(ApiService::class.java)
    }
    singleOf(::ApiDataSourceImpl) { bind<ApiDataSource>() }
    singleOf(::RestRepositoryImpl) { bind<RestRepository>() }
    viewModelOf(::RestViewModel)
}