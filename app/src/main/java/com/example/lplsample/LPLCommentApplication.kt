package com.example.lplsample

import android.app.Application
import com.example.lplsample.di.interactorModule
import com.example.lplsample.di.networkModule
import com.example.lplsample.di.repositoryModule
import com.example.lplsample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class LPLCommentApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LPLCommentApplication)
            modules(networkModule, repositoryModule, viewModelModule, interactorModule)
        }
    }
}