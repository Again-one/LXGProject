# 在服务器上创建一个共享git
	先在本地使用
	git remote -v查看是否有配置和远程服务器连接的 <用户名> <git仓库地址>
	没有就 git remote root@xxxx.xxxx.xxxx.xxxx:/etc/demo/demo.git
	root是用户名，确保与服务器的用户名对应
	
	本地写好的代码提交到远程服务器
	git push origin master
	
	远程服务器上生成一个本地仓库
	git init
	更新代码 git pull /etc/demo/demo.git
