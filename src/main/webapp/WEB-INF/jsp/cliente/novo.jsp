<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Collection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Ouroboros Dashboard</title>

<!-- Bootstrap Core CSS -->
<link href="<c:url value="/assets/css/bootstrap.min.css"/>"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value="/assets/css/sb-admin.css"/>" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="<c:url value="/assets/css/plugins/morris.css"/>"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<c:url value="/assets/font-awesome/css/font-awesome.min.css"/>"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<div id="wrapper">

		<jsp:include page="../menu/menu.jsp"></jsp:include>

		<div id="page-wrapper">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Cadastrar Cliente</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> <a
								href="${pageContext.request.contextPath}/">Dashboard</a></li>
							<li class="active"><i class="fa fa-edit"></i> Cadastrar
								Cliente</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<form role="form"
							action="${pageContext.request.contextPath}/cliente/cadastrar"
							method="post" class="registration-form">
							<div class="col-lg-6">
								<div class="form-group">
									<label>Nome</label> <input type="text"
										name="usuario.pessoa.nome" placeholder="Nome Completo"
										class="form-control" id="form-first-name">
								</div>
								<div class="form-group">
									<label>Telefone</label> <input type="text"
										name="usuario.pessoa.telefone" placeholder="Telefone"
										class="form-control" id="form-telefone">
								</div>
								<div class="form-group">
									<label>Email</label> <input type="text"
										name="usuario.pessoa.email" placeholder="Email"
										class="form-control" id="form-email">
								</div>
								<div class="form-group">
									<label>RG</label> <input type="text" name="usuario.pessoa.rg"
										placeholder="RG" class="form-control" id="form-rg">
								</div>
								<div class="form-group">
									<label>CPF</label> <input type="text" name="usuario.pessoa.cpf"
										placeholder="CPF" class="form-control" id="form-cpf">
								</div>
								<div class="form-group">
									<label>Usuario</label> <input type="text"
										name="usuario.usuario" placeholder="Usuario"
										class="form-control" id="form-usuario">
								</div>
								<div class="col-lg-6 form-group">
									<label>Senha</label> <input type="password"
										name="usuario.senha" placeholder="Senha" class="form-control"
										id="form-senha">
								</div>
								<div class="col-lg-6 form-group">
									<label>Repita a Senha</label> <input type="password"
										name="rsenha" placeholder="Repita a Senha"
										class="form-control" id="form-repetir-senha">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<label>Rua</label> <input type="text"
										name="usuario.pessoa.endereco.logradouro" placeholder="Rua"
										class="form-control" id="form-rua">
								</div>
								<div class="form-group">
									<label>Numero</label> <input type="text"
										name="usuario.pessoa.endereco.numero" placeholder="Numero"
										class="form-control" id="form-rua">
								</div>
								<div class="form-group">
									<label>Complemento</label> <input type="text"
										name="usuario.pessoa.endereco.complemento"
										placeholder="Complemento" class="form-control" id="form-rua">
								</div>
								<div class="form-group">
									<label>CEP</label> <input type="text"
										name="usuario.pessoa.endereco.cep" placeholder="CEP"
										class="form-control" id="form-cep">
								</div>
								<div class="form-group">
									<label>Bairro</label> <input type="text"
										name="usuario.pessoa.endereco.bairro" placeholder="Bairro"
										class="form-control" id="form-bairro">
								</div>
								<div class="form-group">
									<label>Cidade</label> <input type="text"
										name="usuario.pessoa.endereco.cidade" placeholder="Cidade"
										class="form-control" id="form-cidade">
								</div>
								<div class="form-group">
									<label>Estado</label> <input type="text"
										name="usuario.pessoa.endereco.estado" placeholder="Estado"
										class="form-control" id="form-estado">
								</div>
							</div>
							<div class="col-lg-3 col-lg-offset-5">
								<button type="submit" class="btn btn-success">Cadastrar</button>
							</div>
						</form>
					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="<c:url value="/inside/js/jquery.js"/>"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/inside/js/bootstrap.min.js"/>"></script>

	<!-- Morris Charts JavaScript -->
	<script src="<c:url value="/inside/js/plugins/morris/raphael.min.js"/>"></script>
	<script src="<c:url value="/inside/js/plugins/morris/morris.min.js"/>"></script>
	<script src="<c:url value="/inside/js/plugins/morris/morris-data.js"/>"></script>

</body>

</html>
