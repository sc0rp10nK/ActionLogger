<%@ page t="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">活動記録登録</h1>
</div>
<div class="row">
	<div class="col"></div>
	<div class="col-8">
		<form class="form-adduser" action="/ActionLogger/addactionconfirm"
			method="post">

			<h4 h3 mb-3 font-weight-normal>活動記録登録確認</h4>
			<div class="mb-3">日付 : ${actionToAdd.actionDate}</div>
			<div class="mb-3">開始時刻 : ${actionToAdd.actionSTm}</div>
			<div class="mb-3">終了時刻 : ${actionToAdd.actionETm}</div>
			<div class="mb-3">場所 : ${actionToAdd.actionPlace}</div>
			<div class="mb-3">理由 : ${actionToAdd.actionReason}</div>
			<div class="mb-3">備考 : ${actionToAdd.actionRemarks}</div>
			<input type="hidden" name="status" value="confirmed"></input> <input
				type="submit" class="btn btn-secondary btn-block btn-lg"
				id="enterRoom" value="OK"></input>
		</form>
	</div>
	<div class="col"></div>
</div>