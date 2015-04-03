<div id="colonnasx">
    [#include '/blocks/user.c-menu.tpl']
    [#if msg??]
    [#if msg]
    <div id="alertok">
       Inserimento esperienza riuscito con successo!<br />
       Prosegui con le esperienze o passa agli <a href="[@uri.do '30e79708da5585f9'/]">skill</a>
    </div>
    [#else]
    <div id="alert">Inserisci correttamente le date e il ruolo dell'impiego.</div>
    [/#if]
    [/#if]
    <div>
        <form name="ExperienceForm" method="post" action="[@uri.do 'c6ef29cdc9f68c43'/]" id="aspnetForm">
            <fieldset id="form">
                <p>
                    <label>Azienda <span>(opzionale)</span></label>
                    <input name="azienda" type="text" id="CSX_txtNomeAzienda" value="" />

                </p>
                <p>
                    <label>Data inizio <span>(gg/mm/yyyy)</span></label>
                    <input name="inizio" type="text" id="CSX_txtDataInizio" value=""/>
                </p>
                <p>
                    <label>Data fine <span>(gg/mm/yyyy)</span></label>

                    <input name="fine" type="text" id="CSX_txtDataFine" value=""/>
                </p>
                <p>
                    <label>Ruolo</label>
                    <input type="text" name="ruolo" value=""/>
                </p>
                <p>
                    <label>Retribuzione &euro;<span>facoltativo</span></label>
                    <input name="retribuzione" type="text" id="CSX_txtRetribuzione" value=""/>
                </p>
                <p style="padding-left:300px">
                    <a id="CSX_btnAddEsperienza" onclick="document.forms['ExperienceForm'].submit(); return false;" href="">Aggiungi</a>
                </p>
                
            </fieldset>
            
        </form>

    </div>
    [#if cv.experiences??]
 <div class="clear">&nbsp;</div>
    <div id="riepilogo">
        <span style="margin-bottom: 5px; font-weight: bold; font-size: small;">Riepilogo esperienze inserite</span>
        <div class="clear">&nbsp;</div>
         [#list cv.experiences as experience]
        <div class="riepilogo_divisore">${experience.azienda?capitalize} <a href="${@.context}/user/c/experiences/delete/${experience_index}/" style="float: right;"">elimina</a></div>
        <div class="riepilogo_descrizione">
            &nbsp;
            <div class="exp_int">&nbsp;
                <div class="riepilogo-intestazione">Data Inizio</div>
                <div class="riepilogo-intestazione">Data Fine</div>
                <div class="riepilogo-intestazione">Ruolo</div>
                <div class="riepilogo-intestazione">Retribuzione</div>
            </div>
            &nbsp;
                <div class="riepilogo-exp">${experience.inizio}</div>
                <div class="riepilogo-exp">${experience.fine}</div>
                <div class="riepilogo-exp">${experience.ruolo?capitalize}</div>
                <div class="riepilogo-exp">&euro; ${experience.retribuzione}</div>
        </div>
        <div class="clear">&nbsp;</div>
        [/#list]
    </div>
 [/#if]
</div>

<div id="colonnadx">
    <div class="faq-button">
        <a href="${@.context}/faq"><span>Curricul@ scopri come funziona </span><img src="${@.context}/css/base/img/faq_icon.png"/></a>
    </div>
    <div id="item">

        <!--STEP:2-->
        <h3>Gestione Curriculum - Esperienze</h3>
        <div class="testo">
            <strong>Passo 4 di 6: le esperienze sul campo.</strong><br/><br/>
			Le tue esperienze di lavoro. <strong>Aggiungile una ad una e vedi il riepilogo a fondo pagina.</strong><br/><br/>
			Inserisci il nome dell'azienda per cui hai lavorato e gli altri dati prestando particolare attenzione alla selezione del Livello, dell'Area e della SubArea.<br/><br/>

			Se hai ricoperto più ruoli puoi inserire più volte la stessa azienda.<br/><br/>
        </div>

    </div>
</div>