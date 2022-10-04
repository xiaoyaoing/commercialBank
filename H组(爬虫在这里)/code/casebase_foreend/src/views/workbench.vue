<template>
    <div class="block-wrapper">
        <div class="top-bar-wrapper">
            <div class="top-bar">
                <span class="title">银保监会信息管理系统</span>
            </div>
        </div>
        <div class="flex-content-wrapper">
            <div class="menu-wrapper">
                <a-menu
                        mode="inline"
                        class="bench-menu"
                        @select="goToPage">
<!--                    <a-menu-item key="/">-->
<!--                        <a-icon type="home"/>-->
<!--                        <span>主页</span>-->
<!--                    </a-menu-item>-->
                    <a-menu-item key="/home">
                        <a-icon type="search"/>
                        <span>文书查询</span>
                    </a-menu-item>
                    <a-menu-item key="/fileManage">
                        <a-icon type="file"/>
                        <span>文书管理</span>
                    </a-menu-item>
                </a-menu>
            </div>
            <div class="bench-wrapper">
                <keep-alive v-if="$route.meta.keepAlive">
                    <router-view/>
                </keep-alive>
                <keep-alive v-if="!$route.meta.keepAlive">
                    <router-view/>
                </keep-alive>
            </div>
        </div>
    </div>
</template>

<script>

    export default {
        name: "workbench",
        components: {},
        data() {
            return {};
        },
        mounted() {

        },
        computed: {},
        methods: {
            goToPage(element) {
                this.$router.push("/workbench" + element.key);
            }
        }
    };
</script>

<style scoped>
    @import "../../src/assets/styles/wrapper.css";
    @import "../../src/assets/styles/workbench.css";

</style>