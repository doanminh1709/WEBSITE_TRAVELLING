import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { publicRoutes } from './routers/router';
function App() {
  const isLogin = localStorage.getItem('isLogin')
  return (
    <Router>
    <div className="App">
      
        <Routes>
            {publicRoutes.map((route, index) => {
                const Layout = route.component
                return (
                    <Route
                        key={index}
                        path={route.path}
                        element={
                          <Layout />
                        }
                    />
                );
            })}
        </Routes>
    </div>
</Router>
  );
}

export default App;