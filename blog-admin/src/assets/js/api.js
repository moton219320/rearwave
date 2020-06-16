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
    article:{},
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
    category:{},
    user:{

    },
    system:{
        dic:{},
        logs:{},
        platform:{},
        portalSetting:{},
        uploadConf:{}
    },


}
export default api;