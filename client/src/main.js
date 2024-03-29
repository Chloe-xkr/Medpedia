import Vue from 'vue'
import './plugins/vuetify'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import store from './store'
import upperFirst from 'lodash/upperFirst'
import camelCase from 'lodash/camelCase'
import './assets/styles/nprogress.css'
import Vuelidate from 'vuelidate'
import axios from 'axios'
import CKEditor from '@ckeditor/ckeditor5-vue2'

Vue.use(CKEditor)
Vue.use(Vuelidate)

const requireComponent = require.context(
  // The relative path of the components folder
  './components',
  // Whether or not to look in subfolders
  false,
  // The regular expression used to match base component filenames
  /Base[A-Z]\w+\.(vue|js)$/
)

requireComponent.keys().forEach((fileName) => {
  // Get component config
  const componentConfig = requireComponent(fileName)

  // Get PascalCase name of component
  const componentName = upperFirst(
    camelCase(
      // Gets the file name regardless of folder depth
      fileName
        .split('/')
        .pop()
        .replace(/\.\w+$/, '')
    )
  )

  // Register component globally
  Vue.component(
    componentName,
    // Look for the component options on `.default`, which will
    // exist if the component was exported with `export default`,
    // otherwise fall back to module's root.
    componentConfig.default || componentConfig
  )
})
Vue.config.productionTip = false

new Vue({
  created() {
    const userString = localStorage.getItem('user')
    if (userString) {
      const userData = JSON.parse(userString)
      this.$store.commit('user/SET_USER_DATA', userData)
    }
    axios.interceptors.response.use(
      (response) => response,
      (error) => {
        if (error.response.status === 401) {
          this.$store.dispatch('user/logout')
          this.$store.dispatch('message/push', {
            text: '登录失效，请重新登录。',
            type: 'error',
          })
        }
        return Promise.reject(error)
      }
    )
  },
  vuetify,
  router,
  store,
  render: (h) => h(App),
}).$mount('#app')
