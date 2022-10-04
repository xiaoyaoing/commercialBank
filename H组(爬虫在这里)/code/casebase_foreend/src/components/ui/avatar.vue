<template>
    <div :class="['avatar','avatar-'+type,'avatar-'+size,border?'border':'']">
        <img :class="['avatar-img','avatar-'+type]"
             src="../../../public/userIcon.svg"
             alt=""/>
    </div>
</template>

<script>
    export default {
        name: "avatar",
        data() {
            return {};
        },
        props: {
            type: {
                type: String,
                default: "circle"
            },
            size: {
                type: String,
                default: "middle"
            },
            border: {
                type: Boolean,
                default: false
            }
        }
    };
</script>

<style scoped>
    .avatar {
        box-sizing: border-box;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.15);
    }

    .border {
        border: 1px solid rgba(0, 0, 0, 0.4);
    }

    .avatar-img {
        height: 100%;
        width: 100%;
        border: none;
        outline: none;
    }

    .avatar-circle {
        border-radius: 50%;
    }

    .avatar-small {

    }

    .avatar-middle {
        width: 3rem;
        height: 3rem;
    }


</style>