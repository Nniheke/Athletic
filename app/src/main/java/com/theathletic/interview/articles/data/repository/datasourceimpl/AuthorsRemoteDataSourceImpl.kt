package com.theathletic.interview.articles.data.repository.datasourceimpl

import com.theathletic.interview.articles.data.remote.api.AuthorsApi
import com.theathletic.interview.articles.data.remote.model.AuthorsApiModel
import com.theathletic.interview.articles.data.repository.datasource.AuthorsRemoteDataSource

/**
 * Implementation of the [AuthorsRemoteDataSource] interface that retrieves authors' data from
 * the provided [AuthorsApi].
 *
 * @param authorsApi The API service for retrieving authors' data.
 */
class AuthorsRemoteDataSourceImpl(private val authorsApi: AuthorsApi) : AuthorsRemoteDataSource {
    /**
     * Retrieves the list of authors' data.
     *
     * @return The list of [AuthorsApiModel].
     */
    override suspend fun getAuthorsData(): List<AuthorsApiModel> {
        return authorsApi.getAuthors()
    }
}

