<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<h4 class="header">Пользователи</h4>
<c:set var="baseUrl" value="${pageContext.request.contextPath}/user" />
<div class="row">

	<form:form class="col s12" method="POST" action="${baseUrl}"
		modelAttribute="searchFormModel">
		<div class="input-field col s4">
			<form:input path="email" type="text" />
			<label for="email"> введите E-mail</label>
		</div>

		<div class="col s4">
			<button class="btn waves-effect waves-light right" type="submit">search</button>
		</div>
	</form:form>
</div>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">
					<mytaglib:i18n key="user.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="email">
					<mytaglib:i18n key="user.email" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="password">
					<mytaglib:i18n key="user.password" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="role">
					<mytaglib:i18n key="user.role" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">
					<mytaglib:i18n key="user.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">
					<mytaglib:i18n key="user.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="user" items="${listDTO.list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${user.id}" /></td>
				<td><c:out value="${user.email}" /></td>
				<td><c:out value="${user.password}" /></td>
				<td><c:out value="${user.role}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${user.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${user.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${user.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${user.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${baseUrl}/${user.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<mytags:paging />
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>