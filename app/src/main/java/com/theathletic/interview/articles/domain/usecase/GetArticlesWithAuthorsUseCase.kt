package com.theathletic.interview.articles.domain.usecase

import androidx.annotation.OpenForTesting
import com.theathletic.interview.articles.domain.model.ArticleUiModel
import com.theathletic.interview.articles.data.repository.ArticlesWithAuthorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Use case for retrieving articles with authors.
 *
 * @property articlesWithAuthorsRepository The repository for accessing articles and author details.
 */
@OpenForTesting
open class GetArticlesWithAuthorsUseCase(
    private val articlesWithAuthorsRepository: ArticlesWithAuthorsRepository
) {
    /**
     * Retrieves articles with authors.
     *
     * @return The list of article UI models.
     */
    open suspend operator fun invoke(): List<ArticleUiModel> = withContext(Dispatchers.Default) {
        val articles = articlesWithAuthorsRepository.getArticles()
        val authorDetails = articlesWithAuthorsRepository.getAuthors()
        return@withContext articles.mapNotNull { article ->
            val author = authorDetails.find { it.id == article.author.id }
            author?.let { ArticleUiModel(article, it) }
        }
    }
}
