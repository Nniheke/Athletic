package com.theathletic.interview.articles.domain.model

/**
 * Data class representing the details of an author.
 *
 * @property id The ID of the author.
 * @property imageUrlString The URL string for the author's image.
 * @property name The name of the author.
 * @property title The title of the author.
 * @property updatedAt The date and time when the author information was last updated.
 */
data class AuthorDetails(
    val id: String,
    val imageUrlString: String,
    val name: String,
    val title: String,
    val updatedAt: String
)
