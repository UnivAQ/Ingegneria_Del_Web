<div id="colonnasx">
    <div id="submenu">
        <ul>
            <li><a href="[@uri.do '74cde451fd87d769'/]" [#if active == "cv-search"] class="cv-search_active" [#else] class="cv-search" [/#if]>Ricerca Curriculum</a></li>
            <li><a href="[@uri.do '90ee875f41c4df3b'/]" [#if active == "oneshot-search"] class="oneshot_active" [#else] class="oneshot" [/#if]><span>Ricerca </span> <img src="${@.context}/css/base/img/oneshot_button.png"/></a></li>
        </ul>
        <div class="clear"></div>
    </div>
    <div class="area1">
        [#if active == "cv-search"]
            [#include 'company.search-block.tpl']
        [#else]
            [#include 'search-results-table.tpl']
        [/#if]
    </div>
</div>

<div id="colonnadx">
    <div class="faq-button">
        <a href="${@.context}/faq"><span>Curricul@ scopri come funziona </span><img src="${@.context}/css/base/img/faq_icon.png"/></a>
    </div>
    <div id="item">
        <h3><strong>Area personale aziende</strong></h3>
        <div class="testo">
            Bentornato! Cerca un curriculum adatto a te...
        </div>
    </div>
</div>