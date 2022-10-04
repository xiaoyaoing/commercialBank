<template>
  <div class="flex-wrapper extend-2-5">
    <drawer v-if="false">
      <div slot="title" class="user-card">
        <avatar></avatar>
        <div class="vert-wrapper user-info">
          <span class="user-name">用户名</span>
          <tag class="user-des">普通用户</tag>
        </div>
      </div>
      <div slot="body" class="user-content">
        <div class="content-item">
          <card></card>
        </div>
        <div class="content-item">
          <card></card>
        </div>
      </div>
    </drawer>
    <div class="vert-wrapper search-block extend-2-5">
      <div class="search-wrapper">
        <cb-input class="search-input"
                  type="search"
                  label="银保监会信息检索系统"
                  @doAction="search"
                  with-button
                  del>
          <span>搜索</span>
        </cb-input>
      </div>
      <card class="search-list extend-0-5" bg-color="white" v-if="displaySearchList">
        <a-space direction="vertical" :size="space" slot="body">
          <card class="search-item"
                v-for="item in searchRes"
                :key="item.docId"
                :data-key="item.docId"
                @click.native.capture="showDetails">
            <div slot="body" class="file-display">
              <div class="vert-wrapper file-block">
                <div class="file-title">{{ item.name }}</div>
                <div class="file-content">
                  <div v-html="item.highLightedContent"></div>
                </div>
              </div>
              <div class="file-img-wrapper">
                <img slot="body" class="file-img" src="../../public/form.svg" alt=""/>
              </div>
            </div>
          </card>
        </a-space>
      </card>
      <div class="footerLine" v-show="displaySearchList">
        <a-pagination
            :pageSize.sync="pageSize"
            :total="files.length"
            @change="changePage"
            showQuickJumper>
        </a-pagination>
        <div>
          当前已为您找到 {{ files.length }} 个相关文件
        </div>
      </div>
    </div>
    <div class="vert-wrapper search-filter">
      <a-divider></a-divider>
      <a class="ant-dropdown-link">
        <a-icon type="filter"/>
        高级筛选
      </a>
      <a-date-picker
          class="conditionFilter"
          v-model="startTime"
          show-time
          format="YYYY-MM-DD HH:mm:ss"
          placeholder="起始时间"
      />
      <a-date-picker
          class="conditionFilter"
          v-model="endTime"
          show-time
          format="YYYY-MM-DD HH:mm:ss"
          placeholder="终止时间"
      />
      <a-select default-value="DESC" class="conditionFilter" @change="handleChange">
        <a-select-option value="DESC">
          降序
        </a-select-option>
        <a-select-option value="ASC">
          升序
        </a-select-option>

      </a-select>
    </div>
  </div>
</template>

<script>
import Drawer from "../components/ui/drawer";
import Avatar from "../components/ui/avatar";
import Tag from "../components/ui/tag";
import Card from "../components/ui/card";
import cbInput from "../components/ui/cbInput";
import {mapActions, mapGetters} from "vuex";

export default {
  name: "home",
  components: {Card, Avatar, Drawer, Tag, cbInput},
  data() {
    return {
      displayUserInfo: false,
      displaySearchList: false,
      searchRes: [],
      currentPage: 1,
      pageSize: 3,
      minPage: 1,
      maxPage: 1,
      space: 1,
      startTime: null,
      endTime: null,
      direction: "DESC",
      inputKeyword: "",
    };
  },
  mounted() {
    document.title = "信息查询";
    let _this = this;
    if (this.$route.query.content) {
      let QueryBody = {
        "pageNum": 10,
        "currentPage": _this.currentPage,
        "currentPageSize": _this.pageSize,
      };
      QueryBody.filter = {
        "date": {
          "startDate": _this.startTime == null ? 0 : _this.startTime.valueOf(),
          "endDate": _this.endTime == null ? 0 : _this.endTime.valueOf()
        },
        "content": this.$route.query.content
      };
      QueryBody.sorter = {
        "date": _this.direction,
      };
      console.log("QueryBody", QueryBody);
      this.search_files(QueryBody).then(() => {
        _this.searchRes = _this.files.slice((_this.currentPage - _this.minPage) * _this.pageSize, (_this.currentPage - _this.minPage + 1) * _this.pageSize);
        _this.displaySearchList = true;
        console.log("content不为空");
      });
    } else {
      this.get_files({
        pageNum: 10,
        currentPage: 1,
        currentPageSize: 3,
        filter: {},
        sorter: {}
      }).then(() => {
        this.maxPage = this.minPage + this.files.length / this.pageSize;
        this.searchRes = this.files.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
        this.displayUserInfo = this.isLogged;
        console.log("登录状态:" + this.isLogged);
      });
    }

  },
  computed: {
    ...mapGetters([
      "isLogged",
      "files"
    ])
  },
  methods: {
    ...mapActions([
      "get_files",
      "search_files"
    ]),
    handleChange(value) {
      this.direction = value;
      console.log("handleChange" + value);
    },
    changePage(page, pageSize) {
      // TODO 改写分页逻辑与fileManage一致
      if (page < this.minPage) {
        console.log("key: " + this.inputKeyword)
        this.search(this.inputKeyword);
        this.maxPage = this.minPage;
        this.minPage -= 10;
      } else if (page > this.maxPage) {
        console.log("key: " + this.inputKeyword)
        this.search(this.inputKeyword);
        this.minPage = this.maxPage;
        this.maxPage += 10;
      }
      this.currentPage = page;
      this.pageSize = pageSize;
      this.searchRes = this.files.slice((this.currentPage - this.minPage) * this.pageSize, (this.currentPage - this.minPage + 1) * this.pageSize);
    },
    search: function (content) {
      this.inputKeyword = content;
      console.log("res:" + this.displaySearchList);
      console.log("startTime?" + this.startTime == null);
      console.log("endTime?" + this.endTime == null);
      console.log("正在搜索:" + content);
      let _this = this;
      if (content !== "") {
        let QueryBody = {
          "pageNum": 10,
          "currentPage": _this.currentPage,
          "currentPageSize": _this.pageSize,
        };
        QueryBody.filter = {
          "date": {
            "startDate": _this.startTime == null ? 0 : _this.startTime.valueOf(),
            "endDate": _this.endTime == null ? 0 : _this.endTime.valueOf()
          },
          "content": content
        };
        QueryBody.sorter = {
          "date": _this.direction,
        };
        console.log("QueryBody", QueryBody);
        this.search_files(QueryBody).then(() => {
          _this.searchRes = _this.files.slice((_this.currentPage - _this.minPage) * _this.pageSize, (_this.currentPage - _this.minPage + 1) * _this.pageSize);
          _this.displaySearchList = true;
          console.log("content不为空");
        });
      } else {
        console.log("content为空");
      }

    },
    showDetails(e) {
      let pKey = e.target.getAttribute("data-key");
      let routeData = this.$router.resolve({
        path: `/fileDetails`,
        query: {pKey: pKey}
      });
      window.open(routeData.href, "_blank");
    }
  }
};
</script>

<style scoped>
@import "../../src/assets/styles/wrapper.css";
@import "../../src/assets/styles/home.css";

</style>
