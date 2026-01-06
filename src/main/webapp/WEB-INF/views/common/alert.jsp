<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림</title>
<script>
    // 컨트롤러에서 보낸 메시지가 있으면 띄움
    var msg = "${msg}";
    if(msg) {
        alert(msg);
    }
    
    // 이동할 주소가 있으면 이동, 없으면 이전 페이지로 돌아가기
    var url = "${url}";
    if(url) {
        location.href = url;
    } else {
        history.back();
    }
</script>
</head>
<body>
</body>
</html>