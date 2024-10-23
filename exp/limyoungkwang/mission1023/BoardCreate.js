import React, { useState } from "react";
import ApiService from "../services/ApiService";

const BoardCreate = ({ onBoardCreated }) => {
  const [boardName, setBoardName] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await ApiService.createBoard(boardName);
      console.log(response.data);
      onBoardCreated();
      setBoardName("");
    } catch (error) {
      console.error("Error creating board: ", error);
    }
  };

  return (
    <div>
      <h3>새 게시판 만들기</h3>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={boardName}
          onChange={(e) => setBoardName(e.target.value)}
          placeholder="게시판 이름"
          required
        />
        <button type="submit">게시판 추가</button>
      </form>
    </div>
  );
};

export default BoardCreate;
