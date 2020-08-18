<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
	<nav>
		<nav class="#b39ddb deep-purple lighten-3">

			<div class="nav-wrapper container">


				<%-- <ul class="sidenav" id="mobile-demo">
				<li><a href="${pageContext.request.contextPath}/">Home</a></li>
				<li><a href="badges.html">Components</a></li>
				<li><a href="collapsible.html">Javascript</a></li>
				<li><a href="mobile.html">Mobile</a></li>
			</ul> --%>
				<ul id='dropdown1' class='dropdown-content'>
					<li><a href="${baseUrl}?language=ru">RU</a></li>
					<li><a href="${baseUrl}?language=en">ENG</a></li>
					<li class="divider" tabindex="-1"></li>
					<li><a href="${baseUrl}/login">Login</a></li>
					<!-- <li><a href="#!"><i class="material-icons">view_module</i>four</a></li>
					<li><a href="#!"><i class="material-icons">cloud</i>five</a></li> -->
				</ul>
				<a href="#" data-activates="mobile-demo" class="button-collapse"><i
					class="material-icons">menu</i></a>
				<ul class="side-nav" id="mobile-demo">
					<li><a href="${pageContext.request.contextPath}/"><mytaglib:i18n
								key="links.home" /></a></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/bracelet"><mytaglib:i18n
									key="links.second" /></a>
						</sec:authorize></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/client"><mytaglib:i18n
									key="links.clients" /></a>
						</sec:authorize></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/card"><mytaglib:i18n
									key="links.third" /></a>
						</sec:authorize></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/order"><mytaglib:i18n
									key="links.orders" /></a>
						</sec:authorize></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/ticket"><mytaglib:i18n
									key="links.tickets" /></a>
						</sec:authorize></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/payment"><mytaglib:i18n
									key="links.payments" /></a>
						</sec:authorize></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/user"><mytaglib:i18n
									key="links.users" /></a>
						</sec:authorize></li>
					<li><a href="${baseUrl}?language=ru">RU</a></li>
					<li><a href="${baseUrl}?language=en">EN</a></li>
					<li><a href="${baseUrl}/login">Login</a></li>



				</ul>
				<!-- подвинуть меню -->
				<ul id="nav-mobile" class="left hide-on-med-and-down">
					<li><a href="${pageContext.request.contextPath}/"><mytaglib:i18n
								key="links.home" /></a></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/bracelet"><mytaglib:i18n
									key="links.second" /></a>
						</sec:authorize></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/client"><mytaglib:i18n
									key="links.clients" /></a>
						</sec:authorize></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/card"><mytaglib:i18n
									key="links.third" /></a>
						</sec:authorize></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/order"><mytaglib:i18n
									key="links.orders" /></a>
						</sec:authorize></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/ticket"><mytaglib:i18n
									key="links.tickets" /></a>
						</sec:authorize></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/payment"><mytaglib:i18n
									key="links.payments" /></a>
						</sec:authorize></li>
					<li><sec:authorize access="!isAnonymous()">
							<a href="${pageContext.request.contextPath}/user"><mytaglib:i18n
									key="links.users" /></a>
						</sec:authorize></li>
					<%-- <li><a href="${baseUrl}?language=ru">RU</a></li>
					<li><a href="${baseUrl}?language=en">EN</a></li> --%>

					<%-- 	<li><a href="${pageContext.request.contextPath}/login"><mytaglib:i18n
							key="header.login">Login</mytaglib:i18n></a></li>
 --%>
				</ul>

				<ul id="nav-mobile" class="right hide-on-med-and-down">
					<sec:authentication property="principal" />

					<li><a class="dropdown-trigger" href="#!"
						data-activates="dropdown1">Language<i
							class="material-icons right">arrow_drop_down</i></a></li>


				</ul>


				<sec:authorize access="!isAnonymous()">
					<div class="col s3">
						<a
							class="btn waves-effect grey waves-light right #d1c4e9 deep-purple lighten-4"
							href="${baseUrl}/execute_logout" title="logout"><mytaglib:i18n
								key="links.logout" /><i class="material-icons  right"></i> </a>
					</div>
				</sec:authorize>
				<!-- :TODO CSS -->
				<%-- <sec:authorize access="isAnonymous()">
					<div class="col s3">
						<a
							class="btn waves-effect waves-light right #d1c4e9 deep-purple lighten-4"
							href="${baseUrl}/login" title="login"><mytaglib:i18n
								key="links.login" /><i class="material-icons right"></i> </a>
					</div>
				</sec:authorize>
 --%>
			</div>
		</nav>
	</nav>
</header>