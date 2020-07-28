<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">活動記録登録</h1>
</div>
	<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<form class="form-addaction" action="/ActionLogger/addaction" method="post">
				<div class="mb-3">
					<label for="date">日付</label> <input type="date"
						class="form-control" id="date" name="date" placeholder="日付"
						　	required>
					<div class="invalid-feedback">必須</div>
				</div>
				<div class="mb-3">
					<label for="start_time">開始時刻</label> <input type="time"
						class="form-control" id="start_time" name="start_time"
						placeholder="開始時刻" 　required>
					<div class="invalid-feedback">必須</div>
				</div>
				<div class="mb-3">
					<label for="end_time">終了時刻</label> <input type="time"
						class="form-control" id="end_time" name="end_time"
						placeholder="終了時刻" 　required>
					<div class="invalid-feedback">必須</div>
				</div>
				<div class="mb-3">
					<label for="place">場所</label> <input type="text"
						class="form-control" id="place" name="place" placeholder="場所"
						required>
					<div class="invalid-feedback">必須</div>
				</div>
				<div class="mb-3">
					<label for="reason">理由</label> <textarea
						class="form-control" id="reason" name="reason" placeholder="理由"
						rcols="60" rows="5" style="resize: none;" required></textarea>
					<div class="invalid-feedback">必須</div>
				</div>
				<div class="mb-3">
					<label for="remarks">備考</label> <textarea
						class="form-control" id="remarks" name="remarks" placeholder="備考"
						cols="60" rows="5" style="resize: none;"></textarea>
				</div>
				<input type="submit" class="btn btn-secondary btn-block btn-lg"
					id="enterRoom" value="登録"></input>
			</form>
		</div>
		<div class="col"></div>

	</div>