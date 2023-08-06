package com.vitorthemyth.graphql.data

import com.vitorthemyth.CountriesQuery
import com.vitorthemyth.CountryQuery
import com.vitorthemyth.graphql.domain.DetailedCountry
import com.vitorthemyth.graphql.domain.SimpleCountry

fun CountryQuery.Country.toDetailedCountry() = DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital",
        currency = currency ?: "No Currency",
        language = languages.mapNotNull { name },
        continent = continent.name
)


fun CountriesQuery.Country.toSimpleCountry() = SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital"
)