//导入全局模块
import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import Vuex from "vuex";
import axios from "axios";
import VueCookie from "vue-cookies";
import Antd from "ant-design-vue";
import "ant-design-vue/dist/antd.css";

Vue.use(router);
Vue.use(Vuex);
Vue.use(Antd);
Vue.use(store);
Vue.use(VueCookie);

Vue.prototype.$axios = axios;

Vue.config.productionTip = false;

new Vue({
    el: "#app",
    router,
    store: store,
    data: function () {
        return {};

    },
    components: {App},
    render: h => h(App)
}).$mount("#app");


// 页面跳转修改标题
router.beforeEach((to, from, next) => {
    if (to.meta.title) {
        document.title = to.meta.title;
    }
    next();
});