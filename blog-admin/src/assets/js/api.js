const api = {
    auth:{
        verifyCode:{
            name:"获取验证码",
            url:"/auth/getVerifyCode"
        },
        login:{
            name:"用户登录",
            url:"/auth/login"
        }
    },
    article:{
        save:{
            url:"/article/save"
        },
        query:{
            uri:"/article/query"
        }
    },
    tags:{
        save:{
            name:"保存标签",
            uri:"/tags/save"
        },
        query:{
            name:"查询标签",
            uri:"/tags/query"
        }
    },
    category:{
        save:{
            uri:"/category/save"
        },
        query:{
            uri:"/category/query"
        }
    },
    user:{

    },
    system:{
        dic:{
            query:{
                uri:"/dic/query"
            },
            save:{
                url:"/dic/save"
            }
        },
        logs:{},
        platform:{},
        portalSetting:{},
        uploadConf:{}
    },


}
export default api;