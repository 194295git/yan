import { createRouter, createWebHashHistory } from 'vue-router'

const router = createRouter({
  history: createWebHashHistory(), // hash模式：createWebHashHistory，history模式：createWebHistory
  routes: [
    {
      path: '/',
      redirect: '/chat'
    },
    {
      path: '/info',
      name: 'info',
      component: () => import(/* webpackChunkName: "home" */ '@/views/Info.vue'),
      meta: {
        index: 1
      }
    },
    {
      path: '/other',
      name: 'other',
      component: () => import(/* webpackChunkName: "home" */ '@/views/Other.vue'),
      meta: {
        index: 1
      }
    },
    {
      path: '/home',
      name: 'home',
      component: () => import(/* webpackChunkName: "home" */ '@/views/Home.vue'),
      meta: {
        index: 1
      }
    },
    {
      path: '/login',
      name: 'login',
      component: () => import(/* webpackChunkName: "login" */ '@/views/Login.vue'),
      meta: {
        index: 1
      }
    },
    {
      path: '/me',
      name: 'me',
      component: () => import(/* webpackChunkName: "login" */ '@/views/Me.vue'),
      meta: {
        index: 1
      }
    },
    {
      path: '/chat',
      name: 'chat',
      component: () => import(/* webpackChunkName: "login" */ '@/views/Chat.vue'),
      meta: {
        index: 1
      }
    },
    {
      path: '/chatdetail',
      name: 'chatdetail',
      component: () => import(/* webpackChunkName: "login" */ '@/views/ChatDetail.vue'),
      meta: {
        index: 1
      }
    },
    {
      path: '/circle',
      name: 'circle',
      component: () => import(/* webpackChunkName: "login" */ '@/views/Circle.vue'),
      meta: {
        index: 1
      }
    },
    {
      path: '/question',
      name: 'question',
      component: () => import(/* webpackChunkName: "login" */ '@/views/Question.vue'),
      meta: {
        index: 1
      }
    },
    {
      path: '/answer',
      name: 'answer',
      component: () => import(/* webpackChunkName: "login" */ '@/views/Answer.vue'),
      meta: {
        index: 1
      }
    },
    {
      path: '/majorWatch',
      name: 'majorWatch',
      component: () => import(/* webpackChunkName: "login" */ '@/views/Major.vue'),
      meta: {
        index: 1
      }
    },
    {
      path: '/majorWatchSon',
      name: 'majorWatchSon',
      component: () => import(/* webpackChunkName: "login" */ '@/views/MajorWatchSon.vue'),
      meta: {
        index: 1
      }
    },
    {
      path: '/recommend',
      name: 'recommend',
      component: () => import(/* webpackChunkName: "login" */ '@/views/Recommend.vue'),
      meta: {
        index: 1
      }
    },
    {
      path: '/majorQuestion',
      name: 'majorQuestion',
      component: () => import(/* webpackChunkName: "login" */ '@/views/MajorQuestion.vue'),
      meta: {
        index: 1
      }
    },
    
    {
      path: '/course',
      name: 'course',
      component: () => import(/* webpackChunkName: "login" */ '@/views/Course.vue'),
      meta: {
        index: 1
      }
    },
  ]
})

export default router