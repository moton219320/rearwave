<template>
    <div>
        <el-row :gutter="10">
            <el-col :span="10">
                <el-breadcrumb separator=">" >
                    <el-breadcrumb-item :to="{ path: '/meter' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>用户管理</el-breadcrumb-item>
                    <el-breadcrumb-item>修改密码</el-breadcrumb-item>
                </el-breadcrumb>
            </el-col>
        </el-row>
        <el-divider></el-divider>
        <el-row :gutter="10">
            <el-col :span="10">
                <el-form ref="form" :model="change" label-width="120px" :rules="rules">
                    <el-form-item label="原密码" prop="oldpwd">
                        <el-input v-model="change.oldpwd" placeholder="输入原密码"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="newpwd">
                        <el-input v-model="change.newpwd" placeholder="输入新密码"></el-input>
                    </el-form-item>
                    <el-form-item label="重复新密码" prop="newpwd2">
                        <el-input v-model="change.newpwd2" placeholder="再次输入新密码"></el-input>
                    </el-form-item>
                    <el-button type="primary" @click="submit" >提交</el-button>
                </el-form>
            </el-col>
        </el-row>
    </div>

</template>

<script>

    export default {
        name: "ChangePassword",
        data(){
            let checkSame = (rule,value,callback)=>{
                if (value !== this.change.newpwd){
                    this.$alert("两次密码输入不一致")
                    callback(new Error("两次密码输入不一致"));
                }
            }
            return {
                change:{
                    oldpwd:'',
                    newpwd:'',
                    newpwd2:''
                },
                rules:{
                    oldpwd:[
                        {required:true,message:"请输入原密码",trigger:'blur'}
                    ],
                    newpwd:[
                        {required: true,message:"请输入新密码",trigger:"blur"}
                    ],
                    newpwd2:[
                        {required:true,message:"请再次输入新密码",trigger:"blur"},
                        {validator: checkSame,message: "两次密码输入不一致",trigger: "blur"}
                    ]
                }
            }
        },methods:{
            submit(){

            }
        }
    }
</script>

<style scoped>

</style>