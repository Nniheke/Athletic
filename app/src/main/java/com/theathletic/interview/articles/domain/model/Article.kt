package com.theathletic.interview.articles.domain.model

import com.theathletic.interview.articles.data.remote.model.Author

/**
 * Data class representing an article.
 *
 * @property author The author of the article.
 * @property body The body content of the article.
 * @property id The ID of the article.
 * @property imageUrlString The URL string for the article's image.
 * @property title The title of the article.
 */
data class Article(
    val author: Author,
    val body: String,
    val id: String,
    val imageUrlString: String,
    val title: String
)
