package com.example.socket.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.socket.domain.model.UserMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavHostController,
    viewModel: ChatViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Chat")
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            items(
                items = listOf<UserMessage>()
            ) {
                MessageContainer(
                    modifier = Modifier,
                    message = it
                )
            }
        }
    }
}

@Composable
private fun MessageContainer(
    modifier: Modifier = Modifier,
    message: UserMessage,
    backgroundColor: Color = if (message.user.local) Color.Green else Color.Blue,
    isRightAligned: Boolean = message.user.local
) {
    Row(
        modifier = modifier
            .padding(
                end = if (isRightAligned) 0.dp else 48.dp,
                start = if (isRightAligned) 48.dp else 0.dp
            )
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
    ) {
        Column {
            Text(
                text = message.user.name,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = message.message,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}