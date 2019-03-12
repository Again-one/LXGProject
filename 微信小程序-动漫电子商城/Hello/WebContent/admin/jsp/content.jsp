<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,xin.yohuyotu.HelloWorld.bean.GoodsOrder" 
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
 <!-- 分页处的样式 -->
.page{
 list-style: none;
}
.page li{
 float: left;
 padding: 5px 10px;
 cursor: pointer;
}
.page .pageItem{
 border: solid thin #DDDDDD;
 margin: 5px;
}
.page .pageItemActive{
 border: solid thin #0099FF;
 margin: 5px;
 background-color: #0099FF;
 color:white;
}
.page .pageItem:hover{
 border: solid thin #0099FF;
 background-color: #0099FF;
 color:white;
}
.page .pageItemDisable{
 border: solid thin #DDDDDD;
 margin: 5px;
 background-color: #DDDDDD;
}
#getPage{
	
}
td{
padding-left:4px;

}
 
</style>

</head>
<body>
	<div style="text-align:center;"><img style="width:600px;height:161px" src="./image/success.jpg"></div>
</body>
</html>