package com.example.moneyexpanse.core.prasentation.viewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.moneyexpanse.core.common.authState
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.moneyexpanse.core.domain.authRepository.authRepository
import kotlinx.coroutines.launch


@HiltViewModel
class AuthViewModel @Inject constructor( val repositoryImp: authRepository): ViewModel() {

    private val _signUpState= mutableStateOf<authState<FirebaseUser>>(authState.Idle)
    val  signUpState: State<authState<FirebaseUser>> = _signUpState


    private val _loginState= mutableStateOf<authState<FirebaseUser>>(authState.Idle)
    val  loginState: State<authState<FirebaseUser>> = _loginState



    fun signUp(email: String,password:String,name:String){
_signUpState.value= authState.Loading

        viewModelScope.launch {
            _signUpState.value=repositoryImp.signUpPage(email,password,name)

        }
    }


    fun login(email: String,password:String){

      _loginState.value=  authState.Loading
        viewModelScope.launch {
            _loginState.value=repositoryImp.loginPage(email,password)

        }
    }


}