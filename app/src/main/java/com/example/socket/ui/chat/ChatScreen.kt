package com.example.socket.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.socket.domain.model.UserMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavHostController,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

    LaunchedEffect(state.messages) {
        lazyListState.animateScrollToItem(state.messages?.size ?: 0)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Chat")
                },
                navigationIcon = {
                    IconButton(
                        onClick = navController::popBackStack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier.windowInsetsPadding(WindowInsets.ime)
        ) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .weight(0.86F),
            ) {
                items(
                    items = state.messages.orEmpty()
                ) { userMessage ->
                    MessageContainer(
                        modifier = Modifier,
                        message = userMessage
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.14F)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    modifier = Modifier
                        .padding(end = 12.dp, top = 8.dp, bottom = 8.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Black)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    value = state.chatText,
                    onValueChange = viewModel::onChatMessageChanged,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White
                    ),
                    maxLines = 1,
                    cursorBrush = SolidColor(Color.White),
                    singleLine = true,
                )
                FilledTonalIconButton(
                    onClick = viewModel::sendMessage
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Send,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
private fun MessageContainer(
    modifier: Modifier = Modifier,
    message: UserMessage,
    backgroundColor: Color = if (message.local) Color.Green else Color.Blue,
    textColor: Color = if (message.local) Color.Black else Color.White,
    isRightAligned: Boolean = message.local
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .padding(
                    end = if (isRightAligned) 8.dp else 48.dp,
                    start = if (isRightAligned) 48.dp else 8.dp,
                    bottom = 8.dp
                )
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
                .align(if (isRightAligned) Alignment.End else Alignment.Start),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text(
                    text = message.user.name,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = textColor
                    )
                )
                Text(
                    text = message.message,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = textColor
                    )
                )
            }
        }
    }
}