# ✍️ Jerry's Board (웹 게시판 프로젝트)

<div align="center">

### 프로젝트 기간 : 2025.04.13 ~ 2025.06.21

</div>

<br>

## ✔ 목차
- [📖 프로젝트 소개](#-프로젝트-소개)
- [🔧 기술 스택](#-기술-스택)
- [👨‍💻 주요 구현 내용 및 트러블 슈팅](#-주요-구현-내용-및-트러블-슈팅)
- [🖥️ 동작 데모](#️-동작-데모)
- [🎉 프로젝트 후기](#-프로젝트-후기)

<br>
<br>

## 📖 프로젝트 소개
이 프로젝트는 웹 개발의 근간이 되는 **Spring Framework, JSP, MyBatis, Oracle** 기술 스택을 깊이 있게 학습하고, 이를 적용하여 웹 애플리케이션의 핵심 기능을 직접 구현하는 경험을 쌓기 위해 진행되었습니다.

단순한 기능 완성을 넘어, 표준 Maven 프로젝트 구조를 설정하고, 서버-클라이언트 간 비동기 통신(AJAX)을 구현하는 등 **개발 환경을 구축하고 문제를 해결하는 역량**을 기르는 것을 목표로 했습니다.

<br>
<br>

## 🔧 기술 스택
| Category      | Technology                               |
| :------------ | :--------------------------------------- |
| **Backend** | `Java 11`, `Spring Framework 5.3.x`      |
| **Database** | `Oracle Database 21c XE`                 |
| **Persistence**| `MyBatis`                     |
| **View** | `JSP`, `JSTL`                            |
| **Frontend** | `HTML5`, `CSS3`, `JavaScript`, `jQuery` |
| **Build Tool**| `Maven`                                  |
| **Server** | `Apache Tomcat 9.x (Maven Cargo Plugin)` |
| **VCS** | `Git`, `GitHub`                          |
| **IDE** | `IntelliJ IDEA Community Edition`        |

<br>
<br>

## 👨‍💻 주요 구현 내용 및 트러블 슈팅

<details>
<summary><strong>A. Spring MVC 기반 백엔드 시스템 구축</strong></summary>

- **⚙️ 작업 내용**
    - Spring Framework, MyBatis, Oracle DB를 이용한 게시판 CRUD API 설계 및 구현
    - 페이징 처리를 위한 데이터 조회 및 계산 로직 구현
    - MyBatis의 동적 쿼리(Dynamic SQL)를 활용한 다중 조건 검색 기능 구현

- **🔍 방법 및 트러블 슈팅**
    - ▶ **프로젝트 환경 설정**: IntelliJ와 Maven을 사용하여 프로젝트를 시작하며, 표준 Maven 디렉토리 구조(`src/main/java`, `resources`, `webapp`)의 중요성을 학습했습니다. 처음에는 `resources` 폴더의 경로 문제로 빌드 시 XML 설정 파일이 누락되는 **`MyBatis Mapped Statement Not Found`** 에러를 겪었지만, 빌드 로그 분석을 통해 원인을 찾아내고 구조를 바로잡아 해결했습니다.
    - ▶ **DB 연동 및 설정 관리**: MyBatis를 Spring과 연동하여 SQL 매퍼를 구현했으며, DB 접속 정보와 같은 민감 정보는 `.properties` 파일로 분리하고 **`.gitignore`** 에 등록하여 GitHub에 노출되지 않도록 처리했습니다.
    - ▶ **페이징 로직 구현**: 사용자가 요청한 페이지 번호(`curPage`)를 바탕으로, 총 게시물 수를 조회하고 `LIMIT`과 `OFFSET`(Oracle의 경우 `ROWNUM`)을 계산하여 해당 페이지의 데이터만 효율적으로 조회하는 로직을 서비스 계층에 구현했습니다.
    - ▶ **MyBatis 동적 쿼리 활용**: 사용자가 선택한 검색 조건(타입, 제목, 작성자, 기간 등)에 따라 SQL 문이 동적으로 변하는 기능을 구현하기 위해, MyBatis의 `<if>`, `<choose>`, `<when>` 태그를 적극적으로 활용했습니다. 이를 통해 여러 개의 유사한 SQL 문을 만들 필요 없이, 하나의 쿼리만으로 복잡한 다중 조건 검색을 효율적으로 처리할 수 있었습니다.
    - ▶ **동적 쿼리 디버깅**: 다양한 조건이 조합될 때 발생하는 SQL 문법 오류를 잡기 위해, `log4j.xml`에 **MyBatis의 쿼리 로그를 출력하는 설정**을 추가했습니다. 이를 통해 실제 실행되는 SQL 문과 파라미터를 눈으로 직접 확인하며, 복잡한 동적 쿼리의 오류를 효과적으로 디버깅하고 해결할 수 있었습니다.

</details>

<details>
<summary><strong>B. jQuery와 AJAX를 활용한 동적 프론트엔드 구현</strong></summary>

- **⚙️ 작업 내용**
    - 검색, 페이징, 수정/삭제 등 서버와의 통신이 필요한 대부분의 기능을 AJAX로 구현하여 페이지 전체 새로고침 없는 사용자 경험(SPA-like) 제공
    - 순수 CSS와 약간의 JavaScript를 이용한 UI/UX 개선

- **🔍 방법 및 트러블 슈팅**
    - ▶ **AJAX 비동기 통신**: 사용자가 검색 버튼이나 페이지 번호를 클릭하면, JavaScript가 해당 이벤트 정보를 모아 JSON 형태로 서버(`@Controller`)에 전송합니다. 서버는 받은 데이터를 처리하여 **JSON 형태의 결과(게시물 목록, 페이징 정보)를 다시 반환**하고, JavaScript는 이 데이터를 받아 **DOM을 동적으로 조작하여 화면을 다시 그리는 방식**으로 구현했습니다.
    - ▶ **화면 렌더링 문제**: 처음에는 JSP의 `c:forEach`로 목록을 그린 뒤, 페이지 로딩 후 즉시 AJAX로 데이터를 다시 불러와 덮어쓰는 비효율적인 이중 로딩 문제가 있었습니다. 이를 **페이지 최초 로딩 시에는 서버(JSP)가, 그 이후의 모든 동작(검색, 페이징)은 클라이언트(JavaScript)가 화면을 그리도록 역할을 명확히 분리**하여 해결했습니다.
    - ▶ **CSS 스타일 충돌**: 여러 페이지에 공통으로 적용한 `table` 스타일이 의도치 않게 다른 페이지의 레이아웃을 망가뜨리는 문제를 겪었습니다. 이를 해결하기 위해 각 테이블에 **고유한 `class`를 부여**하고(`board-list`, `form-table`), 클래스 기반으로 스타일을 분리하여 **CSS 규칙의 영향 범위를 명확히 하는 방법**을 학습했습니다.

</details>

<br>
<br>

## 🖥️ 동작 데모

-   각 기능의 동작 모습을 GIF로 녹화하여 시연하였습니다.

|            **게시글 생성 (Create)**             |
|:------------------------------------------:|
| *새로운 게시글을 작성하고, 작성 완료 후 해당 상세 페이지로 이동합니다.* |
|          ![게시글 생성](./docs/C.gif)           |

|         **게시글 조회 및 수정 (Read & Update)**          |
|:------------------------------------------------:|
| *게시글 제목을 클릭하여 상세 내용을 조회하고, 제목과 내용을 수정한 뒤 저장합니다.* |
|             ![게시글 수정](./docs/U.gif)              |

|                 **삭제와 다중 선택 삭제 (Bulk Delete)**                 |
|:--------------------------------------------------------------:|
| *상세 페이지에서 삭제 혹은 체크박스로 여러 게시글을 선택한 뒤, '선택 삭제' 버튼으로 한 번에 삭제합니다.* |
|                      ![삭제](./docs/D.gif)                       |

|                      **검색 (Search)**                      |
|:---------------------------------------------------------:|
|  *다양한 조건(제목, 기간 등)으로 게시글을 검색합니다.* |
|                 ![검색](./docs/search.gif)                  |

|           **페이징 (Paging)**            |
|:-----------------------------------------------:|
| *페이지 번호를 이동하며 목록을 조회합니다.* |
|          ![페이징](./docs/pagination.gif)          |

<br>
<br>

## 🎉 프로젝트 후기

이 프로젝트는 Spring Framework, JSP, MyBatis 등 안정적이고 검증된 웹 기술 스택에 대한 깊은 이해를 목표로 시작되었습니다. 단순히 이론을 학습하는 것을 넘어, 직접 게시판의 모든 기능을 처음부터 구현하며 각 기술이 어떻게 유기적으로 동작하는지 체감하고자 했습니다.

개발 과정에서 가장 크게 배운 것은 '보이지 않는 기본'의 중요성이었습니다. 표준 Maven 프로젝트 구조를 잡고, `pom.xml`의 의존성을 관리하며, Spring의 설정 파일들을 올바르게 구성하는 과정에서 수많은 에러를 마주했습니다. 이 문제들을 해결하며, 튼튼한 토대 없이는 기능 구현이 의미가 없다는 것을 깨달았습니다. 특히, MyBatis의 동적 쿼리를 활용해 복잡한 검색 기능을 구현했던 경험은 데이터베이스와 애플리케이션이 어떻게 상호작용하는지에 대한 이해를 높여주었습니다.

프론트엔드 단에서는 AJAX를 이용한 비동기 통신을 구현하며 사용자 경험을 향상시키는 데 집중했습니다. 처음에는 하나의 CSS 파일 안에서 모든 것을 해결하려다 보니 스타일 충돌로 어려움을 겪었지만, 이 문제를 해결하기 위해 각 컴포넌트에 맞는 `class`를 부여하고 역할을 분리하면서 CSS의 동작 원리와 체계적인 관리의 필요성을 배울 수 있었습니다.

결과적으로 이 프로젝트는 저에게 '에러는 피하는 것이 아니라, 해결하는 과정에서 진짜 실력이 쌓인다'는 것을 가르쳐준 값진 경험이 되었습니다. 여기서 다진 기본기를 바탕으로, 다음 프로젝트에서는 더욱 복잡하고 완성도 높은 애플리케이션을 만들어나갈 자신이 생겼습니다.
