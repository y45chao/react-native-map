--创建并切换到分支
git checkout -b yc_branch_201710221747
--切换分支
git checkout yc_branch_201710221747
--添加当前分支所有的文件
git add .
--提交当前分支文件内容
git commit -m "添加站点，航班等基本信息查询"
--切换到主版本库
git checkout master
--获取已建立分支
git branch
--获取远程分支的内容
git pull origin master
--当前分支与yc_branch_201710221747内容合并
git merge  yc_branch_201710221747
--提交当前代码
git commit -m "合并代码"
--提交到远程分支
git push origin master
--创建并切换到新的分支
git checkout -b yc_branch_201710232059


--删除分支
git branch -d <name>


--合并后出现冲突
--查看当前状态
git status


解决完冲突后
--git add . 或 文件路径
--git commit -m "";
