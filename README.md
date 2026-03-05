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
본 프로젝트는 Clean Architecture 원칙을 참고하여 MVVM 기반으로 계층을 분리했습니다.

1. UI Layer (Presentation)
- Jetpack Compose를 사용하여 선언적으로 UI를 구성했습니다.
- ViewModel은 StateFlow를 통해 UI 상태를 관리합니다.

2. Domain Layer (Business Logic)
- UseCase: 기능별(뉴스 가져오기, 요약하기 등)로 로직을 분리하여 재사용성을 높였습니다.
- Repository Interface: 데이터의 통로를 정의합니다.

3. Data Layer (Data Source)
- Local: Room을 통해 최근 검색어 및 캐시 데이터를 관리합니다.
- Remote: Retrofit을 이용한 뉴스 API 통신을# 📰 Knewz - 키워드로 보는 내 뉴스

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
본 프로젝트는 Clean Architecture 원칙을 참고하여 MVVM 기반으로 계층을 분리했습니다.

1. UI Layer (Presentation)
- Jetpack Compose를 사용하여 선언적으로 UI를 구성했습니다.
- ViewModel은 StateFlow를 통해 UI 상태를 관리합니다.

2. Domain Layer (Business Logic)
- UseCase: 기능별(뉴스 가져오기, 요약하기 등)로 로직을 분리하여 재사용성을 높였습니다.
- Repository Interface: 데이터의 통로를 정의합니다.

3. Data Layer (Data Source)
- Local: Room을 통해 최근 검색어 및 캐시 데이터를 관리합니다.
- Remote: Retrofit을 이용한 뉴스 API 통신을 수행합니다.
- Repository Impl: Domain 계층의 인터페이스를 구현하며, Firebase Authentication을 활용한 사용자 인증 로직을 처리합니다.

--- 

## 📁 폴더 구조

**Feature-based (MVVM + Clean Architecture 기반)**

```bash
📦 com.example.knewz
 ┣ 📂 ai           # Gemini AI 연동 및 프롬프트 최적화 (GeminiHelper)
 ┣ 📂 data         # 데이터 소스 및 상세 구현 (Local, Remote, Repository)
 ┣ 📂 di           # Hilt를 활용한 전역 의존성 주입 설정
 ┣ 📂 domain       # 순수 비즈니스 로직 및 UseCase 정의
 ┣ 📂 navigation   # 앱 내 화면 전환 흐름 및 Route 정의
 ┣ 📂 ui           # Jetpack Compose 기반 UI 구성 요소 및 테마 관리
 ┣ 📂 util         # 전역 유틸리티 클래스 및 공통 확장 함수
 ┣ 📜 KnewzApplication.kt 
 ┗ 📜 MainActivity.kt    
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

## 🛠 기술 스택

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

## 📸 Knewz 화면

|                                                   메인 화면                                                  |                                                 뉴스 상세 화면                                                 |
| :------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------: |
| <img src="https://github.com/user-attachments/assets/2d92103a-3897-452d-b945-e8a157502dd4" width="250"/> | <img src="https://github.com/user-attachments/assets/90bf089c-3e64-4772-81d9-71666c735a06" width="250"/> |

|                                                  스크랩 화면                                                  |                                                  키워드 화면                                                  |
| :------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------: |
| <img src="https://github.com/user-attachments/assets/5425bd4c-68e9-4e50-96c6-0680e0a3328e" width="250"/> | <img src="https://github.com/user-attachments/assets/ad0d749d-f566-435c-95e9-fb605fc7fd73" width="250"/> |


|                                                  로그인 화면                                                  |
| :------------------------------------------------------------------------------------------------------: |
| <img src="https://github.com/user-attachments/assets/22d6fef0-5d81-4670-8f23-53845c38b2c6" width="250"/> |


