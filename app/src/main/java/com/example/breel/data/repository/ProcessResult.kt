package com.example.breel.data.repository

import com.example.breel.data.Resource
import com.example.breel.data.api.BackendResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

fun <T> processResult(result: BackendResponse<T>): Flow<Resource<BackendResponse<T>>> = flow {
    val statusCode = result.meta.statusCode
    val successStatusCodeRange = 200..399

    if (statusCode in successStatusCodeRange) {
        emit(Resource.Success(result))
    } else {
        emit(Resource.DataError(errorCode = statusCode))
    }
}.catch {
    emit(Resource.DataError(errorCode = -1)) // Handle any exceptions by emitting a generic error
}