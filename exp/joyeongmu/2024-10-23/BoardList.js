import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

const BoardList = () => {
  const [boards, setBoards] = useState([]);

  const loadBoard = async (e) => {
    try {
      const response = await axios.get('http://localhost:8080/api/boards/all');
      console.log(response.data.response);
      setBoards(response.data.response);
    } catch (error) {
      console.log('error :' + error);
    }
  };

  useEffect(() => {
    loadBoard();
  }, []);

  return (
    <div>
      BoardList
      <ul>
        {boards.map((board) => (
          <li key={board.board_id}>
            <Link to={`/boards/${board.board_id}/posts`}>
              ID: {board.board_id}
              name: {board.board_name}
              status: {board.board_status}
            </Link>
          </li>
        ))}
      </ul>
      <Link to="/create-board">
        <button> 게시판 작성 </button>
      </Link>
      <Link to="/posts">
        <button> 전체 글 보러가기 </button>
      </Link>
    </div>
  );
};

export default BoardList;
