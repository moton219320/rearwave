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
        <el-row :gutter="10" style="margin-top: 30px">
            <el-col :span="2">
                <div style="width: 50px;min-height: 100px"></div>
            </el-col>
            <el-col :span="20">
                <el-row :gutter="10">
                    <el-form ref="form" :model="search" label-width="80px"  >
                        <el-col :span="10">
                            <el-form-item label="分类">
                                <el-input v-model="search.CategoryName" placeholder="输入分类名称"  prefix-icon="el-icon-search"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-button type="primary" @submit="submit('form')">查询分类</el-button>
                            <el-button type="info" @click="dialogVisible=true">添加分类</el-button>
                        </el-col>

                    </el-form>
                </el-row>

                <el-divider></el-divider>
                <el-table
                        :data="search.rows"
                        style="width: 100%;margin-top: 20px">
                    <el-table-column
                            prop="id"
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
                            prop="createTime"
                            align="center"
                            :formatter="format"
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
                        style="margin-top: 15px;text-align: center"
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
                    <el-input v-model="form.id" placeholder="placeholder" type="hidden"></el-input>
                </el-from-item>
                <el-form-item label="分类名称" prop="CategoryName">
                    <el-input v-model="form.name" placeholder="请输入分类名称"></el-input>
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
    import {formatDate} from "../../../assets/js/util";
    import {ajax} from "../../../assets/js/ajax";
    import api from "../../../assets/js/api";

    export default {
        name: "Category",
        data(){
            return {
                dialogVisible:false,
                search:{
                    name:'',
                    pageNum:1,
                    pageSize:8,
                    total:0,
                    pages:0,
                    rows:[]
                },
                form:{
                    name:'',
                    id:null
                },
                dialogTitle:'添加分类',
                rules:{
                    name:[
                        {required: true,message:"分类名称不能为空",trigger:'blur'}
                    ]
                }
            }
        },
        mounted(){
            this.query();
        },
        methods:{
            submit(form){
                this.$refs[form].validate(valid => {
                    console.log(valid)
                    if (valid) {
                        this.query();
                    }
                })
            },
            format(row,col,v){
                return v?formatDate(v,"yyyy-MM-dd hh:mm:ss"):"-";
            },
            handleClose(done){
                console.log(done)
                this.form.id=null;
                this.form.name='';
                this.dialogVisible=false;
                this.$message("操作取消")
            },
            query(pageNum){
                this.search.rows = []
                if(pageNum){
                    this.search.pageNum = pageNum;
                }
                ajax({
                    url:api.category.query.uri,
                    type:"POST",
                    data:this.search
                }).then(res=>{
                    this.search = res.data;
                })
            },
            saveCategory(flag){
                if(!flag){
                    this.$message("操作取消")
                    this.dialogVisible=false;
                    return
                }
                ajax({
                    url:api.category.save.uri,
                    type:"POST",
                    data:this.form
                }).then(res => {
                    if (res.code === 200) {
                        this.$notify({
                            title:"分类保存成功",
                            message:"您可以在创作文章的时候指定对应的分类哦",
                            duration:1500
                        })
                        this.dialogVisible=false;
                        //刷新列表
                        this.form = {}
                        this.query();
                    }else{
                        this.$alert(res.msg);
                    }
                })

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
                this.form.name=row.name
                this.form.id=row.id;
                this.dialogVisible=true;
            }
        }
    }
</script>

<style scoped>

</style>