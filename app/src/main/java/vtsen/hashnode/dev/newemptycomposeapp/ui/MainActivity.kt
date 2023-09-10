/*
 * Copyright 2023 Vincent Tsen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vtsen.hashnode.dev.newemptycomposeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.newemptycomposeapp.repository.ARepository
import vtsen.hashnode.dev.newemptycomposeapp.repository.BRepository
import vtsen.hashnode.dev.newemptycomposeapp.ui.screens.MainScreen
import vtsen.hashnode.dev.newemptycomposeapp.ui.theme.ViewModelFactoryExampleAppTheme
import vtsen.hashnode.dev.newemptycomposeapp.ui.viewmodel.AViewModel
import vtsen.hashnode.dev.newemptycomposeapp.ui.viewmodel.AViewModelFactory
import vtsen.hashnode.dev.newemptycomposeapp.ui.viewmodel.BViewModel
import vtsen.hashnode.dev.newemptycomposeapp.ui.viewmodel.createViewModelFactory

class MainActivity : ComponentActivity() {

    // without helper function
    private val aViewModel by viewModels<AViewModel> {
        AViewModelFactory(ARepository())
    }

    // with helper function
    private val bViewModel by viewModels<BViewModel> {
        createViewModelFactory {
            BViewModel(BRepository())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            ViewModelFactoryExampleAppTheme {
                MainScreen()
            }
        }
    }

    private fun setupSplashScreen() {
        var keepSplashScreenOn = true
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                delay(2000)
                keepSplashScreenOn = false
            }
        }

        installSplashScreen().setKeepOnScreenCondition {
            keepSplashScreenOn
        }
    }
}
