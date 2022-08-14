package com.andrewkingmarshall.apollostarwars.network

import com.andrewkingmarshall.apollostarwars.AllPeopleQuery
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

class GraphQLService @Inject constructor(private val apolloClient: ApolloClient) {

    fun getAllPeople() = apolloClient
        .query(AllPeopleQuery())
        .fetchPolicy(FetchPolicy.CacheAndNetwork)
        .toFlow()

}