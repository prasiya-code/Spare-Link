<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Login - SpareLink</title>
  <script src="https://kit.fontawesome.com/46b998f154.js" crossorigin="anonymous"></script>
  <script src="https://cdn.tailwindcss.com"></script>
  <script defer src="https://cdn.jsdelivr.net/npm/alpinejs@3.x.x/dist/cdn.min.js"></script>
</head>
<body class="bg-[#0f172a] text-gray-300 font-sans">

<!-- Main Container -->
<div class="min-h-screen flex items-center justify-center px-4">
  <div class="bg-[#1e293b] rounded-2xl shadow-2xl overflow-hidden grid grid-cols-1 md:grid-cols-2 w-full max-w-5xl">

    <!-- Left Image -->
    <div class="hidden md:block">
      <img src="images/computer.png" alt="Login" class="object-cover w-full h-full" />
    </div>

    <!-- Right Form -->
    <div class="p-8 md:p-12">
      <h2 class="text-3xl font-bold text-cyan-400 mb-6">Welcome Back</h2>

      <!-- Error/Success Message -->
      <%
        String error = request.getParameter("error");
        String logout = request.getParameter("logout");
        if ("invalid".equals(error)) {
      %>
        <div class="bg-red-600 text-white p-3 rounded mb-4 text-sm">Invalid email or password. Please try again.</div>
      <% } else if ("1".equals(logout)) { %>
        <div class="bg-green-600 text-white p-3 rounded mb-4 text-sm">You have been logged out successfully.</div>
      <% } %>

      <!-- Login Form -->
      <form action="login" method="post" class="space-y-4">
        <div>
          <label class="block text-sm font-medium mb-1">Email</label>
          <input type="email" name="email" required
            class="w-full bg-gray-800 border border-gray-700 text-gray-200 rounded px-4 py-2 focus:outline-none focus:border-cyan-400" />
        </div>
        <div>
          <label class="block text-sm font-medium mb-1">Password</label>
          <input type="password" name="password" required
            class="w-full bg-gray-800 border border-gray-700 text-gray-200 rounded px-4 py-2 focus:outline-none focus:border-cyan-400" />
        </div>
        <button type="submit"
          class="w-full bg-cyan-600 hover:bg-cyan-500 text-white font-semibold py-2 rounded shadow transition duration-300">
          Login
        </button>
      </form>

      <p class="mt-4 text-sm text-gray-400">Don't have an account?
        <a href="register.jsp" class="text-cyan-400 hover:underline">Register here</a>.
      </p>
    </div>
  </div>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp" />
</body>
</html>
