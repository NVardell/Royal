import logo from './logo.svg';
import './App.css';

async function goHome() {
  console.log("Button Clicked ~ Running Go Home Function")
  const response = await fetch('/api/hello');
  // console.log(response)
  const body = await response.json();
  console.log(body)
  // ... more stuff
}

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
      <button onClick={goHome}>Click Me</button>
    </div>
  );
}

export default App;
