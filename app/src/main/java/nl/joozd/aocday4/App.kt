package nl.joozd.aocday4

import android.app.Application

class App : Application(){
    companion object {
        private var instanceHolder: App? = null

        val instance: App
            get() = instanceHolder ?: error ("app is not running (?)")
    }

    override fun onCreate() {
        super.onCreate()
        instanceHolder = this
    }
}