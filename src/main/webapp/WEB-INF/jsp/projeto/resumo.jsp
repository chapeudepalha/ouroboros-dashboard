<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/assets/css/sb-admin.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link
	href="${pageContext.request.contextPath}/assets/css/plugins/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath}/inside/css/resumo.css"
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
						<h1 class="page-header">
							Resumo: <strong>Projeto ${projeto.nome}</strong>
						</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> <a
								href="${pageContext.request.contextPath}/">Dashboard</a></li>
							<li class="active"><i class="fa fa-table"></i> <a
								href="${pageContext.request.contextPath}/projeto/listar">
									Listar Projetos </a></li>
							<li class="active"><i class="fa fa-gears"></i> <a
								href="${pageContext.request.contextPath}/projeto/gerenciar/${projeto.id}">
									Gerenciar Projeto</a></li>
							<li class="active"><i class="fa fa-pie-chart"></i> Resumo</li>
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
										<i class="fa fa-users fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">
										${ociosos}
										</div>
										<div>Colaborador(es) Ocioso(s)</div>
									</div>
								</div>
							</div>
							<a
								href="${pageContext.request.contextPath}/projeto/gerenciar/${projeto.id}">
								<div class="panel-footer">
									<span class="pull-left">Gerenciar Projeto</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
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
							<a
								href="${pageContext.request.contextPath}/projeto/gerenciar/${projeto.id}">
								<div class="panel-footer">
									<span class="pull-left">Gerenciar Projeto</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
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
							<a
								href="${pageContext.request.contextPath}/projeto/gerenciar/${projeto.id}">
								<div class="panel-footer">
									<span class="pull-left">Gerenciar Projeto</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
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
							<a
								href="${pageContext.request.contextPath}/projeto/gerenciar/${projeto.id}">
								<div class="panel-footer">
									<span class="pull-left">Gerenciar Projeto</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
				</div>
				<!-- /.row -->
				<c:if test="${total >= 1}">
					<div class="row">
						<div class="col-lg-4">
							<div class="panel panel-green">
								<div class="panel-heading">
									<h3 class="panel-title">
										<i class="fa fa-long-arrow-right"></i> Resumo: Progresso
									</h3>
								</div>
								<div class="panel-body">
									<div class="flot-chart">
										<div class="flot-chart-content ouroboros-pie-chart"
											id="resumo-progresso"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">
										<i class="fa fa-long-arrow-right"></i> Resumo: Tarefas
									</h3>
								</div>
								<div class="panel-body">
									<div class="flot-chart">
										<div class="flot-chart-content ouroboros-pie-chart"
											id="resumo-tarefas"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="panel panel-red">
								<div class="panel-heading">
									<h3 class="panel-title">
										<i class="fa fa-long-arrow-right"></i> Resumo: Prazo
									</h3>
								</div>
								<div class="panel-body">
									<div class="flot-chart">
										<div class="flot-chart-content ouroboros-pie-chart"
											id="resumo-prazo"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /.row -->
				</c:if>
				<c:if test="${total <= 0}">
					<div class="jumbotron">
						<h1>Ooops!</h1>
						<p>Não fomos capazes de encontrar nenhuma tarefa cadastrada,
							logo, n&atilde;o conseguimos montar um resumo. Que tal cadastrar
							uma nova tarefa?</p>
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
	<script src="${pageContext.request.contextPath}/inside/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/inside/js/bootstrap.min.js"></script>

	<!-- Flot Charts JavaScript -->
	<!--[if lte IE 8]><script src="js/excanvas.min.js"></script><![endif]-->
	<script
		src="${pageContext.request.contextPath}/inside/js/plugins/flot/jquery.flot.js"></script>
	<script
		src="${pageContext.request.contextPath}/inside/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/inside/js/plugins/flot/jquery.flot.resize.js"></script>
	<script
		src="${pageContext.request.contextPath}/inside/js/plugins/flot/jquery.flot.pie.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			console.log("document ready");
			// Flot Pie Chart with Tooltips
			$(function() {

				var tarefa = [ {
					label : "Para Fazer",
					data : ${fazer}
				}, {
					label : "Em Progresso",
					data : ${progresso}
				}, {
					label : "Concluído",
					data : ${concluido}
				} ];
				
				var prazo = [ {
					label : "Em Dia",
					data : ${dia}
				}, {
					label : "Atrasado",
					data : ${atrasados}
				} ];

				var progresso = [ {
					label : "Concluído",
					data : ${concluido}
				}, {
					label : "Em andamento",
					data : ${restantes}
				} ];
				
				var tar = $.plot($("#resumo-tarefas"), tarefa, {
					series : {
						pie : {
							show : true
						}
					},
					grid : {
						hoverable : true
					},
					tooltip : true,
					tooltipOpts : {
						content : "%p.0%, %s", // show percentages, rounding to 2 decimal places
						shifts : {
							x : 20,
							y : 0
						},
						defaultTheme : false
					}
				});
				
				var pro = $.plot($("#resumo-progresso"), progresso, {
					series : {
						pie : {
							show : true
						}
					},
					grid : {
						hoverable : true
					},
					tooltip : true,
					tooltipOpts : {
						content : "%p.0%, %s", // show percentages, rounding to 2 decimal places
						shifts : {
							x : 20,
							y : 0
						},
						defaultTheme : false
					}
				});
				
				var pra = $.plot($("#resumo-prazo"), prazo, {
					series : {
						pie : {
							show : true
						}
					},
					grid : {
						hoverable : true
					},
					tooltip : true,
					tooltipOpts : {
						content : "%p.0%, %s", // show percentages, rounding to 2 decimal places
						shifts : {
							x : 20,
							y : 0
						},
						defaultTheme : false
					}
				});

			});
		});
	</script>
</body>

</html>
