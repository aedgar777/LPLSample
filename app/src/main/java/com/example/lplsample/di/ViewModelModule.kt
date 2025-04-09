package com.example.lplsample.di


import com.example.lplsample.ui.CommentListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CommentListViewModel(get()) }
}