<template>
    <a-config-provider :locale="zh_CN">
        <div id="app">
            <transition name="fade" mode="out-in">
                <router-view/>
            </transition>
        </div>
    </a-config-provider>
</template>

<script>
    import zh_CN from "ant-design-vue/lib/locale-provider/zh_CN";
    import moment from "moment";
    import "moment/locale/zh-cn";

    moment.locale("zh-cn");

    export default {
        name: "App",
        provide() {
            return {};
        },
        components: {},
        data() {
            return {
                zh_CN
            };
        },
        methods: {}
    };
</script>

<style scoped>
    @import "assets/styles/wrapper.css";

    #app {
        margin: 0;
        height: 100%;
        overflow-x: hidden;
    }
</style>