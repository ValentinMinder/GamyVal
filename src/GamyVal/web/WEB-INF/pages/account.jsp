<%@include file="includes/header.jsp" %>

<%-- 
Valentin Minder
Account creation (new, not loggged in) or modification (existing, when logged in).
--%>
<c:if test="${not empty message}">
    <div class="alert alert-info">
        ${requestScope.message}
    </div>
</c:if>

<c:if test="${empty principal}"><h2>Registration</h2></c:if>
<c:if test="${not empty principal}"><h2>Edit your data</h2></c:if>

    <form method="POST" action="account">
        <div class="form-group">
            <label for="email"> Email:</label>
            <input type="email" value="${requestScope.email}" class="form-control" name="email"  
               <c:if test="${empty principal}">required</c:if>
               <c:if test="${not empty principal}">readonly</c:if>>
        </div>
        <div class="form-group">
            <label for="lName">Surname:</label>
            <input type="text" value="${requestScope.lName}" class="form-control" name="lName" required>
    </div>
    <div class="form-group">
        <label for="fName">Name:</label>
        <input type="text" value="${requestScope.fName}" class="form-control" name="fName" required>
    </div>
    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" class="form-control" name="password" required>
    </div>
    <div class="form-group">
        <label for="confirm">Confirmation:</label>
        <input type="password" class="form-control" name="confirm" required>
    </div>
    <c:if test="${empty principal}">
        <button type="submit" class="btn btn-default">Create new account</button>
    </c:if>
    <c:if test="${not empty principal}">
        <button type="submit" class="btn btn-default">Edit your account</button>
    </c:if>
</form>

<%@include file="includes/footer.jsp" %>