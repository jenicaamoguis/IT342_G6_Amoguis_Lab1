import React, { useEffect, useState } from "react";
import { getCurrentUser, logout } from "../utils/auth";

const Dashboard = () => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const currentUser = getCurrentUser();
    if (!currentUser) {
      // Redirect to login if not logged in
      window.location.href = "/login";
    } else {
      setUser(currentUser);
    }
  }, []);

  const handleLogout = () => {
    logout();
    window.location.href = "/login";
  };

  if (!user) return null; // Optionally show a loading spinner here

  return (
    <div className="dashboard-container">
      <h2>Welcome, {user.username}!</h2>
      <p>Email: {user.email}</p>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default Dashboard;
