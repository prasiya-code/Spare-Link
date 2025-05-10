<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en" x-data="{ open: false, selectedProduct: {}, quantity: 1 }">
<head>
  <meta charset="UTF-8">
  <title>SpareLink - Home</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <script src="https://kit.fontawesome.com/46b998f154.js" crossorigin="anonymous"></script>
  <script defer src="https://cdn.jsdelivr.net/npm/alpinejs@3.x.x/dist/cdn.min.js"></script>
</head>

<body class="bg-gray-100 text-gray-800 font-sans">

  <%@ include file="header.jsp" %>

  <main class="max-w-7xl mx-auto px-4 py-12">
    <h1 class="text-3xl font-bold text-center mb-10 text-gray-700">Featured Spare Parts</h1>

    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-8">
      <c:forEach var="product" items="${spare_parts}">
        <div 
          class="bg-white rounded-xl shadow-lg overflow-hidden hover:shadow-2xl transition cursor-pointer"
          @click="open = true; selectedProduct = {
            image: '${pageContext.request.contextPath}/images/${product.id}.jpg',
            name: '${product.name}',
            price: '${product.price}',
            description: '${product.description}',
            brandName: '${product.brandName}',
            categoryName: '${product.categoryName}'
          }; quantity = 1;">
          <img src="${pageContext.request.contextPath}/images/${product.id}.jpg" alt="${product.name}" class="w-full h-48 object-contain p-4">
          <div class="p-4">
            <h3 class="text-lg font-semibold mb-2">${product.name}</h3>
            <p class="text-cyan-600 font-bold text-lg mb-4">LKR ${product.price}</p>
            <button class="bg-cyan-600 text-white px-4 py-2 rounded w-full hover:bg-cyan-700 transition">
              <i class="fas fa-cart-plus mr-2"></i>Add to Cart
            </button>
          </div>
        </div>
      </c:forEach>
    </div>
  </main>

  <!-- Modal -->
  <div 
    x-show="open" 
    x-cloak 
    class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
    <div class="bg-white p-6 rounded-xl shadow-2xl max-w-4xl w-full relative">
      <button @click="open = false" class="absolute top-3 right-4 text-gray-500 hover:text-red-600 text-2xl font-bold">
        &times;
      </button>
      <div class="flex items-center">
        <div class="w-1/2">
          <img :src="selectedProduct.image" class="w-full h-auto object-contain mb-4 rounded-md">
        </div>
        <div class="w-1/2 pl-8">
          <h2 class="text-2xl font-semibold text-gray-800 mb-2" x-text="selectedProduct.name"></h2>
          <p class="text-gray-600 mb-2" x-text="selectedProduct.description"></p>
          <p class="text-cyan-600 font-bold text-lg mb-4">LKR <span x-text="selectedProduct.price"></span></p>
          <p class="text-gray-500 mb-2">Brand: <span x-text="selectedProduct.brandName"></span></p>
          <p class="text-gray-500 mb-4">Category: <span x-text="selectedProduct.categoryName"></span></p>
          <div class="flex items-center space-x-3 mb-4">
            <button @click="quantity > 1 ? quantity-- : quantity" class="px-3 py-1 bg-gray-200 rounded hover:bg-gray-300">-</button>
            <span x-text="quantity" class="text-lg font-semibold"></span>
            <button @click="quantity++" class="px-3 py-1 bg-gray-200 rounded hover:bg-gray-300">+</button>
          </div>
          <button class="bg-green-600 text-white w-full py-2 rounded hover:bg-green-700 mb-2">Buy Now</button>
          <button class="bg-cyan-600 text-white w-full py-2 rounded hover:bg-cyan-700">Add to Cart</button>
        </div>
      </div>
    </div>
  </div>

  <%@ include file="footer.jsp" %>
</body>
</html>
