<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Register - SpareLink</title>
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
      <img src="images/Mobile login.png" alt="Login" class="object-cover w-full h-full" />
    </div>

    <!-- Right Form -->
    <div class="p-8 md:p-12">
      <h2 class="text-3xl font-bold text-cyan-400 mb-6">Create an Account</h2>

      <!-- Success or Error Message -->
      <%
        String error = request.getParameter("error");
        String success = request.getParameter("success");
        if ("email_exists".equals(error)) {
      %>
        <div class="bg-red-600 text-white p-3 rounded mb-4 text-sm">Email already exists. Try a different one.</div>
      <% } else if ("1".equals(success)) { %>
        <div class="bg-green-600 text-white p-3 rounded mb-4 text-sm">Registration successful! You can now log in.</div>
      <% } %>

      <!-- Registration Form -->
      <form action="register" method="post" class="space-y-4">
        <div>
          <label class="block text-sm font-medium mb-1">Full Name</label>
          <input type="text" name="full_name" required
            class="w-full bg-gray-800 border border-gray-700 text-gray-200 rounded px-4 py-2 focus:outline-none focus:border-cyan-400" />
        </div>
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
          Register
        </button>
      </form>

      <p class="mt-4 text-sm text-gray-400">Already have an account?
        <a href="sign-in.jsp" class="text-cyan-400 hover:underline">Login here</a>.
      </p>
    </div>
  </div>
</div>

<!-- Footer -->
 <%@ include file="footer.jsp" %>
</body>
</html>
