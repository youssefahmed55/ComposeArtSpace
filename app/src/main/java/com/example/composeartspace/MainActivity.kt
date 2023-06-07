package com.example.composeartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeartspace.ui.theme.ComposeArtSpaceTheme
import com.example.pojo.Art

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArtSpaceTheme {
                ArtSpace()
            }
        }
    }
}

@Composable
fun ArtSpace() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            var mutableCount by remember { mutableStateOf(0) }
            val listOfArt: List<Art> = listOf(
                Art(
                    R.drawable.bridge_art,
                    stringResource(R.string.sailing_under_the_bridge),
                    stringResource(R.string.kat_kuan_2017)
                ), Art(
                    R.drawable.flowers_art,
                    stringResource(R.string.still_life_of_blue_rose_and_other_flowers),
                    stringResource(R.string.owen_scott_2021)
                )
            )

            ImageAndText(mutableCount, listOfArt)


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ButtonMove(
                    stringResource(R.string.previous), {
                        if (mutableCount != 0) {
                            mutableCount -= 1
                        }
                    },
                    Modifier
                        .width(130.dp)
                        .padding(5.dp)
                )
                ButtonMove(
                    stringResource(R.string.next), {
                        if (mutableCount < listOfArt.size - 1) {
                            mutableCount += 1
                        }
                    },
                    Modifier
                        .width(130.dp)
                        .padding(5.dp)
                )

            }

        }


    }
}

@Composable
fun ImageAndText(count: Int, listOfAtr: List<Art>) {

    Card(
        modifier = Modifier
            .width(300.dp)
            .height(400.dp)
            .padding(top = 20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        elevation = CardDefaults.elevatedCardElevation(15.dp)
    ) {
        Image(
            painter = painterResource(id = listOfAtr[count].image),
            contentDescription = listOfAtr[count].description,
            modifier = Modifier
                .padding(25.dp)
                .fillMaxSize(), contentScale = ContentScale.FillBounds
        )

    }
    TitleAndDescriptionTexts(
        listOfAtr[count].title,
        listOfAtr[count].description,
        Modifier
            .width(300.dp)
            .padding(top = 20.dp)
    )

}

@Composable
fun ButtonMove(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(modifier = modifier, onClick = onClick, content = { Text(text = text) })

}


@Composable
fun TitleAndDescriptionTexts(title: String, description: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontSize = 25.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = description,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }

}

@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Composable
fun ArtSpacePreview() {
    ComposeArtSpaceTheme {
        ArtSpace()
    }
}