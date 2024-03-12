import Vue from 'vue'
import Vuex from 'vuex'

import * as user from '@/store/modules/user.js'
import message from '@/store/modules/message.js'
import article from '@/store/modules/article.js'
import * as category from '@/store/modules/category.js'
import discussion from '@/store/modules/discussion.js'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    user,
    message,
    article,
    category,
    discussion,
  },
})
