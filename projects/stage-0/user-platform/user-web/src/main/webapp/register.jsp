<head>
    <jsp:directive.include
            file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
    <title>My Home Page</title>
</head>
<body>
<div>
    <form action="/login">
        <div><h1>用户登录</h1></div>
        <div>
            <p><input type="text" name="userName" placeholder="请输入用户名"></p>
            <p><input type="password" name="passWord" placeholder="请输入密码"></p>
            <input type="submit" value="登陆"/>
        </div>
    </form>
</div>
</body>
