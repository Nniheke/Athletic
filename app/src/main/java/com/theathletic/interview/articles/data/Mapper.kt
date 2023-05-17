package com.theathletic.interview.articles.data

import com.theathletic.interview.articles.domain.model.Article
import com.theathletic.interview.articles.data.remote.model.ArticleApiModel
import com.theathletic.interview.articles.data.remote.model.AuthorsApiModel
import com.theathletic.interview.articles.domain.model.ArticleUiModel
import com.theathletic.interview.articles.domain.model.AuthorDetails

/**
 * Converts an [ArticleApiModel] to a domain model [Article].
 *
 * @return The converted domain model [Article].
 */
fun ArticleApiModel.toDomain() = Article(
    author = author,
    body = body,
    id = id,
    imageUrlString = imageUrlString,
    title = title
)

/**
 * Converts an [ArticleUiModel] to another [ArticleUiModel].
 *
 * @return The converted [ArticleUiModel].
 */
fun ArticleUiModel.toUiModel() = ArticleUiModel(
    article = article,
    authorDetails = authorDetails
)

/**
 * Converts an [AuthorsApiModel] to a domain model [AuthorDetails].
 *
 * @return The converted domain model [AuthorDetails].
 */
fun AuthorsApiModel.toDomain() = AuthorDetails(
    id = id,
    imageUrlString = imageUrlString,
    name = name,
    title = title,
    updatedAt = updatedAt
)
