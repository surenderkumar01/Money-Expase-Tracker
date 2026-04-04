package com.example.moneyexpanse.core.prasentation.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyexpanse.core.common.authState
import com.example.moneyexpanse.core.data.repository.addIncomeRepoImp
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class addincomeViewModel @Inject constructor(val addIncomeRepoImp: addIncomeRepoImp) : ViewModel() {


    private val _addIncomeState = mutableStateOf(String())
    val addIncomeState: State<String> = _addIncomeState

    fun addIncome(income: String) {
        viewModelScope.launch {
            addIncomeRepoImp.addIncome(income)
        }

    }

    fun fatch() {
        viewModelScope.launch {
            val data=addIncomeRepoImp.fatchIncome()
           _addIncomeState.value= data

        }

    }
}