package me.pavi2410.adomptcheems.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import me.pavi2410.adomptcheems.data.PetRepository
import me.pavi2410.adomptcheems.data.Puppy
import me.pavi2410.adomptcheems.ui.theme.MyTheme

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(onCardClick: (Puppy) -> Unit) {
    Scaffold(topBar = {
        Text(
            text = "Adompt Cheems",
            fontFamily = FontFamily.Cursive,
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(start = 32.dp, top = 16.dp)
        )
    }) {
        println(it)
        LazyVerticalGrid(
            contentPadding = PaddingValues(8.dp),
            cells = GridCells.Fixed(2),
            content = {
                items(PetRepository.puppies) { puppy ->
                    PuppyCard(puppy, onCardClick)
                }
            }
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun PuppyCard(puppy: Puppy, onCardClick: (Puppy) -> Unit) {
    ConstraintLayout(
        modifier = Modifier.clickable { onCardClick(puppy) }
    ) {
        val (image, shadowBox, text) = createRefs()

        Image(
            painter = painterResource(id = puppy.image),
            contentDescription = "Image of a puppy named ${puppy.name}",
            modifier = Modifier
                .constrainAs(image) {}
                .fillMaxSize()
                .padding(16.dp)
                .background(
                    color = puppy.backgroundColor,
                    shape = RoundedCornerShape(16.dp)
                )
        )

        Box(
            modifier = Modifier
                .constrainAs(shadowBox) {
                    top.linkTo(image.top)
                    bottom.linkTo(image.bottom)
                    start.linkTo(image.start)
                    end.linkTo(image.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .padding(16.dp)
                .background(
                    brush = Brush.verticalGradient(
                        0f to Color.Black,
                        .3f to Color.Transparent,
                        startY = Float.POSITIVE_INFINITY,
                        endY = 0f
                    ),
                    alpha = 0.3f,
                    shape = RoundedCornerShape(16.dp)
                )
        )

        Text(
            text = puppy.name,
            color = Color.White,
            modifier = Modifier
                .constrainAs(text) {
                    start.linkTo(image.start, margin = 28.dp)
                    bottom.linkTo(image.bottom, margin = 24.dp)
                }
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Preview
@Composable
fun HomeScreenPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            HomeScreen(onCardClick = {})
        }
    }
}