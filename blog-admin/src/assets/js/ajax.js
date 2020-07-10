import axios from 'axios'
import vue from 'vue'

//创建axios实例
const service = axios.create({
    timeout: 30000 //超时时间
})

let loading;
//添加request拦截器
service.interceptors.request.use(config => {
    if (localStorage.getItem("token")){
        config.headers.common["token"] = localStorage.getItem("token");
    }
    loading = vue.prototype.$loading({
        text:"处理中......"
    })
    return config;
}, error => {
    Promise.reject(error);
})

//添加response拦截器
service.interceptors.response.use(
    response => {
        let {data} = response;
        if (data.code === 201){
            vue.prototype.$message.warning(data.msg);
            localStorage.clear();
            loading.close();
            // vue.prototype.$router.push("/unauthorized")
            window.location.href="/#/";
            window.location.reload();
        }else if (data.code!==200){
            vue.prototype.$message.error(data.msg);
        }
        setTimeout(()=>{
            loading.close();
        },500)

        return data;
    },
    error => {
        if (error.response && error.response.status === 404) {
            //跳转到xx页面
            console.log("404错误")
        }
        return Promise.reject(error.response)
    }
)

//封装get接口
// params={} 是设置默认值
export function get(url, params = {}) {
    params.t_ = new Date().getTime(); //get方法加一个时间参数,解决ie下可能缓存问题.
    return service({
        url: url,
        method: 'get',
        headers: {
        },
        params
    })
}

//封装post请求
export function post(url, data = {}) {
    //默认配置
    let sendObject = {
        url: url,
        method: "post",
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: data
    };
    sendObject.data = JSON.stringify(data);
    return service(sendObject)
}

export function upload(url,file = {}){
    //默认配置
    let sendObject = {
        url: getUrl(url),
        method: "post",
        headers: {
            'Content-Type': 'multipart/form-data'
        },
        data: file
    };
    return service(sendObject)
}

//封装put方法 (resfulAPI常用)
function put(url, data = {}) {
    return service({
        url: url,
        method: 'put',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(data)
    })
}

//删除方法(resfulAPI常用)
function deletes(url) {
    return service({
        url: url,
        method: 'delete',
        headers: {}
    })
}

//patch方法(resfulAPI常用)
function patch(url) {
    return service({
        url: url,
        method: 'patch',
        headers: {}
    })
}

//处理格式化URL（/demo/{id}）
function render(url, data) {
    var re = /{([^]+)?}/
    var match = ''
    while ((match = re.exec(url))) {
        url = url.replace(match[0], data[match[1]])
    }
    return url
}

const fetch = (options) => {
    //process.env.VUE_APP_PATH为环境变量在.env文件中配置
    let url = options.url;
    url = render(url, options.data)
    switch (options.type.toUpperCase()) {
        case 'GET':
            return get(url, options.data)
        case 'POST':
            return post(url, options.data)
        case 'PUT':
            return put(url, options.data)
        case 'DELETE':
            return deletes(url, options.data)
        case 'PATCH':
            return patch(url, options.data)
        default:
            return service(options)
    }
}

/**
 * 提供一个ajax方法
 * url 访问路径 不包括域名和项目名
 * data 参数对象
 * method 请求方式
 *  */
export function ajax(options = {url:'',type :"GET", data : {}}) {
    options.url = getUrl(options.url);
    return fetch(options).catch(error => {
        console.log(error)
        throw error
    })
}

/**
 * 构造url的方法
 */
export function getUrl(url = '') {
    //process.env.VUE_APP_PATH为环境变量在.env文件中配置
    console.log("request url is \"%s\"",process.env.VUE_APP_PATH + url);
    return process.env.VUE_APP_PATH + url;
}
