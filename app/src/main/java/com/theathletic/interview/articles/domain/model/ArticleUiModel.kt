package com.theathletic.interview.articles.domain.model

/**
 * UI model class that represents an article with its associated author.
 *
 * @param article The article model.
 * @param authorDetails The author model.
 */
data class ArticleUiModel(
    val article: Article,
    val authorDetails: AuthorDetails
)

