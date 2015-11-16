<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
						<h1 class="page-header">Cadastrar Projeto</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> <a
								href="${pageContext.request.contextPath}/">Dashboard</a></li>
							<li class="active"><i class="fa fa-edit"></i> Cadastrar
								Projeto</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<form role="form"
							action="${pageContext.request.contextPath}/projeto/cadastrar"
							method="post" class="registration-form">
							<div class="col-lg-6">
								<div class="form-group">
									<label>Nome</label> <input type="text" name="projeto.nome"
										placeholder="Nome" class="form-control" id="form-first-name">
								</div>
								<div class="form-group">
									<label>Cliente</label> <select name="projeto.cliente.id"
										class="form-control">
										<c:forEach var="usuario" items="${usuarios}">
											<option value="${usuario.id}">${usuario.pessoa.nome}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>Dias/semana a serem trabalhados no projeto</label> <select
										name="projeto.semana" class="form-control">
										<option value="1">1 dia</option>
										<option value="2">2 dias</option>
										<option value="3">3 dias</option>
										<option value="4">4 dias</option>
										<option value="5">5 dias</option>
										<option value="6">6 dias</option>
										<option value="7">7 dias</option>
									</select>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<label>In&iacute;cio</label> <input type="text" name="inicio"
										placeholder="10/10/2010" class="form-control" id="form-inicio">
								</div>
								<div class="form-group">
									<label>Descri&ccedil;&atilde;o</label> <input type="text"
										name="projeto.descricao" placeholder="Descri&ccedil;&atilde;o"
										class="form-control" id="form-descricao">
								</div>
								<div class="form-group">
									<label>Horas/dia a serem trabalhados no projeto</label> <select
										name="projeto.dia" class="form-control">
										<c:forEach var="i" begin="1" end="24">
											<option value="${i}">${i}hora(s)</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-lg-3 col-lg-offset-4">
								<button type="submit" class="col-lg-12 btn btn-success">Cadastrar</button>
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
