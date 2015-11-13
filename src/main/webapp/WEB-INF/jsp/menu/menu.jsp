<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Collection"%>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/inicio">Ouroboros <small>1.0</small></a>
	</div>
	<!-- Top Menu Items -->
	<ul class="nav navbar-right top-nav">
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"><i class="fa fa-user"></i>
				${sessao.usuario.usuario} <b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li><a href="${pageContext.request.contextPath}/logout"><i
						class="fa fa-fw fa-power-off"></i> Sair</a></li>
			</ul></li>
	</ul>
	<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav side-nav">
			<li><a href="${pageContext.request.contextPath}/inicio"><i
					class="fa fa-fw fa-dashboard"></i> Dashboard</a></li>
			<c:if test="${sessao.usuario.tipoUsuario == 'ROOT'}">
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#adm"><i class="fa fa-fw fa-user"></i>
						Administrador <i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="adm" class="collapse">
						<li><a href="${pageContext.request.contextPath}/admin/novo">Novo</a></li>
					</ul></li>
			</c:if>
			<c:if test="${sessao.usuario.tipoUsuario == 'ADMINISTRADOR'}">
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#admin"><i class="fa fa-fw fa-user"></i>
						Administrador <i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="admin" class="collapse">
						<li><a href="${pageContext.request.contextPath}/admin/novo">Novo</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/listar">Listar</a></li>
					</ul></li>
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#proj"><i class="fa fa-fw fa-suitcase"></i>
						Projetos <i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="proj" class="collapse">
						<li><a href="${pageContext.request.contextPath}/projeto/novo">Novo</a></li>
						<li><a href="${pageContext.request.contextPath}/projeto/listar">Listar</a></li>
					</ul></li>
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#colab"><i class="fa fa-fw fa-users"></i>
						Colaboradores <i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="colab" class="collapse">
						<li><a
							href="${pageContext.request.contextPath}/colaborador/novo">Novo</a></li>
						<li><a
							href="${pageContext.request.contextPath}/colaborador/listar">Listar</a></li>
					</ul></li>
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#comp"><i class="fa fa-fw fa-th-list"></i>
						Compet&ecirc;ncias <i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="comp" class="collapse">
						<li><a
							href="${pageContext.request.contextPath}/competencia/novo">Novo</a></li>
						<li><a
							href="${pageContext.request.contextPath}/competencia/listar">Listar</a></li>
					</ul></li>
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#cliente"><i class="fa fa-fw fa-building"></i>Clientes
						<i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="cliente" class="collapse">
						<li><a href="${pageContext.request.contextPath}/cliente/novo">Novo</a></li>
						<li><a href="${pageContext.request.contextPath}/cliente/listar">Listar</a></li>
					</ul></li>
			</c:if>
			<c:if test="${sessao.usuario.tipoUsuario == 'COLABORADOR'}">
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#proj"><i class="fa fa-fw fa-suitcase"></i>
						Projetos <i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="proj" class="collapse">
						<li><a href="${pageContext.request.contextPath}/projeto/listar">Listar</a></li>
					</ul></li>
			</c:if>
			<c:if test="${sessao.usuario.tipoUsuario == 'CLIENTE'}">
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#proj"><i class="fa fa-fw fa-suitcase"></i>
						Projetos <i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="proj" class="collapse">
						<li><a href="${pageContext.request.contextPath}/projeto/listar">Listar</a></li>
					</ul></li>
			</c:if>
		</ul>
	</div>
	<!-- /.navbar-collapse -->
</nav>