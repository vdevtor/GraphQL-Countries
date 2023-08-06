package com.vitorthemyth.graphql.domain

data class DetailedCountry(
        val code: String,
        val name: String,
        val emoji: String,
        val capital: String,
        val currency: String,
        val language : List<String>,
        val continent: String
)
