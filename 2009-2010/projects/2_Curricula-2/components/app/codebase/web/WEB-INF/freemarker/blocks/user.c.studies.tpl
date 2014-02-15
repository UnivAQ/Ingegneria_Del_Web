<div id="colonnasx">
    [#include '/blocks/user.c-menu.tpl']
    [#if msg??]
    [#if msg]
    <div id="alertok">
        Studio inserito correttamente.<br/>Inserisci un nuovo titolo di studio o passa alle <a href="[@uri.do 'c6ef29cdc9f68c43'/]">esperienze</a>
    </div>
    [#else]
    <div id="alert">Seleziona il livello di studi che desideri inserire.</div>
    [/#if]
    [/#if]
    <div>
        <form name="StudiesForm" method="post" action="[@uri.do 'ecca508fe1b71b35'/]c/studies/" id="aspnetForm">
            <fieldset id="form">
                [#include 'studies-select.tpl']
                <p style="padding-left:300px">
                    <a id="" href="" onclick="document.forms['StudiesForm'].submit(); return false;">Salva</a>
                </p>
            </fieldset>
            
        </form>

    </div>
    <div class="clear">&nbsp;</div>

   [#if cv.studies??]
    <div id="riepilogo">
        <span style="margin-bottom: 5px; font-weight: bold; font-size: small;">Riepilogo studi</span>
        <div class="clear">&nbsp; </div>
        <div class="riepilogo_divisore">${cv.studies}</div>
        <div class="clear">&nbsp;</div>
    </div>
   [/#if]
</div>

<div id="colonnadx">
    <div class="faq-button">
        <a href="${@.context}/faq"><span>Curricul@ scopri come funziona </span><img src="${@.context}/css/base/img/faq_icon.png"/></a>
    </div>
    <div id="item">
        <h3>Gestione Curriculum - Studi</h3>
        <div class="testo">
            <strong>Passo 3 di 6: i tuoi studi.</strong><br/><br/>
			Inserisci i titoli di studio che hanno arricchito la tua esperienza.<br/><br/>
            <strong>Seleziona un livello ed inserisci nei dettagli la specifica del titolo raggiunto.</strong><br/><br/>
			Sotto al modulo, nel <strong>Riepilogo studi</strong>, puoi visualizzare quanto inserito. Se commetti un errore cancella la voce e inseriscila di nuovo.<br/><br/>
        </div>

    </div>
</div>