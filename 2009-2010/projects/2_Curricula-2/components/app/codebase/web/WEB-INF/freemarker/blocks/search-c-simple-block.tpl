<div id="item">
    <h3><strong>Ricerca un curriculum</strong></h3>

    <div id="search-form-simple" class="form">
        <form id="search-form"method="get" action="${@.context}/search/">
            [#include 'gender-select.tpl']
            [#include 'language-select.tpl']
            [#include 'provinces-select.tpl']
            [#include 'studies-select.tpl']
            <p>
                <a onclick="document.forms['search-form'].submit(); return false;">Cerca</a>
            </p>
        </form>
    </div>
    [#include 'search-results-table.tpl']
</div>