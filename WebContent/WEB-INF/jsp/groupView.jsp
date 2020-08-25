<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">${mbList[0].gpName}</h1>
</div>
<!-- グループidをコピー -->
<div class="form-group row mx-0">
	<input
		class="border border-info rounded text-secondary text-center form-control-plaintext col-2 mr-2"
		id="CopyTarget" type="text" value="${mbList[0].gpId}" readonly>
	<button type="button" class="btn btn-info" onclick="CopyToClipboard()"
		data-toggle="tooltip" data-placement="top" title="コピーする">
		<i class="fas fa-clipboard"></i>
	</button>
</div>
<div class="table-responsive">
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th>名前</th>
				<th>ユーザーID</th>
				<th>Email</th>
				<th>電話番号</th>
				<th>住所</th>
				<th>権限</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${mbList}" var="mb">
				<tr>
					<td><c:out value="${mb.name}" /></td>
					<td><a class="gpvw-userid"
						href="/ActionLogger/?view=getaction&id=<c:out value="${mb.userId}" />&gpid=<c:out value="${mb.gpId}" />"><c:out
								value="${mb.userId}" /></a></td>
					<td><c:out value="${mb.email}" /></td>
					<td><c:out value="${mb.tel}" /></td>
					<td><c:out value="${mb.address}" /></td>
					<td><c:out value="${mb.adm}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
	function CopyToClipboard() {
		// コピー対象をJavaScript上で変数として定義する
		var copyTarget = document.getElementById("CopyTarget");
		// コピー対象のテキストを選択する
		copyTarget.select();
		// 選択しているテキストをクリップボードにコピーする
		document.execCommand("Copy");
	}
</script>