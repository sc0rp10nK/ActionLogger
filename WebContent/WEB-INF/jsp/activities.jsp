<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">行動記録</h1>
	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#modal">絞り込み</button>
</div>
<div class="table-responsive">
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th>日付</th>
				<th>時刻</th>
				<th>場所</th>
				<th>理由</th>
				<th>備考</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${actList}" var="act">
				<tr>
					<td><c:out value="${act.actionDate}" /></td>
					<td><c:out value="${act.actionSTm } - ${act.actionETm}" /></td>
					<td><c:out value="${act.actionPlace}" /></td>
					<td><c:out value="${act.actionReason}" /></td>
					<td><c:out value="${act.actionRemarks}" /></td>
					<td><button type="button"
							class="delete-confirm btn btn-success" value="${act.actionId}"
							data-toggle="modal" data-target="#confirm-delete">削除</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<!-- 検索 Modal -->
<div class="modal fade" id="modal" tabindex="-1" role="dialog"
	aria-labelledby="label" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="label">絞り込み 検索</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col"></div>
					<div class="col-8">
						<form class="form-search" id="search" action="/ActionLogger/"
							method="get">
							<div class="mb-3">
								<input type="hidden" name="view" value="activities"> <label
									for="date">日付</label> <input type="date" class="form-control"
									id="date" name="date" placeholder="日付"> <br> <label
									for="place">場所</label> <input type="text" class="form-control"
									id="place" name="place" placeholder="場所"> <br>
								<div class="form-group">
									<label for="sel1">並び替え:</label> <select class="form-control"
										id="sel1" name="order">
										<option value="0">昇順</option>
										<option value="1">降順</option>
									</select>
								</div>
							</div>
							<input type="hidden" name="search" value="0"> <br>
						</form>
					</div>
					<div class="col"></div>
				</div>
			</div>
			<div class="modal-footer justify-content-center">
				<button type="submit" class="btn btn-danger w-50" form="search"
					data_but="btn-xs">
					<i class='fa fa-search'></i> 検索
				</button>
			</div>
		</div>
	</div>
</div>
<!-- 削除確認 Modal -->
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">確認</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">削除しますか？</div>
			<div class="modal-footer">
				<form action="/ActionLogger/" method="post">
				<input type="hidden" name="view" value="activities">
					<button type="submit" class="btn btn-success" id="deletebtn"
						name="deletebtn">はい</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">いいえ</button>
				</form>
			</div>
		</div>
	</div>
</div>
<script>
	$('.delete-confirm').click(function() {
		$('#deletebtn').val($(this).val());
	});
</script>