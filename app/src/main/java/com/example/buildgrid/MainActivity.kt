package com.example.buildgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buildgrid.data.DataSource
import com.example.buildgrid.model.Topic
import com.example.buildgrid.ui.theme.BuildGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildGridTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScreen()
                }
            }
        }
    }
}

@Composable
fun MyScreen() {
    GridList(topicList = DataSource().loadCategoryList(), modifier = Modifier.padding(
        dimensionResource(id = R.dimen.padding_small)))
}

@Composable
fun GridList(topicList: List<Topic>,modifier: Modifier=Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        state = rememberLazyGridState(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
    ) {
        items(topicList) { topic: Topic ->
            GridCard(topic = topic)
        }
    }
}

@Composable
fun GridCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(modifier = Modifier.height(68.dp)) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(68.dp)
                    .wrapContentWidth(align = Alignment.Start),
                painter = painterResource(id = topic.imageResource),
                contentDescription = stringResource(
                    id = topic.topic
                ),
                contentScale = ContentScale.Crop
            )
            TitleColumn(topic = topic, modifier = modifier.align(Alignment.Bottom))
        }
    }
}

@Composable
fun TitleColumn(topic: Topic, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(top = dimensionResource(id = R.dimen.padding_large), end = dimensionResource(id = R.dimen.padding_large))
            .wrapContentSize(align = Alignment.BottomStart)
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize(align = Alignment.TopEnd)
                .padding(start = dimensionResource(id = R.dimen.padding_large)),
            text = stringResource(id = topic.topic)
        )
        IconNumberRow(
            modifier = Modifier.wrapContentSize(align = Alignment.BottomStart),
            topic = topic
        )
    }
}

@Composable
fun IconNumberRow(topic: Topic, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .wrapContentSize(align = Alignment.BottomStart)
            .padding(top = dimensionResource(id = R.dimen.padding_small)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_large), end = dimensionResource(id = R.dimen.padding_small)),
            painter = painterResource(id = R.drawable.ic_grain),
            contentDescription = null
        )
        Text(text = topic.topicNumber.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyScreen()
}