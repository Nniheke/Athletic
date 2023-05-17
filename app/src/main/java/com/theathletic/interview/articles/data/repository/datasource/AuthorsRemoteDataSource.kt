package com.theathletic.interview.articles.data.repository.datasource

import com.theathletic.interview.articles.data.remote.model.AuthorsApiModel

/**
 * Interface for a remote data source that provides authors' data.
 */
interface AuthorsRemoteDataSource {
    /**
     * Retrieves the list of authors' data.
     *
     * @return The list of [AuthorsApiModel].
     */
    suspend fun getAuthorsData(): List<AuthorsApiModel>
}
