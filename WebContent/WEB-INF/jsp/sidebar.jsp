<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="sidebar-sticky pt-3">

	<ul class="nav flex-column">
		<li class="nav-item"><a class="nav-link active"
			href="/ActionLogger/?view=dashboard"> Dashboard </a></li>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== 活動記録 ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<li class="nav-item"><a class="nav-link"
			href="/ActionLogger/?view=addaction"> <span
				data-feather="file-text"></span> 活動記録登録
		</a></li>
		<li class="nav-item"><a class="nav-link"
			href="/ActionLogger/?view=activities"> 表示 </a></li>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== 管理グループ ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<li class="nav-item"><a class="nav-link"
			href="/ActionLogger/?view=creatgroup"> 新規グループ </a></li>
		<li class="nav-item"><a class="nav-link"
			href="/ActionLogger/?view=joinGroup"> グループに参加 </a></li>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== 管理中のグループ ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<c:forEach items="${admGpList}" var="admGp">
			<li class="nav-item"><a class="nav-link"
				href="/ActionLogger/?view=getmember&id=${admGp.groupId}"><c:out
						value="${admGp.groupName}" /></a></li>
		</c:forEach>
	</ul>
</div>

