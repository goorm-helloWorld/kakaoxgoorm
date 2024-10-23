import React, { useState } from "react";

function RandomNumber(props) {
  const [randomNumber, setRandomNumber] = useState(0);

  const generateRandomNumber = () => {
    const newRandomNumber = Math.floor(Math.random() * 45) + 1; // 1부터 100 사이의 랜덤 숫자 생성
    setRandomNumber(newRandomNumber); // 상태 업데이트
  };

  return (
    <div>
      <h3>현재 랜덤 번호 : {randomNumber}</h3>
      <button onClick={generateRandomNumber}>랜덤 번호 생성</button>
    </div>
  );
}

export default RandomNumber;
