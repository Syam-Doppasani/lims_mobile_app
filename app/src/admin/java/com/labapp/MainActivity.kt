package com.labapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.labapp.presentation.admin.AdminNavGraph
import com.labapp.presentation.theme.LabAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabAppTheme {
                AdminNavGraph()
            }
        }
    }
}
