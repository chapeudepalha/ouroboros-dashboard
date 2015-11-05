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
<title>Ouroboros Dashboard</title>

<!-- CSS -->
<link rel="stylesheet"
	href="<c:url value="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500"/>">
<link rel="stylesheet"
	href="<c:url value="assets/bootstrap/css/bootstrap.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="assets/font-awesome/css/font-awesome.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="assets/css/form-elements.css"/>">
<link rel="stylesheet" href="<c:url value="assets/css/style.css"/>">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">

</head>

<body>

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>Ouroboros</strong> <small>1.0</small>
						</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>Ouroboros Dashboard</h3>
								<p>Gestão Inteligente</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-lock"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form role="form"
								action="${pageContext.request.contextPath}/login" method="post"
								class="login-form">
								<div class="form-group">
									<label>Usuário</label> <input type="text"
										name="usuario.usuario" placeholder="Nome de Usuário"
										class="form-username form-control" id="form-username">
								</div>
								<div class="form-group">
									<label>Senha</label> <input type="password"
										name="usuario.senha" placeholder="Senha"
										class="form-password form-control" id="form-password">
								</div>
								<button type="submit" class="btn btn-success">Entrar</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- Javascript -->
	<script src="<c:url value="assets/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="assets/bootstrap/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="assets/js/jquery.backstretch.min.js"/>"></script>
	<script src="<c:url value="assets/js/scripts.js"/>"></script>

	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

</body>

</html>