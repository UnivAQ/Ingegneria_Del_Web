<div id="colonnasx">
    [#include '/blocks/user.c-menu.tpl']
    <div class="curriculumvitae">
        <div id="cv_anagrafica">
           
            <ul>
                <li><strong>Data di nascita:</strong> [#if cv.birthday??] ${cv.birthday}[#else] -/-/- [/#if]</li>
                <li><strong>Paese di domicilio:</strong> [#if cv.province??] ${cv.province?capitalize}[#else] - [/#if], [#if cv.city??]${cv.city}[#else] -[/#if]</li>
                <li><strong>Livello di Studio:</strong> [#if cv.studies??]${cv.studies?capitalize}[#else]-[/#if]</li>
            </ul>
            
        </div>
        <div id="preferences">
            <a class="esperienze" href="[@uri.do 'c6ef29cdc9f68c43'/]">Preferenze di lavoro <span style="float:right;margin-top:-15px;padding-right:10px;">Modifica</span></a>
            <div class="divisore">Preferenze</div>
            <div class="divisore">
                <div class="exp-intestazione">Fulltime</div>
                <div class="exp-intestazione">Partime</div>
                <div class="exp-intestazione">Turni</div>
                <div class="exp-intestazione">Progetto</div>
            </div>
            <div class="divisore">
                <div class="exp-dati">[#if cv.fulltime == 1]Si[#else]No[/#if]</div>
                <div class="exp-dati">[#if cv.partime == 1]Si[#else]No[/#if]</div>
                <div class="exp-dati">[#if cv.turni == 1]Si[#else]No[/#if]</div>
                <div class="exp-dati">[#if cv.project == 1]Si[#else]No[/#if]</div>
            </div>
            <div class="clear">&nbsp;</div>
        </div>
        <div id="experience">
            <a class="esperienze" href="[@uri.do 'c6ef29cdc9f68c43'/]">Esperienze di lavoro <span style="float:right;margin-top:-15px;padding-right:10px;">Modifica</span></a>
            [#if cv.experiences??]
            [#list cv.experiences as experience]
            <div class="divisore">${experience.azienda?capitalize}</div>
            <div class="divisore-boxintestazione">
                <div class="exp-intestazione">Data Inizio</div>
                <div class="exp-intestazione">Data Fine</div>
                <div class="exp-intestazione">Livello</div>
            </div>
            <div class="divisore-boxdati">
                <div class="exp-dati">${experience.inizio}</div>
                <div class="exp-dati">${experience.fine}</div>
                <div class="exp-dati">${experience.ruolo?capitalize}</div>
            </div>
            <div class="clear">&nbsp;</div>
            [/#list]
            [/#if]
        </div>

        <div id="skills">
            <a class="esperienze" href="[@uri.do '30e79708da5585f9'/]">Conoscenze professionali <span style="float:right;margin-top:-15px;padding-right:10px;">Modifica</span></a>
            [#if cv.skills??]
            [#list cv.skills as skill]
            <div class="divisore">${skill.settore?capitalize}</div>
            <div class="divisore-boxintestazione">
                <div class="exp-intestazione">Tipo</div>
                <div class="exp-intestazione">Livello</div>
            </div>
            <div class="divisore-boxdati">
                <div class="exp-dati">${skill.tipo?capitalize}</div>
                <div class="exp-dati">${skill.livello?capitalize}</div>
            </div>
            <div class="clear">&nbsp;</div>
            <!--
            <div class="divisore">${skill.settore?capitalize}</div>
            <div class="divisore-boxintestazione">
                <div class="skill-intestazione">${skill.tipo?capitalize}</div>
                <div class="skill-intestazione">${skill.livello?capitalize}</div>
            </div>-->
            [/#list]
            [/#if]
        </div>
        <div class="clear">&nbsp;</div>        
    </div>
</div>


<div id="colonnadx">
    <div class="faq-button">
        <a href="${@.context}/faq"><span>Curricul@ scopri come funziona </span><img src="${@.context}/css/base/img/faq_icon.png"/></a>
    </div>
    <div id="item">
        <h3>Gestione Curriculum - Riepilogo</h3>
        <div class="testo">
            Rivedi il tuo Curriculum e clicca nelle singole sezioni per effettuare modifiche.<br/><br/>
            <strong>Il Curriculum è già pubblicato</strong>, se vuoi che non sia visibile ad alcuno vai nell'<a href="${@.context}/user" style="text-decoration: underline;">Area Personale</a> e clicca sul pulsante <strong>nascondi</strong> accanto alla voce <strong>Gestione Curriculum</strong>.<br/><br/>
            Il Curriculum resterà attivo per un mese. Ti sarà richiesto di confermarne la validità via email in modo da mantenerlo attivo di mese in mese.<br/><br/>
        </div>
    </div>
</div>