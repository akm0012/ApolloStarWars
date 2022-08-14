package com.andrewkingmarshall.apollostarwars.repository

import com.andrewkingmarshall.apollostarwars.AllPeopleQuery
import com.andrewkingmarshall.apollostarwars.network.GraphQLService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class StarWarsRepository @Inject constructor(
    private val graphQLService: GraphQLService

) {

    suspend fun getAllPeople(): List<AllPeopleQuery.Person>? {

        val peopleResponse = graphQLService.getAllPeople()
            .map {
                Timber.d("First person got: ${it.data?.allPeople?.people?.first()}")
            }
            .collect()


        return null

    }

}