<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Dashboard</h1>
</div>
<h3>ユーザー情報</h3>
<div class="table-responsive">
	<table class="table table-borderless table-sm">
		<tbody>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">ユーザー名</tk>
				<td>${userid}</td>
			</tr>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">氏名</th>
				<td>${username}</td>
			</tr>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">参加グループ</th>
				<td>grp01</td>
			</tr>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right"></th>
				<td>grp02</td>
			</tr>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">管理グループ</th>
				<td>KBC ITE19</td>
			</tr>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right"></th>
				<td>KBC 教職員</td>
			</tr>
		</tbody>
	</table>
</div>
<h3>最近の行動記録</h3>
<div class="table-responsive">
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th>日付</th>
				<th>時刻</th>
				<th>場所</th>
				<th>理由</th>
				<th>備考</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${actLtyList}" var="act">
				<tr>
					<td><c:out value="${act.actionDate}" /></td>
					<td><c:out value="${act.actionSTm } - ${act.actionETm}" /></td>
					<td><c:out value="${act.actionPlace}" /></td>
					<td><c:out value="${act.actionReason}" /></td>
					<td><c:out value="${act.actionRemarks}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
