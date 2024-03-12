import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import NProgress from 'nprogress'
import store from '@/store'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        displayName: '首页', // 有这一项的会被显示在顶部导航栏
      },
      beforeEnter(routeTo, ignored, next) {
        // const id = parseInt(routeTo.params.id)
        // if (!id) next({ name: 'home' })
        store
          .dispatch('category/fetchCategories')
          .then(() => {
            next()
          })
          .catch(() => {
            store.dispatch('message/push', {
              type: 'error',
              text: '获取entries失败。',
            })
            NProgress.done()
          })
      },
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('./views/Login.vue'),
      meta: {
        requiresNoAuth: true, // 不登录才能访问
        displayName: '登录',
      },
    },
    {
      path: '/signup',
      name: 'signup',
      component: () => import('./views/Signup.vue'),
      meta: {
        requiresNoAuth: true,
        displayName: '注册',
      },
    },
    {
      path: '/searchPage',
      name: 'searchpage',
      component: () => import('./views/SearchPage.vue'),
      meta: {
        requiresAuth: true,
        displayName: '搜索',
      },
    },
    {
      path: '/entries/:id',
      name: 'article',
      component: () => import('./views/Article.vue'),
      props: true,
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('./views/Profile.vue'),
      meta: {
        requiresAuth: true, // 登录后才能访问
      },
    },
    {
      path: '/searchresult',
      name: 'searchresult',
      component: () => import('./views/SearchList.vue'),
    },
    {
      path: '/categories/:id',
      name: 'category',
      component: () => import('./views/CategoryHasArticles.vue'),
      props: true,

      beforeEnter(routeTo, ignored, next) {
        const id = parseInt(routeTo.params.id)
        if (!id) next({ name: 'home' })
        store
          .dispatch('category/fetchEntries', id)
          .then(() => {
            next()
          })
          .catch(() => {
            store.dispatch('message/push', {
              type: 'error',
              text: '获取entries失败。',
            })
            NProgress.done()
          })
      },
    },
    {
      path: '/edit/:id',
      name: 'edit',
      component: () => import('./views/Edit.vue'),
      props: true,
      meta: {
        requiresAuth: true, // 登录后才能访问
      },
    },
    {
      path: '/discussion-list/:id',
      name: 'discussion-list',
      component: () => import('./views/DiscussionList.vue'),
      props: true,
    },
    {
      path: '/discussions/:id',
      name: 'discussion',
      component: () => import('./views/Discussion.vue'),
      props: true,
      meta: {
        requiresAuth: true, // 登录后才能访问
      },
    },
    {
      path: '/new/',
      name: 'new',
      component: () => import('./views/NewArticle.vue'),
      props: true,
      meta: {
        requiresAuth: true, // 登录后才能访问
        displayName: '新建文章',
      },
      beforeEnter(routeTo, ignored, next) {
        // const id = parseInt(routeTo.params.id)
        // if (!id) next({ name: 'home' })
        store
          .dispatch('category/fetchCategories')
          .then(() => {
            next()
          })
          .catch(() => {
            store.dispatch('message/push', {
              type: 'error',
              text: '获取entries失败。',
            })
            NProgress.done()
          })
      },
    },
  ],
})

router.beforeEach((to, from, next) => {
  NProgress.start()
  const loggedIn = localStorage.getItem('user')
  // protected route
  if (to.matched.some((record) => record.meta.requiresAuth) && !loggedIn) {
    store.dispatch('message/push', {
      type: 'error',
      text: '请登录。',
    })
    next('/login')
  }
  // routes hidden for logged-in users, e.g. login and register pages
  else if (
    to.matched.some((record) => record.meta.requiresNoAuth) &&
    loggedIn
  ) {
    next('/')
  } else {
    next()
  }
})

router.afterEach(() => {
  NProgress.done()
})

export default router
