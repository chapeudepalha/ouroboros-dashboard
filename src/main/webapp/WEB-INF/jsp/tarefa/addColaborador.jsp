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
						<h1 class="page-header">Adicionar Colaborador a Tarefa
							${tarefa.nome}</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> <a
								href="${pageContext.request.contextPath}/">Dashboard</a></li>
							<li class="active"><i class="fa fa-table"></i> <a
								href="${pageContext.request.contextPath}/projeto/listar">
									Listar Projetos </a></li>
							<li class="active"><i class="fa fa-gears"></i> <a
								href="${pageContext.request.contextPath}/projeto/gerenciar/${tarefa.projeto.id}">
									Gerenciar Projeto</a></li>
							<li class="active"><i class="fa fa-user"></i> Colaborador</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<form role="form"
							action="${pageContext.request.contextPath}/tarefa/colaborador"
							method="post" class="registration-form">
							<input type="hidden" name="tarefa.id" value="${tarefa.id}">
							<div class="row">
								<div class="col-lg-12 form-group">
									<label>Colaboradores com compet&ecirc;ncia
										${tarefa.competencia.nome}</label> <select name="usuario.id"
										class="form-control">
										<c:forEach var="colaborador" items="${colaboradores}">
											<option value="${colaborador.id}">${colaborador.usuario}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group col-lg-4 col-lg-offset-4">
									<button type="submit" class="col-lg-12 btn btn-success">Adicionar</button>
								</div>
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
