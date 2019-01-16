<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
				<el-select v-model="value" placeholder="请选择" style="width:110px">
					<el-option
						v-for="item in options"
						:key="item.value"
						:label="item.label"
						:value="item.value">	
					</el-option>
  		</el-select>
		</el-form-item>
				<el-form-item>
					<el-input v-model="filters.value" placeholder="搜索内容"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getUsers">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="showPass">显示密码</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="users" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column prop="uId" label="#" width="80" sortable>
			</el-table-column>
			<el-table-column prop="uName" label="姓名" width="130">
			</el-table-column>
			<el-table-column prop="uPhone" label="手机号码" width="150" sortable>
			</el-table-column>
			<el-table-column prop="uPwd" label="密码" width="160">
				<template slot-scope="scope">  
						<span v-if="enPass==true">{{scope.row.uPwd}}</span>
						<span v-else	>********</span>
				</template>
			</el-table-column>
			<el-table-column prop="uState" label="状态" width="120">
				<template slot-scope="scope">    
					<el-switch
						@change="updateState(scope.$index, scope.row)"
						v-model="scope.row.uState"
						active-color="#42d885"
						inactive-color="#ff4949"
						:on-value="0"
    				:off-value="1">
					</el-switch>
				</template>
			</el-table-column>
			<el-table-column prop="uOpenid" label="微信绑定" min-width="100">
				<template slot-scope="scope">               
                    <p v-if="scope.row.uOpenid==null">
                        未绑定
                    </p>               
										 <p v-if="scope.row.uOpenid!=null">
                        已绑定
                    </p>   
            </template>
			</el-table-column>
			<el-table-column prop="uTime" label="注册时间" min-width="150" sortable>
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template slot-scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>     
				</template>
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
			</el-pagination>
		</el-col>

		<!--编辑界面-->
		<el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
				<el-form-item label="姓名" prop="uName">
					<el-input v-model="editForm.uName" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="手机号码" prop="uPhone" required>
					<el-input v-model="editForm.uPhone" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="状态">
					<el-switch
						v-model="editForm.uState"
						active-color="#42d885"
						inactive-color="#ff4949"
						:on-value="0"
    				:off-value="1">
					</el-switch>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>
	</section>
</template>

<script>
	//import NProgress from 'nprogress'
	import { getUserListPage, setUserState, batchRemoveUser, editUser } from '../../api/api';

	export default {
		data() {
			//手机验证
			 var checkPhone = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('手机号不能为空'));
        } else {
          const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
          console.log(reg.test(value));
          if (reg.test(value)) {
            callback();
          } else {
            return callback(new Error('请输入正确的手机号'));
          }
        }
      };
			return {
				 options: [{
          value: 'uName',
          label: '姓名'
        }, {
          value: 'uPhone',
          label: '手机号码'
        }],
        value: 'uName',
				filters: {
					name: ''
				},
				enPass:false,//显示密码
				users: [],
				total: 0,
				page: 1,
				listLoading: false,
				sels: [],//列表选中列
				editFormVisible: false,//编辑界面是否显示
				editLoading: false,
				editFormRules: {
					uName: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					],
					uPhone: [
						{ validator: checkPhone, trigger: 'blur' }
					]
				},
				//编辑界面数据
				editForm: {
					id: 0,
					uName: '',
					uPhone: ''
				},

			}
		},
		methods: {
			//性别显示转换
			formatSex: function (row, column) {
				return row.sex == 1 ? '男' : row.sex == 0 ? '女' : '未知';
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getUsers();
			},
			//获取用户列表
			getUsers() {
				let para = {
					page: this.page,
					value: this.filters.value,
					select: this.value
				};
				this.listLoading = true;
				//NProgress.start();
				getUserListPage(para).then((res) => {
					this.total = res.data.date.totalCount;
					this.users = res.data.date.list;
					this.listLoading = false;
					//NProgress.done();
				});
			},
			//操作用户状态
			updateState: function (index, row) {
					this.listLoading = true;
					//NProgress.start();
					let para = { id: row.uId };
					setUserState(para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
						this.$message({
							message: '操作成功',
							type: 'success'
						});
						this.getUsers();
					});
			},
			//显示编辑界面
			handleEdit: function (index, row) {
				this.editFormVisible = true;
				this.editForm = Object.assign({}, row);
			},
			//编辑
			editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.editForm);
							console.log(this.editForm);
							
								editUser(para).then((res) => {
								this.editLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['editForm'].resetFields();
								this.editFormVisible = false;
								this.getUsers();
							});
						});
					}
				});
			},
			selsChange: function (sels) {
				this.sels = sels;
			},
			//批量删除
			batchRemove: function () {
				var ids = this.sels.map(item => item.id).toString();
				this.$confirm('确认删除选中记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { ids: ids };
					batchRemoveUser(para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getUsers();
					});
				}).catch(() => {

				});
			},
			showPass:function(index, row){
				 this.enPass = true;
			}
		},
		mounted() {
			this.getUsers();
		}
	}

</script>
