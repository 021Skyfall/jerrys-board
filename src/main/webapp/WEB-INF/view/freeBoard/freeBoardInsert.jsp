<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>새 글 작성 - Jeremiah's Board</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/default.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script>
        const CONTEXT_PATH = "${contextPath}";
    </script>
    <script src="${contextPath}/js/freeBoardInsert.js"></script>
</head>
<body>

<div class="container">
    <%-- 템플릿 조각 --%>
    <jsp:include page="/WEB-INF/view/template/defaultHeader.jsp" />

    <div class="board-layout">
        <%-- 사이드 메뉴 --%>
        <aside class="side-menu">
            <jsp:include page="/WEB-INF/view/template/defaultMenu.jsp" />
        </aside>

        <%-- 메인 콘텐츠 --%>
        <main class="main-content">
            <h1 class="main-title">새 글 작성</h1>

            <form id="fm" onsubmit="return false;">
                <table class="form-table">
                    <tbody>
                        <tr>
                            <%-- 라벨은 th로 변경하여 스타일 자동 적용 --%>
                            <th>타입</th>
                            <td>
                                <select name="codeType">
                                    <option value="01">자유</option>
                                    <option value="02">익명</option>
                                    <option value="03">QnA</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>이름</th>
                            <td><input type="text" id="name" name="name" placeholder="이름을 입력하세요."/></td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td><input type="text" id="title" name="title" placeholder="제목을 입력하세요."/></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td><textarea id="content" name="content" rows="15" placeholder="내용을 입력하세요."></textarea></td>
                        </tr>
                    </tbody>
                </table>

                <%-- 버튼 영역 --%>
                <div class="actions-bar">
                    <input type="button" id="submitBtn" value="작성완료">
                    <input type="button" value="다시쓰기" onclick="document.getElementById('fm').reset();">
                    <input type="button" class="toListBtn" value="목록" onclick="location.href='${contextPath}/freeBoardMain'">
                </div>
            </form>
        </main>
    </div>

    <jsp:include page="/WEB-INF/view/template/defaultFooter.jsp" />

</div>
</body>
</html>