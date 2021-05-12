
简单创建并运行一个vue项目

一、安装开发软件

1、开发环境

        node.js环境（npm包管理器）
        vue-cli 脚手架构建工具
        cnpm  npm的淘宝镜像

2、检查开发软件安装是否成功。

        node -v
        npm -v

3、安装cnpm（cnpm 是淘宝团队提供的一个npm国内替代工具）

        命令行中输入 npm install -g cnpm --registry=http://registry.npm.taobao.org

4、安装vue-cli脚手架构建工具

        cnpm install -g vue-cli

5、构建项目(webpack,前端资源加载/打包工具。)

        vue init webpack 项目名称


6、安装依赖包

    cd到项目文件夹，然后运行命令 cnpm install

运行：需要切入到项目根目录。项目根目录、根目录，不然会报错无法启动。
npm run dev

