[#if ! @.auth.isAuthenticated()]
<div id="item">
    <h3><strong>Login</strong></h3>
    <div id="signin-form" class="form">
        <form action="[@uri.do 'cf6a2248d8292274'/]" method="post">
            <p>
                <label for="uid">Nome utente</label>
                <input type="text" name="uid" value=""/>
            </p>
            <p>
                <label for="pass">Password</label>
                <input type="password" name="pass" value=""/>
            </p>
            <p>
                <span>Non sei registrato? <a href="[@uri.do 'c27ca611dd6d8c6d'/]">Registrati</a></span>
                <button id="signin-action" type="submit" name="signin" value="">Entra</button>
            </p>
        </form>
        <p></p>
    </div>
</div>
[/#if]