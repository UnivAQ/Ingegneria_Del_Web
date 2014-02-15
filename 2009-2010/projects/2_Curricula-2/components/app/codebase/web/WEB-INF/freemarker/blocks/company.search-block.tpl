<div id="search-c-area">
    <form id="form" method="get" action="${@.context}/company/search/">
        [#include 'gender-select.tpl']
        [#include 'language-select.tpl']
        [#include 'provinces-select.tpl']
        [#include 'city-input.tpl']
        [#include 'studies-select.tpl']
        <p>
            <p><label>Fulltime:</label>
            <input type="checkbox" name="fulltime" value="1"/></p>
            <p><label>Partime:</label>
            <input type="checkbox" name="partime" value="1"/></p>
            <p><label>Turni:</label>
            <input type="checkbox" name="turni" value="1"/></p>
            <p><label>Progetto:</label>
            <input type="checkbox" name="project" value="1"/></p>
        </p>
        <p style="padding-left: 300px;">
            <a onclick="document.forms['form'].submit(); return false;">Cerca</a>
        </p>
        <button type="submit" name="oneshot" value="save">Salva 1Shot</button>
    </form>
    [#include 'search-results-table.tpl']
</div>