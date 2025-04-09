package com.example.lplsample.di

import com.example.lplsample.data.CommentRepositoryImpl
import com.example.lplsample.domain.CommentRepository
import org.koin.dsl.module

val repositoryModule = module{
    factory<CommentRepository> { CommentRepositoryImpl(get()) }
}