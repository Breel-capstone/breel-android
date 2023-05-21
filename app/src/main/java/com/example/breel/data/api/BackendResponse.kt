package com.example.breel.data.api

data class ResponseMessage(
    val title: String,
    val body: String
)

data class ResponseMeta(
    val path: String,
    val statusCode: Int,
    val timestamp: String,
    val requestId: String,
    val timeElapsed: String
)


data class Pagination(
    val currentPage: Int,
    val totalPages: Int,
    val currentElements: Int,
    val totalElements: Int
)

data class BackendResponse<T>(
    val message: ResponseMessage,
    val meta: ResponseMeta,
    val data: T?,
    val pagination: Pagination?
)
