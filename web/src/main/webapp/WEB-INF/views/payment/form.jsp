<!-- Импорты библиотек -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<h4 class="header">Edit payments</h4>
<c:set var="baseUrl" value="${pageContext.request.contextPath}/payment" />
<div class="row">
	<form:form class="col s12" method="POST" action="${baseUrl}/add"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:select path="orderId" disabled="${readonly}">
					<form:options items="${paymentChoices}" />
				</form:select>
				<form:errors path="orderId" cssClass="red-text" />
				<label for="orderId"><mytaglib:i18n key="payment.orderId" /></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="amount" type="text" disabled="${readonly}" />
				<form:errors path="amount" cssClass="red-text" />
				<label for="amount"><mytaglib:i18n key="payment.amount" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="paymentType" type="text" disabled="${readonly}" />
				<form:errors path="paymentType" cssClass="red-text" />
				<label for="paymentType"><mytaglib:i18n
						key="payment.paymentType" /></label>
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
				<a class="btn waves-effect waves-light right" href="${baseUrl}">к
					списку<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>