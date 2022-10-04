<template>
    <div class="card">
        <slot name="title"></slot>
        <slot name="body"></slot>
    </div>
</template>

<script>
    export default {
        name: "card",
        data() {
            return {};
        },
        mounted() {
            this.setColor();
        },
        props: {
            bgColor: {
                type: String,
                default: null
            }
        },
        methods: {
            setColor() {
                if (this.bgColor != null) {
                    let card = this.$el;
                    card.style.backgroundColor = this.bgColor;
                }
            }
        }
    };
</script>

<style scoped>
    .card {
        border-radius: 5px;
        background-color: rgba(0, 0, 0, 0.1);
        display: flex;
        flex-flow: column;
        position: relative;
        height: 100%;
        width: 100%;
    }

</style>