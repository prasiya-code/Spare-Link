<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>SpareLink - Home</title>
  <script src="https://kit.fontawesome.com/46b998f154.js" crossorigin="anonymous"></script>
  <script src="https://cdn.tailwindcss.com"></script>
  <script defer src="https://cdn.jsdelivr.net/npm/alpinejs@3.x.x/dist/cdn.min.js"></script>
</head>

<body class="bg-gray-50 text-gray-800 font-sans">

  <!-- Include Header -->
  <%@ include file="header.jsp" %>

  <!-- Main Product Grid -->
  <main class="max-w-7xl mx-auto px-4 py-12">
    <h1 class="text-3xl font-bold text-center mb-10 text-gray-700">Featured Spare Parts</h1>
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-8">

      <!-- Product Card -->
		<c:forEach var="product" items="${spare_parts}">
		  <div class="bg-white rounded-xl shadow-lg overflow-hidden hover:shadow-2xl transition">
		    <img src="images/${product.image}" alt="${product.name}" class="w-full h-48 object-contain p-4"> <!-- ✅ Dynamic image path -->
		    <div class="p-4">
		      <h3 class="text-lg font-semibold mb-2">${product.name}</h3> <!-- ✅ Dynamic name -->
		      <p class="text-cyan-600 font-bold text-lg mb-4">$${product.price}</p> <!-- ✅ Dynamic price -->
		      <button class="bg-cyan-600 text-white px-4 py-2 rounded w-full hover:bg-cyan-700 transition">
		        <i class="fas fa-cart-plus mr-2"></i>Add to Cart
		      </button>
		    </div>
		  </div>
		</c:forEach>

		
    </div>
  </main>

  <!-- Include Footer -->
  <%@ include file="footer.jsp" %>

</body>
</html>
