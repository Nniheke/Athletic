package com.theathletic.interview.articles.data.remote.api

import com.theathletic.interview.articles.data.remote.model.AuthorsApiModel
import retrofit2.http.GET

/**
 * Interface for the API service to retrieve authors' data.
 */
interface AuthorsApi {
    /**
     * Fetches the list of authors.
     *
     * @return The list of [AuthorsApiModel].
     */
    @GET("authors")
    suspend fun getAuthors(): List<AuthorsApiModel>
}
