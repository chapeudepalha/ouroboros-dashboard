<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.Collection"%>
<!DOCTYPE html>
<html lang="en">

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
						<h1 class="page-header">Gerenciar compet&ecirc;ncias do
							projeto ${projeto.nome}</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> <a
								href="${pageContext.request.contextPath}/">Dashboard</a></li>
							<li class="active"><i class="fa fa-th-list"></i> Nova
								Compet&ecirc;ncia</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<c:if
							test="${(competencias == null) || (fn:length(competencias) < 1)}">
							<div class="jumbotron">
								<h1>Ooops!</h1>
								<p>Ainda n&atilde;o foram cadastradas compet&ecirc;ncias
									para o projeto ${projeto.nome}</p>
							</div>
						</c:if>
						<c:if
							test="${(competencias != null) && (fn:length(competencias) > 0)}">
							<div class="table-responsive">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>Compet&ecirc;ncia</th>
											<th>Op&ccedil;&otilde;es</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="competencia" items="${competencias}">
											<tr>
												<td>${competencia.nome}</td>
												<td><a
													href="${pageContext.request.contextPath}/projeto/competencia/editar/${competencia.id}"
													class="btn btn-success btn-sm" role="button"><i
														class="fa fa-pencil"></i> Editar</a> <a
													href="${pageContext.request.contextPath}/projeto/competencia/remover/${competencia.id}"
													class="btn btn-danger btn-sm" role="button"><i
														class="fa fa-times"></i> Remover</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:if>
					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<form role="form"
							action="${pageContext.request.contextPath}/projeto/competencia/add"
							method="post" class="registration-form">
							<input type="hidden" value="${projeto.id}"
								name="competencia.projeto.id" />
							<div class="col-lg-12">
								<div class="col-lg-9 form-group">
									<label>Compet&ecirc;ncia</label> <input type="text"
										name="competencia.nome" placeholder="Nome da Compet�ncia"
										class="form-control" id="form-competencia">
								</div>
								<div class="form-group">
									<br />
									<button type="submit" class="col-lg-3 btn btn-success">Adicionar
										Compet&ecirc;ncia</button>
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