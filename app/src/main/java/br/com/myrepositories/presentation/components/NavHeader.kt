package br.com.myrepositories.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import br.com.myrepositories.R
import br.com.myrepositories.presentation.mock.dummyUser
import coil.compose.AsyncImage

@Composable
fun NavHeader(
    image: String?,
    name: String?,
    login: String?,
    bio: String?,
    followers: String?,
    location: String?
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
    ) {

        val (userImage, userNameText, userLoginText, userBioText, userFollowersImage, userFollowersText, userLocationImage, userLocationText) = createRefs()

        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(userImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .width(80.dp)
                .height(80.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Black, CircleShape)
        )

        Text(
            text = name ?: "",
            modifier = Modifier
                .constrainAs(userNameText) {
                    top.linkTo(userImage.top)
                    start.linkTo(userImage.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(
                    start = 16.dp,
                    top = 16.dp
                )
                .wrapContentHeight()
        )

        Text(
            text = login ?: "",
            modifier = Modifier
                .constrainAs(userLoginText) {
                    width = Dimension.fillToConstraints
                    top.linkTo(userNameText.bottom)
                    start.linkTo(userNameText.start)
                    end.linkTo(userNameText.end)
                }
                .padding(
                    start = 16.dp,
                    bottom = 16.dp
                )
                .wrapContentHeight()
        )

        Text(
            text = bio ?: "",
            modifier = Modifier
                .constrainAs(userBioText) {
                    width = Dimension.fillToConstraints
                    top.linkTo(userImage.bottom)
                    start.linkTo(userImage.start)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = 16.dp
                )
                .wrapContentHeight()
        )

        Image(
            painter = painterResource(id = R.drawable.ic_group),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(userFollowersImage) {
                    top.linkTo(userBioText.bottom)
                    start.linkTo(userBioText.start)
                }
                .padding(
                    top = 8.dp
                )
                .wrapContentHeight()
                .wrapContentWidth(),
            colorFilter = ColorFilter.tint(Color.Black)
        )

        Text(
            text = followers ?: "",
            modifier = Modifier
                .constrainAs(userFollowersText) {
                    width = Dimension.fillToConstraints
                    top.linkTo(userFollowersImage.top)
                    start.linkTo(userFollowersImage.end)
                    bottom.linkTo(userFollowersImage.bottom)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = 8.dp,
                    start = 8.dp
                )
                .wrapContentHeight()
        )

        Image(
            painter = painterResource(id = R.drawable.ic_place),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(userLocationImage) {
                    top.linkTo(userFollowersImage.bottom)
                    start.linkTo(userFollowersImage.start)
                }
                .padding(
                    top = 8.dp
                )
                .wrapContentHeight()
                .wrapContentWidth(),
            colorFilter = ColorFilter.tint(Color.Black)
        )

        Text(
            text = location ?: "",
            modifier = Modifier
                .constrainAs(userLocationText) {
                    width = Dimension.fillToConstraints
                    top.linkTo(userLocationImage.top)
                    start.linkTo(userLocationImage.end)
                    bottom.linkTo(userLocationImage.bottom)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = 8.dp,
                    start = 8.dp
                )
                .wrapContentHeight()
        )
    }
}

@SuppressLint("ResourceType")
@Preview(showBackground = false)
@Composable
fun NavHeaderPreview() {
    NavHeader(
        image = stringResource(id = R.drawable.ic_person),
        name = dummyUser.name,
        login = dummyUser.login,
        bio = dummyUser.bio,
        followers = "${dummyUser.followers} followers",
        location = dummyUser.location
    )
}