<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Jeremiah's Board</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/default.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script>
        const CONTEXT_PATH = "${contextPath}";
    </script>
    <script src="${contextPath}/js/freeBoardMain.js"></script>
</head>

<body>

<div class="container">
    <jsp:include page="/WEB-INF/view/template/defaultHeader.jsp" />

    <div class="board-layout">

        <%-- 왼쪽 사이드 메뉴 --%>
        <aside class="side-menu">
            <jsp:include page="/WEB-INF/view/template/defaultMenu.jsp" />
        </aside>

        <%-- 오른쪽 메인 콘텐츠 --%>
        <main class="main-content">

            <h1 class="main-title">자유게시판</h1>

            <div class="actions-bar">
               <a href="${contextPath}/freeBoardInsert"><button type="button">글쓰기</button></a>
            </div>

            <%-- 게시판 목록 테이블 --%>
            <table>
                <thead>
                    <tr>
                        <th><input type="checkbox" id="chkAll"></th>
                        <th>타입</th>
                        <th>글번호</th>
                        <th>글제목</th>
                        <th>글쓴이</th>
                        <th>작성일시</th>
                    </tr>
                </thead>
                <tbody id="tb">
                    <c:forEach var="dto" items="${response.list}">
                        <tr>
                            <td><input type="checkbox" class="chk" value="${dto.num}"></td>
                            <td>${dto.codeType}</td>
                            <td>${dto.num}</td>
                            <td><a href="${contextPath}/freeBoardDetail?num=${dto.num}">${dto.title}</a></td>
                            <td>${dto.name}</td>
                            <td>${dto.regdate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <%-- 테이블 하단 영역: 선택삭제, 검색창 --%>
            <div class="table-footer">
                <div class="placeholder"></div>

                <div class="search-area">
                   <select id="searchOptions">
                      <option value="0">전체</option>
                      <option value="1">타입</option>
                      <option value="2">번호</option>
                      <option value="3">이름</option>
                      <option value="4">제목</option>
                      <option value="5">내용</option>
                      <option value="6">기간</option>
                   </select>
                   <div class="search-inputs">
                      <select id='o1' style="display:none">
                         <option value='01'>자유</option>
                         <option value='02'>익명</option>
                         <option value='03'>QnA</option>
                      </select>
                      <input type='text' id='o2' placeholder="글번호" style="display:none">
                      <input type='text' id='o3' placeholder="글쓴이" style="display:none">
                      <input type='text' id='o4' placeholder="글제목" style="display:none">
                      <input type='text' id='o5' placeholder="글내용" style="display:none">
                      <input type='text' id='o6a' placeholder="시작일" style="display:none">
                      <input type='text' id='o6b' placeholder="종료일" style="display:none">
                      <button id="searchBtn">검색</button>
                   </div>
                </div>

                <div class="delete-action">
                   <input type="button" id="deleteAllBtn" value="선택 삭제">
                </div>
            </div>

            <%-- 페이징 영역 --%>
            <div id="pagination-area">
                <c:if test="${response.paging.totalPageCount > 1}">
                    <c:if test="${response.paging.curPage > 1}">
                        <a href="javascript:searchAndRender(1);">&laquo;</a>
                        <a href="javascript:searchAndRender(${response.paging.firstPage - 1});">&lt;</a>
                    </c:if>

                    <c:forEach var="i" begin="${response.paging.firstPage}" end="${response.paging.lastPage}">
                        <c:choose>
                            <c:when test="${i eq response.paging.curPage}">
                                <a href="#" class="active"><strong>${i}</strong></a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:searchAndRender(${i});">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${response.paging.curPage < response.paging.totalPageCount}">
                        <a href="javascript:searchAndRender(${response.paging.lastPage + 1});">&gt;</a>
                        <a href="javascript:searchAndRender(${response.paging.totalPageCount});">&raquo;</a>
                    </c:if>
                </c:if>
            </div>
        </main>
    </div>

    <jsp:include page="/WEB-INF/view/template/defaultFooter.jsp" />
</div>

</body>
</html>