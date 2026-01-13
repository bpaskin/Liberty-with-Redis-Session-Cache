<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Redis Cache Session Persistence</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        .info-box {
            background-color: #e7f3ff;
            padding: 20px;
            border-radius: 5px;
            margin: 20px 0;
            border-left: 4px solid #2196F3;
        }
        .button {
            display: inline-block;
            padding: 12px 24px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin: 10px 5px;
            transition: background-color 0.3s;
        }
        .button:hover {
            background-color: #45a049;
        }
        .tech-stack {
            background-color: #f9f9f9;
            padding: 15px;
            border-radius: 5px;
            margin: 20px 0;
        }
        ul {
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Redis Cache Session Persistence Demo</h1>
        
        <div class="info-box">
            <h2>Welcome to the Redis Session Persistence Demo</h2>
            <p>This application demonstrates how to use <strong>Open Liberty</strong> with <strong>JCache</strong> 
               and <strong>Redis</strong> for distributed session persistence.</p>
        </div>

        <div class="tech-stack">
            <h3>Technology Stack:</h3>
            <ul>
                <li><strong>Open Liberty</strong> - Application server</li>
                <li><strong>JCache (JSR-107)</strong> - Caching API</li>
                <li><strong>Redisson</strong> - Redis Java client with JCache implementation</li>
                <li><strong>Redis</strong> - In-memory data store for session persistence</li>
                <li><strong>Maven</strong> - Build and dependency management</li>
            </ul>
        </div>

        <div class="info-box">
            <h3>Session Information:</h3>
            <p><strong>Session ID:</strong> <%= session.getId() %></p>
            <p><strong>Creation Time:</strong> <%= new java.util.Date(session.getCreationTime()) %></p>
            <p><strong>Is New Session:</strong> <%= session.isNew() %></p>
        </div>

        <div style="text-align: center; margin: 30px 0;">
            <a href="session" class="button">Test Session Persistence</a>
            <a href="session?action=set&key=welcome&value=Hello%20Redis!" class="button">Set Welcome Message</a>
        </div>

        <div class="info-box">
            <h3>How to Test:</h3>
            <ol>
                <li>Make sure Redis is running on localhost:6379</li>
                <li>Click "Test Session Persistence" to interact with sessions</li>
                <li>Add some session attributes</li>
                <li>Restart the Liberty server</li>
                <li>Return to the session test page</li>
                <li>Verify that your session data persisted across server restarts</li>
            </ol>
        </div>

        <div class="tech-stack">
            <h3>Configuration Files:</h3>
            <ul>
                <li><code>server.xml</code> - Liberty server configuration with sessionCache feature</li>
                <li><code>redisson-config.yaml</code> - Redis connection configuration</li>
                <li><code>pom.xml</code> - Maven dependencies including Redisson JCache</li>
                <li><code>web.xml</code> - Web application session configuration</li>
            </ul>
        </div>
    </div>
</body>
</html>