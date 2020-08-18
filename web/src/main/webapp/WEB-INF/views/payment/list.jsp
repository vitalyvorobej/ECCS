<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<h4 class="header">Payments</h4>
<c:set var="baseUrl" value="${pageContext.request.contextPath}/payment" />
<div class="row">
	<div class="col s12 m10">
		<div class="card-panel blue lighten-5">
			<div class="row">
				<form:form method="POST" action="${baseUrl}"
					modelAttribute="searchFormModel">
					<div class="input-field col s4">
						<form:input path="orderId" type="text" />
						<label for="orderId">Insert order ID</label>
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
					<mytaglib:i18n key="payment.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="amount">
					<mytaglib:i18n key="payment.amount" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="order_id">
					<mytaglib:i18n key="payment.orderId" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}"
					column="payment_type">
					<mytaglib:i18n key="payment.paymentType" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">
					<mytaglib:i18n key="payment.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">
					<mytaglib:i18n key="payment.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="payment" items="${listDTO.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${payment.id}" /></td>
				<td><c:out value="${payment.amount}" /></td>
				<td><c:out value="${payment.orderId}" /></td>
				<td><c:out value="${payment.paymentType}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${payment.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${payment.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${payment.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${payment.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${baseUrl}/${payment.id}/delete"><i
						class="material-icons">delete</i></a></td>
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