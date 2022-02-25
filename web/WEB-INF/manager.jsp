<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% List<User> users = (List<User>) request.getAttribute("users"); %>
<% List<Task> tasks = (List<Task>) request.getAttribute("tasks"); %>
<a href="/logout">Logout</a>
<div style="width: 800px;">
    <div style="width: 50%;float: left">
        Add User:<br>
        <form action="/userRegister" method="post"  enctype="multipart/form-data">
            <input type="text" name="name" placeholder="name"><br>
            <input type="text" name="surname" placeholder="surname"><br>
            <input type="text" name="email" placeholder="email"><br>
            <input type="text" name="password" placeholder="password"><br>
            <input type="file" name="image" ><br>
            <select name="type">
                <option value="USER">USER</option>
                <option value="MANAGER">MANAGER</option>
            </select><br>

            <input type="submit" value="Register">

        </form>
    </div>
    <div style="width: 50%;float: left">
        Add Task:
        <form action="/addTask" method="post">
            <input type="text" name="name" placeholder="name"><br>
            <textarea name="description" placeholder="description"></textarea><br>
            <input type="date" name="date"><br>
            <select name="status">
                <option value="NEW">NEW</option>
                <option value="IN_PROGRESS">IN_PROGRESS</option>
                <option value="FINISHED">FINISHED</option>
            </select><br>
            <select name="user_id">
                <%
                    for (User user : users) { %>
                <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getSurname()%>
                </option>

                <%
                    }
                %>
            </select><br>
            <input type="submit" value="AddTask">

        </form>
    </div>
</div>

</div>
<div>
    All Users:<br>
    <table border="1">
        <tr>
         <th>name</th>
         <th>surname</th>
         <th>email</th>
         <th>type</th>
          <th>picture</th>
        </tr>
        <%
            for (User user : users) { %>
        <tr>
            <td><%=user.getName()%></td>
            <td><%=user.getSurname()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=user.getUserType().name()%></td>
            <td><%=user.getPictureUrl()%></td>
        </tr>

        <%
            }
        %>
    </table>
</div>
<div>
    All Tasks:<br>
    <table border="1">
        <tr>
            <th>name</th>
            <th>description</th>
            <th>deadline</th>
            <th>status</th>
            <th>user</th>
        </tr>
        <%
            for (Task task : tasks) { %>
        <tr>
            <td><%=task.getName()%></td>
            <td><%=task.getDescription()%></td>
            <td><%=task.getDeadline()%></td>
            <td><%=task.getTaskStatus().name()%></td>
            <td><%=task.getUser().getName() + " " + task.getUser().getSurname() %></td>
        </tr>

        <%
            }
        %>
    </table>

</div>
</body>
</html>
