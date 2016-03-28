function redireciona(operacao){  
	//Seta o valor do atributo no input "op"
	document.getElementById("op").value = operacao;
	
	//Dispara o submit
	document.formOperacoes.submit();
} 