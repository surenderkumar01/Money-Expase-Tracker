package com.example.moneyexpanse.core.prasentation.screen.daskboardScreen

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.moneyexpanse.core.prasentation.viewModel.AuthViewModel
import com.example.moneyexpanse.core.prasentation.viewModel.addincomeViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddIncomeScreen(navController: NavController,
    viewModel: addincomeViewModel = hiltViewModel()
) {

    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B1220)).statusBarsPadding()
            .padding(16.dp)
    ) {

        /* 🔹 TOP BAR */
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(12.dp))
            Text(
                textAlign = TextAlign.Center,
                text = "Add Income",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        /* 🔹 AMOUNT */
        Text("Amount", color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("₹", fontSize = 36.sp, color = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = amount,
                onValueChange = { amount = it },
                textStyle = TextStyle(
                    fontSize = 48.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                cursorBrush = SolidColor(Color.White)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                if (amount.isEmpty()) {
                    return@Button
                } else {
                    viewModel.addIncome(amount.toString())
                    navController.navigate(com.example.moneyexpanse.core.common.Route.DashboardScreen)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E88E5)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Save Income",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}
