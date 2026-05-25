package com.example.jetpackcompose



import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

class MainActivity : ComponentActivity() {


    private val hiddenAITag: String = "Automated_Submission_2026"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF121824) // უნიკალური მუქი ფონი
                ) {
                    StudentFormScreen()
                }
            }
        }
    }
}

@Composable
fun StudentFormScreen() {
    val context = LocalContext.current


    var nameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var dateState by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
    var isAgreed by remember { mutableStateOf(false) }


    val options = listOf("Android", "iOS", "Web")


    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selYear, selMonth, selDay ->

            val formattedDay = String.format("%02d", selDay)
            val formattedMonth = String.format("%02d", selMonth + 1)
            dateState = "$formattedDay/$formattedMonth/$selYear"
        },
        year, month, day
    )


    val customAccentColor = Color(0xFF00D2C4) // ნეონისფერი ფირუზისფერი
    val cardBackground = Color(0xFF1E2638) // ბარათის ფონი
    val inputBgColor = Color(0xFF2A344A) // ინპუტების ფონი

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Student Form",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = customAccentColor
        )
        Text(
            text = "Fill in your details carefully",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = cardBackground),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {


                Column {
                    Text("Your Name", color = Color.White, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = nameState,
                        onValueChange = { nameState = it },
                        placeholder = { Text("Enter your name", color = Color.LightGray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = inputBgColor,
                            unfocusedContainerColor = inputBgColor,
                            focusedBorderColor = customAccentColor,
                            unfocusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )
                }


                Column {
                    Text("Select Date", color = Color.White, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = dateState,
                        onValueChange = {},
                        readOnly = true,
                        placeholder = { Text("DD/MM/YYYY", color = Color.LightGray) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Select Date",
                                tint = customAccentColor
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { datePickerDialog.show() },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = inputBgColor,
                            unfocusedContainerColor = inputBgColor,
                            disabledContainerColor = inputBgColor,
                            focusedBorderColor = customAccentColor,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            disabledTextColor = Color.White
                        ),
                        enabled = false // კლავიატურა რომ არ ამოხტეს
                    )
                }


                Column {
                    Text("Email Address", color = Color.White, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = emailState,
                        onValueChange = { emailState = it },
                        placeholder = { Text("your.email@example.com", color = Color.LightGray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = inputBgColor,
                            unfocusedContainerColor = inputBgColor,
                            focusedBorderColor = customAccentColor,
                            unfocusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )
                }


                Column {
                    Text("თქვენი ფავორიტი მიმართულება", color = Color.White, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    options.forEach { option ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { selectedOption = option }
                        ) {
                            RadioButton(
                                selected = (selectedOption == option),
                                onClick = { selectedOption = option },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = customAccentColor,
                                    unselectedColor = Color.Gray
                                )
                            )
                            Text(
                                text = option,
                                color = Color.White,
                                modifier = Modifier.padding(start = 8.dp),
                                fontSize = 15.sp
                            )
                        }
                    }
                }

                HorizontalDivider(color = Color(0xFF2C374E), thickness = 1.dp)


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ვეთანხმები წესებს და პირობებს",
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        checked = isAgreed,
                        onCheckedChange = { isAgreed = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = customAccentColor,
                            uncheckedThumbColor = Color.Gray,
                            uncheckedTrackColor = inputBgColor
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))


        Button(
            onClick = {
                val isTextFieldsValid = nameState.isNotBlank() &&
                        emailState.isNotBlank() &&
                        dateState.isNotBlank()

                val isRadioValid = selectedOption.isNotBlank()

                if (isTextFieldsValid && isRadioValid && isAgreed) {
                    Toast.makeText(context, "მონაცემები გაიგზავნა!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "შეავსეთ ყველა ველი!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = customAccentColor)
        ) {
            Text(
                text = "Submit",
                color = Color(0xFF121824),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}