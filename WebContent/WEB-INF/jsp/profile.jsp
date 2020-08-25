<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
#profile-table {
	margin: 10px auto;
    border: 2px #454d55 solid;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

table {
	margin: auto;
}

td {
	padding: 15px;
}
</style>
<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">プロフィール</h1>
	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#modal">プロフィール編集</button>
</div>
<div class="row">
	<div class="col"></div>
	<div class="col-10">
		<div id="profile-table"class="table-responsive table-dark">
			<table class="table table-borderless table-dark">
					<tr class="d-flex">
						<th scope="row" class="col-2 text-right">ユーザー名</th>
						<td>${userid}</td>
					</tr>
					<tr class="d-flex">
						<th scope="row" class="col-2 text-right">氏名</th>
						<td>${username}</td>
					</tr>
					<tr class="d-flex">
						<th scope="row" class="col-2 text-right">メールアドレス</th>
						<td>${user.email}</td>
					</tr>
					<tr class="d-flex">
						<th scope="row" class="col-2 text-right">住所</th>
						<td>${user.address}</td>
					</tr>
					<tr class="d-flex">
						<th scope="row" class="col-2 text-right">電話番号</th>
						<td>${user.tel}</td>
					</tr>
			</table>
		</div>
	</div>
	<div class="col"></div>
</div>
<div class="modal fade" id="modal" tabindex="-1" role="dialog"
	aria-labelledby="label" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="label">プロフィール編集</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col"></div>
					<div class="col-8">
						<form class="form-edit" id="edit" action="/ActionLogger/profileedit"
							method="post">
							<input type="hidden" name="userid" value="${userid}">
							<div class="mb-3">
								<label for="name">氏名</label> <input type="text"
									class="form-control" id="name" name="name"
									placeholder="${user.name}" >
								<div class="invalid-feedback">必須</div>
							</div>
							<div class="mb-3">
								<label for="email">メールアドレス</label> <input type="text"
									class="form-control" id="email" name="email"
									placeholder="${user.email}">
							</div>
							<div class="mb-3">
								<label for="address">住所</label> <input type="text"
									class="form-control" id="address" name="address"
									placeholder="${user.address}">
							</div>
							<div class="mb-3">
								<label for="tel">電話番号</label> <input type="text"
									class="form-control" id="tel" name="tel"
									placeholder="${user.tel}">
							</div>
							<%-- o正当性確認o --%>
							<input type="hidden" name="vKey" value="${validationKey.value}">
							<div class="mb-3">
								<label for="password"><span class="badge badge-danger">必須</span> 現在のパスワード</label> <input type="password"
									class="form-control" id="password" name="password"
									placeholder="パスワード" 　required>
								<div class="invalid-feedback">必須</div>
							</div>
						</form>
					</div>
					<div class="col"></div>
				</div>
			</div>
			<div class="modal-footer justify-content-center">
				<button type="submit" class="btn btn-success w-50" form="edit"
					data_but="btn-xs">
					<i class="far fa-save"></i> 保存
				</button>
			</div>
		</div>
	</div>
</div>