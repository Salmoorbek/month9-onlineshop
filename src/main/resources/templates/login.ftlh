<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>
<div class="login-page">
    <div class="form">
        <div>
            <#if error!false >
                <p style="color:red">Invalid username or password !</p>
            </#if>
        </div>

        <div>
            <#if password?? >
                <p style="color:black">Your new password: ${password}</p>
            </#if>
        </div>
        <form class="login-form" method="POST" action="/login">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="text" placeholder="email" name="username" value=""/>
            <input type="password" placeholder="password" name="password" value=""/>
            <button>login</button>
            <p class="message">Not registered? <a href="/register">Create an account</a></p>
            <p class="message">Not registered? <a href="/reset-password">reset password</a></p>
        </form>
    </div>
</div>
</body>
</html>