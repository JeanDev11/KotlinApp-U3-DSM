package com.example.woof

import android.hardware.camera2.params.ColorSpaceTransform
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.woof.model.FitnessActivity
import com.example.woof.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FitnessChallengeApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessChallengeApp() {
    val fitnessActivities = listOf(
        FitnessActivity("Día 1", "Cargar pesas", R.drawable.day1, "Realiza ejercicios de levantamiento de pesas para fortalecer tu musculatura y aumentar tu masa muscular. Enfócate en series de 12-15 repeticiones para un entrenamiento efectivo."),
        FitnessActivity("Día 2", "Correr", R.drawable.day2, "Dedica 30 minutos a correr para aumentar tu resistencia cardiovascular y mejorar tu salud general. Es una excelente manera de liberar endorfinas y reducir el estrés."),
        FitnessActivity("Día 3", "Ciclismo", R.drawable.day3, "Montar en bicicleta es ideal para tonificar las piernas y mejorar la resistencia. Disfruta de un paseo al aire libre o usa una bicicleta estática en casa."),
        FitnessActivity("Día 4", "Natación", R.drawable.day4, "Nadar es una excelente actividad para tus pulmones. Relaja tus músculos y mejora tu capacidad pulmonar con 30 minutos de nado."),
        FitnessActivity("Día 4", "Yoga", R.drawable.day5, "El yoga te ayuda a mejorar la flexibilidad, la fuerza y la concentración. Dedica 30 minutos a practicar posturas y técnicas de respiración para relajarte y reducir el estrés."),
        // more
    )

    Column {
        CenterAlignedTopAppBar(
            title = { Text(
                text ="30 Días de Actividad Fisica",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                )
            ) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary // Color de fondo
            ),
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            items(fitnessActivities) { activity ->
                FitnessDayCard(fitnessActivity = activity)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Composable
fun FitnessDayCard(fitnessActivity: FitnessActivity) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(300))
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = fitnessActivity.day,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                ),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = fitnessActivity.title,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = fitnessActivity.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = fitnessActivity.description,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Preview
@Composable
fun FitnessAppPreview() {
    SuperheroesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FitnessChallengeApp()
        }
    }
}