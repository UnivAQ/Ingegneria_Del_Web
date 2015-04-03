        <link rel="shortcut icon" href="${@.context}/css/base/img/favicon.ico"/>
        <link type="text/css" rel="stylesheet" media="all" href="${@.context}/css/base/style.css"/>
[#if resources?? && resources.css??]
[#list resources.css as css]
        <link type="text/css" rel="stylesheet" media="all" href="${@.context}/css/${css}.css"/>
[/#list]
[/#if]
