package com.theathletic.interview.articles.ui

import com.theathletic.interview.articles.domain.model.ArticleUiModel

data class ArticlesViewState(
    val isLoading: Boolean = true,
    val articleModels: List<ArticleUiModel> = emptyList()
)

sealed interface ArticleEvent {
    class OpenArticle(val articleId: String) : ArticleEvent
}