import Vue from 'vue'
import App from './App'
import store from './store'
import uView from "uview-ui";

import { myRequest } from './common/promiseHttp.js'

Vue.use(uView);
Vue.prototype.$store=store;
Vue.prototype.$myRequest=myRequest


Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
