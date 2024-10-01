import { BrowserRouter, Outlet, Route, Routes } from 'react-router-dom';
import './App.css';
// 레이아웃
import NavBar from './components/NavBar';
import Footer from './components/Footer';
// 페이지
import MainPage from './Page/MainPage';
// 랜덤 번호 추첨 페이지
import RandomNumber from './Page/RandomNumber';

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
          <Route path="random-number" element={<RandomNumber />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
