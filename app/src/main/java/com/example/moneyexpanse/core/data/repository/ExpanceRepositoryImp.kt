package com.example.moneyexpanse.core.data.repository

import android.util.Log
import com.example.moneyexpanse.core.data.dataModel.dataModel
import com.example.moneyexpanse.core.domain.authRepository.ExpanceRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ExpanceRepositoryImp @Inject constructor(
    val auth: FirebaseAuth,
    val firestore: FirebaseFirestore
) :
    ExpanceRepository {
    override suspend fun saveExpanse(datamodel: dataModel) {
        val uid = auth.currentUser?.uid
        try {

            val model = mapOf(
                "uid" to uid,
                "date" to datamodel.date,
                "type" to datamodel.type,
                "amount" to datamodel.amount,
                "note" to datamodel.note
            )

            firestore.collection("income").document(uid.toString()).update ("income" , FieldValue.increment(
                -datamodel.amount?.toLong()!!
            )).await()

            try {
                firestore.collection("TotalExpance").document(uid.toString()).update ("totalEx" , FieldValue.increment(datamodel.amount.toLong())).await()
            }catch (e: Exception){
                firestore.collection("TotalExpance").document(uid.toString()).set(mapOf("uid" to uid ,"totalEx" to datamodel.amount.toLong())).await()

            }
            firestore.collection("Expance").add(model).await()
        } catch (E: Exception) {
            Log.d("TAGE", E.message.toString())

        }
    }

    override suspend fun fatchExpanse(): List<dataModel> {
        val uid = auth.currentUser?.uid ?: return emptyList()
        val snap = firestore.collection("Expance").whereEqualTo("uid", uid).get().await()
        val data = snap.documents.mapNotNull {
            it.toObject(dataModel::class.java)
        }
        Log.d("TAGE_DATA", if (data.isEmpty()) "empty" else data.get(0).date.toString())
        return data

    }

    override suspend fun fatchTotalExpanse(): String {
        val uid = auth.currentUser?.uid ?: "0L"
        val snap = firestore.collection("TotalExpance").whereEqualTo("uid", uid).get().await()
        val data = snap.documents.mapNotNull {
            it.getLong("totalEx")
        }

//        Log.d("TAGE", data.get(0).date.toString())
        return data.firstOrNull().toString()
    }

}