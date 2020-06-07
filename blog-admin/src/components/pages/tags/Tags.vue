<template>
  <div>
    <el-row :gutter="10">
     <el-col :span="10">
       <el-breadcrumb separator=">" >
         <el-breadcrumb-item :to="{ path: '/meter' }">首页</el-breadcrumb-item>
         <el-breadcrumb-item>内容管理</el-breadcrumb-item>
         <el-breadcrumb-item>标签管理</el-breadcrumb-item>
       </el-breadcrumb>
     </el-col>
    </el-row>
    <el-divider></el-divider>
    <el-row :gutter="10">
     <el-col :span="2">
       <div style="width: 50px;min-height: 100px"></div>
     </el-col>
      <el-col :span="20">
        <el-form ref="form" :model="search" label-width="60px" class="el-form--inline el-tabs--left" >
          <el-form-item label="标签">
            <el-input v-model="search.tagName" placeholder="输入标签名称"  prefix-icon="el-icon-search" style="margin-left: 5px"></el-input>
          </el-form-item>
          <el-button type="primary" @submit="submit('form')" style="margin-left: 30px">查询标签</el-button>
          <el-button type="info" @click="dialogVisible=true">添加标签</el-button>
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
                  label="标签名称"
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
              <span @click="editTag(scope.row)" style="cursor:pointer">编辑</span>
                |
              <span @click="delTag(scope.row)" style="cursor:pointer" >删除</span>
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
          <el-input v-model="form.tagId" placeholder="placeholder" type="hidden"></el-input>
        </el-from-item>
        <el-form-item label="标签名称" prop="tagName">
            <el-input v-model="form.tagName" placeholder="请输入标签名称"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="saveTag(false)">取 消</el-button>
        <el-button type="primary" @click="saveTag">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "Tags",
        data(){
          return {
            dialogVisible:false,
            search:{
              tagName:'',
              pageNum:1,
              pageSize:10,
              total:129,
              pages:0,
            },
            form:{
              tagName:'',
              tagId:null
            },
            dialogTitle:'添加标签',
            rules:{
              tagName:[
                  {required: true,message:"标签名称不能为空",trigger:'blur'}
                ]
            }
          }
        },
        methods:{
          submit(){

          },
          handleClose(done){
            console.log(done)
            this.form.tagId=null;
            this.form.tagName='';
            this.dialogVisible=false;
            this.$message("操作取消")
          },
          saveTag(flag){
            if(!flag){
              this.$message("操作取消")
              this.dialogVisible=false;
              return
            }
            this.$notify({
              title:"标签保存成功",
              message:"您可以在创作文章的时候指定对应的标签哦",
              duration:1500
            })
            this.dialogVisible=false;
          },delTag(row){
            this.$confirm('确认删除标签【'+row.name+"】？", '提示', {
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
          },editTag(row){
            console.log(row)
            this.dialogTitle="编辑标签";
            this.form.tagName=row.name
            this.form.tagId=row.id;
            this.dialogVisible=true;
          }
        }
    }
</script>

<style scoped>

</style>