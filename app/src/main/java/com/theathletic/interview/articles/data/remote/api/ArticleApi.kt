package com.theathletic.interview.articles.data.remote.api

import com.theathletic.interview.articles.data.remote.model.ArticleApiModel
import retrofit2.http.GET

/**
 * Interface for the API service to retrieve articles' data.
 */
interface ArticleApi {
    /**
     * Fetches the list of articles.
     *
     * @return The list of [ArticleApiModel].
     */
    @GET("articles")
    suspend fun getArticles(): List<ArticleApiModel>
}


