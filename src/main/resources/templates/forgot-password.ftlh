<!DOCTYPE html>
<html>
<head>
    <title>Сброс пароля</title>
</head>
<body>
<h1>Сброс пароля</h1>
<form action="/forgot-password" method="post">
    <label for="email">Введите адрес электронной почты:</label>
    <input type="email" id="email" name="email" required>
    <br>
    <input type="submit" value="Отправить письмо для сброса пароля">
</form>
</body>
</html>