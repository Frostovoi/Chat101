package com.example.conversation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.conversation.components.ChatAppBar
import com.example.core.api.dto.Message
import com.example.ui.theme.ChatTheme
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationContent(
    uiState: ConversationUiState,
    navigateToProfile: (String) -> Unit,
    modifier: Modifier = Modifier,
    onNavIconPressed: () -> Unit
) {
    val authorMe = "me"

    val scrollState = rememberLazyListState()
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ChannelNameBar(
                channelName = uiState.channelName,
                onNavIconPressed = onNavIconPressed,
                scrollBehaviour = scrollBehavior
            )
        },
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.ime),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    )
    { paddingValues ->
        Column(
            Modifier
                .background(color = MaterialTheme.colorScheme.inversePrimary)
                .fillMaxSize()
                .padding(paddingValues)) {
            Messages(
                messages = uiState.messages,
                navigateToProfile = navigateToProfile,
                scrollState = scrollState,
                modifier = Modifier.weight(1f)
            )
            UserInput(
                onMessageSent = { content ->
                    val currentTime = Date()
                    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
                    val formattedTime = formatter.format(currentTime)
                    uiState.addMessage(
                        Message(authorMe, content, formattedTime)
                    )
                },
                resetScroll = {
                    scope.launch {
                        scrollState.scrollToItem(0)
                    }
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelNameBar(
    channelName: String,
    modifier: Modifier = Modifier,
    scrollBehaviour: TopAppBarScrollBehavior? = null,
    onNavIconPressed: () -> Unit = {}
) {
    ChatAppBar(
        modifier = modifier,
        scrollBehaviour = scrollBehaviour,
        onNavIconPressed = onNavIconPressed,
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Channel name
                Text(
                    text = channelName,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        actions = {
            // Search icon
            Icon(
                imageVector = Icons.Outlined.Search,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clickable(onClick = {})
                    .padding(horizontal = 12.dp, vertical = 16.dp)
                    .height(24.dp),
                contentDescription = stringResource(id = com.example.ui.R.string.search_button)
            )
            // Channel Info
            Image(
                painter = painterResource(id = com.example.ui.R.drawable.icon_101_image),
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable(onClick = {})
                    .padding(horizontal = 12.dp, vertical = 16.dp)
                    .size(50.dp),
                contentDescription = stringResource(id = com.example.ui.R.string.channel_info_image_button)
            )
        }
    )
}


@Composable
fun Messages(
    messages: List<Message>,
    navigateToProfile: (String) -> Unit,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    Box(modifier = modifier) {
        val authorMe = "me"
        LazyColumn(
            reverseLayout = true,
            state = scrollState,
            modifier = Modifier.fillMaxSize()
            ) {
            for (index in messages.indices) {
                val prevAuthor = messages.getOrNull(index - 1)?.username
                val nextAuthor = messages.getOrNull(index + 1)?.username
                val content = messages[index]
                val isFirstMessageByAuthor = prevAuthor != content.username
                val isLastMessageByAuthor = nextAuthor != content.username

                item {
                    Message(
                        onAuthorClick = { name -> navigateToProfile(name)},
                        msg = content,
                        isUserMe = content.username == authorMe,
                        isFirstMessageByAuthor = isFirstMessageByAuthor,
                        isLastMessageByAuthor = isLastMessageByAuthor
                    )
                }
            }
        }

    }
}


@Composable
fun Message(
    onAuthorClick: (String) -> Unit,
    msg: Message,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean
) {
    val borderColor = if (isUserMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.tertiary
    }

    val spaceBetweenAuthors = if (isLastMessageByAuthor) Modifier.padding(top = 8.dp) else Modifier

    Row(modifier = spaceBetweenAuthors) {
        if (isLastMessageByAuthor && !isUserMe) {
            Image(
                modifier = Modifier
                    .clickable(onClick = { onAuthorClick(msg.username) })
                    .padding(horizontal = 16.dp)
                    .size(42.dp)
                    .border(1.5.dp, borderColor, CircleShape)
                    .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
                    .clip(CircleShape)
                    .align(Alignment.Bottom),
                painter = painterResource(
                    id = msg.authorImage ?: com.example.ui.R.drawable.icon_101_image
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        } else if (!isLastMessageByAuthor) {
            Spacer(modifier = Modifier.width(74.dp))
        }

        AuthorAndTextMessage(
            msg = msg,
            isUserMe = isUserMe,
            isFirstMessageByAuthor = isFirstMessageByAuthor,
            isLastMessageByAuthor = isLastMessageByAuthor,
            authorClicked = onAuthorClick,
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)
        )
    }
}


@Composable
fun AuthorAndTextMessage(
    msg: Message,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean,
    authorClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = if (isUserMe) Alignment.End else Alignment.Start
    ) {
        ChatItemBubble(msg, isUserMe, isLastMessageByAuthor, authorClicked)
        if (isFirstMessageByAuthor) {
            Spacer(modifier = Modifier.height(8.dp))
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}


@Composable
fun ChatItemBubble(
    msg: Message,
    isUserMe: Boolean,
    isLastMessageByAuthor: Boolean,
    authorClicked: (String) -> Unit
) {

    val backgroundBubbleColor = if (isUserMe) {
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Column {
        val dpCornerRadius = 20.dp
        val chatBubbleShape = RoundedCornerShape(dpCornerRadius)

        Surface(
            color = backgroundBubbleColor,
            shape = chatBubbleShape,
            modifier = Modifier.drawBehind {
                val senderPointerPath = Path().apply {
                    val cornerRadius = dpCornerRadius.toPx()/2
                    if (isUserMe && isLastMessageByAuthor) {
                        arcTo(
                            Rect(
                                size.width,
                                size.height - 4f * cornerRadius,
                                size.width + 2f * cornerRadius,
                                size.height
                            ),
                            180f,
                            -90f,
                            true
                        )
                        arcTo(
                            Rect(
                                size.width - 0.5f * cornerRadius,
                                size.height - 2f * cornerRadius,
                                size.width + 2.5f * cornerRadius,
                                size.height
                            ),
                            90f,
                            90f,
                            true
                        )
                        lineTo(size.width, size.height - 2f * cornerRadius)
                    } else if (isLastMessageByAuthor){
                        arcTo(
                            Rect(
                                -2f * cornerRadius,
                                size.height - 4f * cornerRadius,
                                0f,
                                size.height
                            ),
                            0f,
                            90f,
                            true
                        )
                        arcTo(
                            Rect(
                                -2.5f * cornerRadius,
                                size.height - 2f * cornerRadius,
                                0.5f * cornerRadius,
                                size.height
                            ),
                            90f,
                            -90f,
                            true
                        )
                        lineTo(0f, size.height - 2f * cornerRadius)
                    }
                }
                drawPath(
                    path = senderPointerPath,
                    color = backgroundBubbleColor
                )
            }
        ) {
            ClickableMessage(
                msg = msg,
                isUserMe = isUserMe,
                isLastMessageByAuthor = isLastMessageByAuthor,
                authorClicked = authorClicked
            )
        }

        msg.image?.let {
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                color = backgroundBubbleColor,
                shape = chatBubbleShape
            ) {
                Image(
                    painter = painterResource(id = it),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(160.dp),
                    contentDescription = stringResource(id = com.example.ui.R.string.attached_image)
                )
            }
        }

    }
}

@Composable
fun ClickableMessage(
    msg: Message,
    isUserMe: Boolean,
    isLastMessageByAuthor: Boolean,
    authorClicked: (String) -> Unit
) {
    val uriHandler = LocalUriHandler.current

    val styledMessage = MessageFormatter(
        text = msg.text,
        primary = isUserMe
    )

    if (isLastMessageByAuthor && !isUserMe) {
        Column {
            Text(
                modifier = Modifier.padding(top = 4.dp, start = 16.dp),
                text = msg.username,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
            )
            ClickableTextMessage(
                styledMessage = styledMessage,
                uriHandler = uriHandler ,
                authorClicked = authorClicked,
                modifier = Modifier.padding(16.dp, 4.dp, 16.dp, 16.dp)
            )
        }
    } else {
        ClickableTextMessage(
            styledMessage = styledMessage,
            uriHandler = uriHandler ,
            authorClicked = authorClicked,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ClickableTextMessage(
    styledMessage: AnnotatedString,
    uriHandler: UriHandler,
    authorClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ClickableText(
        text = styledMessage,
        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
        modifier = modifier,
        onClick = {
            styledMessage
                .getStringAnnotations(start = it, end = it)
                .firstOrNull()
                ?.let { annotation ->
                    when (annotation.tag) {
                        SymbolAnnotationType.LINK.name -> uriHandler.openUri(annotation.item)
                        SymbolAnnotationType.PERSON.name -> authorClicked(annotation.item)
                        else -> Unit
                    }
                }
        })
}


private val initialMessages = listOf(
    Message(
        username = "Alex",
        text = "Test text 2",
        timestamp = "08:51"
    ),
    Message(
        username = "me",
        text = "Test text 1",
        timestamp = "08:50",
    ),
    Message(
        username = "Victor",
        text = "Лувр в Париже закрылся сегодня днем по соображением безопасности после поступившего в музей сообщения об угрозе взрыва, передает телеканал BFMTV. Сам музей в соцсети X (бывший твиттер) сообщил, что закроется на весь день. Сейчас полиция проводит проверку.\n" +
                "\n" +
                "Позже эвакуацию объявили и в Версальском дворце — людей эвакуировали из дворца и парка также из-за сообщений об угрозе взрыва. \n" +
                "\n" ,
        timestamp = "08:52",
    ),
    Message(
        username = "Victor",
        text = "Test text 4",
        timestamp = "08:53",
    )
)

@Preview
@Composable
fun ConversationContentPreview() {
    ChatTheme {
        ConversationContent(
            uiState = ConversationUiState(
                channelName = "TestChannelName",
                channelMembers = 2,
                initialMessages = initialMessages
            ),
            navigateToProfile ={} ) {

        }
    }
}

