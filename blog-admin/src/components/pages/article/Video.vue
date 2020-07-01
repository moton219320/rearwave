<template>
    <div>
        <h3>视频发布</h3>
        <el-divider></el-divider>
        <el-form ref="form" :model="form" label-width="90px">
            <el-row :gutter="10">
                <el-col :span="15">
                    <el-form-item label="视频标题：">
                        <el-input v-model="form.title" placeholder="请输入视频标题"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="10">
                <el-col :span="15">
                    <el-form-item label="视频摘要：">
                        <el-input v-model="form.abstractText"
                                  rows="3"
                                  type="textarea"
                                  maxlength="200"
                                  placeholder="请输入视频摘要，最多可输入200个字"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="10">
                <el-col :span="5">
                    <el-form-item label="视频分类：">
                        <el-select v-model="form.category" placeholder="请选择视频分类">
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
                    <el-form-item label="视频标签：">
                        <el-select style="width: 320px"
                                   v-model="form.tags"
                                   multiple
                                   :multiple-limit="limit"
                                   placeholder="请选择视频标签，最多能选3个">
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
                                    content="是否同步视频到第三方自媒体平台，目前支持同步视频到百家号，头条号，企鹅号，公众号等"
                                    placement="top">
                            <el-switch v-model="form.syncStatus" active-color="#13ce66"
                                       inactive-color="#ff4949"></el-switch>
                        </el-tooltip>

                    </el-form-item>
                </el-col>
                <el-col :span="4">
                    <el-form-item label="是否原创：">
                        <el-tooltip class="item" effect="dark"
                                    content="是否是原创的视频，原创视频同步到第三方平台将获得更好的收益，同时在本平台也将打上原创标签！"
                                    placement="top">
                            <el-switch v-model="form.isOriginal" active-color="#13ce66"
                                       inactive-color="#ff4949"></el-switch>
                        </el-tooltip>

                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="10" >
                <el-col :span="20">
                    <h4>上传视频</h4>
                    <span>1、视频时长不超过60s，30s内的竖屏小视频推荐效果最佳</span><br>
                    <span>2、无水印、高清晰度的视频效果更佳</span>
                    <el-form-item  prop="video" style="text-align: center">
                        <!-- action必选参数, 上传的地址 -->
                        <el-upload class="avatar-uploader el-upload--text"
                                   :action="uploadUrl"
                                   :show-file-list="false"
                                   :on-success="handleVideoSuccess"
                                   :before-upload="beforeUploadVideo"
                                   :on-progress="uploadVideoProcess">
                            <video v-if="form.video !='' && videoFlag == false" :src="form.video" class="avatar" controls="controls">您的浏览器不支持视频播放</video>
                            <i v-else-if="form.video =='' && videoFlag == false" class="el-icon-plus avatar-uploader-icon"></i>
                            <el-progress v-if="videoFlag == true" type="circle" :percentage="videoUploadPercent" style="margin-top:30px;"></el-progress>
                        </el-upload>
                        <P class="text">请保证视频格式正确，且不超过10M</P>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="10" style="text-align: center">
                <el-col :span="20">
                    <el-button type="info" style="width: 150px">草稿</el-button>
                    <el-button type="primary" style="width: 150px;margin-left: 40px">发布</el-button>
                </el-col>
            </el-row>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Video",
        data() {
            return {
                videoFlag:true,
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
                this.$upload.post('/上传接口地址', formdata).then(res => {
                    console.log(res.data);
                    this.$refs.md.$img2Url(pos, res.data);
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
            uploadUrl(){},
            videoUploadPercent(){},
            handleVideoSuccess(res) {                               //获取上传图片地址
                this.videoFlag = false;
                this.videoUploadPercent = 0;
                if(res.code == 200){
                    this.form.videoUploadId = res.data.uploadId;
                    this.form.video = res.data.uploadUrl;
                }else{
                    this.$message.error('视频上传失败，请重新上传！');
                }
            },
            beforeUploadVideo(file) {
                const isLt10M = file.size / 1024 / 1024  < 10;
                if (['video/mp4', 'video/ogg', 'video/flv','video/avi','video/wmv','video/rmvb'].indexOf(file.type) == -1) {
                    this.$message.error('请上传正确的视频格式');
                    return false;
                }
                if (!isLt10M) {
                    this.$message.error('上传视频大小不能超过10MB哦!');
                    return false;
                }
            },
            uploadVideoProcess(event, file){
                this.videoFlag = true;
                this.videoUploadPercent = file.percentage.toFixed(0);
            },

        }
    }
</script>

<style scoped>

</style>