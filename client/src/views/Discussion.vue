<template>
  <v-container class="mt-4">
    <v-row>
      <h1 class="text-h4 my-3 px-3">讨论主题</h1>
    </v-row>
    <v-row>
      <v-container>
        <v-card tile>
          <v-card-title>{{ discussion.title }}</v-card-title>
          <v-card-text>
            <div class="text-body-1 grey--text text--darken-4">
              {{ discussion.text }}
            </div>
            <p class="mt-2 mb-n1">
              {{ discussion.creator.name }} 发布于
              {{ new Date(discussion.created).toLocaleString() }}
            </p>
          </v-card-text>
        </v-card>
        <p class="text-h5 my-5">回复</p>
        <v-card tile v-if="discussion.replies.length === 0" class="mt-4">
          <v-card-text class="text-center"> 暂无回复 </v-card-text>
        </v-card>
        <v-card
          tile
          v-for="reply in discussion.replies"
          :key="reply.id"
          class="mt-4"
        >
          <v-card-text>
            <div class="text-body-1 grey--text text--darken-4">
              {{ reply.text }}
            </div>
            <p class="mt-2 mb-n1">
              {{ reply.creator.name }} 发布于
              {{ new Date(reply.created).toLocaleString() }}
            </p>
          </v-card-text>
        </v-card>

        <v-form class="mt-8" ref="newDiscussionForm">
          <v-textarea
            tile
            solo
            name="input-7-4"
            label="参与讨论"
            v-model="replyText"
            :rules="replyRules"
          ></v-textarea>
          <v-btn class="primary" dark @click="submit">提交讨论</v-btn>
        </v-form>
      </v-container>
    </v-row>
  </v-container>
</template>

<script>
import { mapState } from 'vuex'
import store from '@/store'

function fetchDiscussion(to, next) {
  store
    .dispatch('discussion/fetchOneById', to.params.id)
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
  params: {
    id: {
      type: String,
      required: true,
    },
  },
  data: () => ({
    replyText: null,
    replyRules: [(v) => !!v || '请输入回复内容。'],
  }),
  computed: {
    ...mapState({
      discussion: (state) => state.discussion.discussion,
    }),
  },

  methods: {
    submit() {
      if (this.$refs.newDiscussionForm.validate()) {
        this.createReply()
      }
    },

    createReply() {
      this.$store
        .dispatch('discussion/addComment', {
          text: this.replyText,
          main_post_id: this.discussion.id,
        })
        .then(() => {
          this.$store.dispatch('message/push', {
            type: 'success',
            text: '回复成功。',
          })
          this.showNewDiscussion = false
        })
    },
  },

  beforeRouteEnter(to, from, next) {
    fetchDiscussion(to, next)
  },
  beforeRouteUpdate(to, from, next) {
    fetchDiscussion(to, next)
  },
}
</script>
