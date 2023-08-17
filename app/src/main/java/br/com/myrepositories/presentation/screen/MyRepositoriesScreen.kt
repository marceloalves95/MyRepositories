package br.com.myrepositories.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import br.com.myrepositories.domain.models.MyRepositories
import br.com.myrepositories.presentation.components.AppTopBar
import br.com.myrepositories.presentation.components.CardRepositoriesItem
import br.com.myrepositories.presentation.mock.dummyMyRepositories
import br.com.myrepositories.presentation.theme.MyRepositoriesTheme

@Composable
fun ScreenRepositoriesData(paddingValues: PaddingValues, myRepositories: List<MyRepositories>) {
    Column(modifier = Modifier.padding(paddingValues)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            items(
                items = myRepositories,
                itemContent = { content ->
                    CardRepositoriesItem(
                        image = content.owner?.avatarUrl,
                        title = content.name
                    )
                })
        }
    }
}

@Composable
fun ScreenRepositoriesError(paddingValues: PaddingValues, exception: Throwable?) {
    Column(modifier = Modifier.padding(paddingValues)) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = exception?.message ?: "Erro no carregamento dos dados",
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ScreenRepositoriesDataPreview() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    MyRepositoriesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    AppTopBar(scope, drawerState)
                },
                content = { padding ->
                    ScreenRepositoriesData(padding, myRepositories = listOf(dummyMyRepositories))
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ScreenRepositoriesErrorPreview() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    MyRepositoriesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    AppTopBar(scope, drawerState)
                },
                content = { padding ->
                    ScreenRepositoriesError(padding, exception = Throwable())
                }
            )
        }
    }
}