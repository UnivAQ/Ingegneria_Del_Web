        <script type="text/javascript" language="javascript" src="${@.context}/js/jquery.js"> </script>
        <script type="text/javascript" language="javascript" src="${@.context}/js/jquery-ui.js"> </script>
        <script type="text/javascript" language="javascript" src="${@.context}/js/base/lavoro.js"> </script>
        <script type="text/javascript" language="javascript" src="${@.context}/js/base/lavoro-util.js"> </script>
[#if resources?? && resources.js??]
[#list resources.js as js]
        <script type="text/javascript" language="javascript" src="${@.context}/js/${js}.js"> </script>
[/#list]
[/#if]