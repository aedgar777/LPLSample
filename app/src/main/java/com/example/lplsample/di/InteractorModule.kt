package com.example.lplsample.di

import com.example.lplsample.domain.interactors.CommentInteractor
import com.example.lplsample.domain.interactors.CommentInteractorImpl
import org.koin.dsl.module

val interactorModule = module{
    factory<CommentInteractor> { CommentInteractorImpl(get()) }
}