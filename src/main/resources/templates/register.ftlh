<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>
<div class="registration-page">

    <div class="form">
        <form class="register-form" method="POST" action="/signup" >
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div>
                <#if errorMessage?has_content>
                    <p class="error-message" style="color:red">${errorMessage}</p>
                </#if>
                <#if errors?? >
                    <p style="color:red">Incorrect input data !</p>
                    <#list errors as fieldError>
                        <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                    </#list>
                </#if>
            </div>
            <div class="error-message"></div>
            <input type="text" placeholder="name" name="name"  value="${(dto.name)!''}" required/>
            <div class="error-message"></div>
            <input type="text" placeholder="name" name="username"  value="${(dto.username)!''}" required/>
            <div class="error-message"></div>
            <input type="text" placeholder="email address" name="email"  value="${(dto.email)!''}" required/>
            <div class="error-message"></div>
            <input type="password" placeholder="password" name="password"  value="${(dto.password)!''}" required/>
            <button>Register</button>
            <p class="message">Already registered? <a href="/login">Sign In</a></p>
        </form>
    </div>

</div>
</body>
</html>