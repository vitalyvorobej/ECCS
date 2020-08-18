<!-- Импорты библиотек -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<h4 class="header">Edit client</h4>
<c:set var="baseUrl" value="${pageContext.request.contextPath}/client" />
<div class="row">
	<form:form class="col s12" method="POST"
		action="${pageContext.request.contextPath}/client/add"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="first_name" type="text" disabled="${readonly}" />
				<form:errors path="first_name" cssClass="red-text" />
				<label for="first_name"><mytaglib:i18n
						key="client.first_name" /></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="last_name" type="text" disabled="${readonly}" />
				<form:errors path="last_name" cssClass="red-text" />
				<label for="last_name"><mytaglib:i18n key="client.last_name" /></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="birthday_date" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="birthday_date" cssClass="red-text" />
				<label for="birthday_date"><mytaglib:i18n
						key="client.birthday_date" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="phone_number" type="text" disabled="${readonly}" />
				<form:errors path="phone_number" cssClass="red-text" />
				<label for="phone_number"><mytaglib:i18n
						key="client.phone_number" /></label>
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
					href="${pageContext.request.contextPath}/client">к списку<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>