package com.a9992099300.vkclient.samples

import android.os.Handler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun SideEffectTest(myNumber: MyNumber) {
    Column() {
        LazyColumn {
            repeat(5) {
                item {
                    Text(text = "Number ${myNumber.a}")
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        myNumber.a = 5
        LazyColumn {
            repeat(5) {
                item {
                    Text(text = "Number ${myNumber.a}")
                }
            }
        } 
    }
 
}

data class MyNumber(
    var a: Int)
