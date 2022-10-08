<%-- 
    Document   : home
    Created on : 8-Oct-2022, 11:46:38 AM
    Author     : hsp28
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Information</title>
    </head>
    <body>
        <h2>Home Page</h2>
        <p><b>Hello ${sessionuser.username}</b></p>
        <a href="login?logout">Log-out</a>
    </body>
</html>
