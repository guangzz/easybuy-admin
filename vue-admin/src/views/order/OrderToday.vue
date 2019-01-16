<template>
  <section style="margin-top:20px;">
        	<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
        <el-form-item>
          
           <div class="block">
             <span>时间选择：</span>
                <el-date-picker
                  v-model="time"
                  type="daterange"
                  :editable="false"
                  @change="dateChangebirthday1"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  range-separator=" 至 "
                  start-placeholder="开始日期"
                  end-placeholder="结束日期">
                </el-date-picker>
              </div>                   
          </el-form-item>
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
				<el-select v-model="values"  placeholder="请选择" style="width:110px">
					<el-option 
						v-for="item in option"
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
					<el-button type="primary" v-on:click="getTodayOrder">查询</el-button>
				</el-form-item>
			</el-form>
		</el-col>

          <!--列表-->
          <el-table
            :data="order"
            highlight-current-row
            type="index"
            v-loading="listLoading"
            @selection-change="selsChange"
            style="width: 100%;"
            :row-key="getRowKeys"
            :expand-row-keys="expands"
          >
            <el-table-column type="selection" width="55"></el-table-column>
           
            <el-table-column prop="oiId" label="订单号" width="170" sortable>
              <template slot-scope="scope">
                {{scope.row.oiId}}
              </template>
              </el-table-column>
            <el-table-column prop="oiPayment" label="支付方式" width="92"></el-table-column>
            <el-table-column prop="oiTime" label="创建时间" width="165"></el-table-column>
            <el-table-column prop="oiState" label="订单状态" width="100">
              <template slot-scope="scope">
                <span v-if="scope.row.oiState==0">等待发货</span>
                <span v-if="scope.row.oiState==1">未付款</span>
                <span v-if="scope.row.oiState==2">完成</span>
                <span v-if="scope.row.oiState==3">过期</span>
                <span v-if="scope.row.oiState==4">待收货</span>
              </template>
            </el-table-column>
            <el-table-column prop="oiMoney" label="总价格" width="98">
              <template slot-scope="scope">{{scope.row.oiMoney|number}}</template>
            </el-table-column>
            <el-table-column prop="oiName" label="姓名" width="75">
              <template slot-scope="scope">
                <span v-if="scope.row.oiName!=null" >{{scope.row.oiName}}</span>
                <span v-else>无</span>
              </template>
            </el-table-column>
            <el-table-column prop="oiPhone" label="手机号码" width="130">
              <template slot-scope="scope">
                <span v-if="scope.row.oiPhone!=null" >{{scope.row.oiPhone}}</span>
                <span v-else>无</span>
              </template>
            </el-table-column>
             <el-table-column prop="oiLogistics" label="物流单号" width="130">
              <template slot-scope="scope">
                <span v-if="scope.row.oiLogistics!=null" >{{scope.row.oiLogistics}}</span>
                <span v-else>无</span>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="props">
                <el-button type="primary" size="mini" @click="toggleRowExpansion(props.row)">详细</el-button>
                
               <el-button v-if="props.row.oiState==0" type="primary" size="mini" @click="delivery(props.row)">去发货</el-button>
              </template>
            </el-table-column>
             <el-table-column type="expand">
              <template>
                <el-table style="width: 100%;" v-loading="tableLoading" :data="detailedTable">
                  <el-table-column prop="opId" label="#" width="120"></el-table-column>
                  <el-table-column prop="pId.pId" label="商品ID" width="120"></el-table-column>

                  <el-table-column prop="pId.pTitle" label="标题" width="465" class="pTitle">
                    <template slot-scope="scope">
                      <el-popover placement="top-end" width="240" trigger="hover">
                        <span>{{scope.row.pId.pTitle}}</span>
                        <span class="pTitle" slot="reference">{{scope.row.pId.pTitle}}</span>
                      </el-popover>
                    </template>
                  </el-table-column>

                  <el-table-column label="图片" width="80">
                    <template slot-scope="scope">
                      <el-popover placement="top-end" width="240" trigger="hover">
                        <img
                          width="250"
                          height="250"
                          :src="'http://images.concer.cn/'+scope.row.pId.pImages"
                        >
                        <el-button type="info" size="mini" slot="reference">显示</el-button>
                      </el-popover>
                    </template>
                  </el-table-column>
                  <el-table-column label="单价" width="120">
                    <template slot-scope="scope">
                      <span>{{scope.row.pId.pPrice | number}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="opCont" label="商品数量" width="180"></el-table-column>
                  <el-table-column prop="pId.pStock" label="库存"></el-table-column>
                </el-table>
              </template>
            </el-table-column>
          </el-table>

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

        <el-dialog title="编辑" v-model="dialogVisible" :close-on-click-modal="false">
			<el-form :model="editDate" label-width="80px" ref="editDate">
        <el-row>
          <el-col :span="8">
				<el-form-item label="姓名">
         {{editDate.oiName}}
          </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="手机号码">
         {{editDate.oiPhone}}
          </el-form-item>
          </el-col>
          </el-row>
          <el-form-item label="详细地址">
            {{editDate.oiProvince}}{{editDate.oiCity}}{{editDate.oiCounty}}{{editDate.oiDetail}}
          </el-form-item>
        <el-form-item label="物流单号" required>
            <el-input v-model="editDate.oiLogistics"></el-input>
      </el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="dialogVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading" >提交</el-button>
			</div>
		</el-dialog>

        </section>
</template>

<script>
import { getTodayOrderList, getDetailedOrderList,setDelivery,getHistoryOrderList } from "../../api/api";

export default {
  data(){
    return{
       filters: {
					name: ''
        },
      listLoading: false,
       editLoading: false,
      order: [],
      tableLoading: false,
      detailedTable: [],
      page: 1,
      time:'',
      times:'',
      total: 0,
      value: 'oiName',
        values: '',
        options: [{
          value: 'oiName',
          label: '姓名'
        }, {
          value: 'oiPhone',
          label: '手机号码'
        }],
         option: [{
          value: '0',
          label: '等待发货'
        }, {
          value: '1',
          label: '未付款'
        },
        {
          value: '2',
          label: '完成'
        },
        {
          value: '3',
          label: '过期'
        },
        {
          value: '4',
          label: '待收货'
        }],
      dialogVisible:false,
      editDate:[],
       getRowKeys(row) {
        return row.oiId;
      },
      // 要展开的行，数值的元素是row的key值
      expands: [],
      sels: [], //列表选择的数据
    }
  },
  methods:{
    dateChangebirthday1(val) {
        val=val.replace(/\s+|&nbsp;/ig, '')
        this.times=val.split("至");
        
    },
    selsChange: function(sels) {
      this.sels = sels;
    },
    handleCurrentChange(val) {
      this.page = val;
      this.getProduct();
    },
    getTodayOrder() {
      let para = {
         page: this.page,
				value: this.filters.value,
        select: this.value,
        oiState:this.values,
        beginTime:this.times[0],
        endTime:this.times[1]
      };
      getTodayOrderList(para).then(res => {
        this.total = res.data.date.totalCount;
        this.order = res.data.date.list;
        console.log(this.order);
        
        this.Loading = false;
        //NProgress.done();
      });
    },
    toggleRowExpansion(row) {
      this.tableLoading = true;
      let para = {
        oiId: row.oiId
      };
      if (this.expands.indexOf(row.oiId) == 0) {
        this.expands = [];
      } else {
        getDetailedOrderList(para).then(res => {
          this.detailedTable = res.data.date;
          this.expands = [];
          this.expands.push(row.oiId);
          this.tableLoading = false;
        });
      }
    },
    delivery(row){
      if(row.oiState==0){
        this.editDate = row;
        console.log(this.editDate)
        this.dialogVisible = true;
      }else{
        this.$message({
						message: '只能在待发货下的状态下发货',
						type: 'error'
        });
      }
    },
    editSubmit: function () {
      this.$refs.editDate.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
              //NProgress.start();
              this.editDate.oiState = 4;
							let para = Object.assign({}, this.editDate);
							console.log(this.editDate);
							setDelivery(para).then((res) => {
                this.editLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
                });
                 this.dialogVisible = false;
              });
						});
					}
				});
    }
  },
  mounted() {
    this.getTodayOrder();
  }
}
</script>


<style scope>
.el-table__expanded-cell {
  padding: 0;
  padding: 20px;
}
.el-table__expand-column .cell {
  display: none;
}
</style>


