package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            // header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color(255, 215, 0)) //yellow
            ) {
                Text(
                    text = "Lemonade",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Display content
            when (currentStep) {
                1 -> {
                    lemonDisplay(
                        textResourceId = R.string.tap_lemon_tree,
                        imageResourceId = R.drawable.lemon_tree,
                        contentDescriptionId = R.string.lemon_tree_content_description
                    ) {
                        currentStep = 2
                    }
                }
                // Go to next step
                2 -> {
                    lemonDisplay(
                        textResourceId = R.string.tap_lemon_to_squeeze,
                        imageResourceId = R.drawable.lemon_squeeze,
                        contentDescriptionId = R.string.lemon_content_description
                    ) {
                        currentStep = 3
                    }
                }
                // Go to next step
                3 -> {
                    lemonDisplay(
                        textResourceId = R.string.tap_lemonade_to_drink,
                        imageResourceId = R.drawable.lemon_drink,
                        contentDescriptionId = R.string.lemonade_content_description
                    ) {
                        currentStep = 4
                    }
                }
                // Go to next step
                4 -> {
                    lemonDisplay(
                        textResourceId = R.string.tap_empty_glass_to_start,
                        imageResourceId = R.drawable.lemon_restart,
                        contentDescriptionId = R.string.empty_glass_content_description
                    ) {
                        currentStep = 1 //restart
                    }
                }
            }
        }
    }
}

@Composable
fun lemonDisplay(
    textResourceId: Int,
    imageResourceId: Int,
    contentDescriptionId: Int,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // border
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(Color(105, 205, 216), shape = RoundedCornerShape(8.dp))
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable(onClick = onClick)
        ) {
            Image(
                painter = painterResource(imageResourceId),
                contentDescription = stringResource(contentDescriptionId),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .wrapContentSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(textResourceId),
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}
