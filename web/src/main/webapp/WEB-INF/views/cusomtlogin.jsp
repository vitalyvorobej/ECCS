<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>

<c:url value="/j_spring_security_check" var="loginUrl" />
<form class="form-horizontal" action="${loginUrl }" role="form"
	method="POST">
	<fieldset>
		<div class="form-group">
			<label class="control-label col-sm-2" for="email">Email:</label>
			<div class="col-sm-7">
				<input type="email" class="form-control" id="email"
					name="j_username" placeholder="Enter email">
			</div>
		</div>
		<div class="from-group">
			<label class="control-label col-sm-2" for="password">Password:</label>
			<div class="col-sm-7">
				<input type="password" class="form-control" id="password"
					name="j_password" placeholder="Enter password">
			</div>
		</div>
		<div id="buttons" class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary">Login</button>
				<button type="reset" class="btn btn-primary">reset</button>
				<input id="remember_me" name="_spring_security_remember_me"
					type="checkbox" /> <label for="remember_me" class="inline">Remember
					Me</label>
			</div>
		</div>
	</fieldset>
</form>