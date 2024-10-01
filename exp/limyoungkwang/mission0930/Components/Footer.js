import React from 'react';

function Footer() {
  return (
    <footer style={styles.footer}>
      <p>&copy; 2024 My App</p>
    </footer>
  );
}

const styles = {
  footer: {
    textAlign: 'center',
    padding: '10px',
    backgroundColor: '#282c34',
    color: 'white',
    position: 'absolute',
    width: '100%',
    bottom: '0'
  }
};

export default Footer;