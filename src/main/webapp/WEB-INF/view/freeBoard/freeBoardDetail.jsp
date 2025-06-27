<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세 - Jeremiah's Board</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/default.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script>
        const CONTEXT_PATH = "${contextPath}";
    </script>
    <script src="${contextPath}/js/freeBoardDetail.js"></script>
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
            <h1 class="main-title">게시글 상세/수정</h1>

            <form name="modifyForm" onsubmit="return false;">
                <input type="hidden" id="pkNum" name="num" value="${freeBoardDto.num}" />

                <%-- 상세 내용을 테이블로 표시 --%>
                <table class="form-table">
                    <tbody>
                        <tr>
                            <th>타입</th>
                            <td>
                                <select id="selCodeType" name="codeType">
                                    <option value="01" ${freeBoardDto.codeType == '01' ? 'selected' : ''}>자유</option>
                                    <option value="02" ${freeBoardDto.codeType == '02' ? 'selected' : ''}>익명</option>
                                    <option value="03" ${freeBoardDto.codeType == '03' ? 'selected' : ''}>QnA</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>이름</th>
                            <td><input type="text" id="name" name="name" value="${freeBoardDto.name}" readonly/></td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td><input type="text" id="title" name="title"  value="${freeBoardDto.title}"/></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td><textarea name="content" id="content" rows="15">${freeBoardDto.content}</textarea></td>
                        </tr>
                    </tbody>
                </table>

                <%-- 버튼 영역 --%>
                <div class="actions-bar">
                    <input type="button" id="modifyBtn" value="수정" >
                    <input type="button" id="deleteBtn" value="삭제" >
                    <input type="button" class="toListBtn" value="목록" onclick="location.href='${contextPath}/freeBoardMain'">
                </div>
            </form>
        </main>
    </div>

    <jsp:include page="/WEB-INF/view/template/defaultFooter.jsp" />
</div>

</body>
</html>