package com.theathletic.interview.articles.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.theathletic.interview.R
import com.theathletic.interview.articles.ui.ArticlesViewModel
import com.theathletic.interview.ui.theme.Black
import com.theathletic.interview.ui.theme.White

@Composable
fun ArticlePage(id: String, viewModel: ArticlesViewModel) {
    val model by viewModel.getArticleById(id).collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = model?.article?.imageUrlString,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.article_spacer_height_one)))

            Text(
                text = model?.article?.title.orEmpty(),
                style = MaterialTheme.typography.h4,
                color = Black,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.article_horizontal_margin))
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.article_horizontal_margin)))

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(model?.authorDetails?.imageUrlString)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.article_image_start_margin))
                    .size(dimensionResource(id = R.dimen.article_image_size))
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.article_spacer_height)))

            Text(
                text = model?.article?.body.orEmpty(),
                style = MaterialTheme.typography.body2,
                color = Black,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.article_text_horizontal_margin))
            )
        }
    }
}
