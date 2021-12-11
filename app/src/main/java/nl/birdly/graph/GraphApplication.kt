package nl.birdly.graph

import android.app.Application
import timber.log.Timber

class GraphApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant()
    }
}