<template>
    <div class="bg">
        <div class="center-block login-size">
            <card bg-color="#f5f7fa" class="login-card">
                <div slot="title" class="login-title">登录</div>
                <div slot="body" class="login-form">
                    <div class="login-form-item">
                        <cb-input :prefix="userIcon" type="form-input"
                                  v-model="userName"
                                  placeholder="请输入用户名"/>
                    </div>
                    <div class="login-form-item">
                        <cb-input :prefix="userKey" type="form-input"
                                  v-model="password"
                                  placeholder="请输入密码"/>
                    </div>
                    <div class="login-form-item">
                        <button class="form-button"
                                @click="login">登录
                        </button>
                    </div>
                </div>
            </card>
        </div>
    </div>
</template>

<script>
    import Card from "../components/ui/card";
    import CbInput from "../components/ui/cbInput";
    import {mapGetters, mapMutations} from "vuex";

    export default {
        name: "login",
        components: {CbInput, Card},
        data() {
            return {
                userIcon: require("../../public/userIcon.svg"),
                userKey: require("../../public/key.svg"),
                userName: "",
                password: ""
            };
        },
        computed: {
            ...mapGetters([
                "isLogged"
            ]),
        },
        methods: {
            ...mapMutations([
                "set_userStatus"
            ]),
            login: function () {
                this.set_userStatus(true);
                this.$router.push({
                    path: "/workbench"
                });
            }
        }
    };
</script>

<style scoped>
    @import "../../src/assets/styles/wrapper.css";
    @import "../../src/assets/styles/login.css";

    @media screen and (min-width: 1200px) {
        .login-size {
            height: 50%;
            width: 30%;
        }
    }

    @media screen and (min-width: 1024px) and (max-width: 1199px) {
        .login-size {
            height: 50%;
            width: 35%;
        }
    }

    @media screen and (min-width: 769px) and (max-width: 1023px) {
        .login-size {
            height: 50%;
            width: 45%;
        }
    }

    @media screen and (max-width: 768px) {
        .login-size {
            height: 60%;
            width: 80%;
        }

        .login-form-item {
            height: 4.5rem;
        }
    }


</style>