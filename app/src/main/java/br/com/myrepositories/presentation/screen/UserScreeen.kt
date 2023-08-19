package br.com.myrepositories.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.myrepositories.R
import br.com.myrepositories.domain.models.User
import br.com.myrepositories.presentation.components.AppTopBar
import br.com.myrepositories.presentation.components.NavHeader
import br.com.myrepositories.presentation.mock.dummyMyRepositories
import br.com.myrepositories.presentation.mock.dummyUser
import br.com.myrepositories.presentation.others.NavigationItem
import br.com.myrepositories.presentation.theme.MyRepositoriesTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenUserData(
    user: User
) {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    val items = listOf(
        NavigationItem(
            title = "Repositories",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            badgeCount = 53
        )
    )

    ModalDrawerSheet {
        NavHeader(
            image = user.avatarUrl,
            name = user.name,
            login = user.login,
            bio = user.bio,
            followers = "${user.followers} followers",
            location = user.location
        )
        Spacer(modifier = Modifier.height(16.dp))
        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = {
                    Text(text = item.title)
                },
                selected = index == selectedItemIndex,
                onClick = {
                    selectedItemIndex = index
                    scope.launch {
                        drawerState.close()
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.selectedIcon
                        } else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                badge = {
                    item.badgeCount?.let {
                        Text(text = item.badgeCount.toString())
                    }
                },
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenUserError(exception: Throwable?) {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    val items = listOf(
        NavigationItem(
            title = "Repositories",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            badgeCount = 53
        )
    )

    ModalDrawerSheet {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 64.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = exception?.message ?: stringResource(id = R.string.error_repository), fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = {
                    Text(text = item.title)
                },
                selected = index == selectedItemIndex,
                onClick = {
                    selectedItemIndex = index
                    scope.launch {
                        drawerState.close()
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.selectedIcon
                        } else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                badge = {
                    item.badgeCount?.let {
                        Text(text = item.badgeCount.toString())
                    }
                },
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ScreenUserDataPreview() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    MyRepositoriesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ModalNavigationDrawer(
                drawerContent = {
                    ScreenUserData(dummyUser)
                },
                drawerState = drawerState
            ) {
                Scaffold(
                    topBar = {
                        AppTopBar(scope, drawerState)
                    },
                    content = { padding ->
                        Column(modifier = Modifier.padding(padding)) {
                            ScreenRepositoriesData(listOf(dummyMyRepositories))
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ScreenUserErrorPreview() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    MyRepositoriesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ModalNavigationDrawer(
                drawerContent = {
                    ScreenUserError(Throwable())
                },
                drawerState = drawerState
            ) {
                Scaffold(
                    topBar = {
                        AppTopBar(scope, drawerState)
                    },
                    content = { padding ->
                        Column(modifier = Modifier.padding(padding)) {
                            ScreenRepositoriesError(Throwable())
                        }
                    }
                )
            }
        }
    }
}