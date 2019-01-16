<template>
  <section style="margin-top:20px;">
	<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
        <el-form-item>
					<el-button type="primary"  @click="addSorts()">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

<el-table
      :data="sort"
      highlight-current-row
      v-loading="listLoading"
      @selection-change="selsChange"
      style="width: 100%;"
      :row-key="getRowKeys"
      :expand-row-keys="expands"
    >
 <el-table-column type="selection" width="55"></el-table-column>
  
        <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="商品名称">
            <span>{{ props.row.levelSorts }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
      <el-table-column prop="sId" label="#" width="80" sortable></el-table-column>
      <el-table-column prop="sName" label="一级分类名称" width="760"></el-table-column>
      <el-table-column label="操作" width="250">
             <template slot-scope="props">
               <el-button type="primary" size="mini" @click="handleEdits(props.row)">编辑</el-button>
                 <el-button type="primary" size="mini" @click="toggleRowExpansion(props.row)">二级分类</el-button>
                  <el-button type="primary" size="mini" @click="deleteSorts(props.row)">删除</el-button>
                  <el-button type="primary" size="mini" @click="addLevelSorts(props.row)">新增</el-button>
               </template>
      </el-table-column>
       <el-table-column type="expand">
      <template slot-scope="props">
         <el-table
      :data="props.row.levelSorts"
      style="width: 100%" v-loading="levelSortsLoading">
      <el-table-column
        prop="lsId"
        label="二级ID"
        width="180">
      </el-table-column>
       <el-table-column prop="lsName" label="一级分类名称" width="800"></el-table-column>
        <el-table-column label="操作" width="150">
             <template slot-scope="props">
                 <el-button type="primary" size="mini" @click="handleEdit(props.$index, props.row)">编辑</el-button>
                 <el-button type="primary" size="mini" @click="deletelevelSort(props.row)">删除</el-button>
               </template>
      </el-table-column>
    </el-table>
      </template>
    </el-table-column>
  </el-table>

    
		<!--编辑界面-->
		<el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="80px" ref="editForm">
				<el-form-item label="分类名称" prop="lsName" required>
					<el-input v-model="editForm.lsName" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

      <!--一级分类编辑-->
    	<el-dialog title="编辑" v-model="editFormVisibles" :close-on-click-modal="false">
			<el-form :model="editForms" label-width="80px" ref="editForms">
				<el-form-item label="分类名称" prop="sName" required>
					<el-input v-model="editForms.sName" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisibles = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmits" :loading="editLoadings">提交</el-button>
			</div>
		</el-dialog>


    
		<!--新增界面-->
		<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="80px" ref="addForm">
        	<el-form-item label="分类名称" prop="lsName" required>
					<el-input auto-complete="off" v-model="addForm.lsName"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>

    <!--新增界面-->
		<el-dialog title="新增" v-model="addFormVisibles" :close-on-click-modal="false">
      <el-form :model="addForms" label-width="80px" ref="addForms">
        	<el-form-item label="分类名称" prop="sName" required>
					<el-input auto-complete="off" v-model="addForms.sName"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisibles = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmits">提交</el-button>
			</div>
		</el-dialog>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-pagination
        layout="prev, pager, next"
        @current-change="handleCurrentChange"
        :page-size="20"
        :total="total"
        style="float:right;"
      ></el-pagination>
    </el-col>
  </section>
</template>

<script>
import { getSort,editUser,editlevelSorts,deleteSort,deletelevelSorts,editSort,insertlevelSorts,insertSort } from "../../api/api";
export default {
  data() {
    return {
      listLoading: false,
      sort: [],
      sels: [], //列表选中列
      page:1,
      total:0,
      id:'',
      filters:{},
      getRowKeys(row) {
        return row.sId;
      },
      // 要展开的行，数值的元素是row的key值
      expands: [],
      editFormVisible:false,
      editForm:[],
      levelSortsLoading:false,
      editLoading:false,
      editFormVisibles:false,
      editForms:[],
      editLoadings:false,
      addFormVisible:false,
      addForm:[],
      addLoading:false,
      addFormVisibles:false,
      addForms:[]
    };
  },
  methods: {
    selsChange: function(sels) {
      this.sels = sels;
    },
    addLevelSorts(row){
        this.addFormVisible = true;
        this.addForm = { sId: row.sId, lsName: ""}
    },
    addSorts(){
        this.addFormVisibles = true;
        this.addForms = { sName: ""}
    },
    getSorts() {
      let para = {
        page: this.page
      };
      getSort(para).then(res => {
        this.total = res.data.date.totalCount;
        this.sort = res.data.date.list;
        this.Loading = false;
        //NProgress.done();
      });
    },
    deleteSorts(row){
     this.$confirm('确认删除吗', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          row.levelSorts = null;
          let para = Object.assign({}, row);
          deleteSort(para).then((res) => {
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
								this.getSorts();
							});
        });
    },
    deletelevelSort(row){
      this.$confirm('确认删除吗', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          let para = Object.assign({}, row);
          deletelevelSorts(para).then((res) => {
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
								this.getSorts();
							});
         


        });
    },
    handleEdit: function (index, row) {
				this.editFormVisible = true;
				this.editForm = Object.assign({}, row);
    },
    handleEdits: function (row) {
        this.editFormVisibles = true; 
        this.editForms = Object.assign({}, row);
        
    },
    editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.levelSortsLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.editForm);
								editlevelSorts(para).then((res) => {
								this.levelSortsLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['editForm'].resetFields();
								this.editFormVisible = false;
								this.getSorts();
							});
						});
					}
				});
      },
      editSubmits: function () {
				this.$refs.editForms.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.levelSortsLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.editForms);
								editSort(para).then((res) => {
								this.levelSortsLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['editForms'].resetFields();
								this.editFormVisibles = false;
								this.getSorts();
							});
						});
					}
				});
      },
       addSubmit: function () {
				this.$refs.addForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.levelSortsLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.addForm);
								insertlevelSorts(para).then((res) => {
								this.levelSortsLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['addForm'].resetFields();
								this.addFormVisible = false;
								this.getSorts();
							});
						});
					}
				});
      },
       addSubmits: function () {
				this.$refs.addForms.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.levelSortsLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.addForms);
								insertSort(para).then((res) => {
								this.levelSortsLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['addForms'].resetFields();
								this.addFormVisibles = false;
								this.getSorts();
							});
						});
					}
				});
      },
    handleCheck(row) {
        const $table = this.$refs.table
        $table.toggleRowExpansion(row)
        $table.toggleRowSelection(row)
    },
    toggleRowExpansion(row) {
      this.tableLoading = true;
      if (this.expands.indexOf(row.sId) == 0) {
        this.expands = [];
      } else {
          this.expands = [];
          this.expands.push(row.sId);
          this.tableLoading = false;
      }
    },
     handleCurrentChange(val) {
      this.page = val;
      this.getProduct();
    },
  },
  mounted(){
    this.getSorts();
  }
};
</script>

