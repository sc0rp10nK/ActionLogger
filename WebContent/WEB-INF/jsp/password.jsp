<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="row">
	<div class="col"></div>
	<div class="col-8">
		<form class="form-passchange" action="/ActionLogger/passwordchange"
			method="post">
			<input type="hidden" name="userid" value="${userid}">
			<br>
			<h3 h3 mb-3 font-weight-normal>パスワード変更</h3>
			<br>
			<div class="mb-3">
				<label for="password"><span class="badge badge-danger">必須</span>
					現在のパスワード</label>  <input
					type="password" class="form-control" id="password" name="password"
					 required>
				<div class="invalid-feedback">必須</div>
			</div>
			<div class="mb-3">
				<label for="password"><span class="badge badge-danger">必須</span>
					新しいパスワード</label> <input
					type="password" class="form-control" name="newPassword"
					id="newPassword"  required>
				<div class="invalid-feedback">必須</div>
			</div>
			<div class="mb-3">
				<label for="password"><span class="badge badge-danger">必須</span>
					パスワード (確認)</label> <input type="password" class="form-control"
					name="confirm" oninput="CheckPassword(this)" required>
			</div>
			<br>
			<%-- フォームの正当性確認データ --%>
			<input type="hidden" name="vKey" value="${validationKey.value}">
			<input type="submit" class="btn btn-success btn-block btn-lg"
				id="enterRoom" value="保存"></input>
		</form>
	</div>
	<div class="col"></div>
</div>
<script>
    // パスワードと確認用の値を一致かどうか判断
	function CheckPassword(confirm) {
		// 入力値取得
		var input1 = newPassword.value;
		var input2 = confirm.value;
		// パスワード比較
		if (input1 != input2) {
			confirm.setCustomValidity("入力値が一致しません。");
		} else {
			confirm.setCustomValidity('');
		}
	}
</script>