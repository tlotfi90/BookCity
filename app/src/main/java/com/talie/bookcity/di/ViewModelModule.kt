package com.talie.bookcity.di

import com.talie.bookcity.data.Api
import com.talie.bookcity.data.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    fun bookRepository(api: Api)= BookRepository(api)
}