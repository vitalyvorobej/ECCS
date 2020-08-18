<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<h4 class="header">Изменить заказ</h4>
<c:set var="baseUrl" value="${pageContext.request.contextPath}/order" />
<div class="row">
	<form:form class="col s12" method="POST"
		action="${pageContext.request.contextPath}/order/add"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="startTime" type="text" disabled="${readonly}" />
				<form:errors path="startTime" cssClass="red-text" />
				<label for="startTime"><mytaglib:i18n key="order.startTime" /></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="endTime" type="text" disabled="${readonly}" />
				<form:errors path="endTime" cssClass="red-text" />
				<label for="endTime"><mytaglib:i18n key="order.endTime" /></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="cardId" disabled="${readonly}">
					<form:options items="${cardChoices}" />
				</form:select>
				<form:errors path="cardId" cssClass="red-text" />
				<label for="cardId">Фамилия держателя карточки</label>
			</div>
		</div>


		<div class="row">
			<div class="input-field col s12">
				<form:select path="ticketTypeId" disabled="${readonly}">
					<form:options items="${ticketTypeChoices}" />
				</form:select>
				<form:errors path="ticketTypeId" cssClass="red-text" />
				<label for="ticketTypeId"><mytaglib:i18n
						key="order.ticketTypeId" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="braceletId" disabled="${readonly}">
					<form:options items="${braceletChoices}" />
				</form:select>
				<form:errors path="braceletId" cssClass="red-text" />
				<label for="braceletId"><mytaglib:i18n
						key="order.braceletId" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="ticketPrice" type="text" disabled="${readonly}" />
				<form:errors path="ticketPrice" cssClass="red-text" />
				<label for="ticketPrice"><mytaglib:i18n
						key="order.ticketPrice" /></label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">сохранить</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right"
					href="${pageContext.request.contextPath}/order">к списку<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>