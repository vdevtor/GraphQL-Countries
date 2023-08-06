package com.vitorthemyth.graphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.vitorthemyth.graphql.presentation.CountriesScreen
import com.vitorthemyth.graphql.presentation.CountriesViewModel
import com.vitorthemyth.graphql.ui.theme.GraphQLTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphQLTheme {
                val viewModel = hiltViewModel<CountriesViewModel>()
                val state by viewModel.state.collectAsState()
                CountriesScreen(
                        state = state,
                        onSelectedCountry = { viewModel.selectCountry(it)},
                        onDismissDialog = { viewModel.dismissCountryDialog()}
                )  
            }
        }
    }
}

