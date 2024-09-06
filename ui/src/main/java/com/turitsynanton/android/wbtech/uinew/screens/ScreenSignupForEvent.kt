package com.turitsynanton.android.wbtech.uinew.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.generateEvents
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataEvent
import com.turitsynanton.android.wbtech.uinew.components.CountryDropDown
import com.turitsynanton.android.wbtech.uinew.components.PhoneField
import com.turitsynanton.android.wbtech.uinew.items.ComplexTextField
import com.turitsynanton.android.wbtech.uinew.items.GradientButton
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.utils.ButtonStyle
import com.turitsynanton.android.wbtech.uinew.utils.TextFieldStyle

@Composable
internal fun ScreenSignupForEvent(
    modifier: Modifier,
    event: DataEvent, // TODO: DomainEvent
    onCloseClick: () -> Unit,
    onButtonClick: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(16.dp)
        ) {
            Title(modifier = Modifier, event = event)
            Spacer(modifier = Modifier.padding(12.dp))
            /*InteractiveBlock(modifier = Modifier, hint = "sdf", buttonText = "Продолжить") {
            }*/
        }
    }
}

@Composable
internal fun Title(
    modifier: Modifier,
    event: DataEvent // TODO: DomainEvent
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SimpleTextField(
            modifier = Modifier,
            text = stringResource(R.string.signup_to_event),
            fontSize = 50.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000),
            lineHeight = 40.sp
        )
        Spacer(modifier = Modifier.padding(7.dp))
        SimpleTextField(
            modifier = Modifier,
            text = "${event.name} · ${event.date} · ${event.city}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF000000)
        )
        Spacer(modifier = Modifier.padding(12.dp))
        InteractiveBlock(
            modifier = Modifier,
            hint = "Name",
            buttonText = "Next"
        )
    }
}

@Composable
fun InteractiveBlock(
    modifier: Modifier,
    hint: String,
    buttonText:String,
//    selectedCountry: Country,
//    onCountrySelected: (Country) -> Unit,
//    onButtonClick: () -> Unit
) {
    val countries = listOf(
        Country("Russia", "+7", R.drawable.flag_russia),
        Country("Belarus", "+375", R.drawable.flag_belarus),
        // Добавьте сюда другие страны
    )
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ComplexTextField(hint = hint, textFieldStyle = TextFieldStyle.Filled)
        Spacer(modifier = Modifier.padding(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CountryDropDown(modifier = Modifier)
//            ComplexTextField(hint = hint, textFieldStyle = TextFieldStyle.Filled)
            PhoneField(modifier = Modifier, number = "") {
                
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        GradientButton(
            modifier = Modifier,
            text = buttonText,
            buttonStyle = ButtonStyle.Disable
        ) {

        }
    }
}

@Composable
internal fun SetName(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

    }
}

data class Country(val name: String, val code: String, val flag: Int)

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun Preview() {
    ScreenSignupForEvent(
        modifier = Modifier,
        event = generateEvents().first(),
        onCloseClick = {}
    ) {

    }
}