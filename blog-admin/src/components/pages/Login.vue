<template>
    <div v-on:keyup.enter="onSubmit('loginForm')">
        <el-form ref="loginForm" :model="form"  :rules="rules" label-width="80px" class="login-box">
            <h3 class="login-title">欢迎登录</h3>
            <el-form-item label="账号" prop="username">
                <el-input type="text" placeholder="请输入账号" v-model="form.username"/>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
            </el-form-item>
            <el-form-item label="验证码" prop="verifyCode">
                <el-input v-model="form.verifyCode" placeholder="请输入验证码" style="width: 120px;margin-right: 30px" />
                <el-image  alt="点击刷新" :src="verifyImg" @click="refresh" style="vertical-align: middle;" />
            </el-form-item>
            <el-button type="primary" @click="onSubmit('loginForm')" >登录</el-button>
        </el-form>

        <el-dialog
                title="温馨提示"
                :visible.sync="dialogVisible"
                width="30%"
                :before-close="handleClose">
            <span>请输入账号和密码</span>
            <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
        </el-dialog>
    </div>
</template>

<script>
    import {ajax} from "../../assets/js/ajax";
    import api from "../../assets/js/api";
    const base64 = require('js-base64').Base64
    export default {
        name: "Login",
        data() {
            return {
                form: {
                    username: '',
                    password: '',
                    verifyCode:'',
                    token:''
                },

                // 表单验证，需要在 el-form-item 元素中增加 prop 属性
                rules: {
                    username: [
                        {required: true, message: '账号不可为空', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '密码不可为空', trigger: 'blur'}
                    ]
                },

                // 对话框显示和隐藏
                dialogVisible: false,
                verifyImg:''
            }
        },
        mounted(){
            this.refresh();
        },
        methods: {
            refresh(){
                ajax({
                    url:api.auth.verifyCode.url,
                    type:"GET"
                }).then(res => {
                    this.form.token = res.data.token
                    this.verifyImg = res.data.img;
                })
            },
            onSubmit(formName) {
                // 为表单绑定验证功能
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let data = JSON.stringify(this.form);
                        let enc = base64.encode(data)
                        console.log(enc)
                        ajax({
                            url:api.auth.login.url,
                            type:"POST",
                            data:enc
                        }).then(res => {

                            if (res.code === 200) {
                                // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
                                let user = res.data;
                                user.isLogin = true;
                                localStorage.setItem("token",user.token);
                                localStorage.setItem("user",JSON.stringify(user));
                                this.$emit("loginSuccess",user)
                                this.$notify({
                                    title:"登录成功",
                                    message:"您已成功登录系统，现在可以发表文章啦！！",
                                    duration:3000
                                })
                            } else {
                                this.$message(res.msg);
                            }

                        })

                    } else {
                        this.dialogVisible = true;
                        return false;
                    }
                });
            },
            handleClose(obj){
                console.log(obj);
            }
        }
    }
</script>

<style scoped>
    .login-box {
        border: 1px solid #DCDFE6;
        width: 350px;
        margin: 180px auto;
        padding: 35px 35px 15px 35px;
        border-radius: 5px;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        box-shadow: 0 0 25px #909399;
    }

    .login-title {
        text-align: center;
        margin: 0 auto 40px auto;
        color: #303133;
    }
    .el-form-item{
        vertical-align: middle;
    }
</style>