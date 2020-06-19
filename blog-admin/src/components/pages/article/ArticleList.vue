<template>
    <div>
        <el-row :gutter="10">
            <el-col :span="10">
                <el-breadcrumb separator=">" >
                    <el-breadcrumb-item :to="{ path: '/meter' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>内容管理</el-breadcrumb-item>
                    <el-breadcrumb-item>文章管理</el-breadcrumb-item>
                </el-breadcrumb>
            </el-col>
        </el-row>
        <el-divider></el-divider>
        <el-row :gutter="10">
            <el-col :span="24">
                <el-form ref="form" :model="search" label-width="80px"  style="margin-top: 15px">
                    <el-row :gutter="10">
                        <el-col :span="10">
                            <el-form-item label="文章名称">
                                <el-input v-model="search.title" placeholder="输入文章名称"
                                          prefix-icon="el-icon-search"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="7" class="col-align-l">
                            <el-form-item label="分类" >
                                <el-select v-model="search.category" placeholder="选择分类">
                                    <el-option label="全部" value="0"></el-option>
                                    <el-option
                                            v-for="item in category.rows"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.id">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="7" class="col-align-l">
                            <el-form-item label="标签">
                                <el-select v-model="search.tags" placeholder="选择标签" value="0">
                                    <el-option label="全部" value="0"></el-option>
                                    <el-option
                                            v-for="item in tags.rows"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.id">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="10">
                        <el-col :span="10" class="col-align-l">
                            <el-form-item label="发布时间" >
                                <el-date-picker
                                        v-model="search.date"
                                        type="daterange"
                                        align="center"
                                        unlink-panels
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        :picker-options="pickerOptions">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8" class="col-align-l">
                            <el-form-item label="文章类型">
                                <el-select v-model="search.type" placeholder="请选择文章类型" style="width: 180px">
                                    <el-option value="0" label="全部"></el-option>
                                    <el-option value="1" label="图文"></el-option>
                                    <el-option value="2" label="视频"></el-option>
                                    <el-option value="3" label="图集"></el-option>
                                    <el-option value="4" label="碎语"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="3">
                            <el-button type="primary" @click="submit('form')">查询文章</el-button>
                        </el-col>
                        <el-col :span="3">
                            <el-button type="info" @click="publish">发布文章</el-button>
                        </el-col>
                    </el-row>
                </el-form>
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
                            prop="title"
                            align="center"
                            label="文章名称"
                            width="180">
                    </el-table-column>
                    <el-table-column
                            prop="type"
                            align="center"
                            label="文章类型"
                            :formatter="getValue"
                            width="100">
                    </el-table-column>
                    <el-table-column
                            prop="category"
                            align="center"
                            :formatter=getValue
                            label="分类">
                    </el-table-column>
                    <el-table-column
                            prop="syncStatus"
                            align="center"
                            label="同步状态">
                    </el-table-column>
                    <el-table-column
                            prop="createDate"
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
                            <span @click="editArticle(scope.row)" style="cursor:pointer">编辑</span>
                            |
                            <span @click="delArticle(scope.row)" style="cursor:pointer" >删除</span>
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
        </el-row>
    </div>
</template>

<script>
    import {getDicValue,formatDate} from "../../../assets/js/util";
    import {ajax} from "../../../assets/js/ajax";
    import api from "../../../assets/js/api";

    export default {
        name: "ArticleList",
        data(){
            return {
                dialogVisible:false,
                search:{
                    title:'',
                    pageNum:1,
                    pageSize:8,
                    total:129,
                    date:[],
                    type:'',
                    pages:0,
                    category:'',
                    tags:'',
                    rows:[]
                },
                category:{
                    rows:[
                        {
                            id:1,
                            name:"Java"
                        }
                    ]
                },
                tags:{
                    rows:[
                        {
                            id:1,
                            name:"黑科技"
                        }
                    ]
                },
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
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
            },query(pageNum){
                if(pageNum){
                    this.search.pageNum = pageNum;
                }
                let loading = this.$loading({text:"正在查询..."});
                ajax({
                    url: api.article.query.uri,
                    type:"POST",
                    data:this.search
                }).then(res => {
                    this.search.total = res.data.total
                    this.search.rows = res.data.rows
                    loading.close();
                })
            },
            handleClose(done){
                console.log(done)
                this.form.CategoryId=null;
                this.form.CategoryName='';
                this.dialogVisible=false;
                this.$message("操作取消")
            },
            delArticle(row){
                this.$confirm('确认删除文章【'+row.title+"】？", '提示', {
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
            },editArticle(row){
                // console.log(row)
                this.dialogTitle="编辑文章";
                this.form.name=row.name
                this.form.id=row.id;
                this.dialogVisible=true;
            },
            getValue(row){
                return getDicValue(row.id,this.category.rows,'name')
            },
            format(row){
                return formatDate(row.createDate,"yyyy/MM/dd hh:mm:ss");
            },publish(){
                this.$router.push("/article")
            }
        },mounted() {
            ajax({
                url:api.tags.query.uri,
                type:"POST",
                data:{
                    pageNum:1,
                    pageSize:999
                }
            }).then(res => {
                this.tags.rows = res.data.rows;
            })
            ajax({
                url:api.category.query.uri,
                type:"POST",
                data:{
                    pageNum:1,
                    pageSize:999
                }
            }).then(res => {
                this.category.rows = res.data.rows;
            })
            this.query();
        }
    }
</script>

<style scoped>
.el-button{
    margin-left: 5px;
}
.el-form-item__content{
    margin-left: 10px !important;
}
</style>