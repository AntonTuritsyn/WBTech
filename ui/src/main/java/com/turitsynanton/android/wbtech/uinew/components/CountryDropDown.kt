package com.turitsynanton.android.wbtech.uinew.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.screens.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CountryDropDown(
    modifier: Modifier
) {

    val countries = listOf(
        Country("Russia", "+7", R.drawable.flag_russia),
        Country("Belarus", "+375", R.drawable.flag_belarus),
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf(countries.first()) }
    var countryCode by remember { mutableStateOf(selectedCountry.code) }

    Log.d("TAG", "CountryDropDown: $countryCode")

    Box(
        modifier = Modifier
            .background(color = Color.Red, shape = RoundedCornerShape(16.dp))
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
//                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
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

@Preview(showBackground = true)
@Composable
fun CountryDropDownPreview() {
    CountryDropDown(modifier = Modifier)
}

data class Country(val name: String, val code: String, val flag: Int)