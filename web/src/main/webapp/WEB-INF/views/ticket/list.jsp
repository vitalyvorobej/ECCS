<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<h4 class="header">Билеты</h4>
<c:set var="baseUrl" value="${pageContext.request.contextPath}/ticket" />
<div class="row">
	<div class="col s12 m10">
		<div class="card-panel blue lighten-5">
			<div class="row">
				<form:form class="col s12" method="POST" action="${baseUrl}"
					modelAttribute="searchFormModel">
					<div class="input-field col s4">
						<form:input path="name" type="text" />
						<label for="name"> Insert ticket name</label>
					</div>

					<%-- 	<div class="input-field col s4">
						<div class="switch">
							<label>занят<form:checkbox path="free" /> <span
								class="lever"></span> свободен
							</label>
						</div>
						<label class="switch-label">свободен ли браслет</label> <br />
					</div> --%>



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
					<mytaglib:i18n key="ticket.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="name">
					<mytaglib:i18n key="ticket.name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="price">
					<mytaglib:i18n key="ticket.price" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="deleted">
					<mytaglib:i18n key="ticket.deleted" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">
					<mytaglib:i18n key="ticket.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">
					<mytaglib:i18n key="ticket.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="ticket" items="${listDTO.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${ticket.id}" /></td>

				<td><c:out value="${ticket.name}" /></td>
				<td><c:out value="${ticket.price}" /></td>
				<td><c:out value="${ticket.deleted}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${ticket.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${ticket.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${ticket.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${ticket.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${baseUrl}/${ticket.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<mytags:paging />
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>