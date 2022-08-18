package com.example.declarative.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.declarative.model.TVShow
import com.example.declarative.model.TVShowPopular
import com.example.declarative.repository.TVShowRepository
import com.example.declarative.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val tvShowRepository: TVShowRepository):ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val tvShowsFromApi = MutableLiveData<ArrayList<TVShow>>()
    val tvShowPopular = MutableLiveData<TVShowPopular>()

    init {
        apiTVShowPopular(1)
    }

    fun apiTVShowPopular(page: Int) {
        isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = tvShowRepository.apiTVShowPopular(page)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val resp = response.body()
                    Logger.d("MainViewModel",resp.toString())
                    tvShowPopular.postValue(resp)
                    tvShowsFromApi.postValue(resp!!.tv_shows)
                    isLoading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        isLoading.value = false
    }

    fun insertTVShowToDB(tvShow: TVShow) {
        viewModelScope.launch {
            tvShowRepository.insertTVShowToDB(tvShow)
        }
    }
}