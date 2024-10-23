### 필요한 API

board 저장

특정 boardId를 이용하여 post 조회 List 반환

### 🍀 board 저장 API 작성

boardService 에서 jpaRepository에 정의된 save() 메서드를 활용한다.

```java
@Transactional
    public void create(BoardCreateReqDto reqDto) {
        boardRepository.save(Board.of(
                reqDto.getName(),
                Board.Status.REGISTERED.getKey()
        ));
    }
```

### 🍀 boardId를 이용하여 post 리스트를 반환하는 API를 구성

특정 게시판의 게시물만 조회하기 위해서는 새로운 API가 필요하다.

Restful 하게 해당 자원 표현을 위해 boardController에 작성하였다.

postRepository에 boardId를 통한 조회 메서드를 작성한다.

```java
List<Post> findAllByBoardIdOrderByIdDesc(Long boardId);
```

boardService에서 해당 board의 post List를 반환한다.

```java
public List<PostViewResDto> findAllPostsByBoardId(Long id) {
        return postRepository.findAllByBoardIdOrderByIdDesc(id).stream()
                .map(PostViewResDto::new)
                .collect(Collectors.toList());
    }
```

boardController 에 API를 작성한다. (엔드포인트 -> /api/board/{boardId}posts)

```java
@GetMapping("/{boardId}/posts")
    public ResponseEntity<?> getBoardPosts(@PathVariable("boardId") Long id) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK, boardService.findAllPostsByBoardId(id)));
    }
```

### ⚛️ boardCreate 컴포넌트를 작성한다.

exp 요구 조건에 따르면 home page ("/")의 위치는 boardList 가 랜더링된다.

따라서 작성 완료 후 "/" 로 리다이렉트 되도록 구성한다.

```jsx
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

```

ApiService 의 요청 엔드포인트는 다음과 같다.

```jsx
createBoard(name) {
    return axios.post(`${API_BASE_URL}/boards`, { name });
  }
```

### ⚛️ PostList 조건 추가

postList 는 전체 글 목록을 useEffect를 통해 최초에 렌더링시 요청 받아왔다.

만약에 boardId를 포함한 url로 postList를 렌더링 했다면 boardId를 통해 특정 boardId의 post List를 받아올 수 있도록

axios 요청을 진행해야한다.

먼저 시나리오를 흐름을 위해 App.js를 수정 해준다.

```jsx
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

```

postList를 렌더링하는 라우터는 총 2개가 존재한다.

```jsx
 <Route path="/boards/:boardId/posts" element={<PostList />} />
 <Route path="/posts" element={<PostList />}></Route>
```

해당 두가지 경우에 맞게 postList를 수정해준다. 크게 변경되는 요소는 없고 boardId 유무만 판단하였다.

```jsx
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

```

ApiService에 정의된 내용은 아래와 같다.

```jsx
fetchPostsByBoard(boardId) {
    return axios.get(`${API_BASE_URL}/boards/${boardId}/posts`);
  }
```

### ⚛️ Home("/") 은 boardList로 렌더링

app.js를 통해 루트 경로를 boardList로 변경하였다.

그렇다면 루트 경로 특정 게시판 게시물, 게시판 작성, 전체 글 보기 총 3가지를 진행해야한다.

Link 를 통해서 진행하였다.

```jsx
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
```

### 마치며

특정 게시판을 클릭하면 해당 게시판에 등록된 게시물을 조회 할 수 있게 되었으며, 게시물 작성을 하면 baordList로 리다이렉트 되어 새로 작성된 board를 확인할 수 있다.