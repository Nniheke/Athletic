package com.theathletic.interview.injection

import com.theathletic.interview.articles.data.remote.api.ArticleApi
import com.theathletic.interview.articles.data.remote.api.AuthorsApi
import com.theathletic.interview.articles.data.repository.ArticlesWithAuthorsRepositoryImpl
import com.theathletic.interview.articles.data.repository.datasource.ArticlesRemoteDataSource
import com.theathletic.interview.articles.data.repository.datasource.AuthorsRemoteDataSource
import com.theathletic.interview.articles.data.repository.datasourceimpl.ArticlesRemoteDataSourceImpl
import com.theathletic.interview.articles.data.repository.datasourceimpl.AuthorsRemoteDataSourceImpl
import com.theathletic.interview.articles.data.repository.ArticlesWithAuthorsRepository
import com.theathletic.interview.articles.domain.usecase.GetArticlesWithAuthorsUseCase
import com.theathletic.interview.articles.ui.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val baseModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://mobile-interview-backend.theathletic.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(ArticleApi::class.java) }

    factory { get<Retrofit>().create(AuthorsApi::class.java) }

    single<AuthorsRemoteDataSource> { AuthorsRemoteDataSourceImpl(get()) }

    single<ArticlesRemoteDataSource> { ArticlesRemoteDataSourceImpl(get()) }

    single<ArticlesWithAuthorsRepository> { ArticlesWithAuthorsRepositoryImpl(get(), get()) }

    single { GetArticlesWithAuthorsUseCase(get()) }

    viewModel { ArticlesViewModel(get()) }

}