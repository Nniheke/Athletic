package com.theathletic.interview.articles.data.repository

import com.theathletic.interview.articles.domain.model.Article
import com.theathletic.interview.articles.domain.model.AuthorDetails

/**
 * Repository interface for accessing articles and authors.
 */
interface ArticlesWithAuthorsRepository {
    /**
     * Retrieves the list of articles.
     *
     * @return The list of article API models.
     */
    suspend fun getArticles(): List<Article>

    /**
     * Retrieves the list of authors.
     *
     * @return The list of author API models.
     */
    suspend fun getAuthors(): List<AuthorDetails>
}
