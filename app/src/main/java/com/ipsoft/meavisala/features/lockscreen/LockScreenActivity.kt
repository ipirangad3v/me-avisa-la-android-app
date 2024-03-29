package com.ipsoft.meavisala.features.lockscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ipsoft.meavisala.R
import com.ipsoft.meavisala.core.ui.theme.MeAvisaLaTheme
import com.ipsoft.meavisala.core.utils.extensions.turnScreenOffAndKeyguardOn
import com.ipsoft.meavisala.core.utils.extensions.turnScreenOnAndKeyguardOff
import com.ipsoft.meavisala.features.soundalarm.ALARM_DESCRIPTION

class LockScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeAvisaLaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FullScreenContent()
                }
            }
        }
        turnScreenOnAndKeyguardOff()
    }

    @Composable
    fun FullScreenContent() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.background_location_reached_notification_title),
                style = MaterialTheme.typography.headlineMedium
            )
            intent.getStringExtra(ALARM_DESCRIPTION)?.let {
                Text(text = it, style = MaterialTheme.typography.bodyMedium)
            }
            Button(onClick = { finish() }) {
                Text(
                    text = getString(R.string.ok),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        turnScreenOffAndKeyguardOn()
    }
}
