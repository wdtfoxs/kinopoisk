<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="/resources/js/movie.js"></script>
    <script type="text/javascript" src="/resources/js/jquery-1.11.1.min.js"></script>
</head>
<body >
    <form action="/movie" method="post">
        <input type="text" id="name" name="name"><br>
        <textarea id="descr" name="descr"></textarea><br>
        <button type="submit" onclick="add()">add</button>
    </form>
</body>
</html>