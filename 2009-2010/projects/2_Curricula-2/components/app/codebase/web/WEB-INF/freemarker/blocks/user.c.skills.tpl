<div id="colonnasx">
    [#include '/blocks/user.c-menu.tpl']
    [#if msg??]
    [#if msg]
    <div id="alertok">
        Inserimento skill avvenuto con successo.<br/>Inserisci una nuova skill o vai al <a href="[@uri.do '44ac11e708413260'/]">riepilogo</a>
    </div>
    [#else]
    <div id="alert">Inserisci correttamente tutti i campi</div>
    [/#if]
    [/#if]
    <div>
        <form name="SkillForm" method="post" action="[@uri.do 'ecca508fe1b71b35' /]c/skills" id="aspnetForm">
            <fieldset id="form">
                <p>
                    <label>Settore</label>
                    <input type="text" name="settore" id="settore" value=""/>
                </p>
                <p>
                    <label>Tipo</label>
                    <input type="text" name="tipo" id="tipo" value=""/>
                </p>
                <p>
                    <label>Livello</label>
                    <select name="livello" id="livello">
                        <option selected="selected" value="">Seleziona</option>
                        <option value="base">Base</option>
                        <option value="medio">Medio</option>
                        <option value="avanzato">Avanzato</option>
                        <option value="esperto">Esperto</option>
                    </select>
                </p>

                <p style="padding-left:300px">
                    <a id="CSX_btnAddConoscenza" onclick="document.forms['SkillForm'].submit(); return false;" href="">Aggiungi</a>
                </p>
            </fieldset>
        </form>

    </div>
    [#if cv.skills??]
    <div class="clear">&nbsp;</div>
    <div id="riepilogo">
        <span style="margin-bottom: 5px; font-weight: bold; font-size: small;">Riepilogo skill</span>
        <div class="clear">&nbsp; </div>
        [#list cv.skills as skill]
        <div class="riepilogo_divisore">${skill.settore?capitalize} <a href="${@.context}/user/c/skills/delete/${skill_index}/" style="float: right;"">elimina</a></div>
        <div class="riepilogo_descrizione">
            <div class="riepilogo-exp">${skill.tipo?capitalize}</div>
            <div class="riepilogo-exp">
                <select name="livello" id="livello">
                    <option selected="selected" value="${skill.livello}">${skill.livello?capitalize}</option>
                </select>
            </div>
        </div>
        <div class="clear">&nbsp;</div>
        &nbsp;
        [/#list]
    </div>
    [/#if]
</div>

<div id="colonnadx">
    <div class="faq-button">
        <a href="${@.context}/faq"><span>Curricul@ scopri come funziona </span><img src="${@.context}/css/base/img/faq_icon.png"/></a>
    </div>
    <div id="item">
        <h3>Gestione Curriculum - Skill</h3>
        <div class="testo">

            <strong>Passo 5 di 6: le tue capacità concrete.</strong><br/><br/>
			Questo quadro è fondamentale per essere trovati da chi è alla ricerca di persone con le tue conoscenze.<br/><br/>
			Seleziona i campi Area che ti sembrano attinenti e compila il modulo che ti appare <strong>indicando il tuo livello di conoscenza</strong> scegliendo tra:<br/>
            <ul>
                <li><strong>Nessuna</strong></li>
                <li><strong>Base</strong>, per indicare una conosenza generica</li>
                <li><strong>Medio</strong>, indica che hai lavorato con questa tecnologia</li>
                <li><strong>Alto</strong>, hai lavorato e approfondito la conoscenza</li>
                <li><strong>Esperto</strong>, conosci perfettamente questa tecnologia</li>
            </ul>
            <br/>
            A fondo pagina trovi l'elenco delle tue selezioni, puoi modificare o cancellare una voce posizionandoti sulla relativa riga.<br/><br/>
        </div>
    </div>
</div>