<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="../css/dtree.css" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	
	<script type="text/javascript" src="../js/dtree.js"></script>
	<script type="text/javascript">
		
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		d.add('0101','01','用户管理','','','contentFrame');
		d.add('010101','0101','用户管理','${pageContext.request.contextPath}/backUser?method=all','','contentFrame');
		d.add('0102','01','一级分类管理','','','contentFrame');
		d.add('010201','0102','一级分类管理','${pageContext.request.contextPath}/backCid?method=all','','contentFrame');
		
		
		d.add('0104','01','商品管理','','','contentFrame');
		d.add('010401','0104','商品管理','${pageContext.request.contextPath}/backGoods?method=all','','contentFrame');
		d.add('0105','01','订单管理','','','contentFrame');
		
		d.add('010502','0105','未付款订单','${pageContext.request.contextPath}/back?method=wFOrder','','contentFrame');
		d.add('010503','0105','已付款订单','${pageContext.request.contextPath}/back?method=fKOrder','','contentFrame');
		d.add('010504','0105','已发货订单','${pageContext.request.contextPath}/back?method=fHOrder','','contentFrame');
		
		document.write(d);
		
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>