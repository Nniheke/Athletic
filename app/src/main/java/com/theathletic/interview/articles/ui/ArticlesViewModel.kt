package com.theathletic.interview.articles.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theathletic.interview.articles.data.*
import com.theathletic.interview.articles.domain.model.ArticleUiModel
import com.theathletic.interview.articles.domain.usecase.GetArticlesWithAuthorsUseCase
import com.theathletic.interview.core.updateState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * ViewModel class responsible for managing articles-related data and events.
 *
 * @param getArticlesWithAuthorsUseCase The use case for retrieving articles with authors.
 */
class ArticlesViewModel(
    private val getArticlesWithAuthorsUseCase: GetArticlesWithAuthorsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(ArticlesViewState())
    private val _viewEvent = MutableSharedFlow<ArticleEvent>()

    /**
     * The current state of the articles view.
     */
    val viewState = _viewState.asStateFlow()

    /**
     * The flow of article events.
     */
    val viewEvent: Flow<ArticleEvent> = _viewEvent

    init {
        viewModelScope.launch {
            getArticlesWithAuthors()
        }
    }

    /**
     * Retrieves articles with authors and updates the view state.
     */
    private suspend fun getArticlesWithAuthors() {
        try {
            val articles = getArticlesWithAuthorsUseCase.invoke()
            updateViewStateOnArticlesLoaded(articles)
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
        }
    }

    /**
     * Updates the view state when articles are loaded.
     *
     * @param articles The list of article UI models.
     */
    private fun updateViewStateOnArticlesLoaded(
        articles: List<ArticleUiModel>
    ) {
        _viewState.updateState {
            copy(
                articleModels = articles.map { it.toUiModel() },
                isLoading = false
            )
        }
    }

    /**
     * Retrieves an article by ID.
     *
     * @param id The ID of the article to retrieve.
     * @return The state flow of the article UI model, or null if the article is not found.
     */
    fun getArticleById(id: String): StateFlow<ArticleUiModel?> {
        return flowOf(viewState.value.articleModels.find {
            it.article.id == id
        }).stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }

    /**
     * Handles the click event on an article.
     *
     * @param articleId The ID of the clicked article.
     */
    fun onArticleClicked(articleId: String) {
        val event = ArticleEvent.OpenArticle(articleId)
        viewModelScope.launch {
            _viewEvent.emit(event)
        }
    }

}
