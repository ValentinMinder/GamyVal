<%@include file="includes/header.jsp" %>

<%-- 
Valentin Minder
Form to create a new application.
--%>

<c:if test="${not empty message}">
    <div class="alert alert-info">
      ${requestScope.message}
    </div>
</c:if>

<h2>New application</h2>

<form method="POST" action="application">
   <div class="form-group">
        <label for="email"> Email:</label>
        <input type="email" value="${principal}" class="form-control" name="email" readonly>
    </div>
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" hint="application name" class="form-control" name="name" required>
    </div>
    <div class="form-group">
        <label for="descr">Description:</label>
        <input type="text" hint="what it does" class="form-control" name="descr" required>
    </div>
        <button type="submit" class="btn btn-default">Create new!</button>
</form>

<%@include file="includes/footer.jsp" %>