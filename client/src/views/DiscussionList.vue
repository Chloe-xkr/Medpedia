<!-- 每个词条的讨论区话题列表 -->
<template>
  <v-container class="mt-4">
    <v-dialog v-model="showNewDiscussion" width="600">
      <v-card>
        <v-form ref="newDiscussionForm">
          <v-card-title>新增讨论</v-card-title>
          <v-card-text>
            <v-text-field
              v-model="topic.title"
              label="主题"
              :rules="titleRules"
            ></v-text-field>
            <v-textarea
              v-model="topic.text"
              label="详细内容"
              :rules="textRules"
            ></v-textarea>
          </v-card-text>
          <v-card-actions>
            <v-btn @click="showNewDiscussion = false">取消</v-btn>
            <v-spacer></v-spacer>
            <v-btn class="primary" @click="submit">发布</v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>

    <v-row>
      <h1 class="text-h4 my-3 px-3">{{ article.title }}的讨论区</h1>
      <v-spacer></v-spacer>
      <v-btn class="mx-3 my-auto" @click="showNewDiscussion = true"
        >创建讨论</v-btn
      >
    </v-row>
    <v-row>
      <v-container>
        <v-card>
          <v-card-text>
            <v-row>
              <v-col cols="7">内容</v-col>
              <v-col cols="2">创建者</v-col>
              <v-col cols="2">创建时间</v-col>
              <v-col cols="1">回帖数</v-col>
            </v-row>
            <div v-if="discussions.length === 0">
              <v-divider class="my-3"></v-divider>
              <v-row>
                <v-col cols="12" class="text-center"> 暂无讨论 </v-col>
              </v-row>
            </div>
            <div v-for="discussion in discussions" :key="discussion.id">
              <v-divider class="my-3"></v-divider>
              <v-row>
                <v-col cols="7">
                  <router-link
                    class="text-h6"
                    :to="{ name: 'discussion', params: { id: discussion.id } }"
                    >{{ discussion.title }}</router-link
                  >
                  <p>{{ discussion.text }}</p>
                </v-col>
                <v-col cols="2">{{ discussion.creator.name }}</v-col>
                <v-col cols="2">{{
                  new Date(discussion.created).toLocaleString()
                }}</v-col>
                <v-col cols="1">{{ discussion.replies.length }}</v-col>
              </v-row>
            </div>
          </v-card-text>
        </v-card>
      </v-container>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters, mapState } from 'vuex'
import store from '@/store'

function fetchDiscussionList(to, next) {
  store
    .dispatch('article/fetchArticle', to.params.id)
    .then(() => {
      store.dispatch('discussion/fetchByEntryId', to.params.id)
    })
    .then(() => {
      next()
    })
    .catch((err) => {
      store.dispatch('message/push', {
        type: 'error',
        text: err.message,
      })
    })
}

export default {
  props: {
    id: {
      type: String,
      required: true,
    },
  },

  data: () => ({
    headers: ['内容', '创建者', '创建时间', ''],
    showNewDiscussion: false,
    topic: {
      title: null,
      text: null,
    },
    titleRules: [(v) => !!v || '请输入标题。'],
    textRules: [(v) => !!v || '请输入内容。'],
  }),

  computed: {
    ...mapState({
      article: (state) => state.article.article,
    }),
    ...mapGetters({
      discussions: 'discussion/discussionsSortedByCreated',
    }),
  },

  methods: {
    submit() {
      if (this.$refs.newDiscussionForm.validate()) {
        this.createDiscussion()
      }
    },

    createDiscussion() {
      this.$store
        .dispatch('discussion/addByEntryId', {
          ...this.topic,
          article_id: this.id,
        })
        .then(() => {
          this.$store.dispatch('message/push', {
            type: 'success',
            text: '讨论创建成功。',
          })
          this.showNewDiscussion = false
        })
    },
  },

  beforeRouteEnter(to, from, next) {
    fetchDiscussionList(to, next)
  },
  beforeRouteUpdate(to, from, next) {
    fetchDiscussionList(to, next)
  },
}
</script>

<style scoped>
a {
  text-decoration: none;
}
a:hover {
  text-decoration: underline;
}
</style>
