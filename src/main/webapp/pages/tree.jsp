<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Action</title>
    <meta charset="utf-8">
</head>
<body>
    <center>
        <form  method="post">
            <br><b>Enter the number you want to insert/delete</b>
            <br><h7>One value:</h7> <input type="text" size="5" name="one value" value="0"><br>
            <br><b>Enter the number you want to update or before which you want to insert</b>
            <br><h7>Two value:</h7> <input type="text" size="5" name="two value" value="0"><br>
            <br><button type="submit" name="add">add</button>
            <button type="submit" name="add before">add before</button>
            <button type="submit" name="update">update</button>
            <button type="submit" name="delete">delete</button>
            <button type="submit" name="balance">balance</button>
        </form>
    </center>
    <jsp:include page="/servlet"/>
</body>
</html>
