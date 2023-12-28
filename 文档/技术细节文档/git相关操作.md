git add .  // 添加到暂存区
git commit -m '注释说明'   // 提交到本地仓库
git push origin yyp-dev  // 提交到远程仓库  yyp-dev是远程某个分支的名字
// 切换到主分支
git checkout master
// 提交之前先拉取主分支
git pull origin master
// 将我的分支合并到主分支
git merge yyp-dev
// 将合并好的主分支提交到远程仓库
git push origin master
