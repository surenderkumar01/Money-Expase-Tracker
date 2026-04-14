package com.example.moneyexpanse.core.domain.authRepository

import android.util.Log
import com.example.moneyexpanse.core.data.dataModel.dataModel
import kotlinx.coroutines.tasks.await

interface ExpanceRepository {


    suspend fun saveExpanse(datamodel: dataModel)
    suspend fun fatchExpanse(): List<dataModel>
    suspend fun fatchTotalExpanse(): String
    suspend fun fatchIncome(): String
    suspend fun RemoveExpance(id:String)
}