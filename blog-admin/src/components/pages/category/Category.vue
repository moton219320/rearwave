<template>
    <div>
        <el-row :gutter="10">
            <el-col :span="10">
                <el-breadcrumb separator=">" >
                    <el-breadcrumb-item :to="{ path: '/meter' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>内容管理</el-breadcrumb-item>
                    <el-breadcrumb-item>分类管理</el-breadcrumb-item>
                </el-breadcrumb>
            </el-col>
        </el-row>
        <el-divider></el-divider>
        <el-row :gutter="10">
            <el-col :span="2">
                <div style="width: 50px;min-height: 100px"></div>
            </el-col>
            <el-col :span="20">
                <el-form ref="form" :model="search" label-width="60px" class="el-form--inline el-tabs--left" style="margin-top: 15px">
                    <el-form-item label="分类">
                        <el-input v-model="search.CategoryName" placeholder="输入分类名称"  prefix-icon="el-icon-search"></el-input>
                    </el-form-item>
                    <el-button type="primary" @submit="submit('form')">查询分类</el-button>
                    <el-button type="info" @click="dialogVisible=true">添加分类</el-button>
                </el-form>
                <el-divider></el-divider>
                <el-table
                        :data="[{ date: '2016-05-02', name: '王小虎', address: '上海市普陀区金沙江路 1518 弄' }, { date: '2016-05-04', name: '王小虎', address: '上海市普陀区金沙江路 1517 弄' }]"
                        style="width: 100%;margin-top: 20px">
                    <el-table-column
                            prop="index"
                            label="序号"
                            align="center"
                            width="180">
                    </el-table-column>
                    <el-table-column
                            prop="name"
                            align="center"
                            label="分类名称"
                            width="180">
                    </el-table-column>
                    <el-table-column
                            prop="date"
                            align="center"
                            label="创建时间">
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作"
                            align="center"
                            width="100">
                        <template slot-scope="scope">
                            <span @click="editCategory(scope.row)" style="cursor:pointer">编辑</span>
                            |
                            <span @click="delCategory(scope.row)" style="cursor:pointer" >删除</span>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination
                        style="margin-top: 15px"
                        background
                        :page-size="search.pageSize"
                        :pager-count="5"
                        layout="prev, pager, next"
                        :total="search.total">
                </el-pagination>
            </el-col>
            <el-col :span="2">
                <div style="width: 50px;min-height: 100px"></div>
            </el-col>
        </el-row>
        <el-dialog
                :title="dialogTitle"
                :visible.sync="dialogVisible"
                width="30%"
                :before-close="handleClose">
            <el-form ref="form" :model="form" label-width="80px" :rules="rules">
                <el-from-item>
                    <el-input v-model="form.CategoryId" placeholder="placeholder" type="hidden"></el-input>
                </el-from-item>
                <el-form-item label="分类名称" prop="CategoryName">
                    <el-input v-model="form.CategoryName" placeholder="请输入分类名称"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
        <el-button @click="saveCategory(false)">取 消</el-button>
        <el-button type="primary" @click="saveCategory">确 定</el-button>
      </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "Category",
        data(){
            return {
                dialogVisible:false,
                search:{
                    CategoryName:'',
                    pageNum:1,
                    pageSize:10,
                    total:129,
                    pages:0,
                },
                form:{
                    CategoryName:'',
                    CategoryId:null
                },
                dialogTitle:'添加分类',
                rules:{
                    CategoryName:[
                        {required: true,message:"分类名称不能为空",trigger:'blur'}
                    ]
                }
            }
        },
        methods:{
            submit(){

            },
            handleClose(done){
                console.log(done)
                this.form.CategoryId=null;
                this.form.CategoryName='';
                this.dialogVisible=false;
                this.$message("操作取消")
            },
            saveCategory(flag){
                if(!flag){
                    this.$message("操作取消")
                    this.dialogVisible=false;
                    return
                }
                this.$notify({
                    title:"分类保存成功",
                    message:"您可以在创作文章的时候指定对应的分类哦",
                    duration:1500
                })
                this.dialogVisible=false;
            },delCategory(row){
                this.$confirm('确认删除分类【'+row.name+"】？", '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },editCategory(row){
                console.log(row)
                this.dialogTitle="编辑分类";
                this.form.CategoryName=row.name
                this.form.CategoryId=row.id;
                this.dialogVisible=true;
            }
        }
    }
</script>

<style scoped>

</style>