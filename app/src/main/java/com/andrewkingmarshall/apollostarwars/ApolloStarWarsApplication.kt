package com.andrewkingmarshall.apollostarwars

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * How to download the Schema:
 *
 * ./gradlew :app:downloadApolloSchema --endpoint='https://swapi-graphql.netlify.app/.netlify/functions/index' --schema=app/src/main/graphql/com/andrewkingmarshall/apollostarwars/schema.graphqls
 *
 * Where to get the Images for the characters: https://akabab.github.io/starwars-api/api/all.json
 */

@HiltAndroidApp
class ApolloStarWarsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setUpLogging()
    }

    private fun setUpLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}