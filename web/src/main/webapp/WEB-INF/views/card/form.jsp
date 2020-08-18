<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<h4 class="header">Информация о карточке</h4>
<c:set var="baseUrl" value="${pageContext.request.contextPath}/card" />
<div class="row">
	<form:form class="col s12" method="POST" action="${baseUrl}/add"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="dateRegistration" type="text"
					disabled="${readonly}" cssClass="datepicker" />
				<form:errors path="dateRegistration" cssClass="red-text" />
				<label for="dateRegistration"><mytaglib:i18n
						key="card.dateRegistration" /></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="clientId" disabled="${readonly}">
					<form:options items="${clientChoices}" />
				</form:select>
				<form:errors path="clientId" cssClass="red-text" />
				<label for="client"><mytaglib:i18n key="client.last_name" /></label>
			</div>
		</div>
		<!-- Switch -->
		<div class="row">
			<div class="input-field col s12">
				<div class="switch">
					<label><mytaglib:i18n key="card.nonactive" /> <form:checkbox
							path="active" disabled="${readonly}" /> <span class="lever"></span>
						<mytaglib:i18n key="card.active" /> </label>
				</div>
				<%-- <label class="switch-label"> <mytaglib:i18n
						key="card.active" /></label> <br /> --%>
			</div>
		</div>
		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">
						<mytaglib:i18n key="save.button" />
					</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${baseUrl}"><mytaglib:i18n
						key="back.list" /><i class="material-icons right"></i> </a>
			</div>
		</div>
	</form:form>
</div>