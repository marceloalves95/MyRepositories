package br.com.myrepositories.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import br.com.myrepositories.presentation.components.AppTopBar
import br.com.myrepositories.presentation.model.MyRepositoriesState
import br.com.myrepositories.presentation.model.UserState
import br.com.myrepositories.presentation.screen.ScreenRepositoriesData
import br.com.myrepositories.presentation.screen.ScreenRepositoriesError
import br.com.myrepositories.presentation.screen.ScreenUserData
import br.com.myrepositories.presentation.screen.ScreenUserError
import br.com.myrepositories.presentation.theme.MyRepositoriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.apply {
            loadAllRepositories()
            loadUser()
        }
        setContent {
            ContentScreen(mainViewModel)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ContentScreen(
        viewModel: MainViewModel
    ) {

        val state by viewModel.stateRepositories.collectAsState()
        val stateUser by viewModel.stateUser.collectAsState()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        MyRepositoriesTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ModalNavigationDrawer(
                    drawerContent = {
                        LoadUserState(stateUser)
                    },
                    drawerState = drawerState
                ) {
                    Scaffold(
                        topBar = {
                            AppTopBar(scope, drawerState)
                        },
                        content = { padding ->
                            Column(modifier = Modifier.padding(padding)) {
                                LoadRepositoriesState(state)
                            }
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun LoadUserState(state: UserState) {
        when (state) {
            is UserState.ScreenData -> ScreenUserData(state.screenData)
            is UserState.Error -> ScreenUserError(state.exception)
            else -> Unit
        }
    }

    @Composable
    fun LoadRepositoriesState(state: MyRepositoriesState) {
        when (state) {
            is MyRepositoriesState.ScreenData -> ScreenRepositoriesData(state.screenData)
            is MyRepositoriesState.Error -> ScreenRepositoriesError(state.exception)
            else -> Unit
        }
    }
}