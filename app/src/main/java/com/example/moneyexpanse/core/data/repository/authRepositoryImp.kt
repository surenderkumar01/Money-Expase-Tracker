package com.example.moneyexpanse.core.data.repository

import android.util.Log
import com.example.moneyexpanse.core.common.authState
import com.example.moneyexpanse.core.data.dataModel.User
import com.example.moneyexpanse.core.domain.authRepository.authRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject
import kotlin.contracts.Returns

class authRepositoryImp @Inject constructor( val auth: FirebaseAuth,val firestore: FirebaseFirestore): authRepository {
    override suspend fun loginPage(
        email: String,
        password: String
    ): authState<FirebaseUser> {
        authState.Loading
        Log.d("TAG","mvvm")
        return try {
            authState.Loading
            val result=auth.signInWithEmailAndPassword(email,password).await()

            val user=result.user
            if(user!=null){
                Log.d("TAG","Succses")
                authState.Success(user)
            }else{
                authState.Error("User is null")
            }

        }catch (e: Exception){

            val message=when(e){
                is FirebaseAuthInvalidUserException->
                    "Account not found. Please sign up first."
                is FirebaseAuthInvalidCredentialsException ->
                    "Incorrect email or password."

                else ->
                    e.message ?: "Login failed"
            }
            authState.Error(message)
        }

    }

    override suspend fun signUpPage(
        email: String,
        password: String,
        name: String
    ): authState<FirebaseUser> {

        return try {
//
            val result=auth.createUserWithEmailAndPassword(email,password).await()

            val user=result.user
            addUserData(email,name)
            if(user!=null){
                authState.Success(user)

            }else{
                authState.Error("User is null")
            }


        }catch (e: Exception){
            val message = when (e) {
                is FirebaseAuthUserCollisionException ->
                    "This email is already registered. Please log in."

                is FirebaseAuthWeakPasswordException ->
                    "Password is too weak (minimum 6 characters)."

                is FirebaseAuthInvalidCredentialsException ->
                    "Invalid email format."

                else ->
                    "Signup failed. Try again.${e.message}"
            }

            authState.Error(message)
        }

    }

    suspend fun addUserData(email: String, name: String) {
        val uid = auth.currentUser?.uid  ?: return
        Log.d("UID_CHECK", uid.toString())
        val data = mapOf(
            "uid" to uid,
            "email" to email,
            "name" to name
        )

        firestore.collection("User")
            .add(data)
            .await()
    }



}