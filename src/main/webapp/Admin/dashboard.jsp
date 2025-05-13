<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.spareLink.model.SparePart" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>SpareLink - Dashboard</title>
  <script src="https://kit.fontawesome.com/46b998f154.js" crossorigin="anonymous"></script>
  <script src="https://cdn.tailwindcss.com"></script>
  <script defer src="https://cdn.jsdelivr.net/npm/alpinejs@3.x.x/dist/cdn.min.js"></script>
</head>

<body class="bg-gray-50 text-gray-200 font-sans">
<main class="bg-[#0f172a] text-gray-200 min-h-screen p-6 space-y-8">

  <!-- Title -->
  <div class="text-3xl font-bold text-white">
    <i class="fas fa-tachometer-alt text-[#06b6d4] mr-2"></i> Dashboard
  </div>

  <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">

    <!-- Total Parts -->
    <a href="Parts.jsp" class="block group">
      <div class="bg-[#1e293b] p-6 rounded-xl shadow transition hover:bg-[#334155] hover:scale-105 hover:border-l-4 hover:border-[#06b6d4]">
        <div class="text-gray-400 mb-2 text-sm group-hover:text-[#06b6d4]">Total Parts</div>
        <div class="text-3xl font-semibold group-hover:text-white">${totalParts}</div>
        <div class="mt-2"><i class="fas fa-microchip text-[#06b6d4] text-2xl group-hover:animate-pulse"></i></div>
      </div>
    </a>

    <!-- Low Stock Alerts -->
    <a href="LowStock.jsp" class="block group">
      <div class="bg-[#1e293b] p-6 rounded-xl shadow transition hover:bg-[#334155] hover:scale-105 hover:border-l-4 hover:border-red-400">
        <div class="text-gray-400 mb-2 text-sm group-hover:text-red-400">Low Stock Alerts</div>
        <div class="text-3xl font-semibold text-red-400 group-hover:text-white">${lowStockCount}</div>
        <div class="mt-2"><i class="fas fa-exclamation-triangle text-red-400 text-2xl group-hover:animate-pulse"></i></div>
      </div>
    </a>

    <!-- Total Categories -->
    <a href="Categories.jsp" class="block group">
      <div class="bg-[#1e293b] p-6 rounded-xl shadow transition hover:bg-[#334155] hover:scale-105 hover:border-l-4 hover:border-[#06b6d4]">
        <div class="text-gray-400 mb-2 text-sm group-hover:text-[#06b6d4]">Categories</div>
        <div class="text-3xl font-semibold text-red-400 group-hover:text-white">${totalCategories}</div> <!-- Optional: make dynamic -->
        <div class="mt-2"><i class="fas fa-layer-group text-[#06b6d4] text-2xl group-hover:animate-pulse"></i></div>
      </div>
    </a>

    <!-- Active Users -->
    <a href="Users.jsp" class="block group">
      <div class="bg-[#1e293b] p-6 rounded-xl shadow transition hover:bg-[#334155] hover:scale-105 hover:border-l-4 hover:border-[#06b6d4]">
        <div class="text-gray-400 mb-2 text-sm group-hover:text-[#06b6d4]">Total Users</div>
        <div class="text-3xl font-semibold group-hover:text-white">${totalUsers}</div> <!-- Optional: make dynamic -->
        <div class="mt-2"><i class="fas fa-users text-[#06b6d4] text-2xl group-hover:animate-pulse"></i></div>
      </div>
    </a>

  </div>

  <!-- Recent Transactions Table -->
  <div class="bg-[#1e293b] p-6 rounded-xl shadow">
    <div class="text-lg font-semibold mb-4">Recent Transactions</div>
    <div class="overflow-x-auto">
      <table class="w-full text-sm text-left">
        <thead>
          <tr class="text-gray-400 border-b border-gray-600">
            <th class="py-2 px-4">Part Name</th>
            <th class="py-2 px-4">Category</th>
            <th class="py-2 px-4">Action</th>
            <th class="py-2 px-4">Qty</th>
            <th class="py-2 px-4">Date</th>
          </tr>
        </thead>
        <tbody class="text-gray-300">
          <c:forEach var="tx" items="${recentTransactions}">
            <tr class="hover:bg-[#334155] transition">
              <td class="py-2 px-4">${tx.name}</td>
              <td class="py-2 px-4">${tx.categoryName}</td>
              <td class="py-2 px-4">
                <span class="${tx.action == 'Stock In' ? 'text-green-400' : 'text-red-400'}">${tx.action}</span>
              </td>
              <td class="py-2 px-4">${tx.quantity}</td>
              <td class="py-2 px-4">${tx.date}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

  <!-- Chart Placeholder -->
  <div class="bg-[#1e293b] p-6 rounded-xl shadow">
    <div class="text-lg font-semibold mb-4">Usage Trends</div>
    <div class="text-center text-gray-500 text-sm italic">
      📊 Chart goes here (Integrate with Chart.js or ApexCharts)
    </div>
    <div class="h-48 bg-[#0f172a] rounded mt-4 flex items-center justify-center">
      <span class="text-[#06b6d4]">[Chart Placeholder]</span>
    </div>
  </div>

</main>
</body>
</html>
