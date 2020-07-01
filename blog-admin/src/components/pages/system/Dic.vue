<template>
    <div>
        <el-row :gutter="10">
            <el-col :span="10">
                <el-breadcrumb separator=">" >
                    <el-breadcrumb-item :to="{ path: '/meter' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>内容管理</el-breadcrumb-item>
                    <el-breadcrumb-item>字典管理</el-breadcrumb-item>
                </el-breadcrumb>
            </el-col>
        </el-row>
        <el-divider></el-divider>
        <el-row :gutter="10">
            <el-col :span="2">
                <div style="width: 50px;min-height: 100px"></div>
            </el-col>
            <el-col :span="20">
                <el-form ref="form" :model="search" label-width="80px" >
                    <el-row :gutter="10">
                        <el-col :span="6">
                            <el-form-item label="字典名称">
                                <el-input v-model="search.value" placeholder="输入字典名称" prefix-icon="el-icon-search"
                                          style="margin-left: 5px"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="字典分类">
                                <el-input v-model="search.dicName" placeholder="输入字典分类" prefix-icon="el-icon-search"
                                          style="margin-left: 5px"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-button type="primary" @click="submit('form')" style="margin-left: 30px">查询字典</el-button>
                            <el-button type="info" @click="add('form1')">添加字典</el-button>
                        </el-col>
                    </el-row>


                </el-form>
                <el-divider></el-divider>
                <el-table
                        :data="search.rows"
                        style="width: 100%;margin-top: 20px">
                    <el-table-column
                            prop="id"
                            align="center"
                            label="序号"
                            width="80">
                    </el-table-column>
                    <el-table-column
                            prop="key"
                            align="center"
                            label="字典key"
                            width="180">
                    </el-table-column>
                    <el-table-column
                            prop="value"
                            align="center"
                            label="字典名称">
                    </el-table-column>
                    <el-table-column
                            prop="dicName"
                            align="center"
                            label="字典分类">
                    </el-table-column>
                    <el-table-column
                            prop="status"
                            align="center"
                            :formatter="(row)=>{return row.status==1?'有效':'无效'}"
                            label="状态">
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作"
                            align="center"
                            width="100">
                        <template slot-scope="scope">
                            <span @click="editDic(scope.row)" style="cursor:pointer">编辑</span>
                            |
                            <span @click="delDic(scope.row)" style="cursor:pointer" >删除</span>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination
                        style="margin-top: 15px;text-align: center"
                        @current-change="query"
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
            <el-form ref="form1" :model="dic" label-width="80px" :rules="rules">
                <el-form-item hidden>
                    <el-input v-model="dic.id" type="hidden" placeholder="placeholder"></el-input>
                </el-form-item>
                <el-form-item label="字典分类" prop="dicType">
                    <el-input v-model="dic.dicName" placeholder="请输入字典分类"></el-input>
                </el-form-item>
                <el-form-item label="字典key" prop="key">
                    <el-input v-model="dic.key" placeholder="请输入字典key"></el-input>
                </el-form-item>
                <el-form-item label="字典名称" prop="dicName">
                    <el-input v-model="dic.value" placeholder="请输入字典名称"></el-input>
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-radio-group v-model="dic.status">
                        <el-radio :label="1">有效</el-radio>
                        <el-radio :label="0">无效</el-radio>
                    </el-radio-group>

                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
        <el-button @click="saveDic(false,'form1')">取 消</el-button>
        <el-button type="primary" @click="saveDic(true,'form1')">确 定</el-button>
      </span>
        </el-dialog>
    </div>
</template>

<script>
    import {ajax} from "../../../assets/js/ajax"
    import api from "../../../assets/js/api"
    export default {
        name: "Dic",
        data(){
            return {
                dialogVisible:false,
                search:{
                    dicName:'',
                    value:'',
                    pageNum:1,
                    pageSize:8,
                    total:129,
                    pages:0,
                    rows:[]
                },
                dic:{
                    dicName:'',
                    key:'',
                    value:'',
                    status:'',

                },
                dialogTitle:'添加字典',
                rules:{
                    value:[
                        {required: true,message:"字典名称不能为空",trigger:'blur'}
                    ]
                }
            }
        },
        methods:{
            submit(form){
                this.$refs[form].validate((valid)=> {
                    if (valid) {
                        this.query();
                    }
                })
            },
            query(pageNum){
                if(pageNum){
                    this.search.pageNum = pageNum;
                }
                let loading = this.$loading({text:"正在查询..."});
                ajax({
                    url: api.system.dic.query.uri,
                    type:"POST",
                    data:this.search
                }).then(res => {
                    this.search = res.data
                    loading.close();
                })
            },
            handleClose(){
                this.dic.id=null;
                this.dic.dicName='';
                this.dialogVisible=false;
                this.$message("操作取消")
                this.$refs['form1'].resetFields();
            },
            saveDic(flag,form){
                if(!flag){
                    this.$message("操作取消")
                    this.dialogVisible=false;
                    return;
                }
                this.$refs[form].validate((valid)=>{
                    if(valid){
                        ajax({
                            url:api.system.dic.save.url,
                            type:"POST",
                            data:this.dic
                        }).then(res => {
                            if (res.code === 200){
                                this.$notify({
                                    title:"字典保存成功",
                                    message:"字典将在展示的时候将一些ID转换成对应的描述",
                                    duration:1500
                                })
                                this.dialogVisible=false;
                                this.dic={}
                            }
                        })

                    }
                })
                this.$refs[form].resetFields();
            },delDic(row){
                this.$confirm('确认删除字典【'+row.dicName+"】？", '提示', {
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
            },editDic(row){
                console.log(row)
                this.dialogTitle="编辑字典";
                this.dic.dicName=row.dicName;
                this.dic.id=row.id;
                this.dic.value=row.value;
                this.dic.key=row.key;
                this.dic.status=row.status;
                this.dialogVisible=true;
            },add(){
                this.dialogVisible=true;
            }
        },mounted() {
            this.query(1);
        }
    }
</script>

<style scoped>

</style>