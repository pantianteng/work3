<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>拦截页面</title>
    <link href = "css/block.css" rel = "stylesheet" type="text/css">
    <script src="js/main.js"></script>
</head>
<body>
<form>
    <div class="div1">
        <img src="images/错误界面/错误提示.png" width="300px" height="300px"/>
    </div>

    <div class="div2">
        <font color="red"><strong>${blockInfo} </strong></font>
        <br><br>
        请
        <label onClick="returnBack()" style="cursor: pointer;color: red;">
            <u><i>请点击</i></u>
        </label>
		将回到上一页面
    </div>
</form>
</body>
</html>