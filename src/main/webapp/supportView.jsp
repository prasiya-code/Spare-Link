<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Support Form Data - Table</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background: linear-gradient(to bottom, #121e2d, #0a1622);
      color: white;
      margin: 0;
      padding: 20px;
      min-height: 100vh;
    }
    .container {
      max-width: 1000px;
      margin: 40px auto;
    }
    h1 {
      color: #0ee7ff;
      margin-bottom: 25px;
      text-align: center;
    }
    .data-table {
      width: 100%;
      border-collapse: collapse;
      background: linear-gradient(135deg, rgba(18, 30, 45, 0.8), rgba(10, 22, 34, 0.9));
      backdrop-filter: blur(10px);
      border: 1px solid rgba(14, 231, 255, 0.2);
      box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2), inset 0 0 2px rgba(14, 231, 255, 0.3);
      border-radius: 8px;
      overflow: hidden;
    }
    .data-table th, .data-table td {
      padding: 12px 15px;
      border-bottom: 1px solid rgba(255, 255, 255, 0.1);
      text-align: left;
    }
    .data-table th {
      background-color: rgba(14, 231, 255, 0.2);
      color: #0ee7ff;
    }
    .data-table td {
      color: #e0e0e0;
    }
    .long-text {
      white-space: pre-wrap;
      line-height: 1.5;
    }
    .circuit-line {
      position: absolute;
      width: 100%;
      height: 1px;
      background: linear-gradient(to right, transparent, rgba(14, 231, 255, 0.3), transparent);
      left: 0;
      z-index: -1;
    }
    .top-line { top: 50px; }
    .bottom-line { bottom: 50px; }
    .return-btn {
      display: inline-block;
      background-color: #0ee7ff;
      color: #0a1622;
      padding: 10px 20px;
      text-decoration: none;
      border-radius: 4px;
      font-weight: bold;
      margin-top: 20px;
    }
    .return-btn:hover {
      opacity: 0.9;
    }
  </style>
</head>
<body>
  <div class="circuit-line top-line"></div>
  <div class="container">
    <h1>Support Form Submission</h1>
    <table class="data-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Issue Type</th>
          <th>Full Name</th>
          <th>Email Address</th>
          <th>Contact Number</th>
          <th>Inquiry</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="support" items="${allSupports}">
          <tr>
            <td>${support.supportId}</td>
            <td>${support.issueType}</td>
            <td>${support.fullName}</td>
            <td>${support.email}</td>
            <td>${support.contactNo}</td>
            <td class="long-text">${support.inquiry}</td>
            <td>
              <a class="return-btn" href="SupportUpdate.jsp?id=${support.supportId}&issueType=${support.issueType}&fullName=${support.fullName}&email=${support.email}&contactNo=${support.contactNo}&inquiry=${support.inquiry}">Update</a>
            <!-- Delete Button -->
  <a class="return-btn" href="supportDelete?supportId=${support.supportId}" onclick="return confirm('Are you sure you want to delete this record?');">Delete</a>

            </td>
          </tr>
        </c:forEach>
        <c:if test="${empty allSupports}">
          <tr><td colspan="7" style="text-align:center; color: red;">No records found.</td></tr>
        </c:if>
      </tbody>
    </table>
    <div style="text-align: center;">
      <a href="support.html" class="return-btn">Back to Support Form</a>
    </div>
  </div>
  <div class="circuit-line bottom-line"></div>
</body>
</html>
