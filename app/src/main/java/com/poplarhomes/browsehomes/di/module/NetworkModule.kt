package com.poplarhomes.browsehomes.di.module

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.poplarhomes.browsehomes.di.util.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val SERVER_URL = "https://test-service-dev.cerberuslink.net/graphql"
    private const val AUTH_HEADER = "b13f3a77edc38e87e7427608a70485fb"

    @Singleton
    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient
    ): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(SERVER_URL)
            .okHttpClient(httpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(AUTH_HEADER))
            .build()
    }

}
