<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>SpareLink - Total Parts</title>
  <!-- Font Awesome -->
  <script src="https://kit.fontawesome.com/46b998f154.js" crossorigin="anonymous"></script>
  <!-- Tailwind CSS -->
  <script src="https://cdn.tailwindcss.com"></script>
  <!-- Alpine.js -->
  <script defer src="https://cdn.jsdelivr.net/npm/alpinejs@3.x.x/dist/cdn.min.js"></script>
</head>
<body class="bg-gray-50 text-gray-200 font-sans">


<main class="bg-[#0f172a] text-gray-200 min-h-screen p-6 space-y-8">

  <!-- Page Title -->
  <div class="flex justify-center mb-6">
    <div class="text-3xl font-bold text-white flex items-center">
      <i class="fas fa-microchip text-[#06b6d4] mr-2"></i>
      Total Parts
    </div>
  </div>

  <div x-data="{}">

    <!-- Toolbar -->
    <div class="flex justify-end items-center mb-4 space-x-2">
      <form action="<c:url value='/add-part-form' />" method="post">
        <button type="submit" class="bg-[#06b6d4] hover:bg-[#0891b2] text-white px-4 py-2 rounded flex items-center transition">
          <i class="fas fa-plus mr-2"></i>
          Add New Part
        </button>
      </form>

      <input type="text" placeholder="Search parts..."
             class="w-full md:w-[28rem] p-2 rounded bg-[#1e293b] text-gray-200 focus:outline-none focus:ring-2 focus:ring-[#06b6d4]" />
      <button class="ml-2 bg-[#06b6d4] text-white px-4 py-2 rounded hover:bg-[#0891b2] transition">
        <i class="fas fa-search"></i>
      </button>
    </div>

    <!-- Parts Table -->
    <div class="bg-[#1e293b] p-6 rounded-xl shadow">
      <div class="overflow-x-auto">
        <table class="w-full text-sm text-left">
          <thead>
            <tr class="text-gray-400 border-b border-gray-600">
              <th class="py-2 px-4">Product ID</th>
              <th class="py-2 px-4">Photo</th>
              <th class="py-2 px-4">Part Name</th>
              <th class="py-2 px-4">Category</th>
              <th class="py-2 px-4">Description</th>
              <th class="py-2 px-4">Brand</th>
              <th class="py-2 px-4">Price (LKR)</th>
              <th class="py-2 px-4">Status</th>
              <th class="py-2 px-4">Actions</th>
            </tr>
          </thead>
          <tbody class="text-gray-300">
            <c:choose>
              <c:when test="${empty spare_parts}">
                <tr>
                  <td colspan="9" class="text-center text-gray-400 py-4">No parts available.</td>
                </tr>
              </c:when>
              <c:otherwise>
                <c:forEach var="product" items="${spare_parts}">
                  <tr class="hover:bg-[#334155] transition">
                    <td class="py-2 px-4">${product.id}</td>
                    <td class="py-2 px-4">
                      <img src="<c:url value='/images/${product.id}.jpg'/>" alt="${product.name}" class="w-12 h-12 object-cover rounded" />
                    </td>
                    <td class="py-2 px-4">${product.name}</td>
                    <td class="py-2 px-4">${product.categoryName}</td>
                    <td class="py-2 px-4">${product.description}</td>
                    <td class="py-2 px-4">${product.brandName}</td>
                    <td class="py-2 px-4">LKR ${product.price}</td>
                    <td class="py-2 px-4">${product.status}</td>
                    <td class="py-2 px-4">
                      <div class="flex space-x-2">
                        <!-- Edit Button -->
                        <a href="EditPartServlet?action=edit&id=${product.id}">
                          <button type="button" class="bg-yellow-400 text-white px-3 py-1 rounded shadow hover:bg-yellow-500 transition flex items-center">
                            <i class="fas fa-edit mr-1"></i> Edit
                          </button>
                        </a>

                        <!-- Delete Button -->
                        <form action="<c:url value='/DeletePartServlet'/>" method="post" onsubmit="return confirm('Are you sure you want to delete this part?');">
                          <input type="hidden" name="id" value="${product.id}" />
                          <button type="submit"
                                  class="bg-red-400 text-white px-3 py-1 rounded shadow hover:bg-red-500 transition flex items-center">
                            <i class="fas fa-trash mr-1"></i> Delete
                          </button>
                        </form>
                      </div>
                    </td>
                  </tr>
                </c:forEach>
              </c:otherwise>
            </c:choose>
          </tbody>
        </table>
      </div>
    </div>

  </div>
</main>

</body>
</html>
