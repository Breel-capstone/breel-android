package com.example.breel.data.repository

import com.example.breel.data.Resource
import com.example.breel.data.api.BackendResponse
import com.example.breel.data.api.BackendResponseNoData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

fun <T> processResult(result: BackendResponse<T>): Flow<Resource<BackendResponse<T>>> = flow {
    val statusCode = result.meta.statusCode
    val successStatusCodeRange = 200..399

    if (statusCode in successStatusCodeRange) {
        emit(Resource.Success(result))
    } else {
        emit(Resource.DataError(errorCode = statusCode, errorMessage = result.message.title))
    }
}.catch {
    emit(Resource.DataError(errorCode = -1, errorMessage = it.message)) // Handle any exceptions by emitting a generic error
}

fun processResult(result: BackendResponseNoData): Flow<Resource<BackendResponseNoData>> = flow {
    val statusCode = result.meta.statusCode
    val successStatusCodeRange = 200..399

    if (statusCode in successStatusCodeRange) {
        emit(Resource.Success(result))
    } else {
        emit(Resource.DataError(errorCode = statusCode, errorMessage = result.message.title))
    }
}.catch {
    emit(Resource.DataError(errorCode = -1, errorMessage = it.message)) // Handle any exceptions by emitting a generic error
}
