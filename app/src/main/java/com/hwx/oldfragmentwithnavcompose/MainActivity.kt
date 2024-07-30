package com.hwx.oldfragmentwithnavcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp),
                topBar = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.clickable {
                                if (navController.currentDestination?.route?.contains("NumberOneFragmentNav") == true) {
                                    finish()
                                } else {
                                    navController.popBackStack()
                                }
                            },
                            text = "Back!",
                            fontSize = 20.sp
                        )
                        Text(
                            modifier = Modifier.clickable {
                                if (navController.currentDestination?.route?.contains("NumberTwoFragmentNav") == false) {
                                    navController.navigate(NumberTwoFragmentNav("Hello I'm From Fragment One!"))
                                }
                            },
                            text = "Next!",
                            fontSize = 20.sp
                        )
                    }
                },
                content = { paddingValue ->
                    NavHost(
                        modifier = Modifier.padding(paddingValue),
                        navController = navController,
                        startDestination = NumberOneFragmentNav
                    ) {
                        composable<NumberOneFragmentNav> {
                            AndroidViewFragment(
                                modifier = Modifier.fillMaxSize(),
                                fragmentId = R.id.fl_fragment_number_one,
                                fragment = NumberOneFragment.newInstance(),
                                fragmentManager = supportFragmentManager,
                                backPressHandler = {
                                    finish()
                                }
                            )
                        }
                        composable<NumberTwoFragmentNav> {
                            val data = it.toRoute<NumberTwoFragmentNav>()
                            AndroidViewFragment(
                                modifier = Modifier.fillMaxSize(),
                                fragmentId = R.id.fl_fragment_number_two,
                                fragment = NumberTwoFragment.newInstance(data.param),
                                fragmentManager = supportFragmentManager,
                                backPressHandler = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            )
        }
    }
}

@Serializable
object NumberOneFragmentNav

@Serializable
data class NumberTwoFragmentNav(
    val param: String
)
