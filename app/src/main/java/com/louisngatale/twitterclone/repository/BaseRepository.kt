package com.louisngatale.twitterclone.repository

import android.util.Log
import com.louisngatale.twitterclone.network.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract interface BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall : suspend () -> T
    ) : Resource<T>{
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            }catch (throwable: Throwable){
                when(throwable){
                    is HttpException -> {
                        Log.d("Error", throwable.code().toString())
                        Log.d("Error", throwable.response()?.errorBody().toString())
                        Resource.Failure(true, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true,null, null)
                    }
                }
            }
        }
    }
}