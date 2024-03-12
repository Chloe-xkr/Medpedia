import APIService from '@/services/APIService.js'

export default {
  namespaced: true,
  state: {
    article: null,
    kg: [],
    graph: {
      nodes: [],
      links: [],
    },
  },
  getters: {},
  mutations: {
    SET(state, article) {
      state.article = article
    },
    SET_KG(state, params) {
      const root_id = params.root_id
      const kg = params.data

      const root = state.graph.nodes.find((item) => item.id === root_id)
      console.log(root.id, root.title)
      for (const entry of kg) {
        console.log('-->', entry.id, entry.title)
        let node = state.graph.nodes.find((item) => item.id === entry.id)
        if (!node) {
          state.graph.nodes.push({
            id: entry.id,
            group: entry.type === 'drug' ? 1 : 2,
            title: entry.title,
          })
          state.graph.links.push({
            source: root.id,
            target: entry.id,
            value: 5,
          })
        }
      }
    },
    CLEAR(state) {
      state.article = null
    },
    CLEAR_KG(state) {
      state.kg = []
      state.graph = {
        nodes: [],
        links: [],
      }
    },
    INIT_KG(state) {
      state.graph.nodes.push({
        id: state.article.id,
        title: state.article.title,
        group: state.article.type === 'drug' ? 1 : 2,
      })
    },
  },
  actions: {
    createArticle({ commit }, id) {
      return APIService.postArticle(id).then(({ data }) => {
        commit('SET', data)
      })
    },
    fetchArticle({ commit }, id) {
      return APIService.fetchArticle(id).then(({ data }) => {
        commit('SET', data)
      })
    },
    updateArticle({ commit }, data) {
      return APIService.updateArticle(data.id, data).then(({ data }) => {
        commit('SET', data)
      })
    },
    async getKg({ state, commit, dispatch }) {
      commit('CLEAR_KG')
      commit('INIT_KG')

      await dispatch('_$expand', {
        root_id: state.article.id,
        depth: 1,
      })
    },
    async _$expand({ dispatch, commit }, params) {
      const response = await APIService.getKg(params.root_id)

      commit('SET_KG', {
        root_id: params.root_id,
        data: response.data,
      })

      if (params.depth > 0) {
        for (const entry of response.data) {
          await dispatch('_$expand', {
            depth: params.depth - 1,
            root_id: entry.id,
          })
        }
      }
    },
  },
}
