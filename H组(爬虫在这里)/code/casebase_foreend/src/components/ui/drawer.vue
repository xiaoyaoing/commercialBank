<template>
    <div :class="['drawer','drawer-width','drawer-'+direction+isClosed]">
        <img :class="['back-icon','back-'+direction,'back'+isClosed]"
             :src="backIcon"
             @click="changeDrawerState"
             alt=""/>
        <slot name="title"></slot>
        <slot name="body"></slot>
    </div>
</template>

<script>
    export default {
        name: "drawer",
        data() {
            return {
                backIcon: "",
                isClosed: "-close"
            };
        },
        props: {
            direction: {
                type: String,
                default: "left"
            }
        },
        mounted() {
            this.backIcon = require("../../../public/back-" + this.direction + ".svg");
        },
        methods: {
            changeDrawerState: function () {
                if (this.isClosed === "-close") {
                    this.isClosed = "";
                } else {
                    this.isClosed = "-close";
                }
            }
        }
    };
</script>

<style scoped>
    @import "../../../src/assets/styles/media.css";

    .drawer {
        position: absolute;
        height: 100%;
        border: solid rgba(0, 0, 0, 0.4);
        border-width: 0 1px 1px 0;
        background-color: white;
        padding: 0.3rem 0.6rem;
        box-sizing: border-box;
        z-index: 60;
    }

    .drawer-left {
        transform: translateX(0);
        transition: transform, height 0.3s;
        box-shadow: 0 0 50px rgba(0, 0, 0, 0.2);
    }

    .drawer-left-close {
        transform: translateX(calc(-100% + 2.5rem));
        height: 2.5rem;
        /*border-bottom-width: 1px;*/
        transition: transform, height 0.3s;
    }

    .drawer-right {

    }

    .drawer-right-close {

    }

    .back-icon {
        position: absolute;
        cursor: pointer;
        top: 0.3rem;
    }

    .back-left {
        right: 0.4rem
    }

    .back-right {
        left: 0.4rem;
    }

    .back-close {
        transform: rotate(180deg);
    }


</style>