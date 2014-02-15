function InitFormState()
{
	if(document.getElementById)
	{
		var o = document.getElementById('form');
		if ( Get_Cookie( 'showform' ) )
			o.style.display = 'block';
		else
			o.style.display = 'none';
	}
	
}

function FormState()
{
	if(document.getElementById)
	{
		var o = document.getElementById('form');
		if(o.style.display == 'block')
			Delete_Cookie('showform', '/', '');
		else
			Set_Cookie( 'showform', '1', 365, '/', '', '' );
		showHideStyle('form');
	}

}