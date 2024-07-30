package com.hwx.oldfragmentwithnavcompose

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager

/**
 * Haris Weitani
 * Last Update : 29 July 2024
 * Directly use existing fragment without mapping the logic again
 *
 * @param fragmentId your parent layout id on your fragment, make sure you did not have same id on the same navigation
 * @param fragment your fragment instance
 * @param fragmentManager use supportFragmentManager from activity
 * @param enableAnimation set false if you want to disable the transition animation
 * @param backPressHandler to handle on device / physical back button, use this to simplify backstack navigation
 */
@Composable
fun AndroidViewFragment(
    modifier: Modifier = Modifier,
    fragmentId: Int,
    fragment: Fragment,
    fragmentManager: FragmentManager,
    backPressHandler: () -> Unit
) {
    BackHandler {
        backPressHandler()
    }
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val frgId = FragmentContainerView(context).apply {
                id = fragmentId
            }
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(fragmentId, fragment, fragment.javaClass.simpleName)
            transaction.addToBackStack(null)
            transaction.commit()
            frgId
        },
        update = {
            //Do not put the fragment update here, the fragment will be created multiple times
        }
    )
}
