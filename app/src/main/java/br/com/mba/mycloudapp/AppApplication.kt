package br.com.mba.mycloudapp

import android.app.Application
import br.com.mba.mycloudapp.di.firebaseModule
import br.com.mba.mycloudapp.di.repositoryModule
import br.com.mba.mycloudapp.di.viewModelModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this.applicationContext)

        startKoin {
            androidContext(this@AppApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    firebaseModule
                )
            )
        }
    }

}