package com.akshay.daresayweather.utils

import com.akshay.daresayweather.models.Resource
import retrofit2.Response
import retrofit2.Retrofit

inline fun <reified T> Retrofit.create() = create(T::class.java)

fun <ResultType> Response<ResultType>.toResource(): Resource<ResultType> {
    val error = errorBody()?.toString() ?: message()
    return when {
        isSuccessful -> {
            val body = body()
            when {
                body != null -> Resource.success(body)
                else -> Resource.error(error)
            }
        }
        else -> Resource.error(error)
    }
}