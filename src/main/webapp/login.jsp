<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>POS Api</title>

    <link rel="stylesheet" href="/css/styles.css">
</head>

<body>

<div class="login-page">
    <div class="form">
        <form id="formLogin" class="login-form" method="POST" action="j_security_check">
            <input type="text" placeholder="username" name="j_username"/>
            <input type="password" placeholder="password" name="j_password"/>
            <button type="submit" form="formLogin" value="login">login</button>
        </form>
    </div>
</div>

</body>
</html>

