<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<h4 class="header">Клиенты</h4>
<c:set var="baseUrl" value="${pageContext.request.contextPath}/client" />
<div class="row">
	<div class="col s12 m10">
		<div class="card-panel blue lighten-5">
			<div class="row">
				<form:form class="col s12" method="POST" action="${baseUrl}"
					modelAttribute="searchFormModel">
					<div class="input-field col s4">
						<form:input path="last_name" type="text" />
						<label for="last_name"> Insert last name</label>
					</div>

					<div class="col s4">
						<button class="btn waves-effect waves-light right" type="submit">search</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">
					<mytaglib:i18n key="client.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="first_name">
					<mytaglib:i18n key="client.first_name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="last_name">
					<mytaglib:i18n key="client.last_name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}"
					column="birthday_date">
					<mytaglib:i18n key="client.birthday_date" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}"
					column="phone_number">
					<mytaglib:i18n key="client.phone_number" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">
					<mytaglib:i18n key="client.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">
					<mytaglib:i18n key="client.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="client" items="${listDTO.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${client.id}" /></td>

				<td><c:out value="${client.first_name}" /></td>
				<td><c:out value="${client.last_name}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${client.birthday_date}" /></td>
				<td><c:out value="${client.phone_number}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${client.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${client.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${client.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${client.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${baseUrl}/${client.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<mytags:paging />
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>