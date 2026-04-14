package com.example.moneyexpanse.core.prasentation.screen.daskboardScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text

import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moneyexpanse.core.common.BottomNavItem
import com.example.moneyexpanse.core.data.dataModel.dataModel
import com.example.moneyexpanse.core.prasentation.viewModel.ExpanseVieewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID


@Composable
fun AddTransactionScreen(
    navController: NavController,

    viewModelExpanse: ExpanseVieewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
val TotolAmount = viewModelExpanse.TotolAmount
    LaunchedEffect(Unit){
        viewModelExpanse.fatchTotalEncome()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color(0xFF0B1220))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Add Transaction",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🔹 EXPENSE / INCOME TOGGLE
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E293B), RoundedCornerShape(16.dp))
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ToggleButton(
                text = "Expense"
            )


        }

        Spacer(modifier = Modifier.height(32.dp))

        // 🔹 AMOUNT SECTION
        Text(
            text = "Amount",
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "₹",
                fontSize = 36.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = amount,
                onValueChange = { amount = it },
                cursorBrush = SolidColor(Color.White),
                textStyle = TextStyle(
                    fontSize = 48.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ) , singleLine = true,
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 🔹 DETAILS CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF111C2F)
            )
        ) {
            Column {

                DetailItem1(
                    icon = Icons.Default.DateRange,
                    title = "Category",

                    ) {
                    type = it
                }

                Divider(color = Color.DarkGray)

                DetailItem2(
                    icon = Icons.Default.DateRange,
                    title = "Date",
                    ) {
                    date = it
                }

                Log.d("TAGEDATE", date)
                Divider(color = Color.DarkGray)

                // 🔹 NOTE FIELD
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color(0xFF1E88E5)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    OutlinedTextField(
                        value = note,
                        onValueChange = { note = it },
                        placeholder = {
                            Text(
                                text = "Add a note...",
                                color = Color.Gray
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )

                    )

                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        Spacer(modifier = Modifier.height(35.dp))

        // 🔹 SAVE BUTTON
        Button(
            onClick = {
                val total = TotolAmount.value.toIntOrNull() ?: 0
                Log.d("TOTAl_AMOUNT",total.toString())


                    if (total< 1000) {

                        Toast.makeText(context, "Minimum amount should be greater than 1000 ", Toast.LENGTH_SHORT).show()

                        return@Button

                    }

                if (date.isEmpty()) {
                    Toast.makeText(context, "Please enter date", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (type.isEmpty()) {
                    Toast.makeText(context, "Please enter type", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (amount.isEmpty()) {
                    Toast.makeText(context, "Please enter amount", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (note.isEmpty()) {
                    Toast.makeText(context, "Please enter note", Toast.LENGTH_SHORT).show()
                    return@Button
                } else {
                    val data = dataModel("",amount, date, type, note)
                    viewModelExpanse.saveExpanse(data)
                    Toast.makeText(context, "Expanse Saved", Toast.LENGTH_SHORT).show()
                }
                amount = ""
                date = ""
                type = ""
                note = ""
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E88E5)
            )
        ) {
            Text(
                text = "Save Transaction",
            )
        }
    }
}

@Composable
fun ToggleButton(
    text: String,

    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()

            .height(44.dp)
            .background(
                Color(0xFF334155),
                RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun DetailItem1(
    icon: ImageVector,
    title: String,
    onType: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("Select category") }

    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, null, tint = Color(0xFF1E88E5))
            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(title, color = Color.Gray, fontSize = 12.sp)
                Text(selectedItem, color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.KeyboardArrowDown, null, tint = Color.Gray)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listOf("Food", "Travel", "Shopping", "Bills").forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        selectedItem = it
                        onType(selectedItem)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun DetailItem2(
    icon: ImageVector,
    title: String,
    onDate: (String) -> Unit
) {
    var showCalendar by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("Select date") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showCalendar = true }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = Color(0xFF1E88E5))
        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(title, color = Color.Gray, fontSize = 12.sp)
            Text(selectedDate, color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.weight(1f))
        Icon(Icons.Default.DateRange, null, tint = Color.Gray)
    }

    if (showCalendar) {
        CalendarDialog(
            onDateSelected = {
                selectedDate = it
                showCalendar = false
                onDate(selectedDate)
            },
            onDismiss = { showCalendar = false }
        )
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun CalendarDialog(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.let {
                    onDateSelected(formatDate(it))
                }
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}


fun formatDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

