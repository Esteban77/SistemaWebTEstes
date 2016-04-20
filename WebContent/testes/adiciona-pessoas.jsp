<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/Testes/adiciona" method="post">
			<h1>Dados Cadastrais</h1>
			<fieldset>		
				<label>Nome:</label><input type="text" name="nome" id="nome" required><br>
				<label>Sobrenome:</label><input type="text" name="sobrenome" required>	
			</fieldset>
			<fieldset>
				<label>Rua:</label><input type="text" name="rua">
				<label>Bairro:</label><input type="text" name="bairro">
				<label>Número:</label><input type="text" name="num">
				<input type="text" name="id" value="${id}" hidden>
			<input type="submit" value="Adicionar">
			</fieldset>			
			<a href="/Testes/mvc?logica=ListaPessoasLogic">Listar Pessoas</a>
	</form>
</body>
</html>