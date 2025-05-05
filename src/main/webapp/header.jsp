		<%@ page language="java" contentType="text/html; charset=UTF-8"
		    pageEncoding="UTF-8"%>
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
		<body class="bg-gray-50 text-gray-200 font-sans">
		
		  <!-- Header -->
		  <!-- Header -->
<header class="bg-[#0f172a] text-white shadow-2xl z-50" x-data="{ mobileOpen: false }">
  <div class="containe px-4 py-4 flex flex-col md:flex-row md:items-center md:justify-between gap-4">

    <!-- Logo + Mobile Toggle -->
    <div class="flex items-center justify-between w-full md:w-auto">
      <!-- Logo -->
      <div class="text-2xl font-extrabold flex items-center gap-2">
        <i class="fas fa-cogs text-[#06b6d4]"></i>
        <span>SpareLink</span>
      </div>

      <!-- Mobile Nav Toggle -->
      <div class="md:hidden">
        <button @click="mobileOpen = !mobileOpen">
          <i class="fas fa-bars text-white text-2xl"></i>
        </button>
      </div>
    </div>

    <!-- Desktop Nav -->
    <nav class="hidden md:flex gap-4 font-medium">
      <a href="#" class="bg-[#1e293b] px-4 py-2 rounded-lg hover:bg-[#06b6d4] transition-all">Home</a>
      <a href="#" class="bg-[#1e293b] px-4 py-2 rounded-lg hover:bg-[#06b6d4] transition-all">Categories</a>
      <a href="#" class="bg-[#1e293b] px-4 py-2 rounded-lg hover:bg-[#06b6d4] transition-all">Contact</a>
    </nav>

    <!-- Desktop Search + Profile + Cart -->
    <div class="hidden md:flex items-center gap-2">
      <div class="flex">
        <input
          type="text"
          placeholder="Search spare parts..."
          class="md:w-64 px-4 py-2 rounded-l-lg bg-[#1e293b] text-white placeholder-gray-400 focus:outline-none"
        />
        <button class="bg-[#06b6d4] px-4 py-2 rounded-r-lg hover:bg-cyan-400">
          <i class="fas fa-search text-white"></i>
        </button>
      </div>

      <!-- Profile Icon -->
      <a href="#" class="bg-[#1e293b] px-4 py-2 rounded-lg hover:bg-[#06b6d4] transition-all">
        <i class="fas fa-user text-white text-lg"></i>
      </a>

      <!-- Cart Icon with Badge -->
      <a href="#" class="relative bg-[#1e293b] px-4 py-2 rounded-lg hover:bg-[#06b6d4] transition-all">
        <i class="fas fa-shopping-cart text-white text-lg"></i>
        <span class="absolute top-0 right-0 inline-block w-4 h-4 text-xs text-center text-white bg-red-500 rounded-full">3</span>
      </a>
    </div>
  </div>

  <!-- Mobile Menu with Search and Animation -->
  <div
    x-show="mobileOpen"
    x-transition:enter="transition ease-out duration-300"
    x-transition:enter-start="opacity-0 -translate-y-2"
    x-transition:enter-end="opacity-100 translate-y-0"
    x-transition:leave="transition ease-in duration-300"
    x-transition:leave-start="opacity-100 translate-y-0"
    x-transition:leave-end="opacity-0 -translate-y-2"
    class="md:hidden px-4 pb-4 space-y-2"
  >
    <!-- Mobile Search -->
    <div class="flex">
      <input
        type="text"
        placeholder="Search..."
        class="w-full px-4 py-2 rounded-l-lg bg-[#1e293b] text-white placeholder-gray-400 focus:outline-none"
      />
      <button class="bg-[#06b6d4] px-4 py-2 rounded-r-lg hover:bg-cyan-400">
        <i class="fas fa-search text-white"></i>
      </button>
    </div>

    <!-- Menu Links -->
    <a href="#" class="block py-2 px-3 bg-[#1e293b] rounded hover:bg-[#06b6d4]">Home</a>
    <a href="#" class="block py-2 px-3 bg-[#1e293b] rounded hover:bg-[#06b6d4]">Categories</a>
    <a href="#" class="block py-2 px-3 bg-[#1e293b] rounded hover:bg-[#06b6d4]">Contact</a>
    <a href="#" class="block py-2 px-3 bg-[#1e293b] rounded hover:bg-[#06b6d4]">View Cart</a>
  </div>
</header>


</body>
		
		</html>
