<template>
  <div>
    <v-text-field
      class="mt-3"
      label="查询"
      dense
      v-model="content"
      prepend-inner-icon="mdi-magnify"
      @keyup.enter="submit"
    ></v-text-field>
  </div>
</template>

<script>
import NProgress from 'nprogress'
export default {
  data() {
    return {
      content: '',
    }
  },
  methods: {
    submit() {
      NProgress.start()
      this.$store
        .dispatch('category/fetchEntriesbyContent', this.content)
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
  },
}
</script>

<style lang="scss" scoped></style>
