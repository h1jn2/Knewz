# 📰 Knewz - 키워드로 보는 내 뉴스

## 기획 의도

사용자가 등록한 관심 키워드를 기반으로 관련 최신 뉴스를 한눈에 모아볼 수 있는 뉴스 큐레이션 앱입니다.

정보가 넘쳐나는 환경 속에서,
사용자가 원하는 주제의 뉴스를 빠르게 탐색하고 효율적으로 소비할 수 있도록 돕는 것을 목표로 합니다.

이 프로젝트는 아르바이트하던 가게 사장님의 실제 요청에서 출발했습니다.
주식 투자에 관심이 많던 사장님께서
“특정 키워드와 관련된 뉴스를 실시간으로 모아보고 싶다”고 하셨고,

엑셀 기반 간단한 크롤링 도구를 제작해드린 경험을 계기로
이를 모바일 환경에서도 사용할 수 있도록 앱으로 확장 개발하게 되었습니다.

Knewz는 단순 뉴스 검색 앱이 아닌,
사용자 맞춤형 뉴스 소비 환경을 제공하는 개인화 뉴스 플랫폼을 지향합니다.

---

## 📁 폴더 구조

**Feature-based (MVVM + Clean Architecture 기반)**

```bash
com.mukmuk.todori
├── 📁 data/                   # 데이터 계층
│   ├── 📁 local.datastore/    # DataStore (로컬 저장소)
│   ├── 📁 remote/             # 원격 데이터 (Firestore 등)
│   ├── 📁 repository/         # Repository
│   └── 📁 service/            # Firebase / API Service
│
├── 📁 di/                     # Hilt 의존성 주입 모듈
├── 📁 navigation/             # 네비게이션 그래프
│
├── 📁 ui/                     # UI 계층
│   ├── 📁 component/          # 공통 UI 컴포넌트
│   ├── 📁 screen/             # 화면 (로그인, 타이머, TODO, 통계 등)
│   └── 📁 theme/              # 테마 (색상, 폰트, 스타일)
│
├── 📁 util/                   # 유틸리티 클래스 & 헬퍼
│
├── 📁 widget/                 # App Widget 관련
│
├── MainActivity.kt
└── TodoriApplication.kt
```

---

## 주요 기능

* 구글 / 이메일 로그인 및 회원 정보 관리
* 최신 뉴스 제공
* 키워드 기반 뉴스 검색
* 관심 키워드 등록 / 삭제
* 뉴스 스크랩
* 뉴스 Gemini AI 요약

---

## 아키텍처

> 본 프로젝트는 MVVM 패턴과 Clean Architecture를 적용하여 계층 간 의존성을 최소화하고 유지보수성을 높였습니다.
> Firebase를 활용한 **서버리스 구조**로 설계되었습니다.
<img width="1133" height="494" alt="image" src="https://github.com/user-attachments/assets/209efe52-cf01-4056-aa12-1cff3ee16f09" />


### 구조 설명

* **UI Layer (Screen, ViewModel)**: 화면 로직과 상태 분리
* **Data Layer (Repository, Service)**: 데이터 흐름 계층화, API 호출 관리
* **Server**:

  * Firebase Firestore – 앱 데이터 저장
  * Firebase Auth – 사용자 인증
  * Firebase Cloud Functions – 통계/레벨/알림 처리

---

## 기술 스택

| 분류         | 사용 기술                     |
| ---------- | ------------------------- |
| **개발 언어**  | Kotlin                    |
| **UI**     | Jetpack Compose           |
| **아키텍처**   | MVVM + Clean Architecture |
| **비동기 처리** | Coroutines, Flow          |
| **DI**     | Hilt                      |
| **로컬 저장소** | Room, DataStore           |
| **인증**     | Firebase Authentication   |
| **네트워크**   | Retrofit                  |
| **협업 도구**  | GitHub, Notion            |

---

## Knewz 화면

|                                                   메인 화면                                                  |                                                 뉴스 상세 화면                                                 |
| :------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------: |
| <img src="https://github.com/user-attachments/assets/2d92103a-3897-452d-b945-e8a157502dd4" width="250"/> | <img src="https://github.com/user-attachments/assets/90bf089c-3e64-4772-81d9-71666c735a06" width="250"/> |

|                                                  스크랩 화면                                                  |                                                  키워드 화면                                                  |
| :------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------: |
| <img src="https://github.com/user-attachments/assets/5425bd4c-68e9-4e50-96c6-0680e0a3328e" width="250"/> | <img src="https://github.com/user-attachments/assets/ad0d749d-f566-435c-95e9-fb605fc7fd73" width="250"/> |


|                                                  로그인 화면                                                  |
| :------------------------------------------------------------------------------------------------------: |
| <img src="https://github.com/user-attachments/assets/22d6fef0-5d81-4670-8f23-53845c38b2c6" width="250"/> |


