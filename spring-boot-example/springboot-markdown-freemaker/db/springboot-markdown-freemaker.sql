/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.17-log : Database - springboot-markdown-freemaker
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springboot-markdown-freemaker` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `springboot-markdown-freemaker`;

/*Table structure for table `editor` */

DROP TABLE IF EXISTS `editor`;

CREATE TABLE `editor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text_content` longtext,
  `content` longtext,
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `editor` */

insert  into `editor`(`id`,`text_content`,`content`,`create_time`) values (5,'![](http://localhost:8080/upload/1551793648961.png)','<p><img src=\"http://localhost:8080/upload/1551793648961.png\" alt=\"\"></p>\n','2019-03-05');
insert  into `editor`(`id`,`text_content`,`content`,`create_time`) values (6,'[TOC]\n\n#### Disabled options\n\n- TeX (Based on KaTeX);\n- Emoji;\n- Task lists;\n- HTML tags decode;\n- Flowchart and Sequence Diagram;\n\n#### Editor.md directory\n\n    editor.md/\n            lib/\n            css/\n            scss/\n            tests/\n            fonts/\n            images/\n            plugins/\n            examples/\n            languages/\n            editormd.js\n            ...\n\n```html\n<!-- English -->\n<script src=\"../dist/js/languages/en.js\"></script>\n\n<!-- 繁體中文 -->\n<script src=\"../dist/js/languages/zh-tw.js\"></script>\n```\n        ','<div class=\"markdown-toc editormd-markdown-toc\">[TOC]</div><h4 id=\"h4-disabled-options\"><a name=\"Disabled options\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Disabled options</h4><ul>\n<li>TeX (Based on KaTeX);</li><li>Emoji;</li><li>Task lists;</li><li>HTML tags decode;</li><li>Flowchart and Sequence Diagram;</li></ul>\n<h4 id=\"h4-editor-md-directory\"><a name=\"Editor.md directory\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Editor.md directory</h4><pre><code>editor.md/\n        lib/\n        css/\n        scss/\n        tests/\n        fonts/\n        images/\n        plugins/\n        examples/\n        languages/\n        editormd.js\n        ...\n</code></pre><pre><code class=\"lang-html\">&lt;!-- English --&gt;\n&lt;script src=&quot;../dist/js/languages/en.js&quot;&gt;&lt;/script&gt;\n\n&lt;!-- 繁體中文 --&gt;\n&lt;script src=&quot;../dist/js/languages/zh-tw.js&quot;&gt;&lt;/script&gt;\n</code></pre>\n','2019-03-05');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
