<div id="colonnasxmiddle">
    <div id="item">
        <h3><strong>Registrazione</strong></h3>
        <div id="signin-form" class="form">
            <form action="[@uri.do 'c27ca611dd6d8c6d'/]" method="post">
                <p>
                    <label for="uid">Nome Utente</label>
                    <input type="text" name="uid" value=""/>
                </p>
                <p>
                    <label for="pass">Password</label>
                    <input type="password" name="pass" value=""/>
                </p>
                <p>
                    <label for="pass2">Conferma</label>
                    <input type="password" name="pass2" value=""/>
                </p>
                <p>
                    <label for="email">Email</label>
                    <input type="text" name="email" value=""/>
                </p>
                <p>
                    <label for="type">Tipo Utente</label>
                    <select id="account-type-select" name="type">
                        <option value="candidate">Candidato</option>
                        <option value="company">Azienda</option>
                    </select>
                </p>
                <p id="company-piva-box">
                    <label for="email">Partita IVA</label>
                    <input type="text" name="piva" value=""/>
                </p>
                <p>
                <h2><button id="bottone" type="input" name="signup" value="">Registrati</button></h2>
                </p>
            </form>
            [#if error != ""]
            [#if error == "user-length"]
            <h2>Il Nome Utente non può essere vuoto.</h2>
            [/#if]
            [#if error == "pass-different"]
            <h2>La Password non è stata confermata.</h2>
            [/#if]
            [#if error == "pass-length"]
            <h2>La Password non puo' essere vuota.</h2>
            [/#if]
            [#if error == "email-length"]
            <h2>L'Email non puo' essere vuoto.</h2>
            [/#if]
            [#if error == "email-invalid"]
            <h2>L'Email non è valida.</h2>
            [/#if]
            [#if error == "user-exists"]
            <h2>Il Nome Utente inserito è già esistente.</h2>
            [/#if]
            [/#if]
        </div>
    </div>
    <script type="text/javascript">//<![CDATA[
        $("#company-piva-box").hide();
        $("#account-type-select").bind("change", function () {
            $("option:selected", this).each(function () {
                if ($(this).val() == "company") {
                    $("#company-piva-box").show();
                } else {
                    $("#company-piva-box").hide();
                }
            });
        });
    //]]></script>
</div>

<div id="colonnadx">
    <div id="item">
        <h3><strong>Help Registrazione</strong></h3>
        <div class="testo">
            <p>
            </p>
            <p>Tramite questa form ti puoi registrare al sito.</p>
            <br>
            <p>I dati che devi inserire sono:</p>
            <br>
            <p>- Nome Utente che userai per effettuare il login</p>
            <br>
            <p>- Password con la sua Conferma</p>
            <br>
            <p>- Indirizzo Email</p>
            <br>
            <p>- Tipo di utente: Candidato o Azienda</p>
            <br>
        </div>
    </div>
</div>