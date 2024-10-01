import React from 'react';

function Header() {
  return (
    <header style={styles.header}>
      <h1>My App</h1>
      <nav>
        <a href="#home">Home</a>
        <a href="#about">About</a>
        <a href="#contact">Contact</a>
      </nav>
    </header>
  );
}

const styles = {
  header: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: '10px',
    backgroundColor: '#282c34',
    color: 'white'
  }
};

export default Header;