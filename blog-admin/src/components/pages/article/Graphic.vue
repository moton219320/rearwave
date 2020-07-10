<template>
    <div>
        <h3>图文发布</h3>
        <el-divider></el-divider>
        <el-form ref="form" :model="form" label-width="90px">
            <el-row :gutter="10">
                <el-col :span="15">
                    <el-form-item label="文章标题：">
                        <el-input v-model="form.title" placeholder="请输入文章标题"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="10">
                <el-col :span="15">
                    <el-form-item label="文章摘要：">
                        <el-input v-model="form.abstractText"
                                  rows="3"
                                  type="textarea"
                                  maxlength="200"
                                  placeholder="请输入文章摘要，最多可输入200个字"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="10">
                <el-col :span="6">
                    <el-form-item label="文章分类：">
                        <el-select v-model="form.category" placeholder="请选择文章分类">
                            <el-option
                                    v-for="item in category"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="文章标签：">
                        <el-select style="width: 320px"
                                   v-model="form.tags"
                                   multiple
                                   :multiple-limit="limit"
                                   placeholder="请选择文章标签，最多能选3个">
                            <el-option
                                    v-for="item in tags"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="10">
                <el-col :span="4">
                    <el-form-item label="是否同步：">
                        <el-tooltip class="item" effect="dark"
                                    content="是否同步文章到第三方自媒体平台，目前支持同步文章到百家号，头条号，企鹅号，公众号等"
                                    placement="top">
                            <el-switch v-model="form.syncStatus" active-color="#13ce66"
                                       inactive-color="#ff4949"></el-switch>
                        </el-tooltip>

                    </el-form-item>
                </el-col>
                <el-col :span="4">
                    <el-form-item label="是否原创：">
                        <el-tooltip class="item" effect="dark"
                                    content="是否是原创的文章，原创文章同步到第三方平台将获得更好的收益，同时在本平台也将打上原创标签！"
                                    placement="top">
                            <el-switch v-model="form.isOriginal" active-color="#13ce66"
                                       inactive-color="#ff4949"></el-switch>
                        </el-tooltip>

                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="10">
                <el-col :span="24">
                    <el-form-item label="文章内容：">
                        <mavon-editor
                                v-model="form.content"
                                ref="md"
                                @imgAdd="$imgAdd"
                                @change="change"
                                style="min-height: 600px"/>
                    </el-form-item>

                </el-col>
            </el-row>
            <el-row :gutter="10" style="text-align: center">
                <el-col :span="24">
                    <el-button type="info" style="width: 150px">草稿</el-button>
                    <el-button type="primary" style="width: 150px;margin-left: 40px">发布</el-button>
                </el-col>
            </el-row>
        </el-form>
    </div>
</template>

<script>
    import {upload} from "@/assets/js/ajax";
    export default {
        name: "Graphic",
        data() {
            return {
                limit: 3,
                form: {
                    type: 1,
                    title: '',
                    category: null,
                    syncStatus: 0,
                    isOriginal: 0,
                    abstractText: '',
                    content: ''
                },
                category: [
                    {
                        id: 1,
                        name: "前端"
                    }
                ],
                tags: [
                    {
                        id: 1,
                        name: "标签1"
                    }, {
                        id: 2,
                        name: "标签2"
                    }, {
                        id: 3,
                        name: "标签3"
                    }
                ]
            }
        }, methods: {
            // 将图片上传到服务器，返回地址替换到md中
            $imgAdd(pos, $file) {
                let formdata = new FormData();
                formdata.append("file", $file);
                upload('/attach/image', formdata).then(res => {
                    console.table(res);
                    let path = res.data.static+res.data.filePath;
                    console.log("文件路径 %s",path);
                    this.$refs.md.$img2Url(pos, path);
                }).catch(err => {
                    console.log(err)
                })
            },
            // 所有操作都会被解析重新渲染
            change(value, render) {
                // render 为 markdown 解析后的结果[html]
                console.log(render + "\n" + value)
                this.form.content = value;
            },
        }
    }
</script>

<style scoped>

</style>