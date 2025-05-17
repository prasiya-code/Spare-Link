<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  
  <title>SpareLink - Support</title>
  
  <script src="https://kit.fontawesome.com/46b998f154.js" crossorigin="anonymous"></script>
  <script src="https://cdn.tailwindcss.com"></script>
  <script defer src="https://cdn.jsdelivr.net/npm/alpinejs@3.x.x/dist/cdn.min.js"></script>
  <style>
    .input-glow {
      position: absolute;
      inset: 0;
      pointer-events: none;
      border-radius: 0.375rem;
      opacity: 0;
      box-shadow: 0 0 15px rgba(14, 231, 255, 0.5);
      transition: opacity 0.3s ease;
    }

    input:focus + .input-glow,
    textarea:focus + .input-glow {
      opacity: 1;
    }

    select {
      appearance: none;
      background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='white' viewBox='0 0 24 24'%3E%3Cpath d='M7 10l5 5 5-5z'/%3E%3C/svg%3E");
      background-repeat: no-repeat;
      background-position: right 10px center;
      background-size: 20px;
    }

    .circuit-box {
      background: linear-gradient(135deg, rgba(18, 30, 45, 0.8) 0%, rgba(10, 22, 34, 0.9) 100%);
      backdrop-filter: blur(10px);
      border: 1px solid rgba(14, 231, 255, 0.2);
      box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2), inset 0 0 2px rgba(14, 231, 255, 0.3);
    }
  </style>
</head>
<body class="bg-gray-50 text-gray-200 font-sans">
		
  <!-- Header -->
  <%@ include file="header.jsp" %>
  
  <!-- Main Content -->
  <main class="container" style="padding: 3rem 0;">
    <div style="max-width: 800px; margin: 0 auto;">
      <div class="circuit-box" style="border-radius: 0.75rem; overflow: hidden; padding: 1.5rem 2.5rem; margin-bottom: 2rem;">
        <h1 style="font-size: 2rem; font-weight: bold; color: white; margin-bottom: 0.5rem;">Support Us</h1>
        <p style="color: #ccc; margin-bottom: 2rem;">Need help or have questions? Fill out this form and our team will get back to you as soon as possible.</p>
        
        <div id="success-message" style="background-color: rgba(6, 214, 160, 0.2); border: 1px solid rgba(6, 214, 160, 0.5); color: white; padding: 1rem; border-radius: 0.5rem; margin-bottom: 1.5rem; display: none;">
          Thank you for contacting us! Your support request has been submitted successfully. We'll get back to you soon.
        </div>
        
        
        <form action="supportInsertServlet"method="post"id="support-form" style="display: flex; flex-direction: column; gap: 1.5rem;">
          <div>
            <label style="display: block; color: #ccc; margin-bottom: 0.5rem;" for="issueType">Issue Type</label>
            <select 
              id="issueType" 
              name="issueType" 
              style="width: 100%; padding: 0.5rem 1rem; border-radius: 0.375rem; background-color: rgba(31, 41, 55, 0.5); border: 1px solid #4B5563; color: white;"
              required
            >
              <option value="General Inquiry">General Inquiry</option>
              <option value="Technical Support">Technical Support</option>
              <option value="Billing Issue">Billing Issue</option>
              <option value="Feature Request">Feature Request</option>
              <option value="Bug Report">Bug Report</option>
            </select>
          </div>
          
          <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 1.5rem;">
            <div>
              <label style="display: block; color: #ccc; margin-bottom: 0.5rem;" for="name">Full Name</label>
              <div style="position: relative;">
                <input 
                  type="text" 
                  id="name" 
                  name="fullName" 
                  style="width: 100%; padding: 0.5rem 1rem; border-radius: 0.375rem; background-color: rgba(31, 41, 55, 0.5); border: 1px solid #4B5563; color: white;"
                  required
                />
                <div class="input-glow"></div>
              </div>
            </div>
            
            <div>
              <label style="display: block; color: #ccc; margin-bottom: 0.5rem;" for="email">Email Address</label>
              <div style="position: relative;">
                <input 
                  type="email" 
                  id="email" 
                  name="email" 
                  style="width: 100%; padding: 0.5rem 1rem; border-radius: 0.375rem; background-color: rgba(31, 41, 55, 0.5); border: 1px solid #4B5563; color: white;"
                  required
                />
                <div class="input-glow"></div>
              </div>
            </div>
          </div>
          
          <div>
  <label style="display: block; color: #ccc; margin-bottom: 0.5rem;" for="phone">Contact Number</label>
  <div style="position: relative;">
    <input 
      type="tel" 
      id="phone" 
      name="contactNo" 
      pattern="0[0-9]{9}" 
      title="Phone number must be 10 digits starting with 0 (e.g., 0123456789)"
      style="width: 100%; padding: 0.5rem 1rem; border-radius: 0.375rem; background-color: rgba(31, 41, 55, 0.5); border: 1px solid #4B5563; color: white;"
      placeholder="0XXXXXXXXX"
      required
    />
    <div class="input-glow"></div>
    <p style="font-size: 0.875rem; color: #9CA3AF; margin-top: 0.25rem;">Must be 10 digits starting with 0</p>
  </div>
</div>

          
          <div>
            <label style="display: block; color: #ccc; margin-bottom: 0.5rem;" for="inquiry">Your Inquiry</label>
            <div style="position: relative;">
              <textarea 
                id="inquiry" 
                name="inquiry" 
                rows="5" 
                style="width: 100%; padding: 0.5rem 1rem; border-radius: 0.375rem; background-color: rgba(31, 41, 55, 0.5); border: 1px solid #4B5563; color: white; resize: vertical;"
                required
              ></textarea>
              <div class="input-glow"></div>
            </div>
          </div>
          
          <div style="display: flex; justify-content: flex-end;">
            <button 
              type="submit" 
              id="submit-button"
              style="background-color: #0ee7ff; color: #0a1622; padding: 0.75rem 1.5rem; border-radius: 0.375rem; font-weight: 500; border: none; cursor: pointer; transition: opacity 0.2s; display: flex; align-items: center;"
            >
              Submit Inquiry
            </button>
          </div>
        </form>
      </div>
      
      <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 2rem; margin-top: 3rem;">
        <div class="circuit-box" style="padding: 1.5rem; border-radius: 0.75rem;">
          <h2 style="font-size: 1.25rem; font-weight: bold; color: #0ee7ff; margin-bottom: 1rem;">Contact Information</h2>
          <ul style="display: flex; flex-direction: column; gap: 1rem;">
            <li style="display: flex; align-items: center;">
              <span style="color: #0ee7ff; margin-right: 0.75rem;">📞</span>
              <span>+1 (800) 123-4567</span>
            </li>
            <li style="display: flex; align-items: center;">
              <span style="color: #0ee7ff; margin-right: 0.75rem;">📧</span>
              <span>support@sparelink.com</span>
            </li>
            <li style="display: flex; align-items: center;">
              <span style="color: #0ee7ff; margin-right: 0.75rem;">📍</span>
              <span>Auto City, USA</span>
            </li>
          </ul>
        </div>
        
        <div class="circuit-box" style="padding: 1.5rem; border-radius: 0.75rem;">
          <h2 style="font-size: 1.25rem; font-weight: bold; color: #0ee7ff; margin-bottom: 1rem;">Business Hours</h2>
          <ul style="display: flex; flex-direction: column; gap: 0.5rem;">
            <li style="display: flex; justify-content: space-between;">
              <span>Monday - Friday:</span>
              <span>9:00 AM - 6:00 PM</span>
            </li>
            <li style="display: flex; justify-content: space-between;">
              <span>Saturday:</span>
              <span>10:00 AM - 4:00 PM</span>
            </li>
            <li style="display: flex; justify-content: space-between;">
              <span>Sunday:</span>
              <span>Closed</span>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </main>

  <!-- Toast Notification -->
  <div id="toast-container" style="position: fixed; bottom: 20px; right: 20px; z-index: 1000;"></div>

  <!-- Footer -->

<%@ include file="footer.jsp" %>

</body>
</html>
