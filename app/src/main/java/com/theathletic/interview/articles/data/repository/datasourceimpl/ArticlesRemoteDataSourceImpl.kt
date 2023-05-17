package com.theathletic.interview.articles.data.repository.datasourceimpl

import com.theathletic.interview.articles.data.remote.api.ArticleApi
import com.theathletic.interview.articles.data.remote.model.ArticleApiModel
import com.theathletic.interview.articles.data.repository.datasource.ArticlesRemoteDataSource

/**
 * Implementation of the [ArticlesRemoteDataSource] interface that retrieves articles' data from
 * the provided [ArticleApi].
 *
 * @param articleApi The API service for retrieving articles' data.
 */
class ArticlesRemoteDataSourceImpl(private val articleApi: ArticleApi) : ArticlesRemoteDataSource {
    /**
     * Retrieves the list of articles' data.
     *
     * @return The list of [ArticleApiModel].
     */
    override suspend fun getArticlesData(): List<ArticleApiModel> {
        return articleApi.getArticles()
    }
}
