<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Collection"%>
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
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Listar Administradores</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> <a
								href="${pageContext.request.contextPath}/">Dashboard</a></li>
							<li class="active"><i class="fa fa-table"></i> Listar
								Administradores</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<c:if test="${usuarios == null}">
							<div class="jumbotron">
								<h1>Ooops!</h1>
								<p>Não fomos capazes de encontrar nenhum administrador
									cadastrasdo. Que tal cadastrar um novo?</p>
								<p>
									<a href="${pageContext.request.contextPath}/admin/novo"
										class="btn btn-primary btn-lg" role="button">Cadastrar
										Administrador »</a>
								</p>
							</div>
						</c:if>
						<c:if test="${usuarios != null}">
							<div class="table-responsive">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>Nome</th>
											<th>Visits</th>
											<th>% New Visits</th>
											<th>Revenue</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>/index.html</td>
											<td>1265</td>
											<td>32.3%</td>
											<td>$321.33</td>
										</tr>
										<tr>
											<td>/about.html</td>
											<td>261</td>
											<td>33.3%</td>
											<td>$234.12</td>
										</tr>
										<tr>
											<td>/sales.html</td>
											<td>665</td>
											<td>21.3%</td>
											<td>$16.34</td>
										</tr>
										<tr>
											<td>/blog.html</td>
											<td>9516</td>
											<td>89.3%</td>
											<td>$1644.43</td>
										</tr>
										<tr>
											<td>/404.html</td>
											<td>23</td>
											<td>34.3%</td>
											<td>$23.52</td>
										</tr>
										<tr>
											<td>/services.html</td>
											<td>421</td>
											<td>60.3%</td>
											<td>$724.32</td>
										</tr>
										<tr>
											<td>/blog/post.html</td>
											<td>1233</td>
											<td>93.2%</td>
											<td>$126.34</td>
										</tr>
									</tbody>
								</table>
							</div>
						</c:if>
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
