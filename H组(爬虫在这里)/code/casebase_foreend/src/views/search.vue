<template>
  <div id="bg" class="bg bg-img">
    <div class="center-block search-size">
      <card :bg-color="cardColor" class="card-style">
        <div slot="title" class="title">银保监会文书查询系统</div>
        <div slot="body" class="search-input">
          <div class="input-wrapper">
            <cb-input type="search"
                      with-button
                      del
                      @doAction="search"
            >
              <span>搜索</span>
            </cb-input>
            <card :bg-color="notice" class="notice">
              <div slot="body" class="notice-body">
                <div class="notice-item notice-title">● 最新发布 ●</div>
                <div class="notice-item" v-for="(item,index) in infos" :key="index">
                  {{ item.content }}
                </div>
              </div>
            </card>
          </div>
        </div>
      </card>
    </div>
    <!--        <div v-if="!isLogged" class="login-btn" @click="login">-->
    <!--            <img src="../../public/user.svg" class="prefix-ico" alt=""/>-->
    <!--            <span class="content">登录</span>-->
    <!--        </div>-->
  </div>
</template>

<script>
import card from "../components/ui/card";
import cbInput from "../components/ui/cbInput";

const notices = [
  {
    title: "无标题",
    content: "暂无最新内容"
  }
];

export default {
  name: "search",
  components: {
    card,
    cbInput
  },
  mounted() {

  },
  data() {
    return {
      cardColor: "rgba(0, 0, 0, 0.08)",
      notice: "transparent",
      infos: notices,
      isLogged: false
    };
  },
  methods: {
    search: function (content) {
      console.log("正在搜索:" + content);
      this.$router.push({
        path: "/home",
        query: {content:content}
      });
    },
    login: function () {
      this.$router.push("/login");
    }
  }
};
</script>

<style scoped>
@import "../../src/assets/styles/wrapper.css";
@import "../../src/assets/styles/login.css";

.title {
  font-family: 黑体, sans-serif;
  font-size: 2rem;
  color: white;
  letter-spacing: 0.25rem;
  margin: 0 auto;
  height: 4rem;
}

.card-style {
  border: solid 0.2rem white;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.6);
}

.notice {
  margin: 2rem auto 1rem auto;
  height: 55%;
  width: 90%;
  box-sizing: border-box;
  padding: 0.3rem;

  color: white;
  font-size: 0.9rem;
  letter-spacing: 0.1rem;
}

.notice-item {
  border-radius: 3px;
  padding: 0.2rem;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
  width: 90%;
  height: auto;
  margin: 0.1rem auto;
  font-family: 黑体, sans-serif;
  text-align: center;
}

.notice-item:hover {
  cursor: pointer;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1) inset;
}

.notice-body {
  display: flex;
  flex-flow: column;
  padding: 0.1rem;
}

.notice-title {

}

.content {
  margin-left: 0.2rem;
}

.search-input {
  height: calc(100% - 4rem);
}

@media screen and (min-width: 1200px) {
  .bg-img {
    background-image: url("../../public/bg.jpg");
  }

  .search-size {
    height: 50%;
    width: 40%;
  }

  .card-style {
    padding: 0.8rem;
  }
}

@media screen and (min-width: 1024px) and (max-width: 1199px) {
  .bg-img {
    background-image: url("../../public/bg.jpg");
  }

  .search-size {
    height: 50%;
    width: 50%;
  }

  .card-style {
    padding: 0.8rem;
  }
}

@media screen and (min-width: 769px) and (max-width: 1023px) {
  .bg-img {
    background-image: url("../../public/bg.jpg");
  }

  .search-size {
    height: 60%;
    width: 70%;
  }

  .card-style {
    padding: 1rem;
  }

  .content {
    display: none;
  }
}

@media screen and (max-width: 768px) {
  .bg-img {
    background-image: url("../../public/bg.jpg");
  }

  .search-size {
    height: 80%;
    width: 80%;
  }

  .card-style {
    padding: 1.2rem;
  }

  .content {
    display: none;
  }
}


</style>