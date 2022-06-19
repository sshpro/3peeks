package com.sshpro.threepeeks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sshpro.threepeeks.business.DataState
import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.compose.MainView
import com.sshpro.threepeeks.compose.TopBarView
import com.sshpro.threepeeks.compose.collectAsStateLifecycleAware
import com.sshpro.threepeeks.ui.theme.ThreePeeksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThreePeeksTheme {
                Scaffold(
                    topBar = { TopBarView(title = stringResource(id = R.string.title_albums)) },
                ) {
                    MainView(dataState = dataState())
                }
            }
        }
    }

    @Composable
    private fun dataState(): DataState<Album> {
        return viewModel
            .albums.collectAsStateLifecycleAware(initial = DataState.Loading)
            .value
    }
}

