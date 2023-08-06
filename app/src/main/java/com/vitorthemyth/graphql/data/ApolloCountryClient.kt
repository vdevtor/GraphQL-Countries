package com.vitorthemyth.graphql.data

import com.apollographql.apollo3.ApolloClient
import com.vitorthemyth.CountriesQuery
import com.vitorthemyth.CountryQuery
import com.vitorthemyth.graphql.domain.CountryClient
import com.vitorthemyth.graphql.domain.DetailedCountry
import com.vitorthemyth.graphql.domain.SimpleCountry

class ApolloCountryClient(
        private val apolloClient: ApolloClient
) : CountryClient {

    override suspend fun getCountries(): List<SimpleCountry> {
       return apolloClient.query(CountriesQuery())
               .execute()
               .data
               ?.countries
               ?.map { it.toSimpleCountry() }
               ?: emptyList()

    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient.query(CountryQuery(code))
                .execute()
                .data
                ?.country?.toDetailedCountry()
    }
}