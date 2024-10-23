import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import ApiService from '../services/ApiService';
import Post from './Post';

const PostList = () => {
  const [posts, setPosts] = useState([]);
  const { boardId } = useParams();

  const loadPosts = async () => {
    try {
      let response;
      if (boardId) {
        response = await ApiService.fetchPostsByBoard(boardId);
      } else {
        response = await ApiService.fetchPosts();
      }
      setPosts(response.data.response);
    } catch (error) {
      console.log('error :' + error);
    }
  };

  useEffect(() => {
    loadPosts();
  }, [boardId]);

  return (
    <div>
      <h3>게시글 목록</h3>
      <div className="post-list">
        {posts.length > 0 ? (
          posts.map((post) => <Post key={post.id} post={post} />)
        ) : (
          <p>게시글이 없습니다.</p>
        )}
        <hr />
        <Link to="/create-post">
          <button> 게시글 작성 </button>
        </Link>
      </div>
    </div>
  );
};

export default PostList;
