package com.andrewkingmarshall.apollostarwars.inject

import android.content.Context
import com.andrewkingmarshall.apollostarwars.BuildConfig
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideHttpClient(): OkHttpClient {

        val httpLoggingInterceptor = HttpLoggingInterceptor()

        // Set Log Level
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        // Set up the HTTP Client
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
//            .addInterceptor(ErrorInterceptor(context))
            .build()
    }

    @Provides
    fun provideApolloClient(
        @ApplicationContext context: Context,
        httpClient: OkHttpClient
    ): ApolloClient {

        // Creates a 10MB MemoryCacheFactory
        val cacheFactory = MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024)

        val sqlNormalizedCacheFactory = SqlNormalizedCacheFactory(context, "apollo.db")

        val memoryFirstThenSqlCacheFactory = cacheFactory.chain(sqlNormalizedCacheFactory)

        return ApolloClient.Builder()
            .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
            .okHttpClient(httpClient)
            .normalizedCache(memoryFirstThenSqlCacheFactory)
            .build()

    }

}