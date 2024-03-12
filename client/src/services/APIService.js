import axios from 'axios'

const apiClient = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json',
  },
  timeout: 10000,
})

export default {
  // Authentication
  setAuth(token) {
    apiClient.defaults.headers.common['Authorization'] = `Bearer ${token}`
  },
  clearAuth() {
    delete apiClient.defaults.headers.common['Authorization']
  },
  login(user) {
    return apiClient.post('/api/users/login', user)
  },
  userCreate(newUser) {
    console.log(newUser)
    return apiClient.post('/api/users/register', newUser)
  },
  userUpdate(info) {
    return apiClient.patch('/api/users/' + info.id, info.data)
  },
  getUser(username) {
    return apiClient.get('/api/users/getUser', {
      params: {
        userName: username,
      },
    })
  },

  // Article
  fetchArticle(id) {
    return apiClient.get('/api/entries/' + id)
  },
  postArticle(data) {
    return apiClient.post('/api/entries', data)
  },
  updateArticle(id, data) {
    return apiClient.put('/api/entries/' + id, data)
  },
  deleteArticle(id) {
    return apiClient.delete('/api/entries/' + id)
  },
  //Category
  fetchCategories() {
    return apiClient.get('/api/categories/')
  },
  // fetchSubcategories(id) {
  //   return apiClient.get('/api/subcategories/' + id)
  // },
  fetchEntries(id) {
    return apiClient.get('/api/entriesofcat/' + id)
  },
  getKg(id) {
    return apiClient.get('/api/kg/' + id)
  },
  fetchEntriesbyContent(name) {
    var x
    x = apiClient.get('/api/entries/findEntriesLike', {
      params: {
        text: name,
      },
    })
    console.log(x)
    return x
  },

  // 讨论区
  fetchDiscussionsByEntryId(id) {
    return apiClient.get('/api/posts/' + id)
  },

  fetchDiscussionById(id) {
    return apiClient.get('/api/posts/one/' + id)
  },

  addDiscussion(data) {
    return apiClient.post('/api/posts/', data)
  },

  addReply(data) {
    return apiClient.post('/api/posts/reply/', data)
  },
  addSearchHistory(userid, searchKey) {
    let data = {
      userId: userid,
      title: searchKey,
    }
    return apiClient.post('/api/addSearchHistory/', data)
  },
  getSearchHistory(userid) {
    return apiClient.get('/api/getSearchHistory/' + userid)
  },
  getHotList() {
    return apiClient.get('/api/getHotList')
  },
}
