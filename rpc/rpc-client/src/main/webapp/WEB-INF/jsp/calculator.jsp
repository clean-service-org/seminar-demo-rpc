<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>RPC Calculator</title>
      <style>
        body {
          font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
          background-color: #f0f2f5;
          display: flex;
          justify-content: center;
          align-items: center;
          height: 100vh;
          margin: 0;
        }

        .container {
          background: #fff;
          padding: 2rem;
          border-radius: 12px;
          box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
          text-align: center;
          width: 320px;
        }

        h1 {
          margin-top: 0;
          color: #333;
        }

        .badge {
          background: #34a853;
          color: white;
          padding: 4px 12px;
          border-radius: 12px;
          font-size: 0.75rem;
          font-weight: bold;
          margin-left: 8px;
        }

        form {
          display: flex;
          flex-direction: column;
          gap: 1rem;
        }

        .inputs {
          display: flex;
          justify-content: space-between;
          gap: 1rem;
        }

        input,
        select {
          width: 100%;
          padding: 0.75rem;
          border-radius: 8px;
          border: 1px solid #ddd;
          font-size: 1rem;
        }

        button {
          background-color: #34a853;
          color: white;
          border: none;
          padding: 0.75rem;
          border-radius: 8px;
          font-size: 1rem;
          font-weight: bold;
          cursor: pointer;
          transition: background-color 0.2s;
        }

        button:hover {
          background-color: #2d8e47;
        }

        .result {
          margin-top: 1.5rem;
          padding: 1rem;
          background-color: #e6f4ea;
          border-radius: 8px;
        }

        .result h2 {
          margin: 0;
          font-size: 1.5rem;
          color: #333;
        }

        .error {
          color: #d93025;
          font-weight: bold;
        }
      </style>
    </head>

    <body>
      <div class="container">
        <h1>Calculator <span class="badge">RPC</span></h1>
        <form action="/calculate" method="POST">
          <div class="inputs">
            <input type="number" name="num1" required value="${not empty num1 ? num1 : 10}" step="any">
            <input type="number" name="num2" required value="${not empty num2 ? num2 : 5}" step="any">
          </div>
          <select name="operation">
            <option value="add" ${operation=='add' ? 'selected' : '' }>Add (+)</option>
            <option value="subtract" ${operation=='subtract' ? 'selected' : '' }>Subtract (-)</option>
            <option value="multiply" ${operation=='multiply' ? 'selected' : '' }>Multiply (×)</option>
            <option value="divide" ${operation=='divide' ? 'selected' : '' }>Divide (÷)</option>
          </select>
          <button type="submit">Calculate</button>
        </form>

        <c:if test="${not empty result or not empty error}">
          <div class="result">
            <c:choose>
              <c:when test="${not empty error}">
                <h2 class="error">${error}</h2>
              </c:when>
              <c:otherwise>
                <h2>${result}</h2>
              </c:otherwise>
            </c:choose>
          </div>
        </c:if>
      </div>
    </body>

    </html>