<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<h4 class="header">Заказы</h4>
<c:set var="baseUrl" value="${pageContext.request.contextPath}/order" />
<div class="row">
	<div class="col s12 m10">
		<div class="card-panel blue lighten-5">
			<div class="row">
				<form:form method="POST" action="${baseUrl}"
					modelAttribute="searchFormModel">
					<div class="input-field col s4">
						<form:input path="cardId" type="text" />
						<label for="cardId">insert card ID</label>
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
					<mytaglib:i18n key="order.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="start_time">
					<mytaglib:i18n key="order.startTime" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="end_time">
					<mytaglib:i18n key="order.endTime" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="card_id">
					<mytaglib:i18n key="order.cardId" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}"
					column="ticket_type_id">
					<mytaglib:i18n key="order.ticketTypeId" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}"
					column="ticket_price">
					<mytaglib:i18n key="order.ticketPrice" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}"
					column="bracelet_id">
					<mytaglib:i18n key="order.braceletId" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">
					<mytaglib:i18n key="order.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">
					<mytaglib:i18n key="order.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="order" items="${listDTO.list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${order.id}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${order.startTime}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${order.endTime}" /></td>
				<td><c:out value="${order.cardId}" /></td>
				<td><c:out value="${order.ticketTypeId}" /></td>
				<td><c:out value="${order.ticketPrice}" /></td>
				<td><c:out value="${order.braceletId}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${order.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${order.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${order.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${order.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${baseUrl}/${order.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<mytags:paging />
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>