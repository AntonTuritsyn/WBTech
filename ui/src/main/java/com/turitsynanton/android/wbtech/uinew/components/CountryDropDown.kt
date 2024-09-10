package com.turitsynanton.android.wbtech.uinew.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.screens.registration.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CountryDropDown(
    modifier: Modifier,
    selectedCountry: Country,
) {

    val countries = listOf(
        Country("Russia", "+7", R.drawable.flag_russia),
        Country("Belarus", "+375", R.drawable.flag_belarus),
        Country("Kazakhstan", "+7", R.drawable.flag_kz)
    )

    var expanded by remember { mutableStateOf(false) } // оставить здесь
    var selectedCountry by remember { mutableStateOf(countries.first()) }
    var countryCode by remember { mutableStateOf(selectedCountry.code) }

    Box(
        modifier = Modifier
            .background(color = Color(0xFFF6F6FA), shape = RoundedCornerShape(16.dp))
            .padding(vertical = 16.dp, horizontal = 20.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            Row(
                modifier = Modifier.menuAnchor(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = selectedCountry.flag), contentDescription = "")
                Spacer(modifier = Modifier.padding(end = 8.dp))
                SimpleTextField(
                    modifier = Modifier,
                    text = selectedCountry.code,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF000000)
                )
            }

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = {
                            Row {
                                Image(
                                    painter = painterResource(id = country.flag),
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 8.dp),
                                    contentScale = ContentScale.Fit
                                )
                                Text(text = "${country.name} (${country.code})")
                            }
                        },
                        onClick = {
                            selectedCountry = country
                            countryCode = country.code
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

data class Country(val name: String, val code: String, val flag: Int)