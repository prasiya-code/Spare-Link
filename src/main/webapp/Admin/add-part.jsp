<%@ page import="java.util.List" %>
<%@ page import="com.spareLink.model.Brand" %>
<%@ page import="com.spareLink.model.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    List<Brand> brands = (List<Brand>) request.getAttribute("brands");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Spare Part</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script defer src="https://cdn.jsdelivr.net/npm/alpinejs@3.x.x/dist/cdn.min.js"></script>
</head>
<body class="bg-[#0f172a] text-gray-200 font-sans">

<div class="max-w-4xl mx-auto my-10 p-8 bg-[#1e293b] shadow-lg rounded-2xl border border-cyan-600">

    <h2 class="text-2xl font-bold text-cyan-400 mb-6 text-center">Add New Spare Part</h2>

    <c:if test="${not empty error}">
        <div class="text-red-500 mb-4 text-center font-semibold">${error}</div>
    </c:if>

    <form action="add-part-form" method="post" enctype="multipart/form-data" x-data="{ imageName: '', imageUrl: '' }">

        <!-- Name -->
        <div class="mb-4">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Part Name</label>
            <input type="text" name="name" id="name" required
                   class="w-full p-2 bg-[#0f172a] border border-cyan-600 rounded-md focus:outline-none focus:ring-2 focus:ring-cyan-400">
        </div>

        <!-- Price -->
        <div class="mb-4">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Price (LKR)</label>
            <input type="number" step="0.01" name="price" id="price" required
                   class="w-full p-2 bg-[#0f172a] border border-cyan-600 rounded-md focus:outline-none focus:ring-2 focus:ring-cyan-400">
        </div>

        <!-- Quantity -->
        <div class="mb-4">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Quantity</label>
            <input type="number" name="quantity" id="quantity" min="0" required
                   class="w-full p-2 bg-[#0f172a] border border-cyan-600 rounded-md focus:outline-none focus:ring-2 focus:ring-cyan-400">
        </div>

        <!-- Brand -->
        <div class="mb-4">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Brand</label>
            <select name="brand_id" required
                    class="w-full p-2 bg-[#0f172a] border border-cyan-600 rounded-md text-gray-300">
                <option value="">Select Brand</option>
                <c:forEach items="${brands}" var="brand">
                    <option value="${brand.id}">${brand.name}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Category -->
        <div class="mb-4">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Category</label>
            <select name="category_id" required
                    class="w-full p-2 bg-[#0f172a] border border-cyan-600 rounded-md text-gray-300">
                <option value="">Select Category</option>
                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Description -->
        <div class="mb-4">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Description</label>
            <textarea name="description" rows="4" required
                      class="w-full p-2 bg-[#0f172a] border border-cyan-600 rounded-md focus:outline-none focus:ring-2 focus:ring-cyan-400"></textarea>
        </div>

        <!-- Drag and Drop Image Upload -->
        <div class="mb-6">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Upload Image</label>
            <div class="flex items-center justify-center w-full">
                <label for="image" class="flex flex-col w-full h-32 border-2 border-dashed border-cyan-600 rounded-lg cursor-pointer hover:bg-[#0f172a]">
                    <div class="flex flex-col items-center justify-center pt-7">
                        <svg xmlns="http://www.w3.org/2000/svg" class="w-8 h-8 text-cyan-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 16l4-4 4 4m4-4l4 4 4-4" />
                        </svg>
                        <p class="text-sm text-gray-400 mt-1">Drag and drop or click to upload</p>
                        <p x-text="imageName" class="text-sm text-gray-400 mt-2"></p>
                    </div>
                    <input type="file" id="image" name="image" class="hidden" accept="image/*"
                           @change="imageName = $event.target.files[0].name; imageUrl = URL.createObjectURL($event.target.files[0])">
                </label>
            </div>
            <div class="mt-4" x-show="imageUrl">
                <img :src="imageUrl" alt="Image Preview" class="w-32 h-32 object-cover rounded-md border border-cyan-600">
            </div>
        </div>

        <!-- Submit -->
        <div class="mb-6">
            <button type="submit" class="w-full py-3 text-white bg-cyan-600 rounded-md hover:bg-cyan-700">Add Part</button>
        </div>
    </form>
</div>
</body>
</html>
