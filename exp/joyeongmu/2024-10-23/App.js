import React from 'react';
import { Router, Routes, Route } from 'react-router-dom';
import PostCreate from './components/PostCreate';
import PostDetail from './components/PostDetail';
import BoardList from './components/BoardList';
import PostList from './components/PostList';
import './App.css';
import BoardCreate from './components/BoardCreate';

const App = () => {
  return (
    <div>
      <h2> 익명 질문 게시판</h2>
      <Routes>
        {/* 게시판 */}
        <Route path="/" element={<BoardList />}></Route>
        <Route path="/boards" element={<BoardList />}></Route>
        <Route path="/create-board" element={<BoardCreate />}></Route>
        {/* 게시글 */}
        <Route path="/boards/:boardId/posts" element={<PostList />} />
        <Route path="/posts" element={<PostList />}></Route>
        <Route path="/post/:id" element={<PostDetail />}></Route>
        <Route path="/create-post" element={<PostCreate />}></Route>
      </Routes>
    </div>
  );
};

export default App;
