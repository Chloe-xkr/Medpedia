<template>
  <v-card min-height="70vh" rounded="lg" elevation="0">
    <v-card-title class="text-h4 mb-2">
      {{ article.title }}
    </v-card-title>
    <v-card-subtitle>分类：{{ typeName[article.type] }}</v-card-subtitle>
    <v-card-text class="black--text">
      <span id="content" v-html="article.text"></span>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  data: function () {
    return {
      typeName: {
        disease: '疾病',
        drug: '药物',
      },
    }
  },
  props: {
    article: {
      type: Object,
      required: true,
    },
  },
}
</script>

<style>
#content h1 {
  margin-bottom: 10px;
}
#content h2 {
  margin-bottom: 10px;
}
#content h3 {
  margin-bottom: 10px;
}
</style>
