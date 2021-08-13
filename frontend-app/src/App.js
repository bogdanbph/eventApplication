import React, { useState } from 'react';
import LoginForm from './components/LoginForm';
import LoginService from './service/LoginService';

function App() {

  const adminUser = {
    username: "bogdanbph@gmail.com",
    password: "12345"
  }

  const [user, setUser] = useState({username: ""});
  const [error, setError] = useState("");

  const Login = details => {
    console.log(details);

    LoginService.submitLoginForm(details.email, details.password);
    setUser({
      username: details.email
    });
  }

  const Logout = () => {
    setUser({
      username: ""
    })
  }

  return (
    <div className="App">
      {(user.username != "") ? (
        <div className="welcome">
          <h2>Welcome, <span>{user.username}</span></h2>
          <button onClick={Logout}>Logout</button>
        </div>
      ) : (
        <LoginForm Login={Login} error={error}/>
      )}
    </div>
  );
}

export default App;
