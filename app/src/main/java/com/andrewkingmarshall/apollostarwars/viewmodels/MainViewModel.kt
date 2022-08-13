package com.andrewkingmarshall.apollostarwars.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    fun onButtonClicked() {
        viewModelScope.launch {
            Timber.d("Let's think for a sec...")
            delay(1000)
            Timber.i("Done thinking!")
        }

    }

}