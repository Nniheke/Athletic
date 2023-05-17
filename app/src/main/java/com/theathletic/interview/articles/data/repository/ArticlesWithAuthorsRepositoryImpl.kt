package com.theathletic.interview.articles.data.repository

import com.theathletic.interview.articles.data.repository.datasource.ArticlesRemoteDataSource
import com.theathletic.interview.articles.data.repository.datasource.AuthorsRemoteDataSource
import com.theathletic.interview.articles.data.toDomain
import com.theathletic.interview.articles.domain.model.Article
import com.theathletic.interview.articles.domain.model.AuthorDetails

/**
 * Implementation of the [ArticlesWithAuthorsRepository] interface that retrieves articles and authors
 * from remote data sources.
 *
 * @param authorsRemoteDataSource The remote data source for authors.
 * @param articlesRemoteDataSource The remote data source for articles.
 */
class ArticlesWithAuthorsRepositoryImpl(
    private val authorsRemoteDataSource: AuthorsRemoteDataSource,
    private val articlesRemoteDataSource: ArticlesRemoteDataSource
) : ArticlesWithAuthorsRepository {
    /**
     * Retrieves the list of articles.
     *
     * @return The list of article API models and maps to domain model.
     */
    override suspend fun getArticles(): List<Article> {
        return articlesRemoteDataSource.getArticlesData().map { it.toDomain() }
    }

    /**
     * Retrieves the list of authors.
     *
     * @return The list of author API models and maps to domain model.
     */
    override suspend fun getAuthors(): List<AuthorDetails> {
        return authorsRemoteDataSource.getAuthorsData().map { it.toDomain() }
    }
}
