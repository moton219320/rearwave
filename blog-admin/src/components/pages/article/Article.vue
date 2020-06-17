<template>
    <div>
        <el-row :gutter="10">
            <el-col :span="10">
                <el-breadcrumb separator=">" >
                    <el-breadcrumb-item :to="{ path: '/meter' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>内容管理</el-breadcrumb-item>
                    <el-breadcrumb-item :to="{ path:'/articles'}">文章管理</el-breadcrumb-item>
                    <el-breadcrumb-item>发布文章</el-breadcrumb-item>
                </el-breadcrumb>
            </el-col>
        </el-row>
        <el-divider></el-divider>

    </div>

</template>

<script>
    export default {
        name: "Article",
        data(){
            return {
                title:"这是发布图文组件",
                search:{
                    title:'',

                }
            }
        }
    }
</script>

<style scoped>

</style>