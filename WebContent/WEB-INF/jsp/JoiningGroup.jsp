<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${not empty joingGpList}">
		<div class="row">
			<div class="col"></div>
			<div class="col-10">
				<div class="table-responsive">
					<table class="table table-striped table-sm">
						<div
							class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
							<h1 class="h2">参加中のグループ</h1>
						</div>
						<thead>
							<tr>
								<th></th>
								<th>グループ名</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${joingGpList}" var="jgl">
								<tr>
									<td></td>
									<td><c:out value="${jgl.groupName}" /></td>
									<td><button type="button"
											class="delete-confirm btn btn-success" value="${jgl.groupId}"
											data-toggle="modal" data-target="#confirm-delete">退会</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col"></div>

		</div>
	</c:when>
	<c:otherwise>
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2">参加中のグループ</h1>
		</div>
		<div class="message">
			<h5>まだグループに参加されていません</h5>
			<br>
			<button type="button" class="btn btn-info mx-auto d-block"
				onclick="location.href='/ActionLogger/?view=joinGroup'">グループに参加する</button>
		</div>
	</c:otherwise>
</c:choose>
<!-- 退会確認 Modal -->
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
			<div class="modal-body">退会しますか？</div>
			<div class="modal-footer">
				<form action="/ActionLogger/" method="post">
					<input type="hidden" name="view" value="joininggroup">
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