import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

class ApiService {
  fetchPosts() {
    return axios.get(`${API_BASE_URL}/posts/all`);
  }
  createPost(postData) {
    return axios.post(`${API_BASE_URL}/posts`, postData);
  }
  fetchPostDetails(postId, password) {
    return axios.post(`${API_BASE_URL}/posts/view`, { postId, password });
  }
  createBoard(name) {
    return axios.post(`${API_BASE_URL}/boards`, { name });
  }
  fetchPostsByBoard(boardId) {
    return axios.get(`${API_BASE_URL}/boards/${boardId}/posts`);
  }
}

export default new ApiService();
// const apiServiceInsatance = new ApiService();
// export default apiServiceInsatance;
