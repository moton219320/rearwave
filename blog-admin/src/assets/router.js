import Vue from "vue";
import VueRouter from "vue-router";


import Article from "../components/pages/article/Article";
import ArticleList from "../components/pages/article/ArticleList";
import Category from "../components/pages/category/Category";
import Tags from "../components/pages/tags/Tags";
import UserInfo from "../components/pages/user/UserInfo";
import ChangePassword from "../components/pages/user/ChangePassword";
import OnlineUsers from "../components/pages/user/OnlineUsers";
import PortalSetting from "../components/pages/system/PortalSetting";
import Platform from "../components/pages/system/Platform";
import UploadConf from "../components/pages/system/UploadConf";
import Logs from "../components/pages/system/Logs";
import Meter from "../components/pages/Meter";
import Login from "../components/pages/Login";
import Dic from "../components/pages/system/Dic";
// 要告诉 vue 使用 vueRouter
Vue.use(VueRouter);

let routes = [
    {
        path:"/dic",
        component: Dic
    },
    {
        path:'/meter',
        component: Meter
    },
    {
        path:"/article",
        component:Article
    },
    {
        path:'/articles',
        component: ArticleList
    },{
        path: '/category',
        component: Category
    },{
        path: '/tags',
        component: Tags
    },{
        path:'/userInfo',
        component: UserInfo
    },{
        path: '/changePassword',
        component: ChangePassword
    },{
        path: '/onlineUsers',
        component: OnlineUsers
    },{
        path: '/portal',
        component: PortalSetting
    },{
        path:'/platform',
        component: Platform
    },{
        path: '/uploadConf',
        component: UploadConf
    },{
        path:'/logs',
        component: Logs
    },{
        path: '/login',
        component: Login
    }
]

let router = new VueRouter({
    routes
})
export default router