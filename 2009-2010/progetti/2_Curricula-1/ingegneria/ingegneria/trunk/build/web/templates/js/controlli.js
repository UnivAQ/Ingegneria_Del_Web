function verificaAnagrafica()
{
    /*if (document.form.radio.value!='accetta'){
alert("devi accettare il contratto per iscriverti");
return;
}*/

    //Controlla la presenza dei campi username e password
    if(document.anagrafica.nome.value.length<4)
    {
        alert("inserire un username di almeno 4 lettere");
        document.anagrafica.nome.focus();
        

        return false;
    }

    if (document.anagrafica.cognome.value.length < 2 )
    {
        alert("inserire un cognome di almeno 2 caratteri");
        document.anagrafica.cognome.focus();
        return false;
    }

    if(document.anagrafica.dataNascita.value.length<1)
    {
        alert("inserire data di nascita");
        document.anagrafica.dataNascita.focus();

        return false;
    }
    if(document.anagrafica.indirizzo.value.length<4)
    {
        alert("inserire un indirizzo valido");
        document.anagrafica.indirizzo.focus();

        return false;
    }
    if(document.anagrafica.provincia.value.length<1)
    {
        alert("inserire una provincia");
        document.anagrafica.provincia.focus();

        return false;
    }
    var stato=true;


    if(document.anagrafica.email.value.indexOf(" ")!=-1) {
        document.anagrafica.email.focus();
        stato=false;
    }

    var chiocciola=document.anagrafica.email.value.indexOf("@");
    if(chiocciola<2) {
        document.anagrafica.email.focus();
        stato=false;
    }

    var punto=document.anagrafica.email.value.indexOf(".", chiocciola);
    if(punto<chiocciola+3) {
        document.anagrafica.email.focus();
        stato=false;
    }

    var lung=document.anagrafica.email.value.length;
    if(lung-punto<3) {
        document.anagrafica.email.focus();
        stato=false;
    }

    if(stato==false){
        alert("E-mail non valida");
        document.anagrafica.email.focus();
        return false;
    }
    if(document.anagrafica.telefono.value.length<1)
    {
        alert("inserire numero di telefono");
        document.anagrafica.provincia.focus();

        return false;
    }

    document.anagrafica.action="inserimento#maincon";
    return true;
}

function verificaRegUtente()
{
    /*if (document.form.radio.value!='accetta'){
alert("devi accettare il contratto per iscriverti");
return;
}*/

    //Controlla la presenza dei campi username e password
    if(document.registrazione_utente.username.value.length<4)
    {
        alert("inserire un username di almeno 4 lettere");
        document.registrazione_utente.username.focus();

        return false;
    }

    if (document.registrazione_utente.password.value.length < 4 )
    {
        alert("inserire una password di almeno 4 caratteri");
        document.registrazione_utente.password.focus();
        return false;
    }



    var stato=true;


    if(document.registrazione_utente.email.value.indexOf(" ")!=-1) {
        document.registrazione_utente.email.focus();
        stato=false;
    }

    var chiocciola=document.registrazione_utente.email.value.indexOf("@");
    if(chiocciola<2) {
        document.registrazione_utente.email.focus();
        stato=false;
    }

    var punto=document.registrazione_utente.email.value.indexOf(".", chiocciola);
    if(punto<chiocciola+3) {
        document.registrazione_utente.email.focus();
        stato=false;
    }

    var lung=document.registrazione_utente.email.value.length;
    if(lung-punto<3) {
        document.registrazione_utente.email.focus();
        stato=false;
    }

    if(stato==false){
        alert("E-mail non valida");
        document.registrazione_utente.email.focus();
        return false;
    }

    document.registrazione_utente.action="reg";
    return true;
}


function verificaRegAzienda()
{
    /*if (document.form.radio.value!='accetta'){
alert("devi accettare il contratto per iscriverti");
return;
}*/

    //Controlla la presenza dei campi username e password
    if(document.registrazione_azienda.username.value.length<4)
    {
        alert("inserire un username di almeno 4 lettere");
        document.registrazione_azienda.username.focus();

        return false;
    }

    if (document.registrazione_azienda.password.value.length < 4 )
    {
        alert("inserire una password di almeno 4 caratteri");
        document.registrazione_azienda.password.focus();
        return false;
    }



    var stato=true;


    if(document.registrazione_azienda.email.value.indexOf(" ")!=-1) {
        document.registrazione_azienda.email.focus();
        stato=false;
    }

    var chiocciola=document.registrazione_azienda.email.value.indexOf("@");
    if(chiocciola<2) {
        document.registrazione_azienda.email.focus();
        stato=false;
    }

    var punto=document.registrazione_azienda.email.value.indexOf(".", chiocciola);
    if(punto<chiocciola+3) {
        document.registrazione_azienda.email.focus();
        stato=false;
    }

    var lung=document.registrazione_azienda.email.value.length;
    if(lung-punto<3) {
        document.registrazione_azienda.email.focus();
        stato=false;
    }

    if(stato==false){
        alert("E-mail non valida");
        document.registrazione_azienda.email.focus();
        return false;
    }
    if(document.registrazione_azienda.ragione.value.length<4)
    {
        alert("inserire una ragione sociale");
        document.registrazione_azienda.ragione.focus();

        return false;
    }

    if(document.registrazione_azienda.piva.value.length<4)
    {
        alert("inserire una partita iva");
        document.registrazione_azienda.piva.focus();

        return false;
    }

    document.registrazione_azienda.action="reg";
    return true;
}



