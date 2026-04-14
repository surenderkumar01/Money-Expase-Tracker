package com.example.moneyexpanse.core.prasentation.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyexpanse.core.data.dataModel.dataModel
import com.example.moneyexpanse.core.domain.authRepository.ExpanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.exp


@HiltViewModel
class ExpanseVieewModel @Inject constructor(val expenseRepository: ExpanceRepository) :
    ViewModel() {

    private val _addIExpanseState = mutableStateOf<List<dataModel>>(emptyList())
    val addIExpanseState: State<List<dataModel>> = _addIExpanseState

    private val _TotoalExpanse = mutableStateOf("0.0")
    val TotoalExpanse: State<String> = _TotoalExpanse

    private val _TotolAmount= mutableStateOf("0.0")
    val TotolAmount: State<String> = _TotolAmount
     val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    init {
        fatchTotalExpanse()
        fatchExpanse()
    }
    fun saveExpanse(datamodel: dataModel) {
        viewModelScope.launch {
            expenseRepository.saveExpanse(datamodel)
        }
    }

    fun fatchExpanse() {
        viewModelScope.launch {
            _isLoading.value = true

            _addIExpanseState.value = expenseRepository.fatchExpanse()

            Log.d("TAG_LOADING_FALSE",_addIExpanseState.value.toString())
            Log.d("TAG_LOADING_FALSE",isLoading.value.toString())
            _isLoading.value = false


        }
    }
init {
    fatchTotalExpanse()
}
    fun fatchTotalExpanse() {
        viewModelScope.launch {
            _TotoalExpanse.value = expenseRepository.fatchTotalExpanse()
        }
    }

    fun fatchTotalEncome() {
        viewModelScope.launch {

            _TotolAmount.value = expenseRepository.fatchIncome ()
            Log.d("TOTAl_AMOUNT_V",expenseRepository.fatchIncome ().toString())
        }
    }

    fun RemoveExpance(id:String){
        viewModelScope.launch {
            fatchExpanse()
            expenseRepository.RemoveExpance (id)

        }
    }
}