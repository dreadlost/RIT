<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>Тестовая страница</title>
</head>
<body>
<pre id="a">${name}</pre>
<script>
    var pre = document.getElementById('a');
    var json = JSON.parse(pre.innerText)
    pre.innerHTML = JSON.stringify(json, undefined, 2);</script>
</body>
</html>
