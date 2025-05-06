<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Spare Part (Test)</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 p-8">

    <div class="max-w-xl mx-auto bg-white p-6 rounded-xl shadow-md">
        <h2 class="text-2xl font-bold mb-6 text-center text-gray-800">Add Spare Part (Test)</h2>

        <form action="AddSparePartTestServlet" method="post" enctype="multipart/form-data" class="space-y-4">
            <div>
                <label class="block text-sm font-medium text-gray-700">Name</label>
                <input type="text" name="name" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring focus:ring-indigo-200 p-2" />
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700">Price (LKR)</label>
                <input type="number" step="0.01" name="price" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring focus:ring-indigo-200 p-2" />
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700">Description</label>
                <textarea name="description" rows="3" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring focus:ring-indigo-200 p-2"></textarea>
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700">Brand ID</label>
                <input type="number" name="brandId" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring focus:ring-indigo-200 p-2" />
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700">Category ID</label>
                <input type="number" name="categoryId" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring focus:ring-indigo-200 p-2" />
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700">Upload Image</label>
                <input type="file" name="image" accept="image/*" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring focus:ring-indigo-200 p-2" />
            </div>

            <div class="text-center">
                <button type="submit" class="bg-indigo-600 text-white px-6 py-2 rounded-lg hover:bg-indigo-700">Add Spare Part</button>
            </div>
        </form>

        <!-- Success / Error Messages -->
        <c:if test="${param.success == 'true'}">
            <p class="mt-4 text-green-600 text-center font-medium">Spare part added successfully!</p>
        </c:if>
        <c:if test="${param.error == 'true'}">
            <p class="mt-4 text-red-600 text-center font-medium">Failed to add spare part. Please try again.</p>
        </c:if>
    </div>

</body>
</html>
