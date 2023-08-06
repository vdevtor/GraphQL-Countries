package com.vitorthemyth.graphql.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.vitorthemyth.graphql.domain.DetailedCountry
import com.vitorthemyth.graphql.domain.SimpleCountry

@Composable
fun CountriesScreen(
        state: CountriesViewModel.CountriesState,
        onSelectedCountry: (code: String) -> Unit,
        onDismissDialog: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                    modifier = Modifier.fillMaxSize(),

                    ) {
                items(state.countries) { country ->
                    CountryItem(
                            country = country,
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onSelectedCountry(country.code)
                                    }
                                    .padding(16.dp)
                    )
                }
            }

            if (state.selectedCountry != null) {
                CountryDialog(countryDetail = state.selectedCountry,
                        onDismissDialog = {
                            onDismissDialog.invoke()
                        },
                        modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .background(Color.White)
                                .padding(16.dp)
                )
            }
        }
    }
}


@Composable
private fun CountryDialog(
        countryDetail: DetailedCountry,
        onDismissDialog: () -> Unit,
        modifier: Modifier = Modifier
) {

    val joinedLanguages = remember(countryDetail.language) {
        countryDetail.language.joinToString()
    }
    Dialog(
            onDismissRequest = onDismissDialog
    ) {
        Column(
                modifier = modifier
        ) {
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                        text = countryDetail.emoji,
                        fontSize = 30.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                        text = countryDetail.name,
                        fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Continent: " + countryDetail.continent)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Currency: " + countryDetail.currency)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Capital: " + countryDetail.capital)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Language(s): $joinedLanguages")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun CountryItem(
        country: SimpleCountry,
        modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(text = country.emoji,
                fontSize = 30.sp
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                    text = country.name,
                    fontSize = 24.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = country.capital)
        }
    }
}