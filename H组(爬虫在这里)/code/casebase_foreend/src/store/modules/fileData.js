import {
    addFileAPI,
    delFilesAPI,
    getFilesAPI,
    modFileAPI,
    uploadFilesAPI,
    searchFilesAPI,
    getFileByIdAPI, modFileStateAPI
} from "../../api/fileDataAPI";
import {message} from "ant-design-vue";

const fileData = {
    state: {
        number_of_files: 8,
        files: [
            {
                docId: "1",
                key: "1",
                type: "文书",
                docNum: "银保监罚决字〔2021〕35号",
                litigants: [{
                    chargeMan: "个人",
                    name: "张雄师"
                }],
                cause: "",
                basis: "",
                decision: "",
                organ: "",
                date: "",
                name: "中国银行保险监督管理委员会行政处罚决定书\n（银保监罚决字〔2021〕35号）",
                state: 1,
                content: "当 事 人：太平财产保险有限公司\n" +
                    "\n" +
                    "住    所：广东省深圳市福田区益田路6001号太平金融大厦33层\n" +
                    "\n" +
                    "法定代表人：刘世宏\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "当事人：张雄师\n" +
                    "\n" +
                    "身份证号：36011119691209XXXX\n" +
                    "\n" +
                    "职务：时任太平财产保险有限公司责信险部总经理\n" +
                    "\n" +
                    "住址：广东省深圳市福田区梅林路148号梅林一村\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "根据《中华人民共和国保险法》（以下简称《保险法》）和《中华人民共和国行政处罚法》等有关规定，我会对太平财产保险有限公司（以下简称太平财险）涉嫌违法一案进行了立案调查、审理，并依法向当事人告知了作出行政处罚的事实、理由、依据及当事人依法享有的权利。法定期限内，当事人未提出陈述申辩意见。本案现已审理终结。\n" +
                    "\n" +
                    "经查，太平财险存在未按照规定使用经批准或者备案的保险条款、保险费率的违法行为，具体包括：\n" +
                    "\n" +
                    "一是通过签订补充协议，实质性改变经审批或者备案保险产品的问题。太平财险上海分公司与作为投保人代理人的某租赁公司签订合作协议约定“最高赔付金额为相应协议下对应保单实收保费的1.1倍”等内容，与2015年备案的《太平财产保险有限公司贷款履约保证保险条款》第八条“本保险合同的保险金额为投保时投保人与被保险人订立的《借款合同》中列明的贷款本金与利息之和”内容不一致。根据合作协议，约定太平财险承担的总体赔偿责任从保险金额1.68亿元变成了实收保费316.64万元的110%，即348.3万元，二者差额近1.65亿元。\n" +
                    "\n" +
                    "二是修改后的保险条款和保险费率经批准或者备案后，在新订立的保险合同中使用原保险条款和保险费率问题。2015年11月4日,太平财险向原中国保监会备案《贷款履约保证保险》产品；11月17日，该条款备案通过。2016年10月28日，太平财险修改了2015年备案的《贷款履约保证保险》相关条款及费率，再次向原中国保监会备案；11月22日，该条款备案通过。2016年12月至2019年3月，太平财险上海分公司承保的某租赁公司项目贷款履约保证保险业务，投保单、保险单所附条款均为2015年备案的《贷款履约保证保险条款》。\n" +
                    "\n" +
                    "时任太平财险责信险部总经理张雄师，作为责信险部直接负责的主管人员，对上述行为负有直接责任。\n" +
                    "\n" +
                    "上述事实，有保险产品条款、公司说明、任职文件、调查笔录等证据证明。\n" +
                    "\n" +
                    "综上，我会决定作出如下处罚：\n" +
                    "\n" +
                    "上述未按照规定使用经批准或者备案的保险条款、保险费率的行为，违反《保险法》第一百三十五条的规定，根据《保险法》第一百七十条，对太平财险罚款50万元；根据《保险法》第一百七十一条，对张雄师警告并罚款10万元。\n" +
                    "\n" +
                    "当事人应当在接到本处罚决定书之日起十五日内持缴款码到财政部指定的代理银行进行缴款。逾期，将每日按罚款数额的百分之三加处罚款。\n" +
                    "\n" +
                    "当事人如不服本行政处罚决定，可以在收到本处罚决定书之日起六十日内向中国银行保险监督管理委员会申请行政复议，也可以在收到本处罚决定书之日起六个月内向有管辖权的人民法院提起诉讼。复议、诉讼期间本决定不停止执行。"
            },
            {
                docId: "2",
                key: "2",
                type: "文书",
                docNum: "银保监罚决字〔2021〕35号",
                litigants: [{
                    chargeMan: "个人",
                    name: "张雄师"
                }],
                cause: "",
                basis: "",
                decision: "",
                organ: "",
                date: "2016-09-16",
                name: "2016年文件一",
                state: 1,
            },
            {
                docId: "3",
                key: "3",
                type: "文书",
                docNum: "银保监罚决字〔2021〕35号",
                litigants: [{
                    chargeMan: "个人",
                    name: "张雄师"
                }],
                cause: "",
                basis: "",
                decision: "",
                organ: "",
                date: "",
                name: "2017年文件一",
                link: "查看详情",
                state: 0,
            },
            {
                docId: "4",
                key: "4",
                type: "文书",
                docNum: "银保监罚决字〔2021〕35号",
                litigants: [{
                    chargeMan: "个人",
                    name: "张雄师"
                }],
                cause: "",
                basis: "",
                decision: "",
                organ: "",
                date: "",
                name: "2016年文件一",
                link: "查看详情",
                state: 0,
            },
            {
                docId: "5",
                key: "5",
                type: "文书",
                docNum: "银保监罚决字〔2021〕35号",
                litigants: [{
                    chargeMan: "个人",
                    name: "张雄师"
                }],
                cause: "",
                basis: "",
                decision: "",
                organ: "",
                date: "",
                name: "2016年文件一",
                link: "查看详情",
                state: 1,
            },
            {
                docId: "6",
                key: "6",
                type: "文书",
                docNum: "银保监罚决字〔2021〕35号",
                litigants: [{
                    chargeMan: "个人",
                    name: "张雄师"
                }],
                cause: "",
                basis: "",
                decision: "",
                organ: "",
                date: "",
                name: "2016年文件一",
                link: "查看详情",
                state: 1,
            },
            {
                docId: "7",
                key: "7",
                type: "文书",
                docNum: "银保监罚决字〔2021〕35号",
                litigants: [{
                    chargeMan: "个人",
                    name: "张雄师"
                }],
                cause: "",
                basis: "",
                decision: "",
                organ: "",
                date: "",
                name: "2016年文件一",
                link: "查看详情",
                state: 1,
            },
            {
                docId: "8",
                key: "8",
                type: 2,
                docNum: "银保监罚决字〔2021〕35号",
                litigants: [{
                    chargeMan: "张雄师",
                    name: "摸鱼公司"
                }],
                cause: "123",
                basis: "123",
                decision: "123",
                organ: "123",
                date: "1213-02-03",
                name: "一张公开表",
                link: "查看详情",
                state: 0,
            }
        ]
    },
    mutations: {
        set_number_of_files: function (state, data) {
            state.number_of_files = data;
        },
        set_files: function (state, data) {
            state.files = data;
        }
    },
    actions: {
        get_files: async function ({commit}, config) {
            let res = await getFilesAPI(config);
            if (res) {
                //commit("set_number_of_files", res.number_of_files);
                commit("set_files", res);
            }
        },
        /**
         * 删除文件
         * @param commit
         * @param state
         * @param pKeys 数组,存放需要删除的文件key值
         * @returns {Promise<void>}
         */
        delete_files: async ({commit, state}, pKeys) => {
            // TODO 连接后端 删除文件方法
            let res = await delFilesAPI({delList: pKeys});
            if (res) {
                commit("set_files", state.files.filter((item) => {
                    return !pKeys.includes(item.docId + "");
                }));
            } else {
                throw "";
            }

        },
        /**
         * 新增或修改文件
         * @param commit
         * @param state
         * @param data 包括mode和files;mode决定新增或修改;files是一个数组,存放需要新增/修改的文件信息
         * @returns {Promise<void>}
         */
        add_or_modify_file: async ({commit, state}, data) => {
            if (data.mode === "新建") {
                let res = await addFileAPI(data.files);
                if (res) {
                    data.files.docId = res;
                    commit("set_files", [data.files, ...state.files]);
                } else throw "";
            } else if (data.mode === "编辑") {
                // 不可能批量编辑文件
                let res = await modFileAPI(data.files);
                for (let i = 0; i < state.files.length; i++) {
                    if (state.files[i].docId === data.files.docId) {
                        state.files[i] = data.files;
                        break;
                    }
                }
                commit("set_files", state.files);
                if (!res) {
                    throw "";
                }
            }
        },
        upload_files: async ({commit, state}, files) => {
            let res = await uploadFilesAPI({file: files});
            if (res) {
                commit("set_files", state.files);
                message.success("上传成功");
            } else {
                message.error("上传失败");
            }
        },
        search_files: async ({commit}, QueryBody) => {
            let res = await searchFilesAPI(QueryBody);
            if (res) {
                // commit("set_number_of_files", res);
                commit("set_files", res);
                message.success("搜索成功");
            } else {
                message.error("搜索失败");
            }
        },
        // eslint-disable-next-line no-unused-vars
        getFile_ById: async ({commit, state}, pKey) => {
            let res = await getFileByIdAPI(pKey);
            if (res) {
                return res;
            } else {
                return false;
            }
        },
        modify_file_state: async ({commit, state}, fileInfo) => {
            console.log(fileInfo)
            let res = await modFileStateAPI(fileInfo);
            if (res) {
                let count = 0;
                for (let i = 0; i < state.files.length; i++) {
                    if (fileInfo.ids.indexOf(state.files[i].docId+"")>=0) {
                        state.files[i].state = fileInfo.state;
                        count += 1;
                        if(count === fileInfo.ids.length){
                            break;
                        }
                    }
                }
                commit("set_files", state.files);
                message.success("状态变更成功");
            } else {
                message.error("状态变更失败");
            }
        }
    }
};
export default fileData;
