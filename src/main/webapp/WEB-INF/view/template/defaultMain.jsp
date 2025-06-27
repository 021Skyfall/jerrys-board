<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

    <title>Jerry's Board</title>
</head>
<body>
    <div id="container">
        <header>
            <jsp:include page="/WEB-INF/view/template/defaultHeader.jsp" />
        </header>

        <nav>
            <jsp:include page="/WEB-INF/view/template/defaultMenu.jsp" />
        </nav>

        <main>
            <h2>Welcome!</h2>
        </main>

        <footer>
            <jsp:include page="/WEB-INF/view/template/defaultFooter.jsp" />
        </footer>
    </div>

</body>
</html>