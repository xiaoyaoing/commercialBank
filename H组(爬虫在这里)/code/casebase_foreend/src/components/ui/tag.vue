<template>
    <div class="tag">
        <slot></slot>
    </div>
</template>

<script>
    export default {
        name: "tag",
        data() {
            return {};
        },
        props: {
            color: {
                type: String,
                default: "rgb(239, 239, 239)"
            },
            textColor: {
                type: String,
                default: "black"
            },
            noBorder: {
                type: Boolean,
                default: false
            }
        },
        mounted() {
            this.initAttribute();
        },
        methods: {
            initAttribute: function () {
                let tag = this.$el;
                tag.style.backgroundColor = this.color;
                tag.style.color = this.textColor;
                if (this.noBorder) {
                    tag.style.border = "none";
                }
            }
        }
    };
</script>

<style scoped>
    .tag {
        box-sizing: border-box;
        padding: 1px 5px;
        line-height: 0.8rem;
        text-align: center;
        font-size: 0.8rem;
        font-family: sans-serif;

        border: 1px solid rgba(0, 0, 0, 0.4);
        border-radius: 0.5rem;
    }
</style>