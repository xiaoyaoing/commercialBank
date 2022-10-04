<template>
    <div class="block-wrapper">
        <div class="top-bar-wrapper">
            <div class="top-bar">
                <span class="title">文件详情</span>
            </div>
        </div>
        <div class="flex-content-wrapper details-bg">
            <div class="paper" v-if="type === '文书'">
                <div class="paper-title">
                    {{fileDetails.name}}
                </div>
                <mka-divider/>
                <div class="paper-body">
                    <p v-for="(p,index) in fileContent" :key="index">
                        {{p}}
                    </p>
                </div>
            </div>
            <div class="paper" v-if="type === '表格'">
                <div class="paper-title">
                    {{fileDetails.name}}
                </div>
                <a-divider/>
                <div class="paper-body">
                    <!--类型0表单 -->
                    <table v-if="fileDetails.type === 0" class="details-table">
                        <tr>
                            <th>行政处罚决定书文号</th>
                            <td colspan="3">{{fileDetails.docNum}}</td>
                        </tr>
                        <tr>
                            <th rowspan="3">被处罚当事人姓名或名称</th>
                        </tr>
                        <tr>
                            <td rowspan="2">个人</td>
                            <td>工作单位</td>
                            <td><span v-for="(litigant,index) in fileDetails.litigants" :key="index">
                                {{litigant.unit+" "}}
                            </span></td>
                        </tr>
                        <tr>
                            <td>姓名</td>
                            <td colspan="3"><span v-for="(litigant,index) in fileDetails.litigants" :key="index">
                                {{litigant.name+" "}}
                            </span></td>
                        </tr>
                        <tr>
                            <th>主要违法违规事实（案由）</th>
                            <td colspan="3">{{fileDetails.cause}}</td>
                        </tr>
                        <tr>
                            <th>行政处罚依据</th>
                            <td colspan="3">{{fileDetails.basis}}</td>
                        </tr>
                        <tr>
                            <th>行政处罚决定</th>
                            <td colspan="3">{{fileDetails.decision}}</td>
                        </tr>
                        <tr>
                            <th>作出处罚决定的机关名称</th>
                            <td colspan="3">{{fileDetails.organ}}</td>
                        </tr>
                        <tr>
                            <th>作出处罚决定的日期</th>
                            <td colspan="3">{{fileDetails.date}}</td>
                        </tr>
                    </table>
                    <!--类型1表单 -->
                    <table v-if="fileDetails.type === 1" class="details-table">
                        <tr>
                            <th>行政处罚决定书文号</th>
                            <td colspan="3">{{fileDetails.docNum}}</td>
                        </tr>
                        <tr>
                            <th rowspan="3">被处罚当事人姓名或名称</th>
                        </tr>
                        <tr>
                            <td rowspan="2">单位</td>
                            <td>名称</td>
                            <td><span v-for="(litigant,index) in fileDetails.litigants" :key="index">
                                {{litigant.name+" "}}
                            </span></td>
                        </tr>
                        <tr>
                            <td>法定代表人（主要负责人）姓名</td>
                            <td colspan="3"><span v-for="(litigant,index) in fileDetails.litigants" :key="index">
                                {{litigant.chargeMan===null?litigant.unit:litigant.chargeMan+" "}}
                            </span></td>
                        </tr>
                        <tr>
                            <th>主要违法违规事实（案由）</th>
                            <td colspan="3">{{fileDetails.cause}}</td>
                        </tr>
                        <tr>
                            <th>行政处罚依据</th>
                            <td colspan="3">{{fileDetails.basis}}</td>
                        </tr>
                        <tr>
                            <th>行政处罚决定</th>
                            <td colspan="3">{{fileDetails.decision}}</td>
                        </tr>
                        <tr>
                            <th>作出处罚决定的机关名称</th>
                            <td colspan="3">{{fileDetails.organ}}</td>
                        </tr>
                        <tr>
                            <th>作出处罚决定的日期</th>
                            <td colspan="3">{{fileDetails.date}}</td>
                        </tr>
                    </table>
                    <!--类型2表单 -->
                    <table v-if="fileDetails.type === 2" class="details-table">
                        <tr>
                            <th>行政处罚决定书文号</th>
                            <td colspan="3">{{fileDetails.docNum}}</td>
                        </tr>
                        <tr>
                            <th rowspan="5">被处罚当事人姓名或名称</th>
                        </tr>
                        <tr>
                            <td rowspan="2">个人</td>
                            <td>工作单位</td>
                            <td><span v-for="(litigant,index) in fileDetails.litigants" :key="index">
                                {{litigant.chargeMan?"":litigant.unit+" "}}
                            </span></td>
                        </tr>
                        <tr>
                            <td>姓名</td>
                            <td colspan="3"><span v-for="(litigant,index) in fileDetails.litigants" :key="index">
                                {{litigant.chargeMan?"":litigant.name+" "}}
                            </span></td>
                        </tr>
                        <tr>
                            <td rowspan="2">单位</td>
                            <td>名称</td>
                            <td><span v-for="(litigant,index) in fileDetails.litigants" :key="index">
                                {{litigant.unit?"":litigant.name+" "}}
                            </span></td>
                        </tr>
                        <tr>
                            <td>法定代表人（主要负责人）姓名</td>
                            <td colspan="3"><span v-for="(litigant,index) in fileDetails.litigants" :key="index">
                                {{litigant.unit?"":litigant.chargeMan+" "}}
                            </span></td>
                        </tr>
                        <tr>
                            <th>主要违法违规事实（案由）</th>
                            <td colspan="3">{{fileDetails.cause}}</td>
                        </tr>
                        <tr>
                            <th>行政处罚依据</th>
                            <td colspan="3">{{fileDetails.basis}}</td>
                        </tr>
                        <tr>
                            <th>行政处罚决定</th>
                            <td colspan="3">{{fileDetails.decision}}</td>
                        </tr>
                        <tr>
                            <th>作出处罚决定的机关名称</th>
                            <td colspan="3">{{fileDetails.organ}}</td>
                        </tr>
                        <tr>
                            <th>作出处罚决定的日期</th>
                            <td colspan="3">{{fileDetails.date}}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapGetters, mapActions} from "vuex";

    export default {
        name: "fileDetails",
        data() {
            return {
                type: "",
                fileDetails: {},
                fileContent: [],
            };
        },
        async mounted() {
            document.title = "信息管理-文书详情";
            console.log(this.$route.query.pKey);
            let res = await this.getFile_ById(this.$route.query.pKey);
            console.log(res);
            this.fileDetails = res;
            this.type = (this.fileDetails.basis === null ? "文书" : "表格");
            if (this.fileDetails.content) {
                this.fileContent = this.fileDetails.content.split("\n");
            }
            if (!this.fileContent) {
                this.fileContent = [this.fileDetails.content];
            }
        },
        computed: {
            ...mapGetters([
                "files"
            ])
        },
        methods: {
            ...mapActions([
                "getFile_ById"
            ])
        }
    };
</script>

<style scoped>
    @import "../../src/assets/styles/wrapper.css";
    @import "../../src/assets/styles/workbench.css";
    @import "../../src/assets/styles/fileDetails.css";
</style>