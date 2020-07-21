<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">新規グループ登録確認</h1>
</div>
<div class="row">
	<div class="col"></div>
	<div class="col-8">
		<form class="form-adduser" action="/ActionLogger/creatgroupconfirm"
			method="post">

			<h4 h3 mb-3 font-weight-normal>新規グループ登録確認</h4>
			<div class="mb-3">グループ名 : ${groupToAdd.groupName}</div>
			<input type="hidden" name="status" value="confirmed"></input> <input
				type="submit" class="btn btn-secondary btn-block btn-lg"
				id="enterRoom" value="OK"></input>
		</form>
	</div>
	<div class="col"></div>
</div>