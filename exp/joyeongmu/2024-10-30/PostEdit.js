import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, useLocation } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { fetchPostDetails, updatePost } from '../store/slices/postSlice';
import {
  Container,
  TextField,
  Button,
  Typography,
  Box,
  Grid,
  Paper,
} from '@mui/material';

const PostEdit = () => {
  const { postId } = useParams();
  const location = useLocation(); // 비밀번호
  const password = location.state.password;
  const navigate = useNavigate();
  const dispatch = useDispatch();

  // Redux
  const post = useSelector((state) => state.postState.post);
  const boardId = useSelector((state) => state.boardState.boardId);

  // local
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');

  useEffect(() => {
    console.log('postId:', postId);

    if (postId) {
      dispatch(fetchPostDetails({ postId, password }));
    } else {
      console.error('postId is undefined');
    }
  }, [dispatch, postId, password]);

  useEffect(() => {
    if (post) {
      setTitle(post.title || '');
      setContent(post.content || '');
      setUsername(post.username || '');
      setEmail(post.email || '');
    }
  }, [post]);

  const handleEditSubmit = () => {
    const postData = {
      board_id: boardId,
      username: username,
      password: password,
      email: email,
      title: title,
      content: content,
    };

    if (postId) {
      dispatch(updatePost({ postId, postData })).then(() => {
        navigate('/posts');
      });
    } else {
      console.error('Error');
    }
  };

  if (!post) {
    return (
      <Container>
        <Typography variant="h5" align="center">
          로딩 중...
        </Typography>
      </Container>
    );
  }

  return (
    <Container maxWidth="sm">
      <Paper elevation={3} sx={{ padding: 4, marginTop: 4 }}>
        <Typography variant="h5" gutterBottom>
          게시글 수정
        </Typography>
        <Box component="form" noValidate autoComplete="off">
          <TextField
            label="작성자"
            variant="outlined"
            fullWidth
            margin="normal"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <TextField
            label="이메일"
            variant="outlined"
            fullWidth
            margin="normal"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <TextField
            label="제목"
            variant="outlined"
            fullWidth
            margin="normal"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
          <TextField
            label="내용"
            variant="outlined"
            fullWidth
            margin="normal"
            multiline
            rows={4}
            value={content}
            onChange={(e) => setContent(e.target.value)}
          />
          <Grid container spacing={2} sx={{ marginTop: 2 }}>
            <Grid item xs={6}>
              <Button
                variant="contained"
                color="primary"
                fullWidth
                onClick={handleEditSubmit}
              >
                수정하기
              </Button>
            </Grid>
            <Grid item xs={6}>
              <Button
                variant="outlined"
                color="secondary"
                fullWidth
                onClick={() => navigate(-1)}
              >
                취소
              </Button>
            </Grid>
          </Grid>
        </Box>
      </Paper>
    </Container>
  );
};

export default PostEdit;
