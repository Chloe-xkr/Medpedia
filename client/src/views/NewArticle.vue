<template>
  <v-container class="mt-2">
    <v-form ref="newArticleForm">
      <v-row>
        <v-col cols="12">
          <v-text-field
            label="文章标题"
            v-model="article.title"
            :rules="rules"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="6">
          <v-select
            label="药物类型"
            v-model="article.type"
            :items="types"
            :rules="rules"
          ></v-select>
        </v-col>
        <v-col cols="6">
          <v-select
            label="相关科室"
            v-model="article.departmentId"
            :items="categories"
            :rules="rules"
          ></v-select>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <ckeditor
            :editor="editor"
            v-model="article.text"
            :config="editorConfig"
          ></ckeditor>
        </v-col>
      </v-row>
      <v-row align="center" justify="center">
        <v-btn tile class="mr-4" color="success" @click="submit"
          >提交文章</v-btn
        >
      </v-row>
    </v-form>
  </v-container>
</template>

<script>
import store from '@/store'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import router from '../router'

export default {
  props: {
    id: {
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      rules: [(v) => !!v || '请填写此项。'],
      editor: ClassicEditor,
      article: {
        title: '',
        type: null,
        departmentId: null,
        text: '',
      },
      types: [
        {
          text: '疾病',
          value: 'disease',
        },
        {
          text: '药物',
          value: 'drug',
        },
      ],
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
    categories() {
      let all = []
      for (let cat of this.$store.state.category.categories) {
        for (let subcat of cat.childDeparment) {
          all.push({
            text: cat.name + ' - ' + subcat.name,
            value: subcat.id,
          })
        }
      }
      return all
    },
  },
  methods: {
    submit() {
      if (this.$refs.newArticleForm.validate()) {
        this.createArticle()
      }
    },
    createArticle() {
      this.article.id = Math.floor(Math.random() * 100000) + 50000
      store
        .dispatch('article/createArticle', this.article)
        .then(() => {
          store.dispatch('message/push', {
            type: 'success',
            text: '创建成功！',
          })
          router.push({
            name: 'article',
            params: {
              id: this.$store.state.article.article.id,
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
}
</script>
