# 📰 Knewz - 키워드로 보는 내 뉴스

## 💡 기획 의도

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

## 🏗 Architecture & Design Pattern
본 프로젝트는 Clean Architecture와 MVVM 패턴을 결합하여, 각 계층 간의 결합도를 낮추고 유지보수성과 테스트 용이성을 극대화했습니다.

<img width="1408" height="424" alt="Gemini_Generated_Image_4eef5e4eef5e4eef" src="https://github.com/user-attachments/assets/1cd8f7fe-e374-47a5-a183-8993cb69f625" />

1. UI Layer (Presentation)
- Jetpack Compose를 사용하여 선언적으로 UI를 구성했습니다.
- ViewModel은 StateFlow를 통해 UI 상태를 관리하며, 단방향 데이터 흐름(UDF)을 유지합니다.

2. Domain Layer (Business Logic)
- 특정 프레임워크에 의존하지 않는 Pure Kotlin 모듈입니다.
- UseCase: 기능별(뉴스 가져오기, 요약하기 등)로 로직을 분리하여 재사용성을 높였습니다.
- Entity/Repository Interface: 데이터의 형태와 통로를 정의합니다.

3. Data Layer (Data Source)
- Local: Room을 통해 최근 검색어 및 캐시 데이터를 관리합니다.
- Remote: Retrofit을 이용한 뉴스 API 통신 및 **Firebase(Auth/Realtime DB)**를 통한 사용자 인증과 스크랩 데이터를 관리합니다.
- Repository Impl: Domain 계층의 인터페이스를 구현하여 데이터 소스를 캡슐화합니다.

--- 

## 📁 폴더 구조

**Feature-based (MVVM + Clean Architecture 기반)**

```bash
📦 com.example.knewz
 ┣ 📂 ai             # Gemini AI 연동 및 프롬프트 관리 (GeminiHelper)
 ┣ 📂 data           # 데이터 소스 및 상세 구현 계층
 ┃ ┣ 📂 local        # Room Database, DAO, Entity 관리
 ┃ ┣ 📂 model        # Data Transfer Object (News.kt)
 ┃ ┣ 📂 remote       # Retrofit API Service & Response (NewsApiService)
 ┃ ┣ 📂 repository   # 데이터 로직 구현체 (Repository Impl)
 ┃ ┗ 📂 di           # Hilt를 이용한 의존성 주입 모듈 (Network, Database)
 ┣ 📂 domain         # 순수 비즈니스 로직 계층
 ┃ ┗ 📂 usecase      # 기능 단위 UseCase (GetNews, SummarizeNews 등)
 ┣ 📂 ui             # Jetpack Compose 기반 UI 계층
 ┃ ┣ 📂 components   # 재사용 가능한 공통 UI 컴포넌트
 ┃ ┣ 📂 navigation   # AppNavigation, BottomNavItem 등 화면 전환 관리
 ┃ ┣ 📂 theme        # 앱 디자인 시스템 (Color, Typography, Theme)
 ┃ ┗ 📂 [Features]   # home, keyword, scrap, search 등 기능별 Screen & ViewModel
 ┗ 📂 util           # Application 클래스 및 전역 유틸리티
```

---

## 🌟 주요 기능

1. 사용자 맞춤형 뉴스 큐레이션
- 관심 키워드 등록/삭제를 통한 개인화 뉴스 피드 구성.
- Firebase를 통한 기기 간 데이터 동기화.

2. Gemini AI 뉴스 요약
- 방대한 뉴스 본문을 Gemini API를 통해 핵심 3문장으로 요약하여 정보 소비 효율 증대.

3. 소셜 로그인
- Firebase Auth를 통한 구글 및 이메일 간편 로그인.

---

## 기술 스택

| 분류         | 사용 기술                     |
| ---------- | ------------------------- |
| **개발 언어**  | Kotlin                    |
| **UI**     | Jetpack Compose           |
| **아키텍처**   | MVVM + Clean Architecture |
| **비동기 처리** | Coroutines, Flow          |
| **DI**     | Hilt                      |
| **로컬 저장소** | Room           |
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


