
/*<![CDATA[*/

    //     function timestampFormat(timestamp){
    //         debugger;
    // // 根据 timestamp 值初始化一个 Date 对象
    //         var tmpDate = new Date(timestamp);
    // // 按格式填充数据
    //         return tmpDate.getFullYear()+"-"
    //             +(tmpDate.getMonth()+1)+"-"
    //             +tmpDate.getDate()+" "
    //             +tmpDate.getHours()+":"
    //             +tmpDate.getMinutes()+":"
    //             +tmpDate.getSeconds()
    // 			+"."
    //             +tmpDate.getMilliseconds()
    // 			;
    //     }

    //该转换方法在月份和天数为小于 10 的数字的时候不会自动的在前面加 0 填充，对此，将该函数进行完善。

   /* function timestampFormat1(timestamp){
    // 根据 timestamp 值初始化一个 Date 对象
        var tmpDate = new Date(timestamp);
    // 用于当数字小于 10 是自动在首位填充 0
        function firstHolder(num){
            if(num<10){
                num = '0'+num;
            }
            return num;
        }
    // 按格式填充数据
        return tmpDate.getFullYear()+"-"
            +firstHolder(tmpDate.getMonth()+1)+"-"
            +firstHolder(tmpDate.getDate())+" "
            +firstHolder(tmpDate.getHours())+":"
            +firstHolder(tmpDate.getMinutes())+":"
            +firstHolder(tmpDate.getSeconds())
            // +"."
            // +tmpDate.getMilliseconds()
            ;
    }*/

    var timestampFormat1 = function(timestamp){
        // 根据 timestamp 值初始化一个 Date 对象
        var tmpDate = new Date(timestamp);
        // 用于当数字小于 10 是自动在首位填充 0
        function firstHolder(num){
            if(num<10){
                num = '0'+num;
            }
            return num;
        }
        // 按格式填充数据
        return tmpDate.getFullYear()+"-"
            +firstHolder(tmpDate.getMonth()+1)+"-"
            +firstHolder(tmpDate.getDate())
            ;
    };


   /*
   格式化时间 yyyy-MM-dd HH:mm:ss
    */
    var timestampFormat = function(timestamp){
        // 根据 timestamp 值初始化一个 Date 对象
        var tmpDate = new Date(timestamp);
        // 用于当数字小于 10 是自动在首位填充 0
        function firstHolder(num){
            if(num<10){
                num = '0'+num;
            }
            return num;
        }

        // 按格式填充数据
        return tmpDate.getFullYear()+"-"
            +firstHolder(tmpDate.getMonth()+1)+"-"
            +firstHolder(tmpDate.getDate())+" "
            +firstHolder(tmpDate.getHours())+":"
            +firstHolder(tmpDate.getMinutes())+":"
            +firstHolder(tmpDate.getSeconds())
            // +"."
            // +tmpDate.getMilliseconds()
            ;
    };

/*
   格式化时间 yyyy-MM-dd
    */
     function FormatDate(timestamp){

       // 根据 timestamp 值初始化一个 Date 对象
       var tmpDate = new Date(timestamp);
      // 按格式填充数据
       return tmpDate.getFullYear()+"-"
         +(tmpDate.getMonth()+1)+"-"
         +tmpDate.getDate();
   }


/*
  控制input中只能输入金额
*/
    $(document).ready(function(){
        bindKeyEvent($("#s_price"));
        bindKeyEvent($("#c_price"));
        bindKeyEvent($("#s_price_low"));
        bindKeyEvent($("#s_price_high"));
        bindKeyEvent($("#c_price_high"));
        bindKeyEvent($("#c_price_low"));

        //bindKeyEvent($("#input1"));
        //bindKeyEvent($("#input2"));
        //bindKeyEvent($("#input3"));
    });

    var bindKeyEvent = function(obj){
        obj.keyup(function () {
            var reg = $(this).val().match(/\d+\.?\d{0,2}/);
            var txt = '';
            if (reg != null) {
                txt = reg[0];
            }
            $(this).val(txt);
        }).change(function () {
            $(this).keypress();
            var v = $(this).val();
            if (/\.$/.test(v))
            {
                $(this).val(v.substr(0, v.length - 1));
            }
        });
    };




/*]]>*/