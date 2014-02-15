<div id="colonnasx">
    [#include '/blocks/user.c-menu.tpl']
    [#if msg??]
    [#if msg]
    <div id="alertok">
        Dati inseriti correttamente.<br/>Passa agli <a href="[@uri.do '1aed5fa2352d0236'/]">studi</a>
    </div>
    [#else]
    <div id="alert">Inserisci correttamente tutti i campi.</div>
    [/#if]
    [/#if]
    <div>
        <form name="summary-form" method="post" action="[@uri.do 'a8d606833b2b5bb0'/]" id="summary-form">
            <fieldset id="form">
                <p>
                    <label>Nome</label>
                    <input type="text" name="name" value="[#if cv.name??]${cv.name?capitalize}[/#if]"/>
                </p>
                <p>
                    <label>Cognome</label>
                    <input type="text" name="surname" value="[#if cv.surname??]${cv.surname?capitalize}[/#if]"/>
                </p>
                <p>
                    <label>Data di nascita <span>(gg/mm/yyyy)</span></label>
                    <input type="text" name="birthday" value="[#if cv.birthday??]${cv.birthday}[/#if]"/>
                </p>
                <p>
                    <label>Sesso</label>
                    <label for="gender-m">Maschio
                        <input id="gender-m" name="gender" value="m" [#if ! cv.gender?? || (cv.gender?? && cv.gender == "m")]checked[/#if] type="radio"/>
                    </label>
                    <label for="gender-f">Femmina
                        <input id="gender-f" name="gender" value="f" [#if cv.gender?? && cv.gender == "f"]checked[/#if] type="radio"/>
                    </label>
                [#include 'provinces-select.tpl']
                <p>
                    <label>Città</label>
                    <input type="text" name="city" value="[#if cv.city??]${cv.city?capitalize}[/#if]"/>
                </p>
                <p>
                    <label>eMail</label>
                    <input type="text" name="email" value="[#if cv.email??]${cv.email}[/#if]"/>
                </p>

                <p style="padding-left: 300px;">
                    <a id="" href="" onclick="document.forms['summary-form'].submit(); return false;">Salva</a>
                </p>
            </fieldset>
        </form>

    </div>
</div>

<div id="colonnadx">
    <div class="faq-button">
        <a href="${@.context}/faq"><span>Curricul@ scopri come funziona </span><img src="${@.context}/css/base/img/faq_icon.png"/></a>
    </div>
    <div id="item">
        <h3>Gestione Curriculum - Anagrafica</h3>
        <div class="testo">
            <strong>In soli 6 passi l'inserimento del tuo Curriculum in Curricul@.</strong><br><br>
			Compila i campi vuoti o modificali se li avevi già compilati:
                        questo è il primo passo per inserire il tuo Curriculum.<br><br>
			Qui non ci sono dati personali: <strong>nessuno sarà in grado di
                        conoscere i tuoi riferimenti</strong> se non sarai tu a volerlo.<br><br>
			Per saperne di più prenditi due minuti per leggere le 
                        <a href="${@.context}/faq" style="text-decoration: underline;">informazioni su Curricul@</a>.
                        <br/><br/>
        </div>

    </div>

</div>
