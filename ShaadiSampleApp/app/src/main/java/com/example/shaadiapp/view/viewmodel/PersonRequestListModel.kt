package com.example.shaadiapp.view.viewmodel

import androidx.lifecycle.*
import com.example.shaadiapp.domain.entities.PersonRequestEntity
import com.example.shaadiapp.domain.usecases.PersonRequestUseCase
import com.example.shaadiapp.framework.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PersonRequestListModel(private val personRequestUseCase: PersonRequestUseCase) : ViewModel() {

    private val _allRequestsFromDataBase: MutableLiveData<List<PersonRequestEntity>> =
        MutableLiveData()
    val allRequestsFromDataBase: LiveData<List<PersonRequestEntity>> = _allRequestsFromDataBase

    fun getAllRequestsFromServer() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = personRequestUseCase.getAllRequestsFromServer()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getAllRequestFromDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            _allRequestsFromDataBase.postValue(personRequestUseCase.getAllRequestFromDataBase())
        }
    }

    fun updateRequestStatus(personRequest: PersonRequestEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            personRequestUseCase.updatePersonRequest(personRequest)
        }
    }
}