/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.pavi2410.adomptcheems.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
