import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

// 配置路由信息
const routes = [
    /** @param path 链接路径
     * @param name 路由名称
     * @param component 映射的组件
     * @param props 组件参数(与router解耦)
     * @param redirect 跳转路径
     * @param child 嵌套路由信息
     */
    {
        path: "/",
        name: "search",
        meta: {
            title: "银保监会文书检索"
        },
        component: () => import("../views/search")
    },
    {
        path: "/home",
        name: "home",
        meta: {
            title: "银保监会信息查询系统"
        },
        component: () => import("../views/home")
    },
    {
        path: "/workbench",
        name: "workbench",
        meta: {
            title: "银保监会信息管理系统"
        },
        children: [
            {
                path: "home",
                name: "benchSearch",
                meta: {
                    title: "信息管理-文书查询"
                },
                component: () => import("../views/home")
            },
            {
                path: "fileManage",
                name: "fileManage",
                meta: {
                    title: "信息管理-文书管理",
                    keepAlive: true
                },
                component: () => import("../components/fileManage")
            }
        ],
        component: () => import("../views/workbench")
    },
    {
        path: "/login",
        name: "login",
        meta: {
            title: "登录"
        },
        component: () => import("../views/login")
    },
    {
        path: "/fileDetails",
        name: "fileDetails",
        meta: {
            title: "文书详情-"
        },
        component: () => import("../components/fileDetails")
    }
];

// 新建路由方法
const createRouter = () => new VueRouter({
    scrollBehavior: () => ({y: 0}),
    routes: routes
});

// 重置路由方法
export function resetRouter() {
    const newRouter = createRouter();
    router.matcher = newRouter.matcher;// reset router
}

const router = createRouter();

export default router;