import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import Home from './Home/Home';
import './App.css'

function AnimatedRoutes() {
  const location = useLocation();

  return (
    <Routes location={location} key={location.pathname}>
      <Route path="/" element={<Home />} />
    </Routes>
  )
}

// Main App Component
function App() {
  return (
    <Router>
      <AnimatedRoutes />
    </Router>
  );
}

export default App
