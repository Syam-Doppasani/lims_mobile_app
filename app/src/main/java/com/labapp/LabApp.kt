package com.labapp

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class LabApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Setup Timber logging
        Timber.plant(Timber.DebugTree())
        
        // Firestore offline persistence configuration
        val settings = firestoreSettings {
            isPersistenceEnabled = true
            cacheSizeBytes = FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED
        }
        Firebase.firestore.firestoreSettings = settings
    }
}
