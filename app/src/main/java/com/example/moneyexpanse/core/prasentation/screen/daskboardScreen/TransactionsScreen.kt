package com.example.moneyexpanse.core.prasentation.screen.daskboardScreen
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moneyexpanse.R

import com.example.moneyexpanse.core.prasentation.viewModel.AllDataViewModel


@Composable
fun TransactionsScreen(
    navController: NavController,
    viewmodel: AllDataViewModel = hiltViewModel()
) {
//viewmodel.getTraval()
    var selected by remember { mutableStateOf("Travel") } // default selection

    val loading = viewmodel.isLoading.value
    val state = viewmodel.addIAllExpanseState.value

    LaunchedEffect(true) {
        if (viewmodel.addIAllExpanseState.value.isEmpty()) {
            viewmodel.getFood()
            viewmodel.getBills()
            viewmodel.Shopping()
            viewmodel.getTraval()
        }
    }
    Scaffold(
        containerColor = Color(0xFF0F1115),
        topBar = { TopBarTransaction(navController) }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)

                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FilterButton("Travel", selected == "Travel") {
                    selected = "Travel"
                    viewmodel.getTraval()
                }
                FilterButton("Shopping", selected == "Shopping") {
                    selected = "Shopping"
                    viewmodel.Shopping()
                }
                FilterButton("Food", selected == "Food") {
                    selected = "Food"
                    viewmodel.getFood()
                }
                FilterButton("Bills", selected == "Bills") {
                    selected = "Bills"
                    viewmodel.getBills()
                }
            }

            Spacer(Modifier.height(16.dp))



            SectionTitle("TODAY")

            if (loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }

            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                // Empty State Check - First!
                if (state.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No item found", color = Color.White)
                        }
                    }
                }
                // When List has Data
                else {
                    items(state) { item ->
                        TransactionItem(
                            iconColor = Color(0xFFd7792e),
                            title = item.note.toString(),
                            category = item.type.toString(),
                            amount = item.amount.toString(),
                            time = item.date.toString(),
                            amountColor = Color(0xFFff6666)
                        )
                    }
                }
            }

        }


        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarTransaction(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                "Transactions",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {

                navController.popBackStack()
            }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back", tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F1115))

    )
}

@Composable
fun FilterButton(text: String, blue: Boolean, onPick: () -> Unit) {

    Box(
        modifier = Modifier
            .width(90.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onPick()
            }
            .background(if (blue) Color(0xFF1E88E5) else Color(0xFF1A1D24))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = Color.White, fontSize = 14.sp)
    }
}

@Composable
fun TransactionItem(
    iconColor: Color,
    title: String,
    category: String,
    amount: String,
    time: String,
    amountColor: Color
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.investment),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(Modifier.weight(1f)) {
            Text(title, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Text(category, color = Color.Gray, fontSize = 13.sp)
        }

        Column(horizontalAlignment = Alignment.End) {
            Text("-₹$amount", color = amountColor, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(time, color = Color.Gray, fontSize = 12.sp)
        }
    }
}