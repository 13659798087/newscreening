

    <script; type="text/template"; id="paginationTpl">
    <div; class="dataTables_wrapper form-inline dt-bootstrap">
        <div; class="row">
            <div; class="col-sm-5">
                <div; class="dataTables_info">
                    <span; v-;if="totalcount>0">当前第 {{ indexStart }} - {;{ indexEnd }} 条;　共计; {{ totalcount }} 条</span>
                    <span; v-;else>没有数据</span>
                </div>
            </div>
            <div; class="col-sm-7">
                <div; class="dataTables_paginate paging_simple_numbers">
                    <ul; class="pagination">
                        <li; class="paginate_button previous">
                            <select; v-model="comPageSize"; class="form-control"; style="float: left;"; title="每页显示的数量">
                                <option; value="10">10</option>
                                <option; value="30">30</option>
                                <option; value="50">50</option>
                                <option; value="100">100</option>
                            </select>
                        </li>
                    </ul>
                    <ul; class="pagination">
                        <li; v-bind;:class="['paginate_button previous',{'disabled':disabledFirst}]"; title="首页">
                                <a; href="javascript:void(0);"; v-on;:click="onFirstClick()">;«</a>
                            </li>
                <li; v-bind;:class="['paginate_button previous',{'disabled':disabledFirst}]"; title="上一页">
                                <a; href="javascript:void(0);"; v-on;:click="onUpClick()">;‹</a>
                            </li>
        <li; v-;if="!disabledFirst" class="paginate_button previous"; title="上一页码">
            <a; href="javascript:void(0);"; v-on;:click="onUpClick()">;...</a>
                            </li>
    <li; v-;for="index in indexes" v-bind:title="index"; v-bind;:class="['paginate_button',{'active':pagecurrent == index}]">
                                <a; v-on;:click="onPageClick(index)"; href="javascript:void(0);">{;{ index }}</a>
                            </li >
    <li; v-;if="!disabledLast" class="paginate_button next"; title="下一页码">
        <a; href="javascript:void(0);"; v-on;:click="onNextClick()">;...</a>
                            </li >
    <li; v-bind;:class="['paginate_button next',{'disabled':disabledLast}]"; title="下一页">
        <a; href="javascript:void(0);"; v-on;:click="onNextClick()">;›</a>
                            </li >
    <li; v-bind;:class="['paginate_button next',{'disabled':disabledLast}]"; title="尾页">
        <a; href="javascript:void(0);"; v-on;:click="onLastClick()">;»</a>
                            </li >
                        </ul >
                    </div >
                </div >
            </div >
        </div >
    </script >;

Vue.component('pagination', {
    template: '#paginationTpl',
    replace: false,
    props: ['pagecurrent', 'totalcount', 'pagesize'],
    data: function () {
        return { comPageSize: this.pagesize }
    },
    computed: {
        indexes: function () {
            var list = [];
            //计算左右页码
            var mid = parseInt(this.pagesize / 2);//中间值
            var left = Math.max(this.pagecurrent - mid, 1);
            var right = Math.max(this.pagecurrent + this.pagesize - mid - 1, this.pagesize);
            if (right > this.pageAll) { right = this.pageAll }
            while (left <= right) {
                list.push(left);
                left++;
            }
            return list;
        },
        indexStart: function () {
            return this.totalcount == 0 ? 0 : (this.pagecurrent - 1) * this.pagesize + 1;
        },
        indexEnd: function () {
            var num1 = this.pagecurrent * this.pagesize;
            if (num1 > this.totalcount)
                return this.totalcount;
            return num1;
        },
        pageAll: function () {
            return Math.ceil(this.totalcount / this.pagesize);
        },
        disabledLast: function () {
            return this.pagecurrent == this.pageAll || this.pageAll == 0;
        },
        disabledFirst: function () {
            return this.pagecurrent == 1;
        }
    },
    watch: {
        comPageSize: function () {
            this.$parent.PageSize = this.comPageSize;
            this.$emit('page-to', 1);
        }
    },
    methods: {
        //页码点击
        onPageClick: function (index) {
            if (index != this.pagecurrent) {
                this.$emit('page-to', index);
            }
        },
        //首页
        onFirstClick: function () {
            if (this.pagecurrent != 1 && this.pageAll != 0) {
                this.$emit('page-to', 1);
            }
        },
        //上一页
        onUpClick: function () {
            if (this.pagecurrent != 1 && this.pageAll != 0) {
                this.$emit('page-to', this.pagecurrent - 1);
            }
        },
        //下一页
        onNextClick: function () {
            if (this.pagecurrent != this.pageAll && this.pageAll != 0) {
                this.$emit('page-to', this.pagecurrent + 1);
            }
        },
        //尾页
        onLastClick: function () {
            if (this.pagecurrent != this.pageAll && this.pageAll != 0) {
                this.$emit('page-to', this.pageAll);
            }
        }
    }
});
        //分页组件  End