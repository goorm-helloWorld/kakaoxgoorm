import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import BoardCreate from "./BoardCreate";

const BoardList = () => {
  const [boards, setBoards] = useState([]);
  const navigate = useNavigate();

  const loadBoards = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/board/all");
      console.log(response.data);
      setBoards(response.data);
    } catch (e) {
      console.error("Error fetching boards: ", e);
    }
  };

  const handleBoardCreated = () => {
    loadBoards();
  };

  useEffect(() => {
    loadBoards();
  }, []);

  return (
    <div>
      <h3>게시판 목록</h3>
      <ul>
        {boards.map((board) => (
          <li
            key={board.board_id}
            onClick={() => navigate(`/posts/${board.board_id}`)}
          >
            ID : {board.board_id} <br />
            게시판 이름 : {board.board_name} <br />
            상태 : {board.status} <br />
          </li>
        ))}
      </ul>
      <BoardCreate onBoardCreated={handleBoardCreated} />
    </div>
  );
};

export default BoardList;
