package com.theathletic.interview

import com.theathletic.interview.articles.domain.model.ArticleUiModel
import com.theathletic.interview.articles.data.remote.model.Author
import com.theathletic.interview.articles.domain.model.Article
import com.theathletic.interview.articles.domain.model.AuthorDetails

val articles = listOf(
        ArticleUiModel(
            Article(
                author = Author(
                    "1"
                ),
                body = "Article 1 body",
                id = "1",
                imageUrlString = "https://example.com/article1.jpg",
                title = "Article 1"
            ),
            AuthorDetails(
                id = "1",
                imageUrlString = "https://example.com/author1.jpg",
                name = "Author 1",
                title = "Mr.",
                updatedAt = "2023-05-02"
            )
        ),
        ArticleUiModel(
            Article(
                author = Author(
                    "2"
                ),
                body = "Article 2 body",
                id = "2",
                imageUrlString = "https://example.com/article2.jpg",
                title = "Article 2"
            ),
            AuthorDetails(
                id = "2",
                imageUrlString = "https://example.com/author2.jpg",
                name = "Author 2",
                title = "Ms.",
                updatedAt = "2023-05-04"
            )
        ),
        ArticleUiModel(
            Article(
                author = Author(
                    "3"
                ),
                body = "Article 3 body",
                id = "3",
                imageUrlString = "https://example.com/article3.jpg",
                title = "Article 3"
            ),
            AuthorDetails(
                id = "3",
                imageUrlString = "https://example.com/author3.jpg",
                name = "Author 3",
                title = "Dr.",
                updatedAt = "2023-05-06"
            )
        )
    )