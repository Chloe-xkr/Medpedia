import APIService from '@/services/APIService.js'

export const namespaced = true

export const state = {
  // The current logged-in user. Format (when not null):
  // {
  //   id: 1,
  //   userName: 'Justin Liu',
  //   email: 'my@email.com',
  //   type: 'user',
  //   token: ...
  // }
  user: null,
  searchHistory: [],
}

export const mutations = {
  SET_USER_DATA(state, userData) {
    let old_token = null
    const userString = localStorage.getItem('user')
    if (userString) {
      const userData = JSON.parse(userString)
      old_token = userData['token']
    }

    state.user = {
      id: userData.id,
      name: userData.name,
      email: userData.email,
      type: userData.isAdmin ? 'admin' : 'user',
      token: old_token,
    }
    localStorage.setItem('user', JSON.stringify(state.user))
    console.log(state.user.token)
    APIService.setAuth(state.user.token)
  },
  CLEAR_USER_DATA(state) {
    localStorage.removeItem('user')
    state.user = null
    APIService.clearAuth()
  },
  LOGIN(state, data) {
    state.user = {
      name: data.name,
      token: data.token,
    }
    localStorage.setItem('user', JSON.stringify(state.user))
    APIService.setAuth(data.token)
  },
  CLEAR_SEARCH_HISTORY(state) {
    state.searchHistory.splice(0, state.searchHistory.length)
  },
}

export const getters = {
  getCurrentUser(state) {
    return state.user
  },
}

export const actions = {
  login({ state, commit }, credentials) {
    return APIService.login(credentials)
      .then(({ data }) => {
        const name = data.split(' ')[0]
        const token = data.split(' ')[1]
        commit('LOGIN', { name: name, token: token })
      })
      .then(() => {
        return APIService.getUser(state.user.name)
      })
      .then(({ data }) => {
        commit('SET_USER_DATA', {
          ...data,
          name: data.userName,
        })
        commit('CLEAR_SEARCH_HISTORY')
        APIService.getSearchHistory(state.user.id).then((searchHistoryList) => {
          console.log(searchHistoryList)
          searchHistoryList.data.forEach((s) => {
            state.searchHistory.push(s.title)
          })
          localStorage.setItem('searchHistory', state.searchHistory)
        })
      })
  },
  // eslint-disable-next-line no-empty-pattern
  register({}, credentials) {
    console.log(credentials)
    return APIService.userCreate(credentials)
  },
  updateInfo({ commit }, info) {
    return APIService.userUpdate(info).then(({ data }) => {
      commit('SET_USER_DATA', data)
    })
  },
  logout({ commit }) {
    commit('CLEAR_USER_DATA')
    commit('CLEAR_SEARCH_HISTORY')
  },
  addSearchHistory({ state }, searchKey) {
    APIService.addSearchHistory(state.user.id, searchKey)
  },
}
