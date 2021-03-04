/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.pavi2410.adomptcheems

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import me.pavi2410.adomptcheems.data.PetRepository
import me.pavi2410.adomptcheems.ui.screens.DetailScreen
import me.pavi2410.adomptcheems.ui.screens.HomeScreen
import me.pavi2410.adomptcheems.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun MyApp() {
    val navController = rememberNavController()

    Surface(color = MaterialTheme.colors.background) {
        NavHost(navController, startDestination = "home") {
            composable("home") {
                HomeScreen(
                    onCardClick = {
                        navController.navigate("detail/${it.id}")
                    }
                )
            }
            composable(
                "detail/{puppyId}",
                arguments = listOf(navArgument("puppyId") { type = NavType.IntType })
            ) { backStackEntry ->
                val puppyId = backStackEntry.arguments?.getInt("puppyId") ?: 1
                val puppy = PetRepository.puppies[puppyId]
                DetailScreen(
                    puppy,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
