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
						<h1 class="page-header">Projeto ${projeto.nome}</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> <a
								href="${pageContext.request.contextPath}/">Dashboard</a></li>
							<li class="active"><i class="fa fa-table"></i> <a
								href="${pageContext.request.contextPath}/projeto/listar/colaborador">
									Listar Projetos </a></li>
							<li class="active"><i class="fa fa-gears"></i> Gerenciar
								Projeto</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<c:if test="${maior < 1}">
					<div class="jumbotron">
						<h1>Ooops!</h1>
						<p>Ainda n&atilde;o foram cadastradas tarefas para o projeto
							${projeto.nome}</p>
						<p>
							<a
								href="${pageContext.request.contextPath}/projeto/tarefa/${projeto.id}"
								class="btn btn-primary btn-lg" role="button">Cadastrar
								Tarefa »</a>
						</p>
					</div>
				</c:if>
				<c:if test="${maior >= 1}">
					<div class="row">
						<div class="col-lg-3 text-center">
							<div class="panel panel-default">
								<div class="panel-body">
									<strong>Aguardando Aceite </strong>
								</div>
							</div>
						</div>
						<div class="col-lg-3 text-center">
							<div class="panel panel-default">
								<div class="panel-body">
									<strong>Para Fazer</strong>
								</div>
							</div>
						</div>
						<div class="col-lg-3 text-center">
							<div class="panel panel-default">
								<div class="panel-body">
									<strong>Em Progresso</strong>
								</div>
							</div>
						</div>
						<div class="col-lg-3 text-center">
							<div class="panel panel-default">
								<div class="panel-body">
									<strong>Conclu&iacute;do</strong>
								</div>
							</div>
						</div>
					</div>
					<c:forEach var="i" begin="0" end="${maior}">
						<div class="row">
							<c:if test="${aceitacao[i] != null}">
								<div class="col-lg-3 text-center">
									<div class="panel panel-default">
										<div class="panel-body">
											<div class="col-lg-12">
												<strong><c:out value="${aceitacao[i].nome}"></c:out></strong>
											</div>
											<div class="col-lg-12">
												<a class="btn btn-success"
													href="${pageContext.request.contextPath}/projeto/tarefa/aceitar/${aceitacao[i].id}"><i
													class="fa fa-thumbs-up"></i></a> <a class="btn btn-danger"
													href="${pageContext.request.contextPath}/projeto/tarefa/rejeitar/${aceitacao[i].id}"><i
													class="fa fa-thumbs-down"></i></a>
											</div>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${aceitacao[i] == null}">
								<div class="col-lg-3 text-center"></div>
							</c:if>
							<c:if test="${fazer[i] != null}">
								<div class="col-lg-3 text-center">
									<div class="panel panel-default">
										<div class="panel-body">
											<div class="col-lg-12">
												<strong><c:out value="${fazer[i].nome}"></c:out></strong>
											</div>
											<div class="col-lg-12">
												<a class="btn btn-success"
													href="${pageContext.request.contextPath}/projeto/tarefa/visualizar/${fazer[i].id}"><i
													class="fa fa-eye"></i></a> <a class="btn btn-info"
													href="${pageContext.request.contextPath}/projeto/tarefa/colaborador/direita/${fazer[i].id}"><i
													class="fa fa-arrow-right"></i></a>
											</div>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${fazer[i] == null}">
								<div class="col-lg-3 text-center"></div>
							</c:if>
							<c:if test="${progresso[i] != null}">
								<div class="col-lg-3 text-center">
									<div class="panel panel-default">
										<div class="panel-body">
											<div class="col-lg-12">
												<strong><c:out value="${progresso[i].nome}"></c:out></strong>
											</div>
											<div class="col-lg-12">
												<a class="btn btn-info"
													href="${pageContext.request.contextPath}/projeto/tarefa/colaborador/esquerda/${progresso[i].id}"><i
													class="fa fa-arrow-left"></i></a> <a class="btn btn-success"
													href="${pageContext.request.contextPath}/projeto/tarefa/visualizar/${progresso[i].id}"><i
													class="fa fa-eye"></i></a> <a class="btn btn-info"
													href="${pageContext.request.contextPath}/projeto/tarefa/colaborador/direita/${progresso[i].id}"><i
													class="fa fa-arrow-right"></i></a>
											</div>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${progresso[i] == null}">
								<div class="col-lg-3 text-center"></div>
							</c:if>
							<c:if test="${concluido[i] != null}">
								<div class="col-lg-3 text-center">
									<div class="panel panel-default">
										<div class="panel-body">
											<div class="col-lg-12">
												<strong><c:out value="${concluido[i].nome}"></c:out></strong>
											</div>
											<div class="col-lg-12">
												<a class="btn btn-info"
													href="${pageContext.request.contextPath}/projeto/tarefa/colaborador/esquerda/${concluido[i].id}"><i
													class="fa fa-arrow-left"></i></a> <a class="btn btn-success"
													href="${pageContext.request.contextPath}/projeto/tarefa/visualizar/${concluido[i].id}"><i
													class="fa fa-eye"></i></a>
												<!-- <a class="btn btn-danger"
													href="${pageContext.request.contextPath}/projeto/tarefa/remover/${concluido[i].id}"><i
													class="fa fa-times"></i></a> -->
											</div>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${concluido[i] == null}">
								<div class="col-lg-3 text-center"></div>
							</c:if>
						</div>
					</c:forEach>
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
