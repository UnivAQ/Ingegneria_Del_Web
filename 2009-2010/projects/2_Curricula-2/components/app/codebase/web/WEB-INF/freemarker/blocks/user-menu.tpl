<div id="submenu">
    <ul>
        <li><a href="[@uri.do 'ecca508fe1b71b35'/]" [#if active == "personal-area" || active == "signin"] class="area_active" [#else] class="area" [/#if]>Area Personale</a></li>
        <li><a href="[@uri.do 'c24a3ebe339a033a'/]" [#if active == "search-curriculum"] class="curriculum_active" [#else] class="curriculum" [/#if]>Ricerca Curriculum</a></li>
        <li><a href="#" [#if active == "oneshot-search"] class="oneshot_active" [#else] class="oneshot" [/#if]>Ricerca annunci 1Shot</a></li>
    </ul>
    <div class="clear"></div>
</div>