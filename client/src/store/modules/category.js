import APIService from '@/services/APIService.js'

export const namespaced = true

export const state = {
  categories: [
    {
      id: 1,
      name: 'cat1',
      subcategories: [
        { id: 1, name: 'subcat1' },
        { id: 2, name: 'subcat2' },
      ],
    },
    {
      id: 2,
      name: 'cat2',
      subcategories: [
        { id: 3, name: 'subcat3' },
        { id: 4, name: 'subcat4' },
      ],
    },
  ],

  entries: [
    { id: 1, title: 'm1', text: 'dnjqwkdnjkq' },
    { id: 2, title: 'm2', text: 'dnjqwkdnjkq' },
  ],
}
export const getters = {
  getCategories(state) {
    return state.categories
  },
  // getSubcategories(state) {
  //   return state.subcategories
  // },
  getEntries(state) {
    return state.entries
  },
}
export const mutations = {
  SET_CATS(state, cats) {
    state.categories = cats
  },
  // SET_SUBCATS(state, subcats) {
  //   state.subcategories = subcats
  // },
  SET_ENTRIES(state, entries) {
    state.entries = entries
  },
}

export const actions = {
  fetchCategories({ commit }) {
    return APIService.fetchCategories().then((response) => {
      commit('SET_CATS', response.data)
    })
  },
  // fetchSubcategories({ commit }, id) {
  //   return APIService.fetchSubcategories(id).then(({ data }) => {
  //     commit('SET_SUBCATS', data)
  //   })
  // },
  fetchEntries({ commit }, id) {
    return APIService.fetchEntries(id).then(({ data }) => {
      commit('SET_ENTRIES', data)
    })
  },
  fetchEntriesbyContent({ commit }, text) {
    return APIService.fetchEntriesbyContent(text).then(({ data }) => {
      commit('SET_ENTRIES', data)
    })
  },
}
