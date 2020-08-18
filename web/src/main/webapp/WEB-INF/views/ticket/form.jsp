<!-- Импорты библиотек -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>


<h4 class="header">Изменить информацию о билете</h4>
<div class="row">
	<form:form class="col s12" method="POST"
		action="${pageContext.request.contextPath}/ticket/add"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name"><mytaglib:i18n key="ticket.name" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="price" type="text" disabled="${readonly}" />
				<form:errors path="price" cssClass="red-text" />
				<label for="name"><mytaglib:i18n key="ticket.price" /></label>
			</div>
		</div>

		<!-- Switch -->
		<div class="row">
			<div class="input-field col s12">
				<div class="switch">
					<label>занят <form:checkbox path="deleted"
							disabled="${readonly}" /> <span class="lever"></span> свободен
					</label>
				</div>
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
					href="${pageContext.request.contextPath}/ticket">к списку<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>
