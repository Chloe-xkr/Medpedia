<template>
  <v-container class="mt-2">
    <v-row>
      <v-col cols="9">
        <article-main :article="article"></article-main>
      </v-col>
      <v-col cols="3">
        <v-card rounded="lg" elevation="0">
          <v-card-title>编辑词条</v-card-title>
          <v-card-text>
            <v-btn
              tile
              color="success"
              :to="{ name: 'edit', params: { id: id } }"
            >
              <v-icon left> mdi-pencil </v-icon>
              编辑
            </v-btn>
          </v-card-text>
        </v-card>
        <v-card rounded="lg" elevation="0" class="mt-3">
          <v-card-title>讨论与答疑</v-card-title>
          <v-card-text>
            <v-btn
              tile
              color="success"
              :to="{ name: 'discussion-list', params: { id: id } }"
            >
              进入讨论区
            </v-btn>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
    <v-row align="center" justify="space-around">
      <v-col cols="12">
        <v-card rounded="lg" elevation="0">
          <v-card-title>知识图谱</v-card-title>
          <v-card-text>
            <Kg :graph="graph" />
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import store from '@/store'
// eslint-disable-next-line no-unused-vars
import { mapGetters, mapState } from 'vuex'
import ArticleMain from '@/components/ArticleMain.vue'
import NProgress from 'nprogress'
import Kg from '@/components/Kg.vue'

function getArticleAndKg(routeTo, next) {
  const id = parseInt(routeTo.params.id)
  if (!id) next({ name: 'home' })

  store
    .dispatch('article/fetchArticle', id)
    .then(() => {
      store.commit('article/CLEAR_KG')
    })
    .then(() => {
      next()
    })
    .catch(() => {
      store.dispatch('message/push', {
        type: 'error',
        text: '获取文章失败。',
      })
      next({ name: 'home' }) // network issues
    })
    .finally(() => {
      NProgress.done()
    })
}

export default {
  components: {
    ArticleMain,
    Kg,
  },
  props: {
    id: {
      type: Number,
      required: true,
    },
  },

  data: () => ({}),

  computed: {
    ...mapState('article', ['article', 'graph']),
    // ...mapGetters('article', ['graph']),
  },
  beforeRouteEnter(routeTo, ignored, next) {
    getArticleAndKg(routeTo, next)
  },
  beforeRouteUpdate(routeTo, ignored, next) {
    getArticleAndKg(routeTo, next)
  },
}
</script>
