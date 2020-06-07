<template>
    <el-breadcrumb separator=">">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>活动管理</el-breadcrumb-item>
    </el-breadcrumb>
</template>

<script>
  export default {
      name:"Crumb",
      data(){
          return {}
      }
  }

</script>

<style>

</style>