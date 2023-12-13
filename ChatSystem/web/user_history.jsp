<%@ page import="java.util.List" %>
<%@ page import="com.bean.relationship_add" %>
<%@ page import="com.bean.send_information" %>
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
    <title>${requestScope.name}的聊天记录</title>
    <link href="${path}/index.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${path}/index.js"></script>
    <style>
        body
        {
            background: url('${path}login_background.jpg') no-repeat fixed;
            background-size: 100% 100%;
        }

        .Link {
            text-decoration: none;
            color: black;
        }
        .Link:visited {
            color: black;
        }

        #presentTd {
            background-color: rgba(252,193,106,0.5);
        }

        #sup {
            background-color: rgba(252,193,106,0.5);
        }

        #chatTitle {
            width: 650px;
            height: 60px;
            font-size: 22px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
            font-weight: bold;
            display: table-cell;
            vertical-align: middle;
            background-color: rgba(245,190,152,0.96);
        }

        #chatTitle1 {
            left: 15px;
            top: 15px;
            width: 300px;
            height: 50px;
            margin: 0;
            display: table-cell;
            vertical-align: middle;
            border-radius: 3px;
        }

        #chatBack {
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
        #chatBack:hover {
            color: brown;
        }

        #historySearch {
            margin: 10px auto auto;
            width: 540px;
            height: 50px;

        }
        #historySearch input[type = text] {
            position: absolute;
            left: 46px;
            top: 96px;
            width: 476px;
            height: 32px;
            outline: none;
            border: rgba(252,152,95,0.7) solid 1px;
            border-radius: 5px;
            font-size: 17px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
        }
        #historySearch input[type = submit] {
            position: absolute;
            right: 52px;
            top: 98px;
            width: 70px;
            height: 32px;
            padding: 0;
            border: none;
            display: table-cell;
            vertical-align: middle;
            border-radius: 5px;
            background-color: rgba(132,13,6,0.65);

            font-size: 15px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
            color: white;
        }
        #historySearch input[type = submit]:hover {
            background-color: rgba(132,13,6,0.8);
        }

        #chatScreen {
            width: 600px;
            height: 385px;
            margin: 20px auto;
            outline: none;
            border: rgba(252,152,95,0.7) solid 1px;
            border-radius: 5px;
            overflow-x: hidden;
            overflow-y: auto;

            background-color: white;
        }

        #chatView {
            width: 580px;
            font-size: 16px;
            font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
            border-collapse: collapse;
        }
        #chatView tr {
            height: 33px;
        }

        .sendTime {
            font-size: 14px;
            color: rgba(13,3,3,0.6);
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

        #v1 {
            display: block;
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

    <script>
        function roll() {
            var screen = document.getElementById("chatScreen");
            screen.scrollTop = screen.scrollHeight;
        }

        function Open() {
            var list = document.getElementById("v1");
            var target = document.getElementById("t1")
            if(list.style.display !== "none") {
                list.style.display = "none";
                target.innerHTML = "&nbsp ▶好友";
            }
            else {
                list.style.display = "block";
                target.innerHTML = "&nbsp ▼好友";
            }
        }

    </script>
</head>

<body onload="roll()">
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
            <div id = "t1" class = "tar" onclick="Open()"> &nbsp ▼好友 </div>
            <div id = "v1" class = "v">
                <table id = "relationshipView" border = "0" cellpadding="0" cellspacing="0">
                    <%
                        int presentId = (int) request.getAttribute("id");
                        List list = (List) request.getAttribute("key");
                        for(int i = 0; i < list.size(); i++) {
                            relationship_add userInformation = (relationship_add) list.get(i);
                            int userId = userInformation.getUserId();
                            request.setAttribute("userId", userId);
                            String userName = userInformation.getUserName();
                            request.setAttribute("userName", userName);
                            if(userInformation.getRelationship() == 3) {
                                if(userId != presentId) {
                    %>
                    <tr>
                        <td>
                            <a class = "Link" href="/ChatSystem/chat/refresh?id=${userId}&name=${userName}">
                                &nbsp &nbsp <%=userInformation.getUserName()%> &nbsp (<%=userInformation.getUserId()%>)
                            </a>
                        </td>
                        <td>
                            <button id = "delete" class = "butt" onclick = "window.location.href='../relationship/delete?id=${userId}'" type = "button">
                                ×
                            </button>
                        </td>
                    </tr>
                    <%
                    }
                    else {
                    %>
                    <tr>
                        <td id = "presentTd" >
                            &nbsp &nbsp <%=userInformation.getUserName()%> &nbsp (<%=userInformation.getUserId()%>)
                        </td>
                        <td id = "sup"></td>
                    </tr>
                    <%
                                }
                            }
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
        <div id = "chatTitle">
            <div id = "chatTitle1">&nbsp &nbsp &nbsp ${requestScope.name} &nbsp (${requestScope.id})</div>
            <button id = "chatBack" class = "butt" onclick = "window.location.href='../chat/refresh?id=${requestScope.id}&name=${requestScope.name}'" type = "button">
                ×
            </button>
        </div>

        <form id = "historySearch" action = "/ChatSystem/userHistory/search" method = "get">
            <input type = "text" name = "target" placeholder = "输入查找的聊天内容">
            <input type = "hidden" name = "id" value = ${requestScope.id} />
            <input type = "hidden" name = "name" value = ${requestScope.name} />
            <input type = "submit" value = "查询">
        </form>

        <div id = "chatScreen">
            <table id = "chatView" border = "0" cellpadding="0" cellspacing="0">
                <%
                    List list2 = (List) request.getAttribute("key2");
                    for(int i = 0; i < list2.size(); i++) {
                        send_information sendChatInformation = (send_information) list2.get(i);
                        String record = sendChatInformation.getRecordContent();
                        String sendName = sendChatInformation.getSendName();
                        String sendTime = sendChatInformation.getSendTime();
                %>
                <%
                    if(sendChatInformation.getSendId() == (Integer) session.getAttribute("userId")) {
                %>
                <tr align="right">
                    <td>
                        <span class = "sendTime"> <%=sendTime%> </span> &nbsp &nbsp
                    </td>
                </tr>
                <tr align="right">
                    <td>
                        &nbsp <%=record%> &nbsp :<%=sendName%> &nbsp &nbsp
                    </td>
                </tr>
                <%
                }
                else {
                %>
                <tr>
                    <td>
                        &nbsp &nbsp <span class = "sendTime"> <%=sendTime%> </span>
                    </td>
                </tr>
                <tr>
                    <td>
                        &nbsp &nbsp <%=sendName%>: &nbsp <%=record%> &nbsp
                    </td>
                </tr>
                <%
                    }
                %>
                <%
                    }
                %>
            </table>
        </div>
    </div>
</div>
</body>
</html>