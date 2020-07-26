<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">グループ参加</h1>
</div>
	<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<form class="form-addaction" action="/ActionLogger/joingroup" method="post">
				<div class="mb-3">
					<label for="group_id">グループIDを入力してください</label> <input type="text"
						class="form-control" id="group_id" name="group_id" placeholder="グループid"
						required>
					<div class="invalid-feedback">必須</div>
				</div>
				<input type="submit" class="btn btn-secondary btn-block btn-lg"
					id="enterRoom" value="参加"></input>
			</form>
		</div>
		<div class="col"></div>

	</div>