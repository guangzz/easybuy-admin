<template>
<section>
  	<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true">
				<el-form-item>
					<el-button type="primary" @click="addKeyWord()">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="keyword" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column prop="kId" label="#" width="97" sortable>
			</el-table-column>
			<el-table-column prop="kName" label="关键词" width="850">
			</el-table-column>
			<el-table-column label="操作" width="245">
				<template slot-scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>     
          <el-button size="small" @click="deleteKey(scope.row)">删除</el-button>     
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
			<el-form :model="editForm" label-width="80px" ref="editForm">
				<el-form-item label="关键词" prop="kName" required>
					<el-input v-model="editForm.kName" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

    	<!--新增界面-->
		<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="80px" ref="addForm">
        	<el-form-item label="关键词" prop="kName" required>
					<el-input auto-complete="off" v-model="addForm.kName"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>

   </section>
</template>

<script>
import { getKeyWordList,editKeyWord,deleteKeyWord,insertKeyWord } from "../../api/api";
export default {
  data() {
    return {
      keyword:[],
      listLoading:false,
      sels: [], //列表选中列
       page:1,
      total:0,
      editFormVisible:false,
      editForm:[],
      editLoading:false,

      addFormVisible:false,
      addForm:[],
      addLoading:false
    };
  },
  methods: {
    selsChange: function(sels) {
      this.sels = sels;
    },
    handleCurrentChange(val) {
      this.page = val;
      this.getKeyWord();
    },
    getKeyWord(){
      this.listLoading = true;
      let para = {
        page: this.page
      };
       getKeyWordList(para).then(res => {
        this.total = res.data.date.totalCount;
        this.keyword = res.data.date.list;
        this.listLoading = false;
        //NProgress.done();
      });
    },
    addKeyWord(){
        this.addFormVisible = true;
    },
    handleEdit: function (index, row) {
				this.editFormVisible = true;
				this.editForm = Object.assign({}, row);
    },
    editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.editForm);
								editKeyWord(para).then((res) => {
								this.editLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['editForm'].resetFields();
								this.editFormVisible = false;
								this.getKeyWord();
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
							let para = Object.assign({}, this.addForm);
								insertKeyWord(para).then((res) => {
								this.addLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['addForm'].resetFields();
								this.addFormVisible = false;
								this.getKeyWord();
							});
						});
					}
				});
      },
      deleteKey(row){
      this.$confirm('确认删除吗', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          let para = Object.assign({}, row);
          deleteKeyWord(para).then((res) => {
              if(res.data.code==200){
							 this.$message({
                type: 'success',
                message: '删除成功!'
              });
              }else{
							 this.$message({
                type: 'error',
                message: res.data.message
              });
              }
								this.getKeyWord();
							});
        });
    },
  },
  mounted(){
    this.getKeyWord();
  }
};
</script>
