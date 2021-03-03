package me.pavi2410.adomptcheems.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.pavi2410.adomptcheems.data.PetRepository
import me.pavi2410.adomptcheems.data.Puppy
import me.pavi2410.adomptcheems.ui.theme.MyTheme

@ExperimentalMaterialApi
@Composable
fun DetailScreen(puppy: Puppy, onBackClick: () -> Unit) {
    BackdropScaffold(
        backLayerBackgroundColor = puppy.backgroundColor,
        peekHeight = (56 + 360).dp,
        appBar = {
            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier
                    .padding(start = 32.dp, top = 16.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
            ) {
                Image(
                    Icons.Rounded.ArrowBackIosNew,
                    contentDescription = "Back button"
                )
            }
        },
        backLayerContent = {
            Image(
                painter = painterResource(id = puppy.image),
                contentDescription = "Image of ${puppy.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(puppy.backgroundColor)
            )
        },
        frontLayerContent = {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                Column {
                    Text(
                        text = puppy.name,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = puppy.breed)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    InfoCard("Gender", "${puppy.gender}", Color(0xffe2f5cb))
                    InfoCard("Age", "${puppy.age} Years", Color(0xffffe9c2))
                }
                Button(

                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Text(text = "Adompt Mee")
                }
            }
        }
    )
}

@Composable
fun InfoCard(key: String, value: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .size(96.dp)
            .background(color, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(text = value, fontWeight = FontWeight.SemiBold)
        Text(text = key, color = Color.Gray)
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun DetailScreenPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            val puppy = PetRepository.puppies[1]
            DetailScreen(puppy, onBackClick = {})
        }
    }
}