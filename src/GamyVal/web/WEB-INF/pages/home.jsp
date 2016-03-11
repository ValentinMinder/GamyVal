<%@include file="includes/header.jsp" %>

<%-- 
Valentin Minder
Home of the application with statistics.
--%>
<h2>This is home of GamyVal!</h2>

<c:if test="${not empty message}">
    <div class="alert alert-info" role="alert">
        Info: ${message}.
    </div>
</c:if> 

<c:if test="${not empty principal}">
    <div class="alert alert-info" role="alert">
        Currently logged in as ${principal}.
    </div>
</c:if>

<c:if test="${empty principal}">
    <div class="alert alert-info" role="alert">
        Currently not logged in.
    </div>
</c:if>

<c:if test="${not empty requestScope.nbOfAccounts}">
    <h4># Accounts : ${requestScope.nbOfAccounts}</h4>
    <h4># Applications managed: ${requestScope.nbOfApplications}</h4>
    <h4># End-user last 30 days: ${requestScope.newUserLast30days}</h4>
</c:if>

<%@include file="includes/footer.jsp" %>
