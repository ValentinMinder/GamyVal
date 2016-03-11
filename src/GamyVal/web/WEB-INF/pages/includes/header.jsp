<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 
Valentin Minder
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <base href="${pageContext.request.contextPath}/">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <c:if test="${not empty title}">
            <title>${title}</title>
        </c:if>
        <c:if test="${empty title}">
            <title>${title}</title>
        </c:if>

        <link href="static/css/bootstrap.min.css" rel="stylesheet">
        <script src="static/js/jquery.min.js"></script>
        <script src="static/js/bootstrap.min.js"></script>

    </head>
    <body>
        <div class="container">
            <!-- Static navbar -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="">GamyVal</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
<%-- 
dynamic headers when connected or not
--%>
                        <c:if test="${empty principal}">
                            <ul class="nav navbar-nav">
                                <li><a href="account">New Account</a></li>
                            </ul>
                            <form class="navbar-form navbar-right" method="POST" action="auth">
                                <div class="form-group">
                                    <input type="hidden" name="action" value="login">
                                    <input type="hidden" name="targetUrl" value="${targetUrl}">
                                    <input type="email" placeholder="email" name="email" class="form-control" required>
                                    <input type="password" placeholder="password" name="password" class="form-control" required>
                                    <button type="submit" class="btn btn-default">Login</button>
                                </div>
                            </form>
                        </c:if>
                        <c:if test="${not empty principal}">
                            <ul class="nav navbar-nav">
                                <li><a href="account">Manage Account</a></li>
                            </ul>
                            <ul class="nav navbar-nav">
                                <li><a href="application">New application</a></li>
                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="./auth?action=logout">Logout (${principal})</a></li>
                            </ul>
                        </c:if>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>