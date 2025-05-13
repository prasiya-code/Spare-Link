<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Brand or Category</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">

<div class="bg-white p-8 rounded-2xl shadow-lg w-full max-w-md">
    <h2 class="text-2xl font-bold mb-6 text-center text-gray-800">Add Brand or Category</h2>

    <% if (request.getAttribute("message") != null) { %>
        <div class="mb-4 p-3 rounded-lg 
             <% String msg = (String) request.getAttribute("message"); 
                if (msg.contains("Successfully")) { %>
                    bg-green-100 text-green-700 border border-green-300
             <% } else { %>
                    bg-red-100 text-red-700 border border-red-300
             <% } %>">
            <%= request.getAttribute("message") %>
        </div>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/AddBrandCategoryServlet">

        <div>
            <label for="type" class="block mb-1 font-medium text-gray-700">Type</label>
            <select name="type" id="type" required
                    class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500">
                <option value="brand">Brand</option>
                <option value="category">Category</option>
            </select>
        </div>

        <div>
            <label for="name" class="block mb-1 font-medium text-gray-700">Name</label>
            <input type="text" name="name" id="name" required
                   class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500"
                   placeholder="Enter name here">
        </div>

        <div class="flex justify-center">
            <button type="submit"
                    class="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition duration-300">
                Add
            </button>
        </div>
    </form>
</div>

</body>
</html>
