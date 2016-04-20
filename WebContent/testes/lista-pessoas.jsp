<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de pessoas</title>
</head>
<body>

	<table>
		<c:forEach var="pessoa" items="${Lista}">

			<tr>
				<td>${pessoa.nome}</td>
				<td>${pessoa.sobrenome}</td>
				<td>${pessoa.endereco.rua}</td>
				<td>${pessoa.endereco.bairro}</td>
				<td>${pessoa.endereco.numero}</td>
				<td><a href="mvc?logica=RemovePessoaLogic&id=${pessoa.id}">Remover</a></td>
				<td><a
					href="mvc?logica=AdicionaPessoaLogic&id=${pessoa.id}&acao=alter">Alterar</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="mvc?logica=AdicionaPessoaLogic&acao=adic">Adicionar</a>
</body>
</html>