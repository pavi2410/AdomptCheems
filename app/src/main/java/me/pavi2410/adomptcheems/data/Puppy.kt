package me.pavi2410.adomptcheems.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Puppy(
    val id: Int,
    val name: String,
    val age: Int,
    val gender: Gender,
    val breed: String,
    @DrawableRes val image: Int,
    val backgroundColor: Color
)

enum class Gender {
    Male, Female
}
