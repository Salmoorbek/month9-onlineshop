<!DOCTYPE html>
<html>
<head>
    <title>Сброс пароля</title>
</head>
<body>
<h1>Сброс пароля</h1>
<form action="/reset-password" method="post">
    <input type="hidden" name="token" value="${token}">
    <label for="newPassword">Новый пароль:</label>
    <input type="password" id="newPassword" name="newPassword" required>
    <br>
    <input type="submit" value="Сохранить пароль">
</form>
</body>
</html>