<%@ page import="java.util.List" %>
<%@ page import="com.spareLink.model.Brand" %>
<%@ page import="com.spareLink.model.Category" %>
<%@ page import="com.spareLink.model.SparePart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Brand> brands = (List<Brand>) request.getAttribute("brands");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    SparePart part = (SparePart) request.getAttribute("part");

    if (part == null) {
        response.sendRedirect("error.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Spare Part</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script defer src="https://cdn.jsdelivr.net/npm/alpinejs@3.x.x/dist/cdn.min.js"></script>
</head>
<body class="bg-[#0f172a] text-gray-200 font-sans">

<div class="max-w-4xl mx-auto my-10 p-8 bg-[#1e293b] shadow-lg rounded-2xl border border-cyan-600">

    <h2 class="text-2xl font-bold text-cyan-400 mb-6 text-center">Edit Spare Part</h2>

    <form action="EditPartServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
        <input type="hidden" name="id" value="<%= part.getId() %>">

        <!-- Name -->
        <div class="mb-4">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Part Name</label>
            <input type="text" name="name" id="name" required
                   value="<%= part.getName() %>"
                   class="w-full p-2 bg-[#0f172a] border border-cyan-600 rounded-md focus:outline-none focus:ring-2 focus:ring-cyan-400">
        </div>

        <!-- Price -->
        <div class="mb-4">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Price (LKR)</label>
            <input type="number" step="0.01" name="price" id="price" required
                   value="<%= part.getPrice() %>"
                   class="w-full p-2 bg-[#0f172a] border border-cyan-600 rounded-md focus:outline-none focus:ring-2 focus:ring-cyan-400">
        </div>

        <!-- Quantity -->
        <div class="mb-4">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Quantity</label>
            <input type="number" name="quantity" id="quantity" min="0" required
                   value="<%= part.getQuantity() %>"
                   class="w-full p-2 bg-[#0f172a] border border-cyan-600 rounded-md focus:outline-none focus:ring-2 focus:ring-cyan-400">
        </div>

        <!-- Status -->
        <div class="mb-4">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Stock Status</label>
            <select name="status" required
                    class="w-full p-2 bg-[#0f172a] border border-cyan-600 rounded-md text-gray-300">
                <option value="InStock" <%= part.getStatus().equals("InStock") ? "selected" : "" %>>In Stock</option>
                <option value="LowStock" <%= part.getStatus().equals("LowStock") ? "selected" : "" %>>Low Stock</option>
                <option value="OutOfStock" <%= part.getStatus().equals("OutOfStock") ? "selected" : "" %>>Out of Stock</option>
            </select>
        </div>

        <!-- Brand -->
        <div class="mb-4">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Brand</label>
            <select name="brand_id" required
                    class="w-full p-2 bg-[#0f172a] border border-cyan-600 rounded-md text-gray-300">
                <option value="">Select Brand</option>
                <% for (Brand brand : brands) { %>
                    <option value="<%= brand.getId() %>" <%= (brand.getId() == part.getBrandId()) ? "selected" : "" %>><%= brand.getName() %></option>
                <% } %>
            </select>
        </div>

        <!-- Category -->
        <div class="mb-4">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Category</label>
            <select name="category_id" required
                    class="w-full p-2 bg-[#0f172a] border border-cyan-600 rounded-md text-gray-300">
                <option value="">Select Category</option>
                <% for (Category category : categories) { %>
                    <option value="<%= category.getId() %>" <%= (category.getId() == part.getCategoryId()) ? "selected" : "" %>><%= category.getName() %></option>
                <% } %>
            </select>
        </div>

        <!-- Description -->
        <div class="mb-4">
            <label class="block mb-2 text-sm font-semibold text-cyan-300">Description</label>
            <textarea name="description" rows="4" required
                      class="w-full p-2 bg-[#0f172a] border border-cyan-600 rounded-md focus:outline-none focus:ring-2 focus:ring-cyan-400"><%= part.getDescription() %></textarea>
        </div>

        <!-- Image Upload with Preview -->
        <div 
		  x-data="{ 
		    imageName: '', 
		    imageUrl: '<%= part.getImage() != null ? ("images/" + part.getImage()) : "" %>' 
		  }" 
		  class="mb-6"
		>
		  <label class="block mb-2 text-sm font-semibold text-cyan-300">Upload Image</label>
		
		  <div class="flex items-center justify-center w-full">
		    <label for="image" class="flex flex-col w-full h-32 border-2 border-dashed border-cyan-600 rounded-lg cursor-pointer hover:bg-[#0f172a]">
		      <div class="flex flex-col items-center justify-center pt-7">
		        <svg xmlns="http://www.w3.org/2000/svg" class="w-8 h-8 text-cyan-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
		          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 16l4-4 4 4m4-4l4 4 4-4" />
		        </svg>
		        <p class="text-sm text-gray-400 mt-1">Drag and drop or click to upload</p>
		        <p x-text="imageName" class="text-sm text-cyan-400 mt-1"></p>
		      </div>
		      <input 
		        type="file" 
		        name="image" 
		        id="image" 
		        accept="image/*" 
		        class="hidden"
		        @change="const file = $event.target.files[0]; imageName = file.name; imageUrl = URL.createObjectURL(file);"
		      >
		    </label>
		  </div>

  <div x-show="imageUrl" class="mt-4">
    <p class="text-sm mb-1 text-cyan-300">Preview:</p>
    <img :src="imageUrl" alt="Preview" class="h-32 rounded-md border border-cyan-600 object-cover">
  </div>
</div>


        <!-- Submit -->
        <div class="text-center">
            <button type="submit"
                    class="bg-cyan-500 hover:bg-cyan-600 text-white font-semibold py-2 px-6 rounded-lg transition-all">
                Update Spare Part
            </button>
        </div>

    </form>
</div>

<script>
    function validateForm() {
        const name = document.getElementById("name").value.trim();
        const price = document.getElementById("price").value;
        const quantity = document.getElementById("quantity").value;
        if (name === "" || price === "" || quantity === "") {
            alert("Please fill all required fields");
            return false;
        }
        return true;
    }
</script>

</body>
</html>
