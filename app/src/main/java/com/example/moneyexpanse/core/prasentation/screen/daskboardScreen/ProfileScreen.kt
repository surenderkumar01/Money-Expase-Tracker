package com.example.moneyexpanse.core.prasentation.screen.daskboardScreen

import com.example.moneyexpanse.R

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moneyexpanse.core.common.BottomNavItem
import com.example.moneyexpanse.core.common.Route
import com.example.moneyexpanse.core.prasentation.viewModel.UserViewModel
import com.example.moneyexpanse.ui.theme.darkCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, viewModel: UserViewModel = hiltViewModel()) {


    val uriHandler = LocalUriHandler.current
    val uriHandlerAboutUs = LocalUriHandler.current
    LaunchedEffect(Unit) {
        viewModel.getUser()
    }
    val state = viewModel.userState.value
    val user = state.firstOrNull()
    var alertDailog: Boolean by remember { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopAppBar(
                title = {
                    Text(

                        text = "Profile",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0F1115)
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFF0F1115))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {


            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1D24)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.man),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )

                    Spacer(Modifier.width(14.dp))

                    Column {
                        Text(
                            user?.name.toString(),
                            color = Color.Gray,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(user?.email.toString(), color = Color.Gray, fontSize = 14.sp)
                    }
                }
            }





            SectionTitle("SUPPORT")
            SettingItem("Help Center") {
                uriHandler.openUri("https://drive.google.com/drive/home?dmr=1&ec=wgc-drive-%5Bmodule%5D-goto")
            }
            SettingItem("About Us") {
                uriHandlerAboutUs.openUri("https://drive.google.com/file/d/1IlivNT6aNxSiBygdtPYgFMuXa-PSAm3b/view?usp=sharing")
            }
            SettingItem("Version", "Version: 1.0", enableArrow = false)


            Button(
                onClick = {
                    alertDailog = true


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Log Out", color = Color.White)
            }

            if (alertDailog) {
                AlertDailog(viewModel, navController, onAlertDailog = {

                    alertDailog = false
                })
            }

            Spacer(Modifier.height(20.dp))

            Text(
                "Personal Finance Tracker\nDesigned for efficiency & privacy",
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 13.sp,
        color = Color.Gray,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun SettingItem(
    title: String,
    value: String? = null,
    enableArrow: Boolean = true,
    textColor: Color = Color.White,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1D24))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 🔹 Title Left Side
            Text(
                title,
                color = textColor,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )

            // 🔹 Right Side (Value + Arrow)
            Row(verticalAlignment = Alignment.CenterVertically) {
                value?.let {
                    Text(it, color = Color.Gray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                }
                if (enableArrow) {
                    Text("›", color = Color.Gray, fontSize = 20.sp)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDailog(viewModel: UserViewModel, navController: NavController, onAlertDailog: () -> Unit) {
    AlertDialog(
        onDismissRequest = onAlertDailog,
        title = {
            Text(
                text = "Log Out",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        text = {
            Text(
                text = "Are you sure you want to log out?",
                fontSize = 16.sp,
                color = Color(0xFFB0BEC5)
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = Color(0xFFFFC107), // Amber icon color
                modifier = Modifier.size(40.dp)
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onAlertDailog()
                    viewModel.LogOut()
                    navController.navigate(Route.LoginScreen) {
                        popUpTo(BottomNavItem.Profile.route) {
                            inclusive = true
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD32F2F) // red confirm button
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Yes", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            Button(
                onClick = onAlertDailog,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF455A64)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "No", fontSize = 16.sp)
            }
        },
        containerColor = darkCard,
        shape = RoundedCornerShape(18.dp)
    )


}


