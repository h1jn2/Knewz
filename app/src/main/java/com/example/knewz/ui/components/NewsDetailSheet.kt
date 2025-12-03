package com.example.knewz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.knewz.ui.theme.StrokeGray
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailSheet(isVisible: Boolean, onDismissRequest: () -> Unit) {
    val imageUrl =
        "https://blog.kakaocdn.net/dna/byIZZL/btsMyrQRWb9/AAAAAAAAAAAAAAAAAAAAAPG3vMG0hs5TXAjhk5fglaLksnKIs1thKShAX_CExXzA/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1764514799&allow_ip=&allow_referer=&signature=vn0p%2Ft5YNxZGCCnHv3bkWZwFyuU%3D"

    if (isVisible) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        val scope = rememberCoroutineScope()
        val closeSheet: () -> Unit = {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) onDismissRequest()
            }
        }

        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxHeight(),
            dragHandle = null,
            scrimColor = Color.Black.copy(alpha = 0.6f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                DetailSheetHeader(imageUrl, closeSheet)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-40).dp)
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(24.dp))
                    Text(
                        text = "AI 스타트업 투자 급증, 글로벌 트렌드 분석",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        NewsMetaRow("", null)
                        ActionIconButton("스크랩", Icons.Outlined.Bookmark)
                    }
                    Spacer(Modifier.height(8.dp))
                    TagsRow()
                    Spacer(Modifier.height(24.dp))
                    AIQuoteBlock("AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약AI 요약")
                    Spacer(Modifier.height(24.dp))
                    Text(
                        text = "올해 인공지능 분야 스타트업 투자가 전년 대비 150% 증가하며 올해 인공지능 분야 스타트업 투자가 전년 대비 150% 증가하며 \n" +
                                "\n" +
                                "올해 인공지능 분야 스타트업 투자가 전년 대비 150% 증가하며 올해 인공지능 분야 스타트업 투자가 전년 대비 150% 증가하며 올해 인공지능 분야 스타트업 투자가 전년 대비 150% 증가하며 \n" +
                                "\n" +
                                "올해 인공지능 분야 스타트업 투자가 전년 대비 150% 증가하며 스타트업 투자가\n" +
                                "\n" +
                                "올해 인공지능 분야 스타트업 투자가 전년 대비 150% 증가하며 ",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(Modifier.height(24.dp))
                    HorizontalDivider(
                        color = StrokeGray
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        ActionIconButton("공유하기", Icons.Outlined.Share, Modifier.weight(1f))
                        Spacer(Modifier.width(8.dp))
                        ActionIconButton("원문보기", Icons.AutoMirrored.Outlined.OpenInNew, Modifier.weight(1f))
                        Spacer(Modifier.width(8.dp))
                        ActionIconButton("링크 복사", Icons.Outlined.Link, Modifier.weight(1f))
                    }
                    Spacer(Modifier.height(16.dp))
                    NewsStatsBar(1234, 12, 45)
                }
            }
        }
    }
}