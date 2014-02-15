[#include 'home-link.tpl']
<h4><span class="curriculum">Gestione Curriculum</span></h4>
<ul id="menu_area">
    <li><a href="[@uri.do 'a8d606833b2b5bb0'/]" [#if cm_active == "cm-personal"] class="active" [/#if]>Anagrafica</a></li>
    <li><a href="[@uri.do '8473c511418ab366'/]" [#if cm_active == "cm-prefs"] class="active" [/#if]>Preferenze</a></li>
    <li><a href="[@uri.do '1aed5fa2352d0236'/]" [#if cm_active == "cm-studies"] class="active" [/#if]>Studi</a></li>
    <li><a href="[@uri.do 'c6ef29cdc9f68c43'/]" [#if cm_active == "cm-experiences"] class="active" [/#if]>Esperienze</a></li>
    <li><a href="[@uri.do '30e79708da5585f9'/]" [#if cm_active == "cm-skills"] class="active" [/#if]>Competenze</a></li>
    <li><a href="[@uri.do '44ac11e708413260'/]" [#if cm_active == "cm-summary"] class="active" [/#if]>Riepilogo</a></li>
    <li class="clear"></li>
</ul>