<div id="colonnasx">
    <div id="submenu">
        <ul>
            [#if @.auth.isCompany()]
            <li><a href="[@uri.do '74cde451fd87d769'/]" class="cv-search">Ricerca Curriculum</a></li>
            <li><a href="[@uri.do '90ee875f41c4df3b'/]" class="oneshot"><span>Ricerca </span> <img src="${@.context}/css/base/img/oneshot_button.png"/></a></li>
            [#elseif @.auth.isCandidate()]
            <li><a href="[@uri.do '74cde451fd87d769'/]" class="back-home"><img src="${@.context}/css/base/img/home_icon.png"/><span>Torna alla home</span></a></li>
            [#else]
            <li><a href="${@.context}/" class="back-home"><img src="${@.context}/css/base/img/home_icon.png"/><span>Torna alla home</span></a></li>
            [/#if]
        </ul>
        <div class="clear"></div>
    </div>
    <div class="area1">
        <div class="curriculumvitae">
            <div id="cv-view-anagrafic">
                <span class="cv-anagrafic-int">Dati personali</span>
                [#if @.auth.isTrustedCompany()]
                <ul>
                    <li><strong>Nome: ${cv.name}</strong></li>
                    <li><strong>Cognome: ${cv.surname}</strong></li>
                    <li><strong>Sesso: ${cv.gender?capitalize}</strong></li>
                    <li><strong>Data di nascita: ${cv.birthday}</strong></li>
                    <li><strong>eMail: ${cv.email}</strong></li>
                    <li><strong>Provincia: ${cv.province?capitalize}</strong></li>
                    <li><strong>Città: ${cv.city?capitalize}</strong></li>
                    <li><strong>Livello di Studio: ${cv.studies?capitalize}</strong></li>
                </ul>
                [#else]
                <ul>
                    <li><strong>Sesso: ${cv.gender}</strong></li>
                    <li><strong>Data di nascita: ${cv.birthday}</strong></li>
                </ul>
                [/#if]
            </div>
            <div id="cv-view-prefs">
                <span class="cv-experiences-int">Preferenze di lavoro</span>
                <table>
                    <thead>
                        <tr>
                            <th><strong>Partime</strong></th>
                            <th><strong>Fulltime</strong></th>
                            <th><strong>Turni</strong></th>
                            <th><strong>Progetto</strong></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>[#if cv.partime == 1]Si[#else]No[/#if]</td>
                            <td>[#if cv.fulltime == 1]Si[#else]No[/#if]</td>
                            <td>[#if cv.turni == 1]Si[#else]No[/#if]</td>
                            <td>[#if cv.project == 1]Si[#else]No[/#if]</td>
                        </tr>
                    </tbody>
                </table>
                <div class="clear">&nbsp;</div>
            </div>
            <div id="cv-view-experiences">
                <span class="cv-experiences-int">Esperienze di lavoro</span>
                <table>
                    <thead>
                        <tr>
                            <th><strong>Data inizio</strong></th>
                            <th><strong>Data fine</strong></th>
                            <th><strong>Ruolo</strong></th>
                            <th><strong>Retribuzione</strong></th>
                        </tr>
                    </thead>
                    <tbody>
                        [#list cv.experiences as experience]
                        <tr>
                            <td>${experience.inizio}</td>
                            <td>${experience.fine}</td>
                            <td>${experience.ruolo?capitalize}</td>
                            <td>&euro; ${experience.retribuzione}</td>
                        </tr>
                        [/#list]
                    </tbody>
                </table>
                <div class="clear">&nbsp;</div>
            </div>

            <div id="cv-view-skills">
                <span class="cv-skills-int">Conoscenze professionali</span>
                <table>
                    <thead>
                        <tr>
                            <th><strong>Settore</strong></th>
                            <th><strong>Tipo</strong></th>
                            <th><strong>Livello</strong></th>
                        </tr>
                    </thead>
                    <tbody>
                        [#list cv.skills as skill]
                        <tr>
                            <td>${skill.settore?capitalize}</td>
                            <td>${skill.tipo?capitalize}</td>
                            <td>${skill.livello?capitalize}</td>
                        </tr>
                        [/#list]
                    </tbody>
                </table>
            </div>
            <div class="clear">&nbsp;</div>
        </div>
    </div>
</div>

<div id="colonnadx">
    <div class="faq-button">
        <a href="${@.context}/faq/"><span>Curricul@ scopri come funziona </span><img src="${@.context}/css/base/img/faq_icon.png"/></a>
    </div>
    <div id="item">
        [#if @.auth.isCompany()]
        <h3><strong>Contatta subito questo utente!</strong></h3>
        <div class="testo">
            <a href="${@.context}/company/notify/${user_id}/"><img id="send-notify" src="${@.context}/css/base/img/mail-icon.png"/></a>
            Invia una notifica a questo utente
        </div>
        <div class="clear">&nbsp;</div>
        [#else]
        <h3>Riepilogo Curriculum</h3>
        <div class="testo">
            <p>Qui trovi riepilogate le informazioni del curriculim.</p>
            <p>
                Ricorda, per poter visualizzare tutti i campi devi essere registrato al sito
                ed essere un'azienda certificata.
            </p>
        </div>
        [/#if]
    </div>
</div>