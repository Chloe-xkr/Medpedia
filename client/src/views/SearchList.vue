<template>
  <div>
    <v-container class="mt-3">
      <p class="text-h4">搜索结果</p>
      <v-card
        rounded="lg"
        tile
        v-for="entry in entries"
        :key="entry.id"
        class="my-2"
      >
        <v-card-title>
          <v-btn
            text
            class="black--text text-h6"
            :to="{ name: 'article', params: { id: entry.id } }"
            rel="prev"
            >{{ entry.title }}</v-btn
          >
        </v-card-title>
        <v-card-text>
          <span v-html="stripTags(entry.text).substr(0, 200) + '...'"></span>
        </v-card-text>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  computed: {
    ...mapState('category', ['entries']),
  },
  data() {
    return {}
  },
  methods: {
    stripTags(html) {
      let div = document.createElement('div')
      div.innerHTML = html
      return div.textContent || div.innerText || ''
    },
    excerpt(text, length) {
      return text.substr(0, length)
    },
  },
}
</script>

<style lang="scss" scoped></style>
