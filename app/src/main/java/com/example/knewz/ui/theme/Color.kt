package com.example.knewz.ui.theme

import androidx.compose.ui.graphics.Color

// ====================================================================
// 1. 주 색상 (Primary & Accent)
// ====================================================================

// 메인 강조 및 액션 색상 (녹색)
val PrimaryGreen = Color(0xFF22B282)

// 그래프, 링크, 강조 색상
val AccentBlue = Color(0xFF4285F4)
val AccentOrange = Color(0xFFFFC107)
val AccentPurple = Color(0xFF9C27B0)


// ====================================================================
// 2. 주 색상 투명도 변형 (Opacity Variants)
// ====================================================================

// PrimaryGreen의 약 17% 불투명도 (Alpha 43)
val PrimaryGreenHalf = Color(0x2B22B282)
// PrimaryGreen의 약 10% 불투명도 (Alpha 26)
val PrimaryGreenTenth = Color(0x1A22B282)
// 중간 회색의 아주 낮은 불투명도 (Alpha 26)
val TextMediumGray10 = Color(0x1A666666)


// ====================================================================
// 3. 기본/배경 색상 (Base & Background)
// ====================================================================

// 대부분의 화면 기본 배경색
val BackgroundWhite = Color(0xFFFFFFFF)
// 카드, 목록 항목 배경색 (아주 밝은 회색)
val SurfaceLightGray = Color(0xFFF7F7F7)
// 구분선, 입력창 테두리 색상 (밝은 회색)
val DividerGray = Color(0xFFEBEBEB)
// 입력창 플레이스홀더 또는 비활성 요소 색상
val PlaceholderGray = Color(0xFFDCDCDC)
// 영역 강조에 사용될 수 있는 중간 밝기의 회색
val OverlayGray = Color(0xFFC7C7C7)


// ====================================================================
// 4. 텍스트 및 아이콘 색상 (Text & Icon)
// ====================================================================

// 주된 제목, 본문 텍스트 (가장 어두운 색)
val TextBlack = Color(0xFF1E1E1E)
// 부제목이나 중요한 정보 텍스트
val TextDarkGray = Color(0xFF333333)
// 보조 텍스트, 설명 텍스트, 아이콘 기본 색상
val TextMediumGray = Color(0xFF666666)
// 아주 보조적인 텍스트, 예: 시간 표시
val TextLightGray = Color(0xFF999999)