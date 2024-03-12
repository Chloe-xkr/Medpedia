<template>
  <v-app>
    <nav-bar :name="name"></nav-bar>
    <v-main class="teal lighten-5">
      <message-bar></message-bar>
      <router-view></router-view>
    </v-main>
    <v-footer color="teal lighten-5" padless>
      <v-row justify="center" no-gutters>
        <v-col class="py-4 text-center" cols="12">
          {{ new Date().getFullYear() }} — <strong>{{ name }}</strong>
        </v-col>
      </v-row>
    </v-footer>
  </v-app>
</template>

<script>
import MessageBar from '@/components/MessageBar.vue'
import NavBar from '@/components/NavBar.vue'

export default {
  name: 'App',

  data() {
    return {
      name: '泰健',
    }
  },

  components: { MessageBar, NavBar },
}
</script>
