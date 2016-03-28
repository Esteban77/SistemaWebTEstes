<%@ page contentType="text/html" pageEncoding ="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Atividade 03 Servlet</title>
	</head>
	<body>
		<form action="/Testes/atividade03" method="get">
			Nome: 
			<br>
				<input type="text" name="nome">
			<br>
			Sobrenome:
			<br>
				<input type="text" name="sobrenome">
			<br><br>
			<input type="submit" value="Enviar Formulário Get">		
		</form>
		<form action="/Testes/atividade03" method="post">
			Nome: 
			<br>
				<input type="text" name="nome">
			<br>
			Sobrenome:
			<br>
				<input type="text" name="sobrenome">
			<br><br>
			<input type="submit" value="Enviar Formulário Post">		
		</form>

	</body>
</html>