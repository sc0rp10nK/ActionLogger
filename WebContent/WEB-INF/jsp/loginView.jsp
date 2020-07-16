<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ActionLogger ログイン</title>

<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

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
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Action
			Logger</a>
	</nav>
	<div class="row">
		<div class="col"></div>
		<div class="col-８">
			<form class="form-signin" action="/ActionLogger/logincheck"
				method="post">
				<h4 h3 mb-3 font-weight-normal>AcctionLoggerログイン</h4>
				<div class="form-group">

					ユーザー名<br> <input type="text" class="form-control"
						name="userid" placeholder="username" required autofocus>
					パスワード<br> <input type="password" class="form-control"
						name="password" placeholder="パスワード" required>
				</div>
				<input type="submit" class="btn btn-secondary btn-block btn-lg"
					id="login" value="ログイン"></input>
			</form>
			<a href="/ActionLogger/adduser">新規登録</a>

		</div>
		<div class="col"></div>

	</div>
</body>
</html>