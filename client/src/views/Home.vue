<template>
  <v-container class="mt-3">
    <v-card class="scrollable" rounded="lg" elevation="0">
      <v-toolbar flat color="primary" dark>
        <v-toolbar-title>药品分类</v-toolbar-title>
      </v-toolbar>
      <v-tabs vertical>
        <v-tab v-for="category in selectedCategories" :key="category.id">
          {{ category.name }}
        </v-tab>

        <v-tab-item v-for="category in selectedCategories" :key="category.id">
          <v-container>
            <v-row>
              <v-col cols="9">
                <v-container>
                  <v-row>
                    <v-col
                      v-for="(subcategory, index) in category.childDeparment"
                      :key="index"
                      cols="3"
                    >
                      <router-link
                        :to="{
                          name: 'category',
                          params: {
                            id: subcategory.id,
                            subcategory: subcategory.name,
                          },
                        }"
                        rel="prev"
                      >
                        {{ subcategory.name }}
                      </router-link>
                    </v-col>
                  </v-row>
                </v-container>
              </v-col>
            </v-row>
          </v-container>
        </v-tab-item>
      </v-tabs>
    </v-card>
  </v-container>
</template>
<script>
import { mapState } from 'vuex'
export default {
  computed: {
    ...mapState('category', ['categories']),
    selectedCategories() {
      return this.categories.slice(0, 10)
    },
  },
  data() {
    return {}
  },

  // beforeRouteEnter(routeTo, ignored, next) {
  //   getCategory(routeTo, next)
  // },
  // beforeRouteUpdate(routeTo, ignored, next) {
  //   getCategory(routeTo, next)
  // },
}
</script>

<style scoped>
.scrollable {
  overflow-y: scroll;
}
</style>
