<div id="colonnasx">
    [#include '/blocks/user.c-menu.tpl']
    <div>
        <form name="summary-form" method="post" action="${@.context}/user/c/prefs/" id="aspnetForm">
            <fieldset id="form">
                <p><label>Partime:</label>
                    <input type="checkbox" name="partime" value="1" [#if cv.partime?? && cv.partime == 1]checked[/#if]/></p>
                <p><label>Full Time:</label>
                    <input type="checkbox" name="fulltime" value="1" [#if cv.fulltime?? && cv.fulltime == 1]checked[/#if]/></p>
                <p><label>Turni:</label>
                    <input type="checkbox" name="turni" value="1" [#if cv.turni?? && cv.turni == 1]checked[/#if]/></p>
                <p><label>Progetto:</label>
                    <input type="checkbox" name="project" value="1" [#if cv.project?? && cv.project == 1]checked[/#if]/></p>
                <p style="padding-left: 300px;">
                    <a id="" href="" onclick="document.forms['summary-form'].submit(); return false;">Salva</a>
                </p>
            </fieldset>
        </form>

    </div>
    <div class="clear">&nbsp;</div>
</div>

<div id="colonnadx">
    <div class="faq-button">
        <a href="${@.context}/faq"><span>Curricul@ scopri come funziona </span><img src="${@.context}/css/base/img/faq_icon.png"/></a>
    </div>
    <div id="item">
        <h3>Gestione Curriculum - Preferenze</h3>
        <div class="testo">
            <strong>Passo 2 di 6:</strong><br/><br/>
			Inserisci le tue preferenze lavorative.<br/><br/>
        </div>

    </div>
</div>