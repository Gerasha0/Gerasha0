package com.example.lab1resources

import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.os.LocaleListCompat
import com.example.lab1resources.ui.theme.Lab1ResourcesTheme
import com.example.lab1resources.ui.theme.SurnameBackgroundColor
import com.example.lab1resources.ui.theme.SurnameBorderColor
import com.example.lab1resources.ui.theme.SurnameTextStyleCompose
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1ResourcesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        onLanguageSelected = { langTag ->
                            setAppLanguage(langTag)
                        }
                    )
                }
            }
        }
    }

    private fun setAppLanguage(languageCode: String) {
        val appLocales = LocaleListCompat.forLanguageTags(languageCode)
        AppCompatDelegate.setApplicationLocales(appLocales)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onLanguageSelected: (String) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val rawLocale: String = AppCompatDelegate.getApplicationLocales().toLanguageTags()
    val currentLocale: String = rawLocale.ifEmpty {
        Locale.getDefault().toLanguageTag()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Interactive Language Selector Card
            LanguageSwitcherCard(
                currentLocale = currentLocale,
                onLanguageSelected = onLanguageSelected
            )

            // Task 1: Surname with custom Compose style
            Task1SurnameSection()

            // Task 2: XML Graphic Shape custom_shape.xml
            Task2XmlShapeSection()

            // Task 3: Localization (Flag & Coat of Arms from res/drawable*)
            Task3LocalizationSection()

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun LanguageSwitcherCard(
    currentLocale: String,
    onLanguageSelected: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.select_language),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
            ) {
                val isUk = currentLocale.startsWith("uk")
                val isDe = currentLocale.startsWith("de")
                val isEn = currentLocale.startsWith("en") || (!isUk && !isDe)

                FilterChip(
                    selected = isUk,
                    onClick = { onLanguageSelected("uk") },
                    label = { Text(stringResource(id = R.string.lang_uk), fontSize = 13.sp) }
                )

                FilterChip(
                    selected = isDe,
                    onClick = { onLanguageSelected("de") },
                    label = { Text(stringResource(id = R.string.lang_de), fontSize = 13.sp) }
                )

                FilterChip(
                    selected = isEn,
                    onClick = { onLanguageSelected("en-GB") },
                    label = { Text(stringResource(id = R.string.lang_en), fontSize = 13.sp) }
                )
            }
        }
    }
}

@Composable
fun Task1SurnameSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.task1_title),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            //Surname Box
            Box(
                modifier = Modifier
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
                    .background(color = SurnameBackgroundColor, shape = RoundedCornerShape(16.dp))
                    .border(width = 2.dp, color = SurnameBorderColor, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 32.dp, vertical = 18.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.user_surname),
                    style = SurnameTextStyleCompose,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun Task2XmlShapeSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.task2_title),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Display XML Graphic Shape custom_shape.xml via AndroidView interop
            AndroidView(
                factory = { ctx ->
                    View(ctx).apply {
                        val shapeDrawable = ContextCompat.getDrawable(ctx, R.drawable.custom_shape)
                        background = shapeDrawable
                    }
                },
                modifier = Modifier
                    .width(240.dp)
                    .height(100.dp)
                    .padding(4.dp)
            )
        }
    }
}

@Composable
fun Task3LocalizationSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.task3_title),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Text(
                text = stringResource(id = R.string.country_name),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // National Flag
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(id = R.string.flag_label),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.national_flag),
                        contentDescription = stringResource(id = R.string.flag_label),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(90.dp)
                            .width(130.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
                    )
                }

                // Coat of Arms
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(id = R.string.emblem_label),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.national_emblem),
                        contentDescription = stringResource(id = R.string.emblem_label),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(110.dp)
                            .width(100.dp)
                            .padding(2.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Lab1ResourcesTheme {
        MainScreen()
    }
}