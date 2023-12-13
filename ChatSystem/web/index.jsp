<%@ page import="java.util.List" %>
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
  <title>首页</title>
  <link href="${path}/index.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="${path}/index.js"></script>
  <style>
    body
    {
      background: url('${path}login_background.jpg') no-repeat fixed;
      background-size: 100% 100%;
    }

    #userName {
      position: absolute;
      transform: translate(-50%, -50%);

      width: 130px;
      height: 40px;
      top: 13%;
      left: 18%;

      font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
      font-size: 30px;
      font-weight: bold;
      color: beige;
    }

    #openMenu {
      position: absolute;
      top: 163px;
      left: 390px;
      z-index: 998;
      width: 30px;
      height: 34px;
      background-color: rgba(255,255,255,0);
      padding: 0;
      margin: 0;
      border: none;
      display: table-cell;
      vertical-align: middle;
      line-height: 30px;
      border-radius: 0;

      font-size: 40px;
      font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
      color: rgba(167,26,24,0.5);
      text-align: center;

    }

    #opMenu {
      position: absolute;
      width: 120px;
      height: 160px;
      top: 196px;
      left: 390px;
      z-index: 999;
      background-color: #fffaef;
      border-radius: 5px;
      visibility: hidden;
      overflow: hidden;

      filter:progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=4);
      -moz-box-shadow: 2px 2px 10px #909090;
      -webkit-box-shadow: 2px 2px 10px #909090;
      box-shadow:2px 2px 10px #909090;
    }

    .option {
      width: 120px;
      height: 40px;
      background-color: #fffaef;
      padding: 0;
      margin: 0;
      border: none;

      display: table-cell;
      vertical-align: middle;

      font-size: 16px;
      font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
      border-collapse: collapse;
      color: black;
    }
    .option:hover {
      background-color: rgba(132,13,6,0.65);
      color: #fffaef;
    }

    .fra {
      background-color: rgba(255, 255, 255, 0.3);
      border-radius: 5px;
      position: absolute;
      transform: translate(-50%, -50%);
      display: flex;
      align-items: center;
      justify-content: center;
    }

    #frame1 {
      width: 260px;
      height: 600px;
      top: 55%;
      left: 22%;
    }

    #frame2 {
      width: 680px;
      height: 600px;
      top: 55%;
      left: 62%;
    }

    .comp {
      background-color: #fffaef;
    }

    #component1 {
      width: 240px;
      height: 570px;
      border-radius: 3px;
      overflow: hidden;
    }

    #quit {
      position: absolute;
      transform: translate(-50%, -50%);

      width: 130px;
      height: 40px;
      top: 14%;
      left: 30%;
    }

    input[type = submit] {
      width: 100px;
      height: 35px;
      background-color: rgba(255, 255, 255, 0);
      border: none;

      font-family: 微软雅黑, 宋体, Arial, Helvetica, Verdana, sans-serif;
      color: beige;
      font-size: 22px;
    }
    input[type = submit]:hover {
      color: #fdd8a6;
    }

    #relationshipList {
      margin: 10px auto auto;
      width: 234px;
      height: 440px;
      overflow-x: hidden;
      overflow-y: auto;

      background-color: #fffaef;
    }

    #relationshipTitle {
      width: 240px;
      height: 60px;
      font-size: 22px;
      font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
      font-weight: bold;
      display: table-cell;
      vertical-align: middle;
      background-color: rgba(245,190,152,0.96);
    }

    .tar {
      height: 44px;
      width: 230px;
      margin: auto auto;
      line-height: 44px;

      font-size: 17px;
      font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
    }
    .tar:hover {
      background: rgba(0,0,0,0.06);
    }

    .v {
      width: 230px;
      margin: auto auto;

      display: none;
    }

    #relationshipView {
      width: 230px;
      font-size: 16px;
      font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
      border-collapse: collapse;
    }
    #relationshipView tr {
      height: 33px;
    }
    #relationshipView tr:hover {
      background-color: rgba(252,193,106,0.2);
    }

    #relationshipLink {
      text-decoration: none;
      color: black;
    }
    #relationshipLink:visited {
      color: black;
    }

    #relationshipLinkG {
      text-decoration: none;
      color: black;
    }
    #relationshipLinkG:visited {
      color: black;
    }

    #relationshipViewG {
      width: 230px;
      font-size: 16px;
      font-family: 微软雅黑, 宋体, Arial, Helvetica, erdana, sans-serif;
      border-collapse: collapse;
    }
    #relationshipViewG tr {
      height: 33px;
    }
    #relationshipViewG tr:hover {
      background-color: rgba(252,193,106,0.2);
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

    #deleteG {
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
    #deleteG:hover {
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

  <script>
    function Open2() {
      var list = document.getElementById("v2");
      var target = document.getElementById("t2");
      if(list.style.display !== "block") {
        list.style.display = "block";
        target.innerHTML = "&nbsp ▼群聊";
      }
      else {
        list.style.display = "none";
        target.innerHTML = "&nbsp ▶群聊";
      }
    }
  </script>
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
      <div id = "t1" class = "tar" onclick = "Open()"> &nbsp ▶好友 </div>
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
          <td width="200px">
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

</div>
</body>
</html>
