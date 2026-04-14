package com.example.moneyexpanse.core.data.repository

import android.util.Log
import com.example.moneyexpanse.core.data.dataModel.dataModel
import com.example.moneyexpanse.core.domain.authRepository.ExpanceRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class ExpanceRepositoryImp @Inject constructor(
    val auth: FirebaseAuth,
    val firestore: FirebaseFirestore
) :
    ExpanceRepository {
    override suspend fun saveExpanse(datamodel: dataModel) {

        val uid = auth.currentUser?.uid ?: return

        val docRef = firestore.collection("Expance").document()

        Log.d("DOC_ID", docRef.id)

        val model = mapOf(

            "id" to docRef.id,

            "uid" to uid,

            "date" to datamodel.date,

            "type" to datamodel.type,

            "amount" to datamodel.amount,

            "note" to datamodel.note

        )

        try {


            docRef.set(model).await()


            // income update
            firestore.collection("income")

                .document(uid)

                .update(

                    "income",

                    FieldValue.increment(

                        -datamodel.amount.toLong()

                    )

                ).await()


            // total expense update
            try {

                firestore.collection("TotalExpance")

                    .document(uid)

                    .update(

                        "totalEx",

                        FieldValue.increment(

                            datamodel.amount.toLong()

                        )

                    ).await()

            }

            catch (e: Exception){

                firestore.collection("TotalExpance")

                    .document(uid)

                    .set(

                        mapOf(

                            "uid" to uid,

                            "totalEx" to datamodel.amount.toLong()

                        )

                    ).await()

            }

        }

        catch (e: Exception){

            Log.d("SAVE_ERROR", e.message.toString())

        }

    }

    override suspend fun fatchExpanse(): List<dataModel> {

        val uid = auth.currentUser?.uid ?: return emptyList()
        val snap = firestore.collection("Expance").whereEqualTo("uid", uid).get().await()

        val data = snap.documents.mapNotNull { doc ->

            dataModel(

                id = doc.id,   // 🔥 document id yaha set

                amount = doc.getString("amount") ?: "",

                date = doc.getString("date") ?: "",

                type = doc.getString("type") ?: "",

                note = doc.getString("note") ?: ""

            )

        }

        Log.d("FETCH_ID", data.toString())
//
//        val data = snap.documents.mapNotNull {
//            it.toObject(dataModel::class.java)
//        }
//        Log.d("TAGE_DATA_fatch", if (data.isEmpty()) "empty" else data.get(0).id.toString())
        return data

    }

    override suspend fun fatchTotalExpanse(): String {
        val uid = auth.currentUser?.uid ?: return "0L"
        val snap = firestore.collection("TotalExpance").whereEqualTo("uid", uid).get().await()
        val data = snap.documents.mapNotNull {
            it.getLong("totalEx")
        }


        return data.firstOrNull().toString()
    }
    override suspend fun fatchIncome(): String {
        val uid = auth.currentUser?.uid
        return try {
            val snap= firestore.collection("income").document(uid.toString()).get().await()
            val data=snap.getLong("income")?:"0"
            Log.d("TAGDATA_Uid","$data"?:"-1")

            snap.getLong("income").toString()


        }catch (e: Exception){
            "Error"

        }
    }

    override suspend fun RemoveExpance(id:String) {

        Log.d("DELETE_ID_d", id)

        firestore.collection("Expance")

            .document(id)

            .delete()

            .await()

    }}