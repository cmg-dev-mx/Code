package mx.dev.cmg.android.code

import android.app.Application
import mx.dev.cmg.android.code.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CodeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CodeApplication)
            modules(appModule)
        }
    }
}