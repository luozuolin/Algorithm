# Quake Web Project (雷神计划-前端)

## 技术构成

- [Git](http://gitref.org/zh/) (版本控制)
- [Gradle](https://dongchuan.gitbooks.io/gradle-user-guide-/content/) (项目构建)
- [SpringBoot](https://qbgbook.gitbooks.io/spring-boot-reference-guide-zh/content/) (服务级框架)
- [SpringMVC](https://linesh.gitbooks.io/spring-mvc-documentation-linesh-translation/content/) (服务级框架)
- [MyBatis](http://www.mybatis.org/mybatis-3/zh/) (持续化)
- [FreeMarker](http://t.bdtool.net/freemarker/) (动态页面模板框架)
- [Jetty](http://www.eclipse.org/jetty/documentation/current/) (Web网络框架)
- [Glup](https://github.com/lisposter/gulp-docs-zh-cn) (前端构建)
- [jQuery](http://www.jquery123.com/) (JS选择器)
- [Vue.js](http://vuejs.org.cn/) (前端数据驱动)
- [SCSS](http://sass.bootcss.com/docs/sass-reference/) (样式单语言)
- [Foundation](http://foundation.zurb.com/sites/docs//) (样式框架)
- [Compass](http://compass-style.org/reference/compass//) (样式框架组件)

## 项目结构

	├── readme.md (本说明文件) 
	├── .gitignore (git工程忽略文件) 
	├── gulpfile.js (gulp配置文件) 
	├── package.json (npm安装配置) 
	├── node_modules (npm仓库,被忽略,由npm安装时创建) 
	├── webpack.config.js (webpack配置) 
	├── package.json (npm安装配置) 
	├─┬ doc (文档区) 
	│ └─┬ shell (脚本区)
	│   └── init.sh (准备脚本)
	├─┬ src (源代码区) 
	│ ├── test (测试代码区)
	│ └─┬ main(java/com.dnp)
	│   ├── data (数据操作区)
	│   ├── util (工具区)
	│   ├── web (web操作区)
	│   └── QuakeApplication.java (主程序)
	├── conf (Spring配置文件夹) 
	├─┬ web (Web资源文件夹) 
	│ ├─┬ asset (Web开发资源)
	│ │ ├── component (vue组件区)
	│ │ ├── javascript (自主js组件区)
	│ │ ├── scss (scss组件区)
	│ │ └── vendor (第三方库文件)
	│ ├── WEB-INF (web配置区)
	│ ├─┬ static (web发布静态资源)
	│ │ ├── dist (资源发布区/js/css/font/img)
	│ │ └── favicon.ico (网站图标)
	│ └─┬ templates (模板资源)
	│   ├── framework (框架级)
	│   └── module (模块级)
	└── log (日志文件夹)

## 环境准备

##### 安装Node.js环境
在命令行中执行`$ node -v`
如果没有安装Node.js或者版本小于4.0
下载安装包[Node.js](https://nodejs.org/en/download/)并安装
##### 初始化文件和目录结构
从项目根目录中执行
`$ bash doc/prepared/init.sh`
##### 安装基本前端开发环境(工程的根目录下)
	sudo npm update -g npm
	npm rebuild node-sass
	npm install -g bower --registry=http://registry.npm.taobao.org
	sudo npm install --global gulp-cli --registry=http://registry.npm.taobao.org
##### 安装gulp所需插件
	npm install\
	    gulp\
	    gulp-load-plugins\
	    gulp-livereload\
	    scss\
	    gulp-clean\
	    gulp-sass\
	    gulp-autoprefixer\
	    gulp-plumber\
	    gulp-sourcemaps\
	    gulp-concat\
	    gulp-babel\
	    gulp-strip-debug\
	    gulp-uglify\
	    gulp-rename\
	    gulp-vue-module\
	    gulp-util\
	    require-dir\
	    run-sequence\
	    vinyl-named\
	    --save-dev\
	    --registry=https://registry.npm.taobao.org
备注:
##### 运行期的环境
    ./gradlew run -Pprofile=stable
    ./gradlew run -Pprofile=dev

- vue开发安装chrome[浏览器插件](https://chrome.google.com/webstore/detail/vuejs-devtools/nhdogjmejiglipccpnnnanhbledajbpd)
- livereload需要配合chrome[浏览器插件](http://livereload.com/extensions/)
##### GIT提交前缀规范
    [FIX]错误修复
    [TUN]优化和改进
    [NEW]新建功能
    [DEL]删除文件或者功能
    [OTH]其他操作