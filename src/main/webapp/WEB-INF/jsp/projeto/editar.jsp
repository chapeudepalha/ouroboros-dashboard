<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
						<h1 class="page-header">Editar Projeto</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> <a
								href="${pageContext.request.contextPath}/">Dashboard</a></li>
							<li class="active"><i class="fa fa-table"></i> <a
								href="${pageContext.request.contextPath}/projeto/listar">
									Listar Projetos </a></li>
							<li class="active"><i class="fa fa-edit"></i> Editar Projeto</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<form role="form"
							action="${pageContext.request.contextPath}/projeto/editar"
							method="post" class="registration-form">
							<input type="hidden" value="${projeto.id}" name="projeto.id" />
							<div class="col-lg-6">
								<div class="form-group">
									<label>Nome</label> <input type="text" value="${projeto.nome}"
										name="projeto.nome" placeholder="Nome" class="form-control"
										id="form-first-name">
								</div>
								<div class="form-group">
									<label>Cliente</label> <select name="projeto.cliente.id"
										class="form-control">
										<option value="${projeto.cliente.id}" selected="selected">${projeto.cliente.pessoa.nome}</option>
										<c:forEach var="usuario" items="${usuarios}">
											<option value="${usuario.id}">${usuario.pessoa.nome}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>Dias/semana a serem trabalhados no projeto</label><br />
									<c:forEach var="semana" items="${projeto.semana}">
										<label class="checkbox-inline"> <input type="checkbox"
											name="semana" value="${semana}" checked="checked">
											${semana}
										</label>
									</c:forEach>
									<label class="checkbox-inline"> <input type="checkbox"
										name="semana" value="1"> Domingo
									</label> <label class="checkbox-inline"> <input type="checkbox"
										name="semana" value="2">Segunda
									</label> <label class="checkbox-inline"> <input type="checkbox"
										name="semana" value="3">Ter&ccedil;a
									</label> <label class="checkbox-inline"> <input type="checkbox"
										name="semana" value="4">Quarta
									</label> <label class="checkbox-inline"> <input type="checkbox"
										name="semana" value="5">Quinta
									</label> <label class="checkbox-inline"> <input type="checkbox"
										name="semana" value="6">Sexta
									</label> <label class="checkbox-inline"> <input type="checkbox"
										name="semana" value="7">S&aacute;bado
									</label>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<label>In&iacute;cio</label> <input type="text" name="inicio"
										value='<fmt:formatDate value="${projeto.inicio.time}"
												pattern="dd/MM/yyyy" />'
										placeholder="10/10/2010" class="form-control" id="form-inicio">
								</div>
								<div class="form-group">
									<label>Descri&ccedil;&atilde;o</label> <input type="text"
										value="${projeto.descricao}" name="projeto.descricao"
										placeholder="Descri&ccedil;&atilde;o" class="form-control"
										id="form-descricao">
								</div>
								<div class="form-group">
									<label>Horas/dia a serem trabalhados no projeto</label>
									<div class="col-lg-6 form-group">
										<select name="comeco" class="form-control">
											<option value='${fn:replace(projeto.comeco, ".", ",")}' selected="selected">${comeco}</option>
											<c:forEach begin="0" end="23" var="i">
												<option value="${i}">${i}:00</option>
												<option value="${i},5">${i}:30</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-lg-6 form-group">
										<select name="fim" class="form-control">
											<option value='${fn:replace(projeto.fim, ".", ",")}' selected="selected">${fim}</option>
											<c:forEach begin="0" end="23" var="i">
												<option value="${i}">${i}:00</option>
												<option value="${i},5">${i}:30</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-lg-3 col-lg-offset-4">
								<button type="submit" class="col-lg-12 btn btn-success">Editar</button>
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
