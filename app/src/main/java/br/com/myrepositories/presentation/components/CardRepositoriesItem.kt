package br.com.myrepositories.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import br.com.myrepositories.presentation.mock.dummyMyRepositories
import br.com.myrepositories.presentation.mock.dummyOwner
import coil.compose.AsyncImage

@Composable
fun CardRepositoriesItem(
    image: String?,
    title: String?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White),
        ) {
            val (userImage, titleText) = createRefs()

            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
                    .constrainAs(userImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .width(80.dp)
                    .height(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape)
            )

            Text(
                text = title ?: "",
                modifier = Modifier
                    .constrainAs(titleText) {
                        top.linkTo(userImage.top)
                        start.linkTo(userImage.end)
                        end.linkTo(parent.end)
                        bottom.linkTo(userImage.bottom)
                        width = Dimension.fillToConstraints
                    }
                    .padding(
                        start = 16.dp,
                        end = 8.dp
                    )
                    .wrapContentHeight()
            )
        }
    }
}

@Preview
@Composable
fun CardRepositoriesItemPreview() {
    CardRepositoriesItem(
        image = dummyOwner.avatarUrl,
        title = dummyMyRepositories.name
    )
}