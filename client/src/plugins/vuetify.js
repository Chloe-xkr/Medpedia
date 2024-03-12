import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import colors from 'vuetify/lib/util/colors'

Vue.use(Vuetify)

export default new Vuetify({
  icons: {
    iconfont: 'mdi',
  },
  theme: {
    themes: {
      light: {
        primary: colors.teal.base,
        secondary: colors.indigo.base,
        accent: colors.teal.accent4,
        error: colors.deepPurple.accent1,
        warning: colors.deepPurple.lighten3,
        info: colors.indigo.lighten2,
        success: colors.teal.accent4,
      },
    },
  },
})
