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
package vtsen.hashnode.dev.newemptycomposeapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import vtsen.hashnode.dev.newemptycomposeapp.repository.ARepository
import vtsen.hashnode.dev.newemptycomposeapp.repository.BRepository
import vtsen.hashnode.dev.newemptycomposeapp.repository.CRepository
import vtsen.hashnode.dev.newemptycomposeapp.ui.theme.ViewModelFactoryExampleAppTheme
import vtsen.hashnode.dev.newemptycomposeapp.ui.viewmodel.AViewModel
import vtsen.hashnode.dev.newemptycomposeapp.ui.viewmodel.AViewModelFactory
import vtsen.hashnode.dev.newemptycomposeapp.ui.viewmodel.BViewModel
import vtsen.hashnode.dev.newemptycomposeapp.ui.viewmodel.BViewModelFactory
import vtsen.hashnode.dev.newemptycomposeapp.ui.viewmodel.CViewModel
import vtsen.hashnode.dev.newemptycomposeapp.ui.viewmodel.CViewModelFactory
import vtsen.hashnode.dev.newemptycomposeapp.ui.viewmodel.createViewModelFactory

@Composable
fun MainScreen() {
    WithoutHelperFunctions()
    WithHelperFunctions()
}

@Composable()
fun WithoutHelperFunctions() {
    val aViewModel: AViewModel = viewModel(
        factory = AViewModelFactory(ARepository()),
    )

    val bViewModel: AViewModel = viewModel(
        factory = BViewModelFactory(BRepository()),
    )

    val cViewModel: AViewModel = viewModel(
        factory = CViewModelFactory(CRepository()),
    )
}

@Composable()
fun WithHelperFunctions() {
    val aViewModel: AViewModel = viewModel(
        factory = createViewModelFactory {
            AViewModel(ARepository())
        }
    )

    val bViewModel: BViewModel = viewModel(
        factory = createViewModelFactory {
            BViewModel(BRepository())
        }
    )

    val cViewModel: CViewModel = viewModel(
        factory = createViewModelFactory {
            CViewModel(CRepository())
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewModelFactoryExampleAppTheme(useSystemUIController = false) {
        MainScreen()
    }
}
