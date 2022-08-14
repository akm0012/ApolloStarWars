package com.andrewkingmarshall.apollostarwars.network

import com.andrewkingmarshall.apollostarwars.AllPeopleQuery
import com.apollographql.apollo3.ApolloClient
import timber.log.Timber
import javax.inject.Inject

class GraphQLService @Inject constructor(private val apolloClient: ApolloClient) {

    suspend fun getAllPeople() {
        val response = apolloClient.query(AllPeopleQuery()).execute()

        response.data?.allPeople?.people?.forEach {
            Timber.d("Got a person: ${it?.name}")
        }
    }

}