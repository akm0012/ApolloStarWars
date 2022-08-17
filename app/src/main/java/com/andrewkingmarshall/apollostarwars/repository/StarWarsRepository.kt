package com.andrewkingmarshall.apollostarwars.repository

import com.andrewkingmarshall.apollostarwars.AllPeopleQuery
import com.andrewkingmarshall.apollostarwars.network.GraphQLService
import com.andrewkingmarshall.apollostarwars.ui.domainmodels.StarWarsPerson
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class StarWarsRepository @Inject constructor(
    private val graphQLService: GraphQLService

) {

    private val errorChannel = Channel<StarWarsRepositoryError>(Channel.RENDEZVOUS)
    val errorFlow = errorChannel.receiveAsFlow()

    fun getAllPeople(): Flow<List<StarWarsPerson>> {

        return graphQLService.getAllPeople()
            .map {
                // Check for errors
                if (it.hasErrors()) {
                    it.errors?.forEach { error ->
                        Timber.w("Error: getAllPeople(): ${error.message}")
                        errorChannel.send(StarWarsRepositoryError(error.message))
                    }
                }
                // Transform flow to send along just the list of people
                it.data?.allPeople?.people
            }
            .map {
                Timber.d("First person got: ${it?.first()}")

                val listOfDomainObjects = mutableListOf<StarWarsPerson>()
                it?.forEach { personDto ->
                    personDto?.let {
                        listOfDomainObjects.add(
                            StarWarsPerson(
                                personDto.id,
                                personDto.name,
                            )
                        )
                    }
                }

                listOfDomainObjects
            }
    }

}

class StarWarsRepositoryError(message: String, cause: Throwable? = null) : Throwable(message, cause)