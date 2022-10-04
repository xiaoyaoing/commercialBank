<template>
    <div class="file-manage-wrapper">
        <div class="top-block">
            <div class="title-wrapper">
                <div class="title">文书管理</div>
                <div class="extra">
                    <transition name="fade-away" mode="out-in">
                        <a-button class="btn" key="3" icon="folder" @click="doBatch" v-if="!batchMode">批量操作</a-button>
                        <span v-if="batchMode">
                            <a-button class="btn" key="6" icon="file" type="primary"
                                      @click="batchRelease">批量发布</a-button>
                            <a-button class="btn" key="5" icon="delete" type="danger"
                                      @click="batchDelete">批量删除</a-button>
                            <a-button class="btn" key="4" icon="rollback" type="dashed"
                                      @click="cancelBatch">取消</a-button>
                        </span>
                    </transition>
                    <a-divider type="vertical"/>
                    <a-button class="btn" key="2" icon="form" modalMode="新建" @click="openModal">新建文书</a-button>
                    <a-button class="btn" key="1" icon="upload" type="primary" @click="openUploader">上传文书</a-button>
                </div>
            </div>
            <div class="header-wrapper">
                <a-input-search class="header-search"
                                placeholder="输入关键字以查询信息"
                                @search="search">
                </a-input-search>
            </div>
        </div>
        <div class="bottom-block">
            <a-table class="list"
                     :columns="columns"
                     :data-source="dataList"
                     :pagination="false"
                     :loading="loading"
                     :row-selection="rowSelectionConfig">

                <span slot="fileNameTitle" class="list-header">文件名</span>
                <span slot="contentTitle" class="list-header">文件概述</span>
                <span slot="dateTitle" class="list-header">发布时间</span>
                <span slot="docTitle" class="list-header">文件详情</span>
                <span slot="stateTitle" class="list-header">发布状态</span>
                <span slot="actionTitle" class="list-header">操作</span>

                <a-icon slot="filterIcon" type="calendar" theme="filled" class="filterIcon"/>

                <span slot="name" slot-scope="name">
                    <a-avatar class="list-avatar"
                              src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimages.669pic.com%2Felement_pic%2F78%2F30%2F94%2F67%2F15ee4f874e176250fad8dd5fbd23c525.jpg&refer=http%3A%2F%2Fimages.669pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1637509237&t=366bd15a30b5f381144683e91be77d33"></a-avatar>
                    {{"《 "+name+" 》"}}
                </span>
                <span slot="docId" slot-scope="docId">
                    <a @click="showDetails" :data-p-key="docId">查看详情</a>
                </span>
                <span slot="state" slot-scope="state">
                    <a-tag :color="state?'green':'red'">{{state?"已发布":"未发布"}}</a-tag>
                    <a-tooltip placement="top">
                        <template slot="title">
                            {{state?"撤销发布":"发布"}}
                        </template>
                        <span class="state-icon-wrapper" :data-s-value="state"  @click="fileRelease">
                            <a-icon :type="state?'cloud-download':'cloud-upload'" class="state-icon-style"></a-icon>
                        </span>
                    </a-tooltip>
                </span>
                <span slot="action">
                    <a-button icon="edit" modalMode="编辑" @click="openModal">编辑</a-button>
                    <a-divider type="vertical"/>
                    <a-button icon="delete" type="danger" @click="deleteItem">删除</a-button>
                </span>
            </a-table>
            <div class="table-pagination">
                <a-pagination
                        :pageSize="pageSize"
                        :total="this.files.length"
                        @change="changePage"
                        :show-total="(total) => `当前文件库共有 ${total} 条数据`"
                        showQuickJumper></a-pagination>
            </div>
        </div>
        <a-modal v-model="addOrMod" :title="modalMode+'文件'" @ok="submitForm">
            <a-form-model :model="form" :rules="formRules" :label-col="labelCol" :wrapper-col="wrapperCol">
                <a-form-model-item label="文件类型" required>
                    <a-select :default-value="form.type" @select="(value)=>this.form.type=value">
                        <a-select-option value="文书">
                            文书
                        </a-select-option>
                        <a-select-option value="表格">
                            公开表
                        </a-select-option>
                    </a-select>
                </a-form-model-item>
                <a-form-model-item label="文件名" required>
                    <a-input v-model="form.name" placeholder="请输入文件名"></a-input>
                </a-form-model-item>
                <a-form-model-item label="文号" required>
                    <a-input v-model="form.docNum" placeholder="请输入文号"></a-input>
                </a-form-model-item>
                <a-form-model-item label="当事人">
                    <a-button type="dashed" style="" @click="addLitigant">
                        <a-icon type="plus"/>
                        添加当事人
                    </a-button>
                </a-form-model-item>
                <a-form-model-item v-for="(litigant,index) in form.litigants" :key="index" :label="'当事人'+(index+1)">
                    <a-select :default-value="litigant.chargeMan">
                        <a-select-option value="个人">
                            个人
                        </a-select-option>
                        <a-select-option value="单位">
                            单位
                        </a-select-option>
                    </a-select>
                    <a-input v-model="litigant.name" placeholder="请输入当事人姓名"></a-input>
                </a-form-model-item>
                <a-form-model-item v-if="form.type === '表格'" label="处罚原因">
                    <a-input v-model="form.cause" placeholder="处罚的原因"></a-input>
                </a-form-model-item>
                <a-form-model-item v-if="form.type === '表格'" label="处罚依据">
                    <a-input v-model="form.basis" placeholder="处罚的依据"></a-input>
                </a-form-model-item>
                <a-form-model-item v-if="form.type === '表格'" label="处罚决定">
                    <a-input v-model="form.decision" placeholder="处罚的决定"></a-input>
                </a-form-model-item>
                <a-form-model-item v-if="form.type === '表格'" label="处罚组织">
                    <a-input v-model="form.organ" placeholder="处罚的组织"></a-input>
                </a-form-model-item>
                <a-form-model-item v-if="form.type === '表格'" label="处罚日期">
                    <a-input v-model="form.date" placeholder="处罚的日期"></a-input>
                </a-form-model-item>
                <a-form-model-item label="发布方式" required>
                    <a-select :default-value="form.state">
                        <a-select-option :value="1">
                            立即发布
                        </a-select-option>
                        <a-select-option :value="0">
                            暂不发布
                        </a-select-option>
                    </a-select>
                </a-form-model-item>
                <a-form-model-item v-if="form.type === '文书'" label="文件内容" required>
                    <a-textarea placeholder="请输入文件内容" :rows="9" v-model="form.content">

                    </a-textarea>
                </a-form-model-item>
            </a-form-model>
        </a-modal>
        <a-modal v-model="uploader" title="上传文件" @ok="uploadFiles" :ok-text="uploading?'上传中':'上传'"
                 :confirm-loading="uploading">
            <a-upload-dragger
                    name="file"
                    :multiple="true"
                    :remove="handleRemove"
                    action="http://localhost:8081/api/document/uploadFiles"
                    @change="handleChange">
                <p class="ant-upload-drag-icon">
                    <a-icon type="inbox"/>
                </p>
                <p class="ant-upload-text">点击或拖拽文件到此区域进行上传</p>
                <p class="ant-upload-hint">可选中一个或多个文件进行上传</p>
            </a-upload-dragger>
        </a-modal>
    </div>
</template>

<script>

    import {mapActions, mapGetters, mapMutations} from "vuex";

    const columns = [
        {
            dataIndex: "name",
            key: "name",
            slots: {title: "fileNameTitle", filterIcon: "filterIcon"},
            scopedSlots: {customRender: "name"},
            filters: [
                {
                    text: "2016",
                    value: "2016"
                },
                {
                    text: "2017",
                    value: "2017"
                },
                {
                    text: "2018",
                    value: "2018"
                },
                {
                    text: "2019",
                    value: "2019"
                },
                {
                    text: "2020",
                    value: "2020"
                },
                {
                    text: "2021",
                    value: "2021"
                }
            ],
            onFilter: (value, record) => record.name.indexOf(value) !== -1,
        },
        {
            dataIndex: "date",
            key: "date",
            slots: {title: "dateTitle"},
            width: 110
        },
        {
            dataIndex: "content",
            key: "content",
            slots: {title: "contentTitle"},
            ellipsis: true
        },
        {
            dataIndex: "docId",
            key: "docId",
            slots: {title: "docTitle"},
            scopedSlots: {customRender: "docId"},
            width: 150
        },
        {
            key: "state",
            dataIndex:"state",
            slots: {title: "stateTitle"},
            scopedSlots: {customRender: "state"},
            width: 150,
            filters: [
                {
                    text: "已发布",
                    value: 1
                },
                {
                    text: "未发布",
                    value: 0
                }
            ],
            onFilter: (value, record) => record.state === value,
        },
        {
            key: "action",
            slots: {title: "actionTitle"},
            scopedSlots: {customRender: "action"},
            width: 220
        },
    ];

    const pagination = {
        position: "bottom",
        pageSize: 6,
        total: 0,
        showQuickJumper: true,
        showTotal: (total) => `当前文件库共有 ${total} 条数据`
    };

    const rowSelection = {
        selections: true,
        selectedRowKeys: [],
        onChange: (selected) => {
            rowSelection.selectedRowKeys = selected;
        }
    };

    const form = {
        docId: "",
        key: "",
        name: "",
        type: "文书",
        docNum: "",
        litigants: [{
            chargeMan: "个人",
            name: ""
        }],
        cause: "",
        basis: "",
        decision: "",
        organ: "",
        date: "",
        description: "",
        state: 0,
        content: ""
    };

    const formRules = {
        name: [{required: true, message: "请输入文件名", trigger: "blur"}],
        state: [{required: true, message: "请选择发布方式", trigger: "blur"}],
        content: [{required: true, message: "请输入文件内容", trigger: "blur"}]
    };

    export default {
        name: "fileManage",
        components: {},
        data() {
            return {
                paginationConfig: pagination,
                rowSelectionConfig: null,
                form: form,
                formRules: formRules,
                dataList: [],//显示文件列表
                currentPage: 1,
                pageSize: 6,
                minPage: 1,
                maxPage: 1,

                fileList: [],// 上传文件列表
                labelCol: {span: 5},
                wrapperCol: {span: 18},
                columns: columns,
                loading: false,
                addOrMod: false,
                uploader: false,
                uploading: false,
                batchMode: false,
                modalMode: "新建",

                // 提醒发布通知
                noticeRelease: true
            };
        },
        mounted() {
            document.title = "信息管理-文书管理";
            this.loading = true;
            let that = this;
            this.get_files({
                pageNum: 10,
                currentPage: 1,
                currentPageSize: 6,
                filter: {},
                sorter: {}
            }).then(() => {
                that.currentPage = 1;
                that.dataList = that.files.slice((that.currentPage - that.minPage) * that.pageSize, (that.currentPage - that.minPage + 1) * that.pageSize);
                this.maxPage = this.minPage + this.files.length / this.pageSize;
                this.dataList = this.files.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
                that.loading = false;
            }).catch(() => {
                that.$message.error("数据获取失败");
                that.loading = false;
            });
        },
        computed: {
            ...mapGetters([
                "files",
                "number_of_files"
            ])
        },
        methods: {
            ...mapMutations([
                "set_files"
            ]),
            ...mapActions([
                "get_files",
                "delete_files",
                "add_or_modify_file",
                "upload_files",
                "modify_file_state",
                "search_files"
            ]),
            doBatch() {
                this.rowSelectionConfig = rowSelection;
                this.batchMode = true;
            },
            cancelBatch() {
                this.rowSelectionConfig = null;
                this.batchMode = false;
                this.rowSelection.selectedRowKeys = [];
            },
            batchDelete() {
                let that = this;
                this.$confirm({
                    title: "提示",
                    content: "你确定要删除这些文件吗?",
                    onOk() {
                        return new Promise((resolve) => {

                            setTimeout(resolve, 200);
                            // TODO 批量行问题
                            let pKeys = [];
                            for (let rowKey in that.rowSelectionConfig.selectedRowKeys) {
                                pKeys.push(that.files[rowKey].docId + "");
                            }
                            console.log(pKeys);
                            let _that = that;
                            that.delete_files(pKeys).then(() => {
                                _that.dataList = _that.files.slice((_that.currentPage - _that.minPage) * _that.pageSize, (_that.currentPage - _that.minPage + 1) * _that.pageSize);
                                _that.paginationConfig.total = that.files.length;
                                _that.$message.success("删除成功");
                                _that.cancelBatch();
                            });
                        }).catch(() => {
                            that.$message.error("删除失败");
                        });
                    },
                    onCancel() {
                    },
                });
            },
            batchRelease() {
                let pKeys = [];
                for (let rowKey in this.rowSelectionConfig.selectedRowKeys) {
                    pKeys.push(this.files[rowKey].docId + "");
                }
                this.doRelease(pKeys, 1);
            },
            fileRelease(e) {
                let value = e.target.getAttribute("data-s-value");
                e.target.setAttribute("data-s-value",value+"");
                value = (value === "1"?0:1);
                console.log(e.target)
                console.log(value)
                let pKeys = [e.target.parentNode.parentNode.parentNode.childNodes[3].firstChild.firstChild.getAttribute("data-p-key")];
                this.doRelease(pKeys, value);
            },
            doRelease(pKeys, value) {
                this.modify_file_state({
                    state: value,
                    ids: pKeys
                }).then(() => {
                    if (this.noticeRelease) {
                        this.$notification.info({
                            message: "提示",
                            description: "在发布完成之后，可以再次点击图标来取消发布"
                        });
                        this.noticeRelease = false;
                    }
                });
            },
            deleteItem(e) {
                let that = this;
                let pKeys = [e.target.parentNode.parentNode.parentNode.childNodes[3].firstChild.firstChild.getAttribute("data-p-key")];
                this.$confirm({
                    title: "提示",
                    content: "你确定要删除这份文件吗?",
                    onOk() {
                        return new Promise((resolve) => {
                            setTimeout(resolve, 200);
                            let _that = that;
                            that.delete_files(pKeys).then(() => {
                                _that.dataList = _that.files.slice((_that.currentPage - _that.minPage) * _that.pageSize, (_that.currentPage - _that.minPage + 1) * _that.pageSize);
                                console.log(_that.dataList);
                                _that.paginationConfig.total = _that.files.length;
                                _that.$message.success("删除成功");
                            });
                        }).catch(() => {
                            that.$message.error("删除失败");
                        });
                    },
                    onCancel() {
                    },
                });
            },
            openModal(e) {
                this.modalMode = e.target.getAttribute("modalMode");
                if (this.modalMode === "编辑") {
                    let pKey = e.target.parentNode.parentNode.parentNode.childNodes[3].firstChild.firstChild.getAttribute("data-p-key");

                    console.log("编辑");
                    console.log(this.files);
                    console.log(pKey);
                    this.form = this.files.find((item) => {
                        return item.docId == pKey;
                    });
                    console.log(this.form);
                    this.form.type = (this.form.basis === "" ? "文书" : "表格");
                } else if (this.modalMode === "新建") {
                    this.form = {
                        docId: "",
                        key: "",
                        name: "",
                        type: "文书",
                        docNum: "",
                        litigants: [],
                        cause: "",
                        basis: "",
                        decision: "",
                        organ: "",
                        date: "",
                        description: "",
                        state: 0,
                        content: ""
                    };
                }
                this.addOrMod = true;
            },
            openUploader() {
                this.uploader = true;
            },
            submitForm() {
                let newFile = {
                    basis: this.form.basis,
                    cause: this.form.cause,
                    content: this.form.content,
                    date: this.form.date,
                    decision: this.form.decision,
                    docId: this.modalMode === "编辑" ? this.form.docId : "",
                    docNum: this.form.docNum,
                    html: "",
                    litigants: this.form.litigants,
                    name: this.form.name,
                    organ: this.form.organ,
                    state: this.form.state,
                    type: "",
                };
                let that = this;
                this.add_or_modify_file({
                    mode: this.modalMode,
                    files: newFile,
                }).then(() => {
                    that.dataList = that.files.slice((that.currentPage - that.minPage) * that.pageSize, (that.currentPage - that.minPage + 1) * that.pageSize);
                    that.$message.success(that.modalMode + "成功");
                    that.addOrMod = false;
                });
            },
            uploadFiles() {
                if (this.fileList[0].response !== null) {
                    this.set_files([this.fileList[0].response.content].concat(this.files));
                    this.dataList = this.files.slice((this.currentPage - this.minPage) * this.pageSize, (this.currentPage - this.minPage + 1) * this.pageSize);
                    this.$message.success(`导入数据库成功`);
                    this.uploader = false;
                }
                setTimeout(() => {
                    this.uploading = false;
                }, 2000);
            },
            addLitigant() {
                this.form.litigants.push({
                    chargeMan: "个人",
                    name: ""
                });
            },
            handleChange(info) {
                const status = info.file.status;
                this.fileList = info.fileList;
                console.log(this.fileList.response);
                console.log("调用了一次handleChange");
                if (status === "done") {
                    this.$message.success(`${info.file.name} 上传成功.`);
                } else if (status === "error") {
                    this.$message.error(`${info.file.name} 上传失败.`);
                }

            },
            handleRemove(file) {
                const index = this.fileList.indexOf(file);
                const newFileList = this.fileList.slice();
                newFileList.splice(index, 1);
                this.fileList = newFileList;
            },
            search: function (content) {
                let _this = this;
                if (content !== "") {
                    let QueryBody = {
                        "pageNum": 10,
                        "currentPage": _this.currentPage,
                        "currentPageSize": _this.pageSize,
                    };
                    QueryBody.filter = {
                        "date": {},
                        "content": content
                    };
                    QueryBody.sorter = {
                    };
                    console.log("QueryBody", QueryBody);
                    this.loading = true;
                    this.search_files(QueryBody).then(()=>{
                        _this.dataList = _this.files.slice((_this.currentPage - _this.minPage) * _this.pageSize, (_this.currentPage - _this.minPage + 1) * _this.pageSize);
                        _this.loading = false;
                    }).catch(()=>{
                        _this.loading = false;
                    });
                } else {
                    this.$message.error("请输入搜索内容");
                }

            },
            showDetails(e) {
                // let pKey = e.target.parentNode.parentNode.parentNode.getAttribute("data-row-key");
                let pKey = e.target.getAttribute("data-p-key");
                let routeData = this.$router.resolve({
                    path: `/fileDetails`,
                    query: {pKey: pKey}
                });
                window.open(routeData.href, "_blank");
            },
            changePage(page, pageSize) {
                if (page < this.minPage) {
                    this.get_files({
                        pageNum: 10,
                        currentPage: 1,
                        currentPageSize: 6,
                        filter: {},
                        sorter: {}
                    });
                    this.maxPage = this.minPage;
                    this.minPage -= 10;
                } else if (page > this.maxPage) {
                    this.get_files({
                        pageNum: 10,
                        currentPage: 1,
                        currentPageSize: 6,
                        filter: {},
                        sorter: {}
                    });
                    this.minPage = this.maxPage;
                    this.maxPage += 10;
                }
                this.currentPage = page;
                this.pageSize = pageSize;
                this.dataList = this.files.slice((this.currentPage - this.minPage) * this.pageSize, (this.currentPage - this.minPage + 1) * this.pageSize);
            },
        }
    };
</script>

<style scoped>
    @import "../../src/assets/styles/wrapper.css";
    @import "../../src/assets/styles/fileManage.css";

    .filterIcon {
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -5px;
        margin-left: -6px;
    }


</style>
