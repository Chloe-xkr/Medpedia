<template>
  <div>
    <v-container fluid>
      <v-row class="justify-center">
        <v-col cols="5">
          <v-menu offset-y>
            <template v-slot:activator="{ on }">
              <v-text-field
                class="mt-3"
                label="查询"
                solo
                hide-details
                v-model="searchKey"
                autocomplete="off"
                v-on="on"
                prepend-inner-icon="mdi-magnify"
                @keyup.enter="submit"
              ></v-text-field>
            </template>
            <v-list v-if="completeData.length > 0" class="border-list" dense>
              <v-list-item
                v-for="(item, index) in completeData"
                :key="index"
                @click="itemClick(item)"
              >
                <v-list-item-title>{{ item }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-col>
      </v-row>
    </v-container>
    <v-container fluid>
      <v-row class="justify-center">
        <v-col cols="1" v-for="(item, index) in searchHistory1" :key="index">
          <v-btn @click="searchBy(item)" depressed>
            {{ item }}
          </v-btn>
        </v-col>
      </v-row>
      <v-row class="justify-center">
        <v-col cols="1" v-for="(item, index) in searchHistory2" :key="index">
          <v-btn @click="searchBy(item)" depressed>
            {{ item }}
          </v-btn>
        </v-col>
      </v-row>
    </v-container>

    <v-container fluid>
      <v-card class="mx-auto" max-width="500">
        <v-toolbar color="teal" dark>
          <v-toolbar-title>搜索热榜</v-toolbar-title>
        </v-toolbar>
        <v-list dense>
          <v-list-item-group v-model="selectItem" color="primary">
            <v-list-item
              v-for="(item, i) in hotList"
              :key="i"
              @click="searchBy(item.title)"
            >
              <v-icon color="red"> mdi-fire </v-icon>
              <v-list-item-content>
                <v-list-item-title
                  v-text="item.searchCounter"
                ></v-list-item-title>
              </v-list-item-content>

              <v-list-item-content>
                <v-list-item-title v-text="item.title"></v-list-item-title>
              </v-list-item-content>
              <v-list-item-icon>
                <v-icon v-text="item.title"></v-icon>
              </v-list-item-icon>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import APIService from '@/services/APIService.js'
import NProgress from 'nprogress'

export default {
  data() {
    return {
      searchKey: null,
      dataList: [], // 查询数据，一般由后端返回
      completeData: [],
      showSelect: false,
      hotList: [],
      selectItem: '',
      searchHistory1: [],
      searchHistory2: [],
    }
  },
  mounted() {
    APIService.getHotList()
      .then((hl) => {
        this.hotList = hl.data
      })
      .catch(() => {
        this.$store.dispatch('message/push', {
          type: 'error',
          text: '获取搜索热榜失败。',
        })
      })
    APIService.getSearchHistory(this.$store.state.user.user.id).then((data) => {
      let cnt = 0
      data.data.reverse()
      data.data.forEach((item) => {
        if (item.title !== null) {
          if (cnt < 5) this.searchHistory1.push(item.title)
          else if (cnt < 10) this.searchHistory2.push(item.title)
          cnt++
        }
      })
    })
  },
  watch: {
    // 用watch去监听 input框里面的值的变化，然后做出相应操作
    searchKey: 'inputHandle',
  },
  computed: {},
  methods: {
    itemClick(item) {
      this.searchKey = item
    },
    inputHandle(searchKey) {
      console.log(this.searchHistory)
      if (searchKey.trim() !== '') {
        this.showSelect = true
        this.completeData = []
        setTimeout(() => {
          this.getArticle()
        }, 300)
      } else {
        this.showSelect = false
        this.completeData = []
      }
    },
    getArticle() {
      APIService.fetchEntriesbyContent(this.searchKey)
        .then((articleList) => {
          console.log(articleList)
          articleList = articleList.data
          articleList.length = 7
          articleList.forEach((article) => {
            this.completeData.push(article.title)
          })
          this.completeData.length = 7
        })
        .catch(() => {
          this.$store.dispatch('message/push', {
            type: 'error',
            text: '获取搜索结果失败。',
          })
        })
    },
    submit() {
      NProgress.start()
      this.$store.dispatch('user/addSearchHistory', this.searchKey)
      this.$store
        .dispatch('category/fetchEntriesbyContent', this.searchKey)
        .then(() => {
          this.$router.push({ name: 'searchresult' })
        })
        .catch(() => {
          this.$store.dispatch('message/push', {
            type: 'error',
            text: '获取搜索结果失败。',
          })
          NProgress.done()
        })
    },
    searchBy(title) {
      this.searchKey = title
      this.submit()
    },
  },
}
</script>

<style scoped>
.input-search {
  width: 40%;
  margin: auto;
}
.width-20-percent {
  width: 20%;
}
.img-div {
  margin: 16vh 0 40px 0;
  text-align: center;
}
.v-menu__content {
  box-shadow: none !important;
}
.border-list {
  border: 1px solid #eee !important;
}
</style>
