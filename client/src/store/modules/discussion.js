import APIService from '@/services/APIService'

export default {
  namespaced: true,

  state: {
    articleId: null,

    discussions: [
      {
        id: null,
        title: null,
        text: null,
        creator: {
          id: null,
          name: null,
        },
        created: null,
        replies: [
          {
            id: null,
            text: null,
            creator: {
              id: null,
              name: null,
            },
            created: null,
          },
        ],
      },
    ],

    discussion: {
      id: null,
      title: null,
      text: null,
      creator: {
        id: null,
        name: null,
      },
      created: null,
      replies: [
        {
          id: null,
          text: null,
          creator: {
            id: null,
            name: null,
          },
          created: null,
        },
      ],
    },
  },

  getters: {
    discussionsSortedByCreated(state) {
      return [...state.discussions].sort((a, b) => {
        return Date.parse(a.created) - Date.parse(b.created)
      })
    },
  },

  mutations: {
    SET_ARTICLE_ID(state, id) {
      state.articleId = id
    },
    SET_DISCUSSIONS(state, data) {
      state.discussions = data
    },
    SET_DISCUSSION(state, data) {
      state.discussion = data
    },
  },

  actions: {
    fetchByEntryId({ commit }, id) {
      return APIService.fetchDiscussionsByEntryId(id).then(({ data }) => {
        commit('SET_ARTICLE_ID', id)
        commit('SET_DISCUSSIONS', data)
      })
    },

    fetchOneById({ commit }, id) {
      return APIService.fetchDiscussionById(id).then(({ data }) => {
        commit('SET_DISCUSSION', data)
      })
    },

    addByEntryId({ rootState, dispatch, commit }, data) {
      console.log('here')
      APIService.addDiscussion({
        article_id: data.article_id,
        user_id: rootState.user.user.id,
        title: data.title,
        post_text: data.text,
        publish_time: new Date().toISOString(),
      })
        .then(({ data }) => {
          commit('SET_DISCUSSIONS', data)
        })
        .catch((err) => {
          dispatch(
            'message/push',
            {
              type: 'error',
              text: err.message,
            },
            {
              root: true,
            }
          )
        })
    },

    addComment({ rootState, dispatch, commit }, data) {
      APIService.addReply({
        main_post_id: data.main_post_id,
        user_id: rootState.user.user.id,
        post_text: data.text,
        reply_time: new Date().toISOString(),
      })
        .then(({ data }) => {
          commit('SET_DISCUSSION', data)
        })
        .catch((err) => {
          dispatch(
            'message/push',
            {
              type: 'error',
              text: err.message,
            },
            {
              root: true,
            }
          )
        })
    },
  },
}
