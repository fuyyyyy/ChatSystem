<%@ page import="java.util.List"%>
<%@ page import="com.bean.relationship_add" %>
<%@ page import="com.bean.groupRelationship_information" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.setAttribute("path", basePath);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改信息</title>
    <link href="${path}/index.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${path}/index.js"></script>
    <style>
        body
        {
            background: url('${path}login_background.jpg') no-repeat fixed;
            background-size: 100% 100%;
        }

        #changeTitle {
            width: 650px;
            height: 60px;
            font-size: 22px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
            font-weight: bold;
            display: table-cell;
            vertical-align: middle;
            background-color: rgba(245,190,152,0.96);
        }

        #changeTitle1 {
            left: 15px;
            top: 15px;
            width: 300px;
            height: 50px;
            margin: 0;
            display: table-cell;
            vertical-align: middle;
            border-radius: 3px;
        }

        #changeBack {
            position: absolute;
            right: 20px;
            top: 25px;
            width: 40px;
            height: 40px;
            padding: 0;
            margin: 0;
            border: none;
            display: table-cell;
            vertical-align: middle;
            border-radius: 0;
            background-color: rgba(245,190,152,0.96);

            font-size: 30px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
            color: rgba(167,26,24,0.5);
            text-align: center;
            line-height: 15px;
        }
        #changeBack:hover {
            color: brown;
        }

        #delete {
            right: 20px;
            top: 18px;
            width: 25px;
            height: 25px;
            padding: 0;
            margin: 0;
            border: none;
            display: table-cell;
            vertical-align: middle;
            border-radius: 0;
            background-color: rgba(255,255,255,0);

            font-size: 20px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
            color: black;
            font-weight: normal;
            text-align: center;
            line-height: 10px;

        }
        #delete:hover {
            color: brown;
        }

        #changeInformation {
            width: 600px;
            height: 400px;
            margin: 30px auto auto;
        }

        #changeName {
            width: 520px;
            height: 140px;
            margin: auto auto;
            border: rgba(252,152,95,0.7) solid 1px;
            border-radius: 5px;
        }
        #changeName div{
            position: absolute;
            left: 105px;
            top: 125px;
            width: 85px;
            height: 30px;
            background-color: #fdd8a6;
            font-size: 20px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
        }
        #changeName input[type = text] {
            position: absolute;
            left: 105px;
            top: 180px;
            width: 320px;
            height: 30px;
            outline: none;
            border: rgba(252,152,95,0.7) solid 1px;
            border-radius: 5px;
            font-size: 16px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
        }
        #changeName input[type = submit] {
            position: absolute;
            right: 120px;
            top: 182px;
            width: 100px;
            height: 30px;
            padding: 0;
            border: none;
            display: table-cell;
            vertical-align: middle;
            border-radius: 5px;
            background-color: rgba(132,13,6,0.65);

            font-size: 14px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
            color: white;
        }
        #changeName input[type = submit]:hover {
            background-color: rgba(132,13,6,0.8);
        }

        #changePassword {
            width: 520px;
            height: 250px;
            margin: 30px auto auto;
            border: rgba(252,152,95,0.7) solid 1px;
            border-radius: 5px;
        }
        #changePassword div{
            position: absolute;
            left: 105px;
            top: 300px;
            width: 85px;
            height: 30px;
            background-color: #fdd8a6;
            font-size: 20px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
        }
        #changePassword input[type = password] {
            width: 320px;
            height: 30px;
            outline: none;
            border: rgba(252,152,95,0.7) solid 1px;
            border-radius: 5px;
            font-size: 16px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
        }
        #changePassword input[type = submit] {
            position: absolute;
            right: 120px;
            top: 462px;
            width: 100px;
            height: 30px;
            padding: 0;
            border: none;
            display: table-cell;
            vertical-align: middle;
            border-radius: 5px;
            background-color: rgba(132,13,6,0.65);

            font-size: 14px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
            color: white;
        }
        #changePassword input[type = submit]:hover {
            background-color: rgba(132,13,6,0.8);
        }

        #c1 {
            position: absolute;
            left: 105px;
            top: 360px;
        }
        #c2 {
            position: absolute;
            left: 105px;
            top: 410px;
        }
        #c3 {
            position: absolute;
            left: 105px;
            top: 460px;
        }

        #error {
            position: absolute;
            left: 225px;
            top: 302px;
            font-size: 16px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
            color: brown;
        }

        #ser {
            margin-top: 10px;
            margin-left: 13px;
            width: 214px;
            height: 30px;
        }
        #ser form input[type=text] {
            margin: 0 auto auto;
            width: 210px;
            height: 28px;
            outline: none;
            border: #f0d897 solid 1px;
            border-radius: 5px;
            font-size: 14px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
        }

    </style>
</head>

<body>
<div id = "userName" >
    ${sessionScope.userName}
</div>
<form id = "quit" action = "/ChatSystem/user/quit" method = "get">
    <input type="submit" value="退出" />
</form>
<button id = "openMenu" onmouseover="keep()" onmouseout="back()">
    +
</button>
<div id = "opMenu" onmouseover="keep()" onmouseout="back()">
    <button class = "option" onclick = "window.location.href='../relationship/add'" type = "button">
        添加好友
    </button>
    <button class = "option" onclick = "window.location.href='../create/group'" type = "button">
        创建群聊
    </button>
    <button class = "option" onclick = "window.location.href='../group/add'" type = "button">
        加入群聊
    </button>
    <button class = "option" onclick = "window.location.href='../information/change'" type = "button">
        修改信息
    </button>
</div>
<div id = "frame1" class = "fra">
    <div id = "component1" class = "comp" >
        <div id = "relationshipTitle">
            &nbsp &nbsp 我的好友
        </div>

        <div id = "ser">
            <form action="/ChatSystem/list/search" method = "get">
                <input type="text" name="target" placeholder="输入查找的账号/昵称">
            </form>
        </div>

        <div id = "relationshipList">
            <div id = "t1" class = "tar" onclick="Open()"> &nbsp ▶好友 </div>
            <div id = "v1" class = "v">
                <table id = "relationshipView" border = "0" cellpadding="0" cellspacing="0">
                    <%
                        List list = (List) request.getAttribute("key");
                        for(int i = 0; i < list.size(); i++) {
                            relationship_add userInformation = (relationship_add) list.get(i);
                            int userId = userInformation.getUserId();
                            request.setAttribute("userId", userId);
                            String userName = userInformation.getUserName();
                            request.setAttribute("userName", userName);
                    %>
                    <%if(userInformation.getRelationship() == 3) {%>
                    <tr>
                        <td>
                            <a id = "relationshipLink" href="/ChatSystem/chat/refresh?id=${userId}&name=${userName}">
                                &nbsp &nbsp <%=userInformation.getUserName()%> &nbsp (<%=userInformation.getUserId()%>)
                            </a>
                        </td>
                        <td>
                            <button id = "delete" class = "butt" onclick = "window.location.href='../relationship/delete?id=${userId}'" type = "button">
                                ×
                            </button>
                        </td>
                    </tr>
                    <%}%>
                    <%
                        }
                    %>
                </table>

            </div>
            <div id = "t2" class = "tar" onclick = "Open2()"> &nbsp ▶群聊 </div>

            <div id = "v2" class = "v">
                <table id = "relationshipViewG" border = "0" cellpadding="0" cellspacing="0">
                    <%
                        List listG = (List) request.getAttribute("keyG");
                        for(int i = 0; i < listG.size(); i++) {
                            groupRelationship_information information = (groupRelationship_information) listG.get(i);
                            int groupId = information.getGroupId();
                            request.setAttribute("groupId", groupId);
                            String groupName = information.getGroupName();
                            request.setAttribute("groupName", groupName);
                    %>
                    <tr>
                        <td width="200px">
                            <a id = "relationshipLinkG" href="/ChatSystem/groupChat/refresh?groupId=${groupId}&groupName=${groupName}">
                                &nbsp &nbsp <%=information.getGroupName()%> &nbsp (<%=information.getGroupId()%>)
                            </a>
                        </td>
                        <td>
                            <button id = "deleteG" class = "butt" onclick = "window.location.href='../group/delete?groupId=${groupId}'" type = "button">
                                ×
                            </button>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
    </div>
</div>


<div id = "frame2" class = "fra">
    <div id = "component2" class = "comp" >
        <div id = "changeTitle">
            <div id = "changeTitle1">&nbsp &nbsp &nbsp 修改信息</div>
            <button id = "changeBack" class = "butt" onclick = "window.location.href='../relationshipList/refresh'" type = "button">
                ×
            </button>
        </div>
        <div id = "changeInformation">
            <form id = "changeName" action = "/ChatSystem/name/change" method = "get">
                <div>修改昵称</div>
                <input class = "change" type = "text" name = "name" placeholder="输入新昵称" />
                <input type = "submit" value = "确认修改" />
            </form>
            <form id = "changePassword" action = "/ChatSystem/password/change" method = "get">
                <div>修改密码</div>
                <input id = "c1" class = "change" type = "password" name = "password" placeholder="输入旧密码" />
                <input id = "c2" class = "change" type = "password" name = "newPassword" placeholder="输入新密码" />
                <input id = "c3" class = "change" type = "password" name = "againPassword" placeholder="再次输入新密码" />
                <input type = "submit" value = "确认修改" />
            </form>
            <div id = "error">信息输入错误，请重新输入</div>
        </div>
    </div>
</div>
</body>
</html>
