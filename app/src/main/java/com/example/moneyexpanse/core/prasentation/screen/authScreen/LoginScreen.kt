package com.example.moneyexpanse.core.prasentation.screen.authScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.moneyexpanse.R
import com.example.moneyexpanse.core.common.Route
import com.example.moneyexpanse.core.common.authState
import com.example.moneyexpanse.core.prasentation.viewModel.AuthViewModel
import com.example.moneyexpanse.ui.theme.bluecard
import com.example.moneyexpanse.ui.theme.darkBackground
import com.example.moneyexpanse.ui.theme.darkCard
import com.example.moneyexpanse.ui.theme.textDark
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val state = viewModel.loginState.value
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val user = FirebaseAuth.getInstance().currentUser

    // Already logged in ?
    LaunchedEffect(Unit) {
        if (user != null) {
            navController.navigate(Route.DashboardScreen) {
                popUpTo(Route.LoginScreen) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBackground)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
                .statusBarsPadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.lo),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Spacer(Modifier.height(24.dp))

            Text(
                "Manage Your Wealth",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Track expenses & income effortlessly",
                color = textDark,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 32.dp, top = 6.dp)
            )

            // LOGIN TITLE
            Text(
                "Log In",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(18.dp))

            // EMAIL
            Text(
                "Email Address",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it
                 },
                modifier = Modifier
                    .fillMaxWidth()
                 .height(52.dp),
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = null, tint = textDark)
                },

                placeholder = { Text("example@gmail.com", fontSize = 15.sp, color = textDark) },

                colors = TextFieldDefaults.colors(
                    focusedContainerColor = darkCard,
                    unfocusedContainerColor = darkCard,
                    focusedIndicatorColor = bluecard,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                ),
                shape = RoundedCornerShape(16.dp),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(Modifier.height(18.dp))

            // PASSWORD FIELD
            Text(
                "Password",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth().height(52.dp)
                    ,
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null, tint = textDark)
                },
                placeholder = { Text("Enter your password", fontSize = 15.sp, color = textDark)},
//                label = { Text("Enter your password", color = textDark) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = darkCard,
                    unfocusedContainerColor = darkCard,
                    focusedIndicatorColor = bluecard,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions {
                    keyboardController?.hide()
                }
            )

            Spacer(Modifier.height(10.dp))


            Spacer(Modifier.height(32.dp))

            // LOGIN BUTTON
            OutlinedButton(
                onClick = {
                    if (email.isEmpty()) Toast.makeText(context, "Please enter email", Toast.LENGTH_SHORT).show()
                    if (password.isEmpty()) Toast.makeText(context, "Please enter password", Toast.LENGTH_SHORT).show()
                    else viewModel.login(email, password)
                },
                modifier = Modifier
                    .fillMaxWidth().height(45.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = bluecard)
            ) {
                Text("Log In", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(Modifier.height(38.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(Modifier.weight(1f), color = textDark, thickness = 1.dp)
                Text("  Or continue with  ", color = textDark, fontSize = 16.sp)
                Divider(Modifier.weight(1f), color = textDark, thickness = 1.dp)
            }

            Spacer(Modifier.height(28.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    "Don't have an Account?",
                    color = Color.White,
                    fontSize = 15.sp,
                )


                Text(" Sign Up", color = Color.Blue, modifier = Modifier.clickable{
                    navController.navigate(Route.SignScreen)
                }, fontSize = 18.sp)


            }
        }
        when (state) {
            is authState.Error -> {
                Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            }

            is authState.Success -> {

                navController.navigate(Route.DashboardScreen) {
                    popUpTo(Route.LoginScreen) { inclusive = true }
                }
            }

            is authState.Loading -> {
                Box(modifier = Modifier.fillMaxSize().padding(bottom = 43.dp), contentAlignment = Alignment.BottomCenter) {
                    CircularProgressIndicator(color = Color.White)
                }

                return
            }

            is authState.Idle -> {}
        }
    }
}
