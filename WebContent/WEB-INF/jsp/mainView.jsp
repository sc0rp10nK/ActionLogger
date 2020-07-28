<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String view = request.getParameter("view");
%>

<!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Action Logger</title>

<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
	<script defer src="https://use.fontawesome.com/releases/v5.8.1/js/all.js" integrity="sha384-g5uSoOSBd7KkhAMlnQILrecXvzst9TdC09/VM+pjDTCM+1il8RHz5fKANTFFb+gQ" crossorigin="anonymous"></script>

<meta name="theme-color" content="#563d7c">
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->
<link href="/GuiWork/css/dashboard.css" rel="stylesheet">
</head>
<body>
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="/ActionLogger/">Action
			Logger</a>
		<button class="navbar-toggler position-absolute d-md-none collapsed"
			type="button" data-toggle="collapse" data-target="#sidebarMenu"
			aria-controls="sidebarMenu" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"></li>
			<li class="nav-item text-nowrap"><a class="nav-link"
				href="/ActionLogger/logout">ログアウト</a></li>
		</ul>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<nav id="sidebarMenu"
				class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
				<!-- サイドバーの中身をインポート -->
				<jsp:include page="/WEB-INF/jsp/sidebar.jsp" />
			</nav>
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
				<!-- コンテンツエリアの中身をインポート -->
				<%
					if (view != null && view.equals("activities")) {
				%>
				<jsp:include page="/WEB-INF/jsp/activities.jsp" />
				<%
					} else if (view != null && view.equals("getaction")) {
				%>
				<jsp:include page="/WEB-INF/jsp/getAction.jsp" />
				<%
					} else if (view != null && view.equals("addaction")) {
				%>
				<jsp:include page="/WEB-INF/jsp/addAction.jsp" />
				<%
					} else if (view != null && view.equals("addactionconfirm")) {
				%>
				<jsp:include page="/WEB-INF/jsp/addActionConfirm.jsp" />
				<%
					} else if (view != null && view.equals("creatgroup")) {
				%>
				<jsp:include page="/WEB-INF/jsp/creatGroup.jsp" />
				<%
					} else if (view != null && view.equals("creatgroupconfirm")) {
				%>
				<jsp:include page="/WEB-INF/jsp/creatGroupConfirm.jsp" />
				<%
					} else if (view != null && view.equals("groupView")) {
				%>
				<jsp:include page="/WEB-INF/jsp/groupView.jsp" />
				<%
					} else if (view != null && view.equals("joinGroup")) {
				%>
				<jsp:include page="/WEB-INF/jsp/joinGroup.jsp" />
				<%
					} else {
				%>
				<jsp:include page="/WEB-INF/jsp/dashboard.jsp" />
				<%
					}
				%>
			</main>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"
		integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd"
		crossorigin="anonymous"></script>

</body>
</html>
