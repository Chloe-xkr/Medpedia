<template>
  <v-container fluid fill-height>
    <v-row class="align-center justify-center">
      <v-col>
        <v-card width="400" class="mx-auto my-5">
          <v-card-title>
            <h1 class="display-1">登录</h1>
          </v-card-title>
          <v-card-text>
            <v-form ref="loginForm">
              <v-text-field
                label="用户名"
                v-model="user.userName"
                prepend-icon="mdi-account-circle"
                :rules="nameRules"
                required
                validate-on-blur
              />
              <v-text-field
                :type="showPassword ? 'text' : 'password'"
                label="密码"
                v-model="user.password"
                prepend-icon="mdi-lock"
                :rules="passwordRules"
                :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                @click:append="showPassword = !showPassword"
                required
                validate-on-blur
              />
            </v-form>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-btn light color="success" :to="{ name: 'signup' }">注册</v-btn>
            <v-spacer></v-spacer>
            <v-btn color="info" @click="submit">登录</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import NProgress from 'nprogress'

export default {
  data() {
    return {
      user: {
        userName: '',
        password: '',
      },
      error: null,
      showPassword: false,
      nameRules: [(v) => !!v || '请输入用户名。'],
      passwordRules: [(v) => !!v || '请输入密码。'],
    }
  },

  methods: {
    submit() {
      if (this.$refs.loginForm.validate()) {
        this.login()
      }
    },
    login() {
      NProgress.start()

      this.$store
        .dispatch('user/login', this.user)
        .then(() => {
          this.$store.dispatch('message/push', {
            type: 'success',
            text: '登录成功。',
          })
          this.$router.push({ name: 'home' })
        })
        .catch((err) => {
          if (err.response && err.response.data.message) {
            this.$store.dispatch('message/push', {
              type: 'error',
              text:
                err.response.data.message === 'INCORRECT_EMAIL_OR_PASSWORD'
                  ? '邮箱或密码错误。'
                  : err.response.data.message,
            })
            this.password = ''
            this.$v.$reset() //?
          } else {
            this.$store.dispatch('message/push', {
              type: 'error',
              text: '发生了未知错误。',
            })
          }
          NProgress.done()
        })
    },
  },
}
</script>

<style scoped></style>
