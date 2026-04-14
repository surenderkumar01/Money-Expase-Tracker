package com.example.moneyexpanse.core.prasentation.screen.daskboardScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moneyexpanse.core.common.BottomNavItem
import com.example.moneyexpanse.core.common.Route
import com.example.moneyexpanse.core.prasentation.viewModel.ExpanseVieewModel
import com.example.moneyexpanse.core.prasentation.viewModel.UserViewModel
import com.example.moneyexpanse.core.prasentation.viewModel.addincomeViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: addincomeViewModel = hiltViewModel(),
    viewModelExpanse: ExpanseVieewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {

    val data = viewModel.addIncomeState.value

    LaunchedEffect(Unit) {
    viewModelExpanse.fatchTotalExpanse()
    viewModel.fatch()
//        if(viewModelExpanse.addIExpanseState.value.isEmpty()) {

            viewModelExpanse.fatchExpanse()
//        }
    }


    val expanse by viewModelExpanse.addIExpanseState
    val isLoading = viewModelExpanse.isLoading.value
    val totalExpanse by viewModelExpanse.TotoalExpanse

Log.d("TOTAL",expanse.toString())
//    user name fatching
    LaunchedEffect(Unit) {
        userViewModel.getUser()

    }
    val state = userViewModel.userState.value
    val user = state.firstOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color(0xFF0F172A)) // Dark background
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Row(  verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        Log.d("TAGE", "ADD")
                        navController.navigate(Route.AddIncomeScreen)
                    }
                    .padding(8.dp)) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Add",
                    tint = Color(0xFF1E88E5),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "Add Money",
                    color = Color(0xFF1E88E5),
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "Good Morning,",
            color = Color.White,
            fontSize = 22.sp
        )
        Text(
            text = user?.name.toString(),
            color = Color(0xFF1E88E5),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 🔹 BALANCE + INCOME CARDS

            BalanceCardNewDesign(data, totalExpanse)



        Spacer(modifier = Modifier.height(20.dp))


        Spacer(modifier = Modifier.height(20.dp))

        // 🔹 RECENT TRANSACTIONS TITLE
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Recent Transactions",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "See All",
                color = Color(0xFF1E88E5),
                modifier = Modifier.clickable {
                    navController.navigate(BottomNavItem.Transaction.route)
                }
            )
        }

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                   CircularProgressIndicator()

            }
        }


        Spacer(modifier = Modifier.height(12.dp))

        // 🔹 TRANSACTION ITEM
        LazyColumn {
            if (expanse.isEmpty()){
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
           else {

            val  list= expanse.reversed()

                items(list) { item ->

                    TransactionItem(
                        id = item.id.toString(),
                        title = item.note.toString(),
                        time = item.date.toString(),
                        amount = item.amount.toString()
                    ){
                        viewModelExpanse.RemoveExpance(it)
                        Log.d("TAG_ID",it)

                    }

                }
            }
        }

    }
}


@Composable
fun TransactionItem(
    id:String,
    title: String,
    time: String,
    amount: String,
    onDelete: (String) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1F2933)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(title, color = Color.White, fontWeight = FontWeight.Bold)
                Text(time, color = Color.Gray, fontSize = 12.sp)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    "-₹$amount",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(10.dp))

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete",
                    tint = Color.Red,
                    modifier = Modifier.clickable {

                        onDelete(id)

                    }
                )
            }

        }

    }

}
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BalanceCardNewDesign(balance: String, totalExpanse: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Card(
            modifier = Modifier
                .weight(1f)
                .height(210.dp),
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF0072FF),
                                Color(0xFF00C6FF)
                            )
                        )
                    )
                    .padding(20.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_info_details),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Account Summary",
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    }


                    Text(
                        text = "₹$balance",
                        color = Color.White,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.ExtraBold
                    )


                    Spacer(modifier = Modifier.height(8.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Income:₹100000 ",   // it will add in future
                            color = Color(0xFFFFFFFF),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Total Expanse:₹$totalExpanse",
                            color = Color(0xFFFF5252),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}



