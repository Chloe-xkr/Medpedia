<template>
  <v-app-bar app dark color="primary" clipped-left>
    <v-toolbar-title class="mr-5">{{ name }}</v-toolbar-title>
    <v-btn
      v-for="link in linksPermitted"
      :key="`${link.name}-header-link`"
      text
      rounded
      :to="link.path.length ? link.path : '/'"
    >
      {{ link.meta.displayName }}
    </v-btn>
    <v-spacer></v-spacer>
    <search />
    <v-btn v-if="user" text rounded :to="{ name: 'profile' }">
      {{ user.name }}
    </v-btn>
    <v-btn v-else text rounded :to="{ name: 'login' }">游客</v-btn>
  </v-app-bar>
</template>

<script>
import { mapGetters } from 'vuex'
import Search from '@/components/Search.vue'

export default {
  components: { Search },

  props: ['name'],
  computed: {
    links() {
      return this.$router.getRoutes().filter((link) => link.meta.displayName)
    },
    linksPermitted() {
      return this.user
        ? this.links.filter((link) => !link.meta.requiresNoAuth)
        : this.links.filter((link) => !link.meta.requiresAuth)
    },
    ...mapGetters('user', {
      user: 'getCurrentUser',
    }),
  },
}
</script>

<style scoped></style>
