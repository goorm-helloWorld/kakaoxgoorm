import React, { useState } from 'react';

const RandomNumber = () => {
  const [randomNumber, setRandomNumber] = useState(0);

  const createRandomNumber = () => {
    const random = Math.floor(Math.random() * 9999);
    setRandomNumber(random);
  };

  return (
    <div className="random-num-form">
      <span className="random-num-info">{randomNumber}</span>
      <button className="submit-btn" onClick={createRandomNumber}>
        {' '}
        랜덤 숫자 출력{' '}
      </button>
    </div>
  );
};

export default RandomNumber;
