<template>
    <div id="app">
        <div v-if="login">
            <el-header style="height: auto;vertical-align: middle">
                <el-row :gutter="10">
                    <el-col :span="21">
                        <div class="el-page-header__left" style="width: 200px">
                            <a href="http://rearwave.com" target="_blank"><img src="./assets/logo.png" alt="LOGO"></a>
                        </div>
                    </el-col>
                    <el-col :span="3">
                        <div style="vertical-align: middle;height: 100%; ">
                            <img src="./images/avatar.jpg"  @click="handleAvatar"
                                 style="width: 40px;height: 40px;margin-top:20px;border-radius: 20px"
                                 alt="avatar">
                            <el-dropdown >

                                <span class="el-dropdown-link">
                                    {{user.username}}
                                    <i class="el-icon-s-operation"></i>
                                </span>
                                <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item @click.native="$router.push('/changePassword')">
                                        <i class="el-icon-setting"></i>
                                        <span>修改密码</span>
                                    </el-dropdown-item>
                                    <el-dropdown-item @click.native="$router.push('/userInfo')">
                                        <i class="el-icon-user-solid"></i>
                                        <span>个人信息维护</span>
                                    </el-dropdown-item>
                                    <el-dropdown-item @click.native="logout">
                                        <i class="el-icon-finished"></i>
                                        <span>退出登录</span>
                                    </el-dropdown-item>

                                </el-dropdown-menu>

                            </el-dropdown>
                        </div>

                    </el-col>
                </el-row>


            </el-header>
            <el-row :gutter="10" class="tac">
                <el-col :span="5" style="width: 15%;min-width: 180px">
                    <Menu msg="系统菜单"></Menu>
                </el-col>
                <el-col :span="19">
                    <el-main>
                        <router-view></router-view>
                    </el-main>
                </el-col>
            </el-row>
            <el-footer style="height: 90px">
                <div style="vertical-align: middle;height: 90px">
                    <span style="height: 90px;line-height: 90px">CopyRight©2020 By @Moton www.rearwave.com</span>
                </div>
            </el-footer>
        </div>
        <div v-else>
            <Login @loginSuccess="loginSuccess" :isLogin="login"></Login>
        </div>
    </div>
</template>

<script>
    import Menu from './components/Menu.vue'
    import Login from './components/pages/Login'

    export default {
        name: 'App',
        components: {
            Menu, Login
        },
        methods: {
            handleSelectHeaderMenu(index) {
                console.log(index);
                this.headerMenu = false
            },
            showMenu() {
                this.headerMenu = !this.headerMenu;
            },
            loginSuccess(isLogin) {
                console.log("isLogin  %s", isLogin)
                this.login = isLogin;
            },
            logout(e) {
                console.log(e)
                this.login=false
            },handleAvatar(){
                this.$notify({
                    title:"将要修改头像",
                    message:"系统会调用用户信息页面去修改头像",
                    duration:1500
                })
            }
        },
        data() {
            return {
                headerMenu: false,
                login: false,
                user:{
                    username:'冷落清秋',
                    userId:'',
                }
            }
        },
        created() {
            try {
                document.body.removeChild(document.getElementById('appLoading'))
                setTimeout(function () {
                    document.getElementById('app').style.display = 'block';
                }, 1500)
            } catch (e) {
                console.error(e)
            }
        }
    }
</script>

<style>
    html, body {
        margin: 0;
    }

    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #FFFFFF;
        background-color: #FFFFFF;
        height: 100%;
    }

    .el-header {
        background-color: cadetblue;
    }

    .el-footer {
        background-color: #2c3e50;;
        color: #FFFFFF;
    }

    .el-dropdown-link {
        cursor: pointer;
        color: #FFFFFF;
        height: 100%;
        line-height: 100%;
        margin-left: 10px;
        font-size: 18px;
        position: relative;
        top: -13px;

    }
    el-form{
        border-radius: 8px;
    }
</style>
