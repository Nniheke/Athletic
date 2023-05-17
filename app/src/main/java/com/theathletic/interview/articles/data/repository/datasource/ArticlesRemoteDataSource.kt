package com.theathletic.interview.articles.data.repository.datasource

import com.theathletic.interview.articles.data.remote.model.ArticleApiModel

/**
 * Interface for a remote data source that provides articles' data.
 */
interface ArticlesRemoteDataSource {
    /**
     * Retrieves the list of articles' data.
     *
     * @return The list of [ArticleApiModel].
     */
    suspend fun getArticlesData(): List<ArticleApiModel>
}
