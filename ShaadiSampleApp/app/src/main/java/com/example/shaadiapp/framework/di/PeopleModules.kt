package com.example.shaadiapp.framework.di

import androidx.room.Room
import com.example.shaadiapp.data.datasources.PersonRequestDataSource
import com.example.shaadiapp.data.repositories.impl.PersonRequestRepositoryImpl
import com.example.shaadiapp.domain.repository.PersonRequestRepository
import com.example.shaadiapp.domain.usecases.PersonRequestUseCase
import com.example.shaadiapp.framework.database.PersonDatabase
import com.example.shaadiapp.framework.datasources.impl.PersonRequestDataSourceImpl
import com.example.shaadiapp.framework.network.ApiService
import com.example.shaadiapp.framework.network.RetrofitBuilder
import com.example.shaadiapp.view.viewmodel.PersonRequestListModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModules = module {
    viewModel { PersonRequestListModel(personRequestUseCase = get()) }
}

val domainModules = module {
    factory { PersonRequestUseCase(personRequestRepository = get()) }
}

val dataModules = module {
    single<PersonRequestRepository> { PersonRequestRepositoryImpl(personRequestDataSource = get()) }
    single<PersonRequestDataSource> {
        PersonRequestDataSourceImpl(
            apiService = get(),
            personRequestDao = get()
        )
    }
    single<ApiService> { RetrofitBuilder.getRetrofit().create(ApiService::class.java) }
    single {
        Room.databaseBuilder(androidApplication(), PersonDatabase::class.java, "person-db")
            .build()
    }
    single { get<PersonDatabase>().personRequestDao() }
}

