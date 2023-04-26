package com.a9992099300.vkclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.a9992099300.vkclient.ui.theme.ActivityResultTest
import com.a9992099300.vkclient.ui.theme.VKClientTheme
import com.a9992099300.vkclient.ui.theme.VKViewMainScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VKClientTheme {
                ActivityResultTest()
               // VKViewMainScreen()
            }
        }
    }
}

@Composable
private fun Test() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "To bar title")
                }, navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
           BottomNavigation {
               BottomNavigationItem(
                   selected = true,
                   onClick = { /*TODO*/ },
                   icon = {
                       Icon(Icons.Filled.AccountBox, contentDescription = null)
                   },
                   label = {
                       Text(text = "Favorite")
                   }
               )
               BottomNavigationItem(
                   selected = true,
                   onClick = { /*TODO*/ },
                   icon = {
                       Icon(Icons.Filled.Edit, contentDescription = null)
                   },
                   label = {
                       Text(text = "Edit")
                   }
               )
               BottomNavigationItem(
                   selected = true,
                   onClick = { /*TODO*/ },
                   icon = {
                       Icon(Icons.Filled.Delete, contentDescription = null)
                   },
                   label = {
                       Text(text = "Delete")
                   }
               )
           }
        },
        drawerContent = {
            Text(text = "text 1")
            Text(text = "text 2")
            Text(text = "text 3")
        }
    ) {
        Text(
            modifier = Modifier.padding(it),
            text = "Text"
        )
    }
}