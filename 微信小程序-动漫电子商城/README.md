# 文件结构
	Hello //服务端代码
	weChatProject //微信小程序前端代码
	helloworld.sql //sql语句

## 后台部署步骤
	使用的平台是eclipse 
	选择tomcat版本是7
	将lib下所有jar包全部依赖上
	将数据库导入
		
		create database helloworld;
		source d:\helloworld.sql
	
## 前台部署步骤
	下载微信开发者工具
	导入weChatProject->输入属于自己的appid
	进入app.js里修改
	 globalData: {
		userInfo: null,
		myHttp: 'http://39.108.50.93:9090/Hello'
	}
	myHttp为自己localhost或是自己的服务器