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
        <form class="login-form" method="POST" action="user/password/recover">
            <div>
                <#if errors?? >
                    <p style="color:red">Incorrect input data !</p>
                    <#list errors as fieldError>
                        <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                    </#list>
                </#if>
                <#if customerError??>
                    <p style="color:red">${customerError}</p>
                </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="text" placeholder="email address" name="email"  value="${(dto.email)!''}" required/>
            <button type="">Recover</button>
            <p class="message">Not registered? <a href="/signup">Create an account</a></p>
            <p class="message"><a href="/">Go back to the home page</a></p>
        </form>
    </div>
</div>
</body>
</html>