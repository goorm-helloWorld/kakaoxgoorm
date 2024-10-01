import React from 'react';

function Sidebar() {
  return (
    <aside style={styles.sidebar}>
      <ul>
        <li>카테고리 1</li>
        <li>카테고리 2</li>
        <li>카테고리 3</li>
      </ul>
    </aside>
  );
}

const styles = {
  sidebar: {
    width: '200px',
    padding: '10px',
    backgroundColor: '#f4f4f4'
  }
};

export default Sidebar;