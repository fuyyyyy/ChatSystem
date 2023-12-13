<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.setAttribute("path", basePath);
%>
<!DOCTYPE html>
<html lang = "en">
<head>
    <meta charset = "UTF-8">
    <title>登录</title>
    <style>
        body
        {
            background: url('${path}login_background.jpg') no-repeat fixed;
            background-size: 100% 100%;
        }

        #t1 {
            font-family: 微软雅黑, 宋体, Arial, Helvetica, Verdana, sans-serif;
            font-size: 60px;
            font-weight: bolder;
            color: beige;
        }

        .aCenter {
            text-align: center;
        }

        #div1 {
            margin-left: 560px;
            width: 300px;
            height: 200px;
        }

        #form1 {
            background-color: rgba(255, 255, 255, 0.3);
            width: 400px;
            height: 440px;
            border-radius: 30px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            backdrop-filter: blur(3px);
            border-left: 2px solid rgba(255, 255, 255, .3);
            border-top: 2px solid rgba(255, 255, 255, .3);
            box-shadow: 2px 2px 10px rgba(0, 0, 0, .2);
            text-align: center;

            font-family: 微软雅黑, 宋体, Arial, Helvetica, Verdana, sans-serif;
            font-size: 25px;
            color: beige;
        }

        .iText1 {
            width: 250px;
            height: 30px;
            border: 1px solid #ccc;
            border-radius: 4px;

            font-size: 18px;
        }

        input[type = submit] {
            width: 200px;
            height: 45px;
            background-color: rgba(132,65,60,0.95);
            border: none;
            border-radius: 4px;

            font-family: 微软雅黑, 宋体, Arial, Helvetica, Verdana, sans-serif;
            font-size: 25px;
            color: white;
        }
        input[type = submit]:hover {
            background-color: #853230;
        }

        a {
            color: beige;
        }

        p {
            font-size: 18px;
            text-align: center;
        }

    </style>
</head>
<body>
<div id = "div1">
    <form id = "form1" action = "/ChatSystem/user/login" method = "post">
        <h1 id = "t1" class = "aCenter"> 登&nbsp录 </h1>
        <input class = "iText1" type = "text" name = "userId" placeholder="账号" />
        <br>
        <br>
        <input class = "iText1" type = "password" name = "password" placeholder="密码" />
        <br>
        <p>
            没有账号？<a href = "user_register.jsp">点击注册</a>
        </p>
        <input type="submit" value="登 录" />
    </form>
</div>
</body>
</html>