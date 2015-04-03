[#macro do]
<div id="container">
    <div id="navi">
        <ul class="forum">
            [#if @.auth.isAuthenticated()]
                <li><a href="[@uri.do '94611bb80ded8d7d'/]">Profilo</a></li>
                <li><a href="[@uri.do '484c9f0d7efb8ec9'/]">Esci</a></li>
            [/#if]
        </ul>
        <div class="clear"></div>
    </div>
    <div id="header">
        <h1><a href="${@.context}/" title="Curricul@ - Trova lavoro più facilmente"></a></h1>
    </div>
    <div id="corpo">
        [#nested]
    </div>
    <div class="clear">
    </div>
</div>
<div id="finepagina">
</div>
<div id="footer">
</div>
[/#macro]