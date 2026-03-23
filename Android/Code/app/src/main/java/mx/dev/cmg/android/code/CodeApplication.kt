package mx.dev.cmg.android.code

import android.app.Application
import mx.dev.cmg.android.code.di.aiModule
import mx.dev.cmg.android.code.di.dataPersistenceModule
import mx.dev.cmg.android.code.di.mainModule
import mx.dev.cmg.android.code.di.remoteConfigModule
import mx.dev.cmg.android.code.di.restModule
import mx.dev.cmg.android.code.di.sharedPreferencesModule
import mx.dev.cmg.android.code.di.webModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CodeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CodeApplication)
            modules(
                sharedPreferencesModule,
                remoteConfigModule,
                dataPersistenceModule,
                mainModule,
                restModule,
                webModule,
                aiModule
            )
        }
    }
}