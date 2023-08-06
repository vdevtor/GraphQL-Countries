package com.vitorthemyth.graphql.di

import com.apollographql.apollo3.ApolloClient
import com.vitorthemyth.graphql.data.ApolloCountryClient
import com.vitorthemyth.graphql.domain.CountryClient
import com.vitorthemyth.graphql.domain.GetCountriesUseCase
import com.vitorthemyth.graphql.domain.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApolloClient(): ApolloClient {
        return ApolloClient.Builder()
                .serverUrl("https://countries.trevorblades.com/graphql")
                .build()
    }


    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient = apolloClient)
    }

    @Provides
    @Singleton
    fun provideCountriesUseCase(countryClient: CountryClient): GetCountriesUseCase {
        return GetCountriesUseCase(countryClient)
    }

    @Provides
    @Singleton
    fun provideCountryUseCase(countryClient: CountryClient): GetCountryUseCase {
        return GetCountryUseCase(countryClient)
    }


}