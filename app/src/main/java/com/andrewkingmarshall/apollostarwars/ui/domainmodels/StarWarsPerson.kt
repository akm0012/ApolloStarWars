package com.andrewkingmarshall.apollostarwars.ui.domainmodels

data class StarWarsPerson(
    val id: String,
    val name: String?,
    val imageUrl: String? = null,

    val species: String? = null
    // Todo: Add more detail fields

)
