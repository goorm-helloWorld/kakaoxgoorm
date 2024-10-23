// App.js
import React from "react";
import { Routes, Route } from "react-router-dom";
import BoardList from "./components/BoardList";
import PostList from "./components/PostList";
import PostDetail from "./components/PostDetail";
import PostCreate from "./components/PostCreate";
import "./App.css";

const App = () => {
  return (
    <div>
      <h1>익명 질문 게시판</h1>
      <Routes>
        {/* Home - 게시판 목록 */}
        <Route path="/" element={<BoardList />} />

        {/* 게시글 목록 */}
        <Route path="/posts/:boardId" element={<PostList />} />

        {/* 게시글 상세 */}
        <Route path="/post/:id" element={<PostDetail />} />

        {/* 게시글 작성 */}
        <Route path="create-post" element={<PostCreate />} />
      </Routes>
    </div>
  );
};

export default App;
