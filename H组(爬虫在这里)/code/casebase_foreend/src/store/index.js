import Vuex from "vuex";
import Vue from "vue";
import getters from "./getters";
import user from "./modules/user";
import fileData from "./modules/fileData";

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        user,
        fileData
    },
    tag: {
        isLogged: false,
        // 服务器地址
        serverAddress: ""
    },
    mutations: {
        set_userStatus(state, flag) {
            state.isLogged = flag;
        }
    },
    actions: {},
    getters
});