<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<h5>
	<p align="center">
		<mytaglib:i18n key="links.mainlogin" />
	</p>
</h5>
<div class="row">
	<div class="col s3"></div>
	<div class="col s6">
		<form name='loginForm' action="<c:url value='login' />" method='POST'>
			<div class="row">
				<div class="input-field col s12 center">
					<input type='text' name='username' value=''> <label
						for="username"><mytaglib:i18n key="user.email" />:</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12 center ">
					<input type='password' name='password' /><label for="password"><mytaglib:i18n
							key="user.password" />:</label>
				</div>
			</div>
			<c:if test="${not empty error}">
				<div class="row">
					<div class="col s12 center">
						<div class="error">${error}</div>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="row">
					<div class="col s12 center">
						<div class="msg">${msg}</div>
					</div>
				</div>
			</c:if>
			<div class="row">
				<div class="col s12 center">
					<button
						class="btn waves-effect waves-light #b39ddb deep-purple lighten-3 "
						type="submit">
						<mytaglib:i18n key="links.login" />
					</button>
				</div>
			</div>
		</form>
	</div>
	<div class="col s3"></div>
</div>