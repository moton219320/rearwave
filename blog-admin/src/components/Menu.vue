<template>
    <el-aside style="background-color: #545c64;width: 100%;min-width: 180px;">
        <el-menu  class="el-menu-vertical-demo" mode="vertical"
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b"
                  :default-openeds=openeds
                  router
                  @open="handleOpen"
                  @select="handleSelect">
            <el-menu-item index="/meter" style="height: 70px;line-height:70px;font-size: 18px;font-weight: 800"><i class="el-icon-odometer"></i>DashBoard</el-menu-item>
            <el-submenu index="article">
                <template slot="title">
                    <i class="el-icon-edit"></i>
                    <span style="font-size: 15px;font-weight: 800">内容管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="/articles">
                        <span>文章管理</span>
                    </el-menu-item>
                    <el-menu-item index="/category"><span>分类管理</span></el-menu-item>
                    <el-menu-item index="/tags"><span>标签管理</span></el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-submenu index="user">
                <template slot="title">
                    <i class="el-icon-user"></i>
                    <span style="font-size: 15px;font-weight: 800">用户管理</span>
                </template>
                <el-menu-item-group>

                    <el-menu-item index="/onlineUsers"><span>在线用户</span></el-menu-item>
                    <el-menu-item index="/userInfo"><span>个人信息维护</span></el-menu-item>
                    <el-menu-item index="/changePassword"><span>修改密码</span></el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-submenu index="system">
                <template slot="title">
                    <i class="el-icon-setting"></i>
                    <span style="font-size: 15px;font-weight: 800">系统设置</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="/dic"><span>字典管理</span></el-menu-item>
                    <el-menu-item index="/portal"><span>网站信息维护</span></el-menu-item>
                    <el-menu-item index="/platform"><span>平台信息维护</span></el-menu-item>
                    <el-menu-item index="/uploadConf"><span>文件上传配置</span></el-menu-item>
                    <el-menu-item index="/logs"><span>操作日志</span></el-menu-item>
                </el-menu-item-group>
            </el-submenu>

        </el-menu>
    </el-aside>

</template>

<script>

    export default {
        name: "Menu",
        data() {
            return {
                collapse:false,
                openeds:["article"],
                uniqueOpened:true
            }
        },
        methods: {
            handleSelect(index) {
                console.log(index)

            },
            handleOpen(index){
                this.openeds=[index];
            }
        }
    }
</script>

<style>
    .el-aside{
        overflow-x: hidden;
        min-height: 700px;
        background-color:#545c64;
        height: 100%;
        margin-bottom:-900px;
        padding-bottom:900px;
        overflow-y: hidden;
    }
    .el-menu {
        height: auto;
        text-align: left;
    }
    .el-submenu i{
        margin-left: 30px;
    }
    .el-menu-item{
        width: 100%;
        text-align: center;
    }
    .el-menu-item-group{
        width: 100%;
    }
    .el-main{
        color:#2c3e50 ;
    }
</style>