<template>
  <section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
          <div>
              <el-cascader
                expand-trigger="hover"
                :options="options"
                 filterable
                 @change="handleItemChange"
                change-on-select
                :clearable=true
                >
              </el-cascader>
          </div>
          </el-form-item>
				<el-form-item>
					<el-input v-model="filters.value" placeholder="搜索内容"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary"  @click="getProduct()">查询</el-button>
				</el-form-item>
        <el-form-item>
					<el-button type="primary"  @click="AddProduct()">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="product" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column prop="pId" label="#" width="80" sortable>
			</el-table-column>
		  <el-table-column prop="pTitle" label="标题" width="230" class="pTitle">
        <template slot-scope="scope">

          <el-popover
                  placement="top-end"
                  width="240"
                  trigger="hover"
                  >
                  <span>{{scope.row.pTitle}}</span>
                  <span class="pTitle" slot="reference">{{scope.row.pTitle}}</span>
                </el-popover>
        
        </template>
			</el-table-column>  
      	<el-table-column prop="pPrice" label="单价" width="90">
          <template slot-scope="scope">
            <span>{{scope.row.pPrice | number}}</span>
          </template>
			</el-table-column>
      <el-table-column label="图片" width="80">
          <template slot-scope="scope">
             
                 <el-popover
                  placement="top-end"
                  width="240"
                  trigger="hover"
                  >
                  <img width="250" height="250" :src='"http://images.concer.cn/"+scope.row.pImages' />
                  <el-button type="info" size="mini" slot="reference">显示</el-button>
                </el-popover>
          </template>
      </el-table-column>
      <el-table-column label="描述" width="80">
          <template slot-scope="scope">
             
                 <el-popover
                  placement="top-end"
                  width="240"
                  trigger="hover"
                  >
                  <span>{{scope.row.pDescription}}</span>
                  <el-button type="info" size="mini" slot="reference">显示</el-button>
                </el-popover>
          </template>
      </el-table-column>
      	<el-table-column prop="pDiscount" label="折扣" width="80">
          <template slot-scope="scope">
            <span v-if="scope.row.pDiscount==1">
              无折扣
            </span>
            <span v-else>{{scope.row.pDiscount}}</span>
          </template>
			</el-table-column>
      <el-table-column prop="pQuantity" label="销量" width="94">
			</el-table-column>
      <el-table-column prop="lsId.lsName" label="二级分类" width="100">
			</el-table-column>
      <el-table-column prop="lsId.sId.sName" label="一级分类" width="100">
			</el-table-column>
      <el-table-column prop="pStock" label="库存" width="80">
			</el-table-column>
      <el-table-column prop="pState" label="状态" width="80">
        <template slot-scope="scope">
          <el-switch
						@change="updateState(scope.$index, scope.row)"
						v-model="scope.row.pState"
						active-color="#42d885"
						inactive-color="#ff4949"
						:on-value="0"
    				:off-value="1">
					</el-switch>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="120">
				<template slot-scope="scope">
					<el-button size="small" @click="EditProduct(scope.$index, scope.row)">编辑</el-button>     
				</template>
			</el-table-column>
		</el-table>

    <!--编辑界面-->
		<el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <el-row>
				<el-form-item label="标题" prop="pTitle" required>
					<el-input v-model="editForm.pTitle" auto-complete="off"></el-input>
				</el-form-item>
        </el-row>
        <el-row>
        <el-col :span="6">
				<el-form-item label="价格" prop="pPrice" required>
					<el-input v-model="editForm.pPrice" auto-complete="off"></el-input>
				</el-form-item>
        </el-col>
        <el-col :span="6">
        <el-form-item label="库存" prop="pPrice" required>
					<el-input v-model="editForm.pStock" auto-complete="off"></el-input>
				</el-form-item>
        </el-col>
        <el-col :span="6">
        <el-form-item label="折扣" prop="pDiscount" required>
           <el-tooltip class="item" effect="dark" content="折扣输入1，则无折扣 输入0.8 则8折" placement="right">
					<el-input v-model="editForm.pDiscount" auto-complete="off"></el-input>
           </el-tooltip>
				</el-form-item>
        </el-col>
        <el-col :span="6">
           <el-form-item label="状态" prop="pDiscount" required>
          <el-switch
						v-model="editForm.pState"
						active-color="#42d885"
						inactive-color="#ff4949"
						:on-value="0"
    				:off-value="1">
					</el-switch>
          </el-form-item>
        </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
          	<el-form-item label="分类" required>
          <div>
              <el-cascader
                expand-trigger="hover"
                :options="options"
                v-model="selectedOptions"
                 filterable
                 @change="handleItemChange"
                change-on-select
                :clearable=true
                >
              </el-cascader>
          </div>
          </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="销量" prop="pDiscount" required>
					<el-input v-model="editForm.pQuantity " auto-complete="off"></el-input>
				</el-form-item>
        </el-col>
        </el-row>
        <el-row>
				<el-form-item label="描述" prop="pTitle" required>
          <el-input type="textarea" v-model="editForm.pDescription"></el-input>
				</el-form-item>
        </el-row>
        
				<el-row>
        <el-form-item label="图片">
        <el-upload
          class="avatar-uploader"
          :action="domain"
          :show-file-list="false"
          :data="QiniuData"
          :on-remove="handleRemove"
          :on-error="uploadError"
          :on-success="uploadSuccess"
          :before-remove="beforeRemove"
          :before-upload="beforeAvatarUpload">
          <img v-if="editForm.pImages!=null" :src="'http://images.concer.cn/'+editForm.pImages" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <span>不上传图片默认不修改图片</span>
        </el-form-item>
        </el-row>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>


    
		<!--新增界面-->
		<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
        <el-row>
				<el-form-item label="标题" prop="pTitle" required>
					<el-input v-model="addForm.pTitle" auto-complete="off"></el-input>
				</el-form-item>
        </el-row>
        <el-row>
        <el-col :span="6">
				<el-form-item label="价格" prop="pPrice" required>
					<el-input v-model="addForm.pPrice" auto-complete="off"></el-input>
				</el-form-item>
        </el-col>
        <el-col :span="6">
        <el-form-item label="库存" prop="pPrice" required>
					<el-input v-model="addForm.pStock" auto-complete="off"></el-input>
				</el-form-item>
        </el-col>
        <el-col :span="6">
        <el-form-item label="折扣" prop="pDiscount" required>
           <el-tooltip class="item" effect="dark" content="折扣输入1，则无折扣 输入0.8 则8折" placement="right">
					<el-input v-model="addForm.pDiscount" auto-complete="off"></el-input>
           </el-tooltip>
				</el-form-item>
        </el-col>
        <el-col :span="6">
           <el-form-item label="状态" prop="pDiscount" required>
          <el-switch
						v-model="addForm.pState"
						active-color="#42d885"
						inactive-color="#ff4949"
						:on-value="0"
    				:off-value="1">
					</el-switch>
          </el-form-item>
        </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
          	<el-form-item label="分类" required>
          <div>
              <el-cascader
                expand-trigger="hover"
                :options="options"
                v-model="selectedOptions"
                 filterable
                 @change="handleItemChange"
                change-on-select
                :clearable=true
                >
              </el-cascader>
          </div>
          </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="销量" prop="pDiscount" required>
					<el-input v-model="addForm.pQuantity " auto-complete="off"></el-input>
				</el-form-item>
        </el-col>
        </el-row>
        <el-row>
				<el-form-item label="描述" prop="pTitle" required>
          <el-input type="textarea" v-model="addForm.pDescription"></el-input>
				</el-form-item>
        </el-row>
        
				<el-row>
        <el-form-item label="图片">
        <el-upload
          class="avatar-uploader"
          :action="domain"
          :show-file-list="false"
          :data="QiniuData"
          :on-remove="handleRemove"
          :on-error="uploadError"
          :on-success="uploadSuccess"
          :before-remove="beforeRemove"
          :before-upload="beforeAvatarUpload">
          <img v-if="this.addForm.pImages!=null" :src="'http://images.concer.cn/'+this.addForm.pImages" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <span>不上传图片默认不修改图片</span>
        </el-form-item>
        </el-row>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>

  <el-col :span="24" class="toolbar">
			<el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
			</el-pagination>
		</el-col>

	</section>
</template>

<script>
//import NProgress from 'nprogress'
  import { getProductList,setProductState,getSortList,editProduct,addProduct } from '../../api/api';
  import {genUpToken} from "../../common/js/genUpToken";
  import NProgress from 'nprogress'
  import 'nprogress/nprogress.css'
export default {
  data(){
    return{
      //imgUrl:'data:image/gif;base64,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',
      page: 1,
      total: 0,
      listLoading: false,
      sels: [],//列表选中列

      editFormVisible: false,//编辑界面是否显示
      editLoading: false,
      editFormRules: {
				},
      //编辑界面数据
			editForm: {},  
      filters:{
        value: '',
        sId:'',
        lsId:''
      },

      addForm:{
        pImages:''
      },
      addFormVisible:false,
      addLoading: false,
      addForm: {
        },
        addFormRules: {
				},
      selectedOptions2: [],
      options: [],
      loading: false,
      QiniuData: {
        key: "", //图片名字处理
        token: "" //七牛云token
      },
      domain: "http://up-z2.qiniup.com", // 七牛云的上传地址（华南区）
      qiniuaddr: "http://images.concer.cn", // 七牛云的图片外链地址
      uploadPicUrl: "", //提交到后台图片地址
      fileList: [],
      product:[],
      selectedOptions:[]
    }
  },
  methods:{
    //获取商品
    getProduct() {
				let para = {
					page: this.page,
          pTitle: this.filters.value,
          'lsId.lsId':this.lsId,
          'lsId.sId.sId':this.sId
				};
        //NProgress.start();
        this.listLoading = true;
				getProductList(para).then((res) => {
          this.total = res.data.date.totalCount;
          this.product = res.data.date.list;
          this.listLoading = false;
				});
      },
      handleCurrentChange(val) {
				this.page = val;
				this.getProduct();
      },
      handleItemChange(val) {
        this.sId = val[0];
        this.lsId = val[1];
      },
      //编辑窗口
      EditProduct: function (index, row) {
        this.editFormVisible = true;
        this.editForm = Object.assign({}, row);
       // this.selectedOptions[0] = row.lsId.sId.sId;
        //this.selectedOptions[1] = row.lsId.lsId;
        this.$set(this.selectedOptions,0,row.lsId.sId.sId)
        this.$set(this.selectedOptions,1,row.lsId.lsId)
        console.log(this.selectedOptions);
      },
      AddProduct: function () {
				this.addFormVisible = true;
				this.addForm = {
				};
			},
      getSort:function(){
        getSortList().then((res) => {
          this.getProduct();
            this.options = res.data.date.map(item => {
            return {
              value:item.sId,
              label:item.sName,
              children: item.levelSorts.map(levelSorts => {
                return {
                  value:levelSorts.lsId,
                  label:levelSorts.lsName
                }
              })
            }
          })
					});
      },
      //编辑
			editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
              //NProgress.start();
              this.editForm['lsId.lsId'] = this.lsId;
              this.editForm['lsId.sId.sId'] = this.sId;
              let para = Object.assign({}, this.editForm);
               para.lsId = ''
							 console.log(this.editForm);
								editProduct(para).then((res) => {
								this.editLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
                });
                this.selectedOptions = []
								this.$refs['editForm'].resetFields();
								this.editFormVisible = false;
								this.getProduct();
							});
						});
					}
				});
      },
      addSubmit: function () {
				this.$refs.addForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.addLoading = true;
              //NProgress.start();
              this.addForm['lsId.lsId'] = this.lsId;
              this.addForm['lsId.sId.sId'] = this.sId;
							let para = Object.assign({}, this.addForm);
							addProduct(para).then((res) => {
								this.addLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['addForm'].resetFields();
								this.addFormVisible = false;
								this.getProduct();
							});
						});
					}
				});
			},
      updateState: function (index, row) {
					this.listLoading = true;
					//NProgress.start();
					let para = { pId: row.pId };
					setProductState(para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
						this.$message({
							message: '操作成功',
							type: 'success'
						});
						this.getProduct();
      console.log(this.uploadPicUrl)
					});
			},
      selsChange: function (sels) {
				this.sels = sels;
      },
      handleRemove(file, fileList) {
      this.uploadPicUrl = "";
    },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
    beforeAvatarUpload(file) {
      NProgress.start();
      const isPNG = file.type === "image/png";
      const isJPEG = file.type === "image/jpeg";
      const isJPG = file.type === "image/jpg";
      const isLt2M = file.size / 1024 / 1024 < 2;
      
      if (!isPNG && !isJPEG && !isJPG) {
        this.$message.error("上传图片只能是 jpg、png、jpeg 格式!");
        return false;
      }
      if (!isLt2M) {
        this.$message.error("上传图片大小不能超过 2MB!");
        return false;
      }
      this.QiniuData.key = `upload_pic_${file.name}`;
    },
    uploadSuccess(response, file, fileList) {
      this.editForm.pImages = response.key
      this.addForm.pImages = response.key
      console.log(this.addForm);
      this.uploadPicUrl = `${this.qiniuaddr}/${response.key}`;
      console.log(this.uploadPicUrl)
      NProgress.done();
    },
    uploadError(err, file, fileList) {
      this.$message({
        message: "上传出错，请重试！",
        type: "error",
        center: true
      });
    },
    beforeRemove(file, fileList) {
      // return this.$confirm(`确定移除 ${ file.name }？`);
    },
  },
  mounted(){
    this.getSort();
  },
  created(){
      var token;
      var policy = {};
      var bucketName = 'easybuy';
      var AK ='DUiKfJhWejN7wrxGAKkA0fJlqjuANY1UZ7O8SvCe';
      var SK = '4AVsmeCr-4P8CYa9a4JwjNk1kuGmlHS1jLlAsrhh';
      var deadline = Math.round(new Date().getTime() / 1000) + 3600;
      policy.scope = bucketName;
      policy.deadline = deadline;
      token=genUpToken(AK, SK, policy);
      this.QiniuData.token = token;
      
  }
}
</script>

<style>
  .pTitle{
    white-space: nowrap;
    text-overflow:ellipsis;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

