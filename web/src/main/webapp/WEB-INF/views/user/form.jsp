<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<h4 class="header">Изменить информацию о пользователе</h4>
<%-- <c:set var="baseUrl" value="${pageContext.request.contextPath}/user" /> --%>
<div class="row">
	<form:form class="col s12" method="POST"
		action="${pageContext.request.contextPath}/user/add"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<%-- <form:input path="version" type="hidden"/> --%>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="email" type="text" disabled="${readonly}" />
				<form:errors path="email" cssClass="red-text" />
				<label for="email"><mytaglib:i18n key="user.email" /></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="password" type="text" disabled="${readonly}" />
				<form:errors path="password" cssClass="red-text" />
				<label for="password"><mytaglib:i18n key="user.password" /></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="role" type="text" disabled="${readonly}" />
				<form:errors path="role" cssClass="red-text" />
				<label for="role"><mytaglib:i18n key="user.role" /></label>
				<!-- допилить как у
				димы с календарём -->
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
					href="${pageContext.request.contextPath}/user">к списку<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>