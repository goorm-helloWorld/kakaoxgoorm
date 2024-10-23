import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import ApiService from '../services/ApiService';

const BoardCreate = () => {
  const [name, setName] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await ApiService.createBoard(name);
      navigate('/');
    } catch (error) {
      console.log('error : ' + error);
    }
  };
  return (
    <div>
      <h3>게시판 생성</h3>
      <form onClick={handleSubmit}>
        <div>
          <label>게시판 이름 : </label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          ></input>
        </div>
        <button type="submit"> 저장하기</button>
      </form>
    </div>
  );
};

export default BoardCreate;
