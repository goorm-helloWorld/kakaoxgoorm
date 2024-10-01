import './App.css';
import Header from './Components/Header';
import Sidebar from './Components/Sidebar';
import FeaturedSection from './Components/FeaturedSection';
import Footer from './Components/Footer';

function App() {
  return (
    <div style={styles.app}>
      <Header />
      <div style={styles.mainContent}>
        <Sidebar />
        <FeaturedSection />
      </div>
      <Footer />
    </div>
  );
}

const styles = {
  app: {
    textAlign: 'center',
    display: 'flex',
    flexDirection: 'column',
    minHeight: '100vh'
  },
  mainContent: {
    display: 'flex',
    flexGrow: 1
  }
};

export default App;
