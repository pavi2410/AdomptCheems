package me.pavi2410.adomptcheems.data

import androidx.compose.ui.graphics.Color
import me.pavi2410.adomptcheems.R
import kotlin.random.Random

object PetRepository {
    private val randomSeed = System.currentTimeMillis()
    private val seededRandom = Random(randomSeed)

    private fun getRandomName() =
        listOf("Foo", "Bar", "Fizz", "Buzz").random(seededRandom)

    private fun getRandomAge() =
        (3..10).random(seededRandom)

    private fun getRandomGender() =
        Gender.values().random(seededRandom)

    private fun getRandomBreed() =
        listOf("German Shepherd", "Labrador", "Bull dog").random(seededRandom)

    private fun getRandomImage() =
        listOf(R.drawable.cheem1, R.drawable.cheem2, R.drawable.cheem3).random(seededRandom)

    private fun getRandomColor() =
        listOf(Color(0xffffe9c2), Color(0xff9dd6ff), Color(0xffeca5b5), Color(0xffe2f5cb)).random()


    val puppies: List<Puppy> by lazy {
        List(10) {
            Puppy(
                id = it,
                name = getRandomName(),
                age = getRandomAge(),
                gender = getRandomGender(),
                breed = getRandomBreed(),
                image = getRandomImage(),
                backgroundColor = getRandomColor()
            )
        }
    }
}
