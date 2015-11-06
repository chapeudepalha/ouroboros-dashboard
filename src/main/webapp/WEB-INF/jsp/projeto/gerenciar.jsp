<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
						<h1 class="page-header">Projeto ${projeto.nome}</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> <a
								href="${pageContext.request.contextPath}/">Dashboard</a></li>
							<li class="active"><i class="fa fa-table"></i> <a
								href="${pageContext.request.contextPath}/projeto/listar">
									Listar Projetos </a></li>
							<li class="active"><i class="fa fa-gears"></i> Gerenciar
								Projeto</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-calendar fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge"><fmt:formatDate value="${projeto.entrega.time}"
														pattern="dd/MM/yy" /></div>
										<div>Entrega</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-pie-chart fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">${concluidos}%</div>
										<div>Concluido</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-pie-chart fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">${restantes}%</div>
										<div>Restantes</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-pie-chart fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">${atrasados}%</div>
										<div>Atrasadas</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-6">
						<a
							href="${pageContext.request.contextPath}/projeto/colaboradores/${projeto.id}"
							class="btn btn-info btn-lg col-lg-12" role="button"><i
							class="fa fa-user"></i> Gerenciar Colaboradores</a>
					</div>
					<div class="col-lg-6">
						<a
							href="${pageContext.request.contextPath}/projeto/tarefa/${projeto.id}"
							class="btn btn-success btn-lg col-lg-12" role="button"><i
							class="fa fa-tasks"></i> Adicionar Tarefa</a>
					</div>
				</div>
				<!-- /.row -->
				<br />
				<c:if test="${total >= 1}">
					<div class="row">
						<div class="col-lg-4 text-center">
							<div class="panel panel-default">
								<div class="panel-body">Para Fazer</div>
							</div>
						</div>
						<div class="col-lg-4 text-center">
							<div class="panel panel-default">
								<div class="panel-body">Em Progresso</div>
							</div>
						</div>
						<div class="col-lg-4 text-center">
							<div class="panel panel-default">
								<div class="panel-body">Conclu&iacute;do</div>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${total <= 0}">
					<div class="jumbotron">
						<h1>Ooops!</h1>
						<p>Não fomos capazes de encontrar nenhuma tarefa cadastrasdo.
							Que tal cadastrar uma nova tarefa?</p>
						<p>
							<a
								href="${pageContext.request.contextPath}/projeto/tarefa/${projeto.id}"
								class="btn btn-primary btn-lg" role="button">Cadastrar
								Tarefa »</a>
						</p>
					</div>
				</c:if>
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
