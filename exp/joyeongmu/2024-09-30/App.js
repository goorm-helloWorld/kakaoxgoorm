import { BrowserRouter, Outlet, Route, Routes } from 'react-router-dom';
import './App.css';
// 레이아웃
import NavBar from './components/NavBar';
import Footer from './components/Footer';
// 페이지
import MainPage from './Page/MainPage';

const Layout = () => {
    return (
        <>
            <NavBar />
            <br />
            <Outlet />
            <br />
            <Footer />
        </>
    );
};

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<MainPage />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
