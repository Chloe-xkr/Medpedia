<template>
  <v-container class="mt-2">
    <v-row>
      <v-col>
        <ckeditor
          :editor="editor"
          v-model="editorData"
          :config="editorConfig"
        ></ckeditor>
      </v-col>
    </v-row>
    <v-row align="center" justify="center">
      <v-btn tile class="mr-4" color="success" @click="updateArticle"
        >提交修改</v-btn
      >
    </v-row>
  </v-container>
</template>

<script>
import store from '@/store'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import router from '../router'

function fetchArticle(to, next) {
  store
    .dispatch('article/fetchArticle', to.params.id)
    .then(() => {
      to.params.editorData = store.state.article.article.text
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
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      editor: ClassicEditor,
      editorData: this.$store.state.article.article.text,
      editorConfig: {
        toolbar: {
          items: [
            'heading',
            '|',
            'bold',
            'italic',
            '|',
            'outdent',
            'indent',
            '|',
            'link',
            '|',
            'bulletedList',
            'numberedList',
            '|',
            'insertTable',
            'blockQuote',
            '|',
            'undo',
            'redo',
          ],
          shouldNotGroupWhenFull: true,
        },
      },
    }
  },
  computed: {
    article() {
      return {
        departmentId: null,
        id: this.id,
        text: this.editorData,
        title: this.$store.state.article.article.title,
        type: this.$store.state.article.article.type,
      }
    },
  },
  methods: {
    updateArticle() {
      store
        .dispatch('article/updateArticle', this.article)
        .then(() => {
          router.push({
            name: 'article',
            params: {
              id: this.id,
            },
          })
        })
        .catch((err) => {
          store.dispatch('message/push', {
            type: 'error',
            text: err.message,
          })
        })
    },
  },
  beforeRouteEnter(to, from, next) {
    fetchArticle(to, next)
  },
  beforeRouteUpdate(to, from, next) {
    fetchArticle(to, next)
  },
}
</script>
