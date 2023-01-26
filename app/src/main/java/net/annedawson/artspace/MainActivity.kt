/*
Date: Thursday 26th January 2023, 11:10 PT
Programmer: Anne Dawson
App: Art Space
File: MainActivity.kt
Purpose: Unit 2: Introduction to UI and state in Compose
From: https://developer.android.com/courses/pathways/android-basics-compose-unit-2-pathway-3#:~:text=Project%3A%20Create%20an%20Art%20Space%20app
Status: Completed to end of Unit 2, Pathway 3, Part 6
        Part 6 Project: Create an Art Space app
*/

package net.annedawson.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.annedawson.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme { // defined in Theme
                // A surface container using the 'background' color from the theme

                // Surface() remains in its default position. This from ChatGPT:
                /*
                In a Jetpack Compose app, the Surface composable should be placed
                as a parent of the other composable elements that make up the surface.
                This will ensure that the Surface composable applies its
                styling and behavior to all of its child composable.
                 */
                // The behavior and style of any child composable
                // can be further modified using a modifier.
                // All composable should have a modifier parameter.

                Surface(
                    modifier = Modifier.fillMaxSize(), // the whole device screen
                    color = MaterialTheme.colors.background,
                    //color = Color.LightGray
                    // the background color appears behind scrollable content
                    // MaterialTheme is defined in Theme.kt
                    elevation = 8.dp
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    MainScreen(
        modifier = Modifier
            .padding(24.dp) // New. Comment out this line to see effect.
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var screen by remember {
        mutableStateOf(1)
    }

    val myImageResource = when (screen) {
        1 -> R.drawable.image1
        2 -> R.drawable.image2
        3 -> R.drawable.image3
        4 -> R.drawable.image4
        5 -> R.drawable.image5
        else -> R.drawable.image6
    }

    val myTextResource1 = when (screen) {
        1 -> R.string.image_1_title
        2 -> R.string.image_2_title
        3 -> R.string.image_3_title
        4 -> R.string.image_4_title
        5 -> R.string.image_5_title
        else -> R.string.image_6_title
    }

    val myTextResource2 = when (screen) {
        1 -> R.string.image_1_artist
        2 -> R.string.image_2_artist
        3 -> R.string.image_3_artist
        4 -> R.string.image_4_artist
        5 -> R.string.image_5_artist
        else -> R.string.image_6_artist
    }

    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Spacer(modifier = Modifier.height(8.dp))

        Surface(color = MaterialTheme.colors.background,
            elevation = 16.dp, border = BorderStroke(3.dp, Color.LightGray)
        ){
            // elevation makes the contents appear elevated
            // by darkening and shading the background a little
            // try commenting out the elevation = 16.dp line above to see the
            // difference in the UI. Elevation makes the UI much more attractive with
            // very little effort.
            Image(
                painter = painterResource(myImageResource), contentDescription = "Image $screen",

                modifier = Modifier

                    //.size(200.dp, 280.dp)
                    // .border(3.dp, Color(169, 169, 169))
                    //.clip(CutCornerShape(16.dp)) - compare with Lemonade app
                    .padding(36.dp)
                /* .clickable(enabled = true, onClickLabel = "Clickable image", onClick = {
                     screen++
                     if (screen > 6) screen = 1
                 })*/
            )

        }


        Spacer(modifier = Modifier.height(24.dp))
        // Surface() {        the () are optional here and should be removed
        Surface {
            Column {
                Text(
                    text = stringResource(myTextResource1),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 0.dp, bottom = 4.dp, end = 0.dp),
                    fontSize = 20.sp,fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Left
                )
                val myTextRes2 = stringResource(myTextResource2)
                val myIndex = myTextRes2.indexOf("(")
                val myName = myTextRes2.substring(0,myIndex)
                val myYear = myTextRes2.substring(myIndex)


               /* Text(
                    //text = myTextRes2,
                    text = myYear,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 0.dp, bottom = 0.dp, end = 0.dp),
                    fontSize = 14.sp, fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left
                )*/

                Text(
                    // buildAnnotatedString returns an AnnotatedString
                    // which like a String type can be assigned to the text parameter
                    // of a Text composable.
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(myName)
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Light)) {
                            append(myYear)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 0.dp, bottom = 0.dp, end = 0.dp),
                    fontSize = 14.sp, fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left
                )
            }
        }


        // Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    screen--
                    if (screen < 1) screen = 6
                },
                Modifier
                    .fillMaxWidth(0.5f)
                    .padding(10.dp)
            ) {
                Text(text = stringResource(R.string.prev))
            }

            Button(
                onClick = {
                    screen++
                    if (screen > 6) screen = 1
                },
                Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp)
            ) {
                Text(text = stringResource(R.string.next))
            }
        }


    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {  // defined in Theme
        ArtSpaceApp()
    }
}