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
        <el-row :gutter="10"  style="min-height:500px">
         <el-col :span="24">
             <el-tabs  tab-position="left">
                 <el-tab-pane label="图文信息">
                    <Graphic />
                 </el-tab-pane>
                 <el-tab-pane label="视频信息">
                    <Video />
                 </el-tab-pane>
                 <el-tab-pane label="图集信息">
                    <Gallery />
                 </el-tab-pane>
                 <el-tab-pane label="闲言碎语">
                    <Twitter />
                 </el-tab-pane>
             </el-tabs>
         </el-col>
        </el-row>

    </div>

</template>

<script>
    import Twitter from "./Twitter";
    import Video from "./Video";
    import Gallery from "./Gallery";
    import Graphic from "./Graphic";

    export default {
        name: "Article",
        components:{
            Twitter,
            Video,
            Gallery,
            Graphic
        },
        data(){
            return {
                title:"这是发布图文组件"

            }
        }
    }
</script>

<style scoped>

</style>