<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<h4 class="header">Карточки</h4>
<c:set var="baseUrl" value="${pageContext.request.contextPath}/card" />
<div class="row">
	<div class="col s12 m10">
		<div class="card-panel blue lighten-5">
			<div class="row">
				<form:form method="POST" action="${baseUrl}"
					modelAttribute="searchFormModel">
					<div class="input-field col s4">
						<form:input path="clientId" type="text" />
						<label for="clientId">insert id client</label>
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
					<mytaglib:i18n key="card.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}"
					column="date_registration">
					<mytaglib:i18n key="card.dateRegistration" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="client_id">
					<mytaglib:i18n key="card.clientId" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="active">
					<mytaglib:i18n key="card.active" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">
					<mytaglib:i18n key="card.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">
					<mytaglib:i18n key="card.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="card" items="${listDTO.list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${card.id}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${card.dateRegistration}" /></td>
				<td><c:out value="${card.clientId}" /></td>
				<td><c:out value="${card.active}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${card.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${card.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${card.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${card.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${baseUrl}/${card.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>


<!-- <script>
	var latestId = '${newestCardId}';

	setInterval(function() {
		$.get("${pageContext.request.contextPath}/card/lastId", function(
				lastIdFromServer) {

			if (latestId < lastIdFromServer) {
				Materialize.toast('Someone created a new card!!!', 4000) // simple popup message using Materialize framework
				latestId = lastIdFromServer;
			}
		})
	}, 5 * 1000);
</script> -->