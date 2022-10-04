import Vue from "vue";
import axios from "axios";
import {VueAxios} from "axios";
import {message, notification} from "ant-design-vue";
import store from "../store";
import router from "../router";

const service = axios.create({
    baseURL: process.env.NODE_ENV === "production" ? "" : "http://localhost:8081",
    withCredentials: true
});

const err = (error) => {
    if (error.response) {
        const data = error.response.data;
        const token = Vue.ls.get("ACCESS_TOKEN");
        if (error.response.status === 403) {
            notification.error({
                message: "Forbidden",
                description: data.message
            });
        }
        if (error.response.status === 401 && !(data.result && data.result.isLogin)) {
            notification.error({
                message: "Unauthorized",
                description: "Authorization verification failed"
            });
            if (token) {
                store.dispatch("Logout").then(() => {
                    setTimeout(() => {
                        window.location.reload();
                    }, 1500);
                });
            }
        }
    }
    return Promise.reject(error);
};

//request incerceptor
service.interceptors.request.use((config) => {
    const requestConfig = {
        ...config,
        url: `${config.url}`,
    };
    const token = localStorage.getItem("token");
    if (token) {
        requestConfig.headers.token = token;
    }
    return requestConfig;
}, err);

service.interceptors.response.use((response) => {
    switch (response.status) {
        case 200:
            if (response.data.success && response.data.success) {
                return response.data.content;
            }
            message.error(response.data.message);
            break;
        case 404:
            return false;
        case 401:
            store.commit("logout");
            router.push("/login");
            break;
        default:
            message.error(response.data.message);
    }
});

const installer = {
    vm: {},
    install(Vue) {
        Vue.use(VueAxios, service);
    }
};

export {
    installer as VueAxios,
    service as axios
};