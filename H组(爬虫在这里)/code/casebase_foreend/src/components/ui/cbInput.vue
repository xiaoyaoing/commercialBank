<template>
    <div class="cb-input-wrapper">
        <img v-if="prefix"
             :src="prefix"
             class="prefix"
             alt=""/>
        <label>
            {{label}}
            <input :class="['cb-input','cb-'+type,withButton?'cb-input-w':'cb-input-nw',del?'w-suf':'',prefix?'w-pre':'']"
                   v-model="content"
                   :placeholder="placeholder"
                   @keyup.enter="doAction"
            />
        </label>
        <img v-if="content!=='' & del"
             src="../../../public/close.svg"
             class="suffix"
             alt=""
             @click="clean"
        />
        <button v-if="withButton"
                class="w-btn"
                @click="doAction">
            <slot></slot>
        </button>
    </div>
</template>

<script>
    export default {
        name: "cbInput",
        data() {
            return {
                content: "",
                input: null
            };
        },
        props: {
            withButton: {
                type: Boolean,
                default: false
            },
            del: {
                type: Boolean,
                default: false
            },
            prefix: {
                type: String,
                default: ""
            },
            type: {
                type: String,
                default: "default"
            },
            label: {
                type: String,
                default: ""
            },
            placeholder: {
                type: String,
                default: ""
            }
        },
        mounted() {
            if(this.label){
                this.input = this.$el.childNodes[1].childNodes[1];
            }
            else {
                this.input = this.$el.childNodes[1].firstChild;
            }
        },
        methods: {
            doAction: function () {
                this.$emit("doAction", this.content);
                // this.content = "";
            },
            clean: function () {
                this.content = "";
                console.log(this.input);
                this.input.focus();
            }
        }
    };
</script>

<style scoped>
    @import "../../../src/assets/styles/wrapper.css";

    .cb-input {
        height: 2.2rem;
        font-size: 1rem;
        color: black;
        font-family: sans-serif;
        padding: 0 0.6rem;
        box-sizing: border-box;
        vertical-align: middle;
    }

    .cb-default {
        outline: none;
        border: none;
    }

    .cb-search {
        outline: none;
        border: 2px solid #c4c7ce;
    }

    .cb-search:hover {
        border-color: #A7AAB5;
    }

    .cb-search:focus {
        border-color: #4E6EF2;
    }


    .cb-form-input {
        outline: none;
        border: 1px solid rgba(0, 0, 0, 0.4);
    }

    .cb-form-input:focus {
        border: 1px solid #409EFF;
    }

    .w-suf {
        padding-right: 1.6rem;
    }

    .w-pre {
        padding-left: 2.2rem;
    }

    .cb-input-nw {
        width: 70%;
        border-radius: 5px;
    }

    .cb-input-w {
        width: 60%;
        border-radius: 5px 0 0 5px;
        vertical-align: middle;
        border-right: 0 solid transparent;
    }

    .w-btn {
        outline: none;
        border: 0;
        box-sizing: border-box;
        width: 10%;
        min-width: 4rem;
        height: 2.2rem;
        color: white;
        font-size: 1.1rem;
        font-family: sans-serif;

        border-radius: 0 5px 5px 0;
        vertical-align: middle;
        cursor: pointer;
        background-color: #4E6EF2;
    }

    .w-btn:hover {
        background-color: #4662D9;
    }

    .prefix {
        position: absolute;
        top: 50%;
        transform: translate(50%, -50%);
    }

    .suffix {
        position: absolute;
        top: 50%;
        transform: translate(-150%, -50%);
        cursor: pointer;
    }

</style>