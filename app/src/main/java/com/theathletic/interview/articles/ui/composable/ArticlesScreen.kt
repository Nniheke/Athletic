package com.theathletic.interview.articles.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.theathletic.interview.articles.domain.model.ArticleUiModel
import com.theathletic.interview.articles.data.remote.model.Author
import com.theathletic.interview.articles.ui.ArticleEvent.OpenArticle
import com.theathletic.interview.core.collectWithLifecycle
import com.theathletic.interview.core.formatDate
import com.theathletic.interview.ui.theme.Black
import com.theathletic.interview.ui.theme.White
import com.theathletic.interview.R
import com.theathletic.interview.articles.domain.model.Article
import com.theathletic.interview.articles.domain.model.AuthorDetails
import com.theathletic.interview.articles.ui.ArticlesViewModel
import com.theathletic.interview.articles.ui.ArticlesViewState
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticlesScreen(
    viewModel: ArticlesViewModel = getViewModel(),
    navController: NavHostController
) {
    val state by viewModel.viewState.collectAsState(initial = ArticlesViewState(true, emptyList()))

    viewModel.viewEvent.collectWithLifecycle { event ->
        when (event) {
            is OpenArticle -> {
                navController.navigate("articlePageDestination/${event.articleId}")
            }
        }
    }

    ArticlesList(
        showLoading = state.isLoading,
        models = state.articleModels,
        onItemClick = { articleId ->
            viewModel.onArticleClicked(articleId)
        }
    )
}

@Composable
fun ArticlesList(
    showLoading: Boolean,
    models: List<ArticleUiModel>,
    onItemClick: (String) -> Unit
) {
    Box {
        if (showLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.articles_column_space))) {
            items(models) {
                ArticleItem(it, onItemClick)
            }
        }
    }
}

@Composable
fun ArticleItem(model: ArticleUiModel, onItemClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Black)
            .height(dimensionResource(id = R.dimen.article_item_height))
            .clickable(onClick = { onItemClick(model.article.id) })
    ) {
        AsyncImage(
            alpha = 0.5f,
            modifier = Modifier.fillMaxWidth(),
            model = model.article.imageUrlString,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.padding(
                vertical = dimensionResource(id = R.dimen.article_vertical_margin),
                horizontal = dimensionResource(
                    id = R.dimen.articles_text_horizontal_margin
                )
            ),
            text = model.article.title,
            style = MaterialTheme.typography.body1,
            color = White
        )
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.articles_column_margin))
                .align(Alignment.BottomStart)
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(dimensionResource(id = R.dimen.articles_image_size)),
                model = model.authorDetails.imageUrlString,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.articles_text_top_margin)),
                text = model.authorDetails.name,
                style = MaterialTheme.typography.caption,
                color = White
            )
            Text(
                text = model.authorDetails.updatedAt.formatDate(),
                style = MaterialTheme.typography.caption,
                color = White
            )
        }
    }
}

@Preview(backgroundColor = 0xFFffffff, showBackground = true, name = "Article")
@Composable
fun ArticleItemPreview() {
    ArticleItem(
        ArticleUiModel(
            Article(
                author = Author("1"),
                body = "sample body",
                id = "sample id",
                imageUrlString = "sample imageUrlString",
                title = "sample title"
            ),
            AuthorDetails(
                id = "id",
                imageUrlString = "sample imageUrlString",
                name = "sample name",
                title = "sample title",
                updatedAt = "sample updatedAt"
            )
        ),
        {}
    )
}