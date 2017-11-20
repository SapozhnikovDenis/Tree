<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Action</title>
    <meta charset="utf-8">
</head>
<body>
    <center>
        <form  method="post">
            <br><b>Введите число которое вы хотите вставить/удалить</b>
            <br><h7>Первое число:</h7> <input type="text" size="5" name="one value" value="0"><br>
            <br><b>Введите число которое вы хотите изменить, или перед которым хотите вставить</b>
            <br><h7>Второе число:</h7> <input type="text" size="5" name="two value" value="0"><br>
            <br><button type="submit" name="add">Вставить</button>
            <button type="submit" name="add before">Вставить перед</button>
            <button type="submit" name="update">Изменить</button>
            <button type="submit" name="delete">Удалить</button>
            <button type="submit" name="balance">Сбалансировать</button>
        </form>
    </center>
    <jsp:include page="/servlet"/>
</body>
</html>
