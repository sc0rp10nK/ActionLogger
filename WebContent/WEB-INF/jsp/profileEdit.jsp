<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="row">
	<div class="col"></div>
	<div class="col-8">
		<form class="form-adduser" action="/ActionLogger/adduser"
			method="post">
			<h4 h3 mb-3 font-weight-normal>プロフィール編集</h4>
			<div class="mb-3">
				<label for="userid">ユーザーID</label> <input type="text"
					class="form-control" id="userid" name="userid" placeholder="ユーザーID"
					　	required>
				<div class="invalid-feedback">必須</div>
			</div>
			<div class="mb-3">
				<label for="password">パスワード</label> <input type="password"
					class="form-control" id="password" name="password"
					placeholder="パスワード" 　required>
				<div class="invalid-feedback">必須</div>
			</div>
			<div class="mb-3">
				<label for="name">氏名</label> <input type="text" class="form-control"
					id="name" name="name" placeholder="氏名" 　required>
				<div class="invalid-feedback">必須</div>
			</div>
			<div class="mb-3">
				<label for="address">住所</label> <input type="text"
					class="form-control" id="address" name="address" placeholder="住所">
			</div>
			<div class="mb-3">
				<label for="tel">電話番号</label> <input type="text"
					class="form-control" id="tel" name="tel"
					placeholder="xxxx-xxxx-xxxx">
			</div>
			<%-- フォームの正当性確認データ --%>
			<input type="hidden" name="vKey" value="${validationKey.value}">
			<div class="mb-3">
				<label for="email">メールアドレス</label> <input type="text"
					class="form-control" id="email" name="email" placeholder="メールアドレス">
			</div>
			<input type="submit" class="btn btn-secondary btn-block btn-lg"
				id="enterRoom" value="登録"></input>
			</form>
		</div>
		<div class="col"></div>

	</div>