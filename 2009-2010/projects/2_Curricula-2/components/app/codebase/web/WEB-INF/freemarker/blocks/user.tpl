<div id="colonnasx">
    <div id="submenu">
        <ul>
            <li><a href="[@uri.do '919d15a198e10234'/]" class="back-home"><img src="${@.context}/css/base/img/home_icon.png"/><span>Torna alla home</span></a></li>
        </ul>
        <div class="clear"></div>
    </div>
    <div id="personal-area">
        <ul class="area1">
            <li class="titoloarea"><h3><strong>Gestione curriculum</strong></h3></li>
            <p>
                Benvenuto nella pagina del tuo profilo.
            </p>
            [#if cv.new]
            <li><a class="curriculum" href="[@uri.do 'a8d606833b2b5bb0'/]">Inserisci curriculum<span>Inserisci il tuo curriculum nel nostro sito!</span></a></li>
            [#else]
            <li><a class="curriculum" href="[@uri.do 'a8d606833b2b5bb0'/]">Modifica curriculum<span>Vuoi modificare i dati del tuo curriculum?</span></a></li>
            [/#if]
        </ul>
    </div>
</div>

<div id="colonnadx">
    <div class="faq-button">
        <a href="${@.context}/faq"><span>Curricul@ scopri come funziona </span><img src="${@.context}/css/base/img/faq_icon.png"/></a>
    </div>
    <div id="item">
        <h3><strong>Notifiche</strong></h3>
        <div class="testo">
            [#if notifies_num > 0]
            <img src="${@.context}/css/base/img/alert-icon.png"/>
            Hai <strong>${notifies_num}</strong> messaggi ricevuti da
            aziende interessate al tuo curriculum.<br/><br/><br/>
            <ul>
                [#list notifies as ns]
                <li>
                    <p>L'azienda </p>
                    <p>
                        <strong>${ns.uid} &lt;${ns.email}&gt;</strong>
                    </p>
                    <p>
                        [#if ns.piva != '']<strong>Piva:${ns.piva}</strong>[/#if]
                    </p>
                    <p>
                        è interessata al tuo Curriculum. <a href="${@.context}/user/read-note/${ns._id}/">rimuovi</a>
                    </p>
                </li>
                [/#list]
            </ul>
        </div>
        [#else]
        <div class="testo">
            <img src="${@.context}/css/base/img/sad-icon.png"/>
            Ci dispiace, purtroppo al momento <strong>non hai messaggi</strong> da parte di aziende.
            Lo staff di Curricul@ ti augura di trovare lavoro al più presto!
        </div>
        [/#if]
    </div>
</div>