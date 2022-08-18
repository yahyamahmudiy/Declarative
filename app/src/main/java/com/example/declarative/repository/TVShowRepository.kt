package com.example.declarative.repository

import com.example.declarative.db.TVShowDao
import com.example.declarative.model.TVShow
import com.example.declarative.network.TVShowService
import javax.inject.Inject

class TVShowRepository @Inject constructor(private val tvShowService:TVShowService,private val tvShowDao: TVShowDao) {
    suspend fun apiTVShowPopular(page:Int) = tvShowService.apiTVShowPopular(page)
    suspend fun apiTVShowDetails(q:Int) = tvShowService.apiTVShowDetails(q)

    suspend fun getTvShowsFromDB() = tvShowDao.getTVShowsFromDB()
    suspend fun insertTVShowToDB(tvShow: TVShow)=tvShowDao.insertTVShowToDB(tvShow)
    suspend fun deleteTVShowsFromDB()=tvShowDao.deleteTvShowsFromDB()
}