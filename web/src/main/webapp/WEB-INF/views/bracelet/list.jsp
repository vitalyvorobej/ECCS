<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<h4 class="header">Bracelets</h4>
<c:set var="baseUrl" value="${pageContext.request.contextPath}/bracelet" />
<div class="row">
	<div class="col s12 m10">
		<div class="card-panel blue lighten-5">
			<div class="row">
				<form:form class="col s12" method="POST" action="${baseUrl}"
					modelAttribute="searchFormModel">
					<div class="input-field col s4">
						<form:input path="uuid" type="text" />
						<label for="uuid"> insert uuid</label>
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
					<mytaglib:i18n key="bracelet.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="uuid">
					<mytaglib:i18n key="bracelet.uuid" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="free">
					<mytaglib:i18n key="bracelet.free" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">
					<mytaglib:i18n key="bracelet.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">
					<mytaglib:i18n key="bracelet.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="bracelet" items="${listDTO.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${bracelet.id}" /></td>

				<td><c:out value="${bracelet.uuid}" /></td>
				<td><c:out value="${bracelet.free}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${bracelet.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${bracelet.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${bracelet.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${bracelet.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${baseUrl}/${bracelet.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<mytags:paging />
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>