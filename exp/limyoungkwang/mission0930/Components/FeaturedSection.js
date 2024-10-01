import React from 'react';

function FeaturedSection() {
  return (
    <section style={styles.section}>
      <h2>추천 콘텐츠</h2>
      <p>리버풀 현재 1위</p>
    </section>
  );
}

const styles = {
  section: {
    padding: '20px',
    backgroundColor: '#eaeaea',
    margin: '10px 0'
  }
};

export default FeaturedSection;