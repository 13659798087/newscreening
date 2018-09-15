
/* bitadmin.js;querySuite.js;formSuite.js;
* =======================================
* BitAdmin 是基于jquery;bootstrap;adminlte 构建的管理系统开发框架
* 引用组件：jquery;jquery.form;jquery.validate;bootstrap;adminlte;underscore
* 
* @Author  chenyinxin
* @Support <http://bitadmin.bitdao.cn>
* @Email   <chenyinxin@qq.com>
* @version 1.0.0
*/

/*框架函数*/
var BitAdmin = {
    //内容模式：tab,iframe,load
    //load模式需要使用LayoutLoad，tab,iframe模式使用LayoutIFrame.
    mode: "tab",
    addTab: function (options) {
        var url = options.tabUrl;
        //option:
        //tabMainName:tab标签页所在的容器
        //tabName:当前tab的名称
        //tabTitle:当前tab的标题
        //tabUrl:当前tab所指向的URL地址
        var exists = $("#" + options.tabMainName + " > #tab_li_" + options.tabName).length > 0;
        if (exists) {

            $("#tab_a_" + options.tabName).click();
        } else {

            var li = $('<li id="tab_li_' + options.tabName + '"></li>');
            var a = $('<a href="#tab_content_' + options.tabName + '" data-toggle="tab" id="tab_a_' + options.tabName + '">' + options.tabTitle + '  </a>');

            var spanRefresh = $('<span class="glyphicon glyphicon-repeat" style="float:none"></span>');
            spanRefresh.bind('click', function () { BitAdmin.refreshTab('iframe_' + options.tabName); });
            var spanClose = $('<span class="glyphicon glyphicon-remove"  style="float:none"></span>');
            spanClose.bind('click', function () { BitAdmin.closeTab(this); });

            a.append(spanRefresh).append(spanClose);
           // $("#" + options.tabMainName).append(li.append(a));

            if(typeof(url)!="undefined"){

                $("#" + options.tabMainName).append(li.append(a));

            }


            
            /*var layout = "Layout.html";
            if (options.tabUrl.indexOf(".") > -1) {
                layout = options.tabUrl.split('.')[1] + ".html";
            }*/

            //var pageUrl = "/Pages/Shared/" + layout + "?page=" + encodeURIComponent(options.tabUrl.split('.')[0]) + "&sign=" + options.tabSign;

            var tabIframe = $('<iframe id="iframe_' + options.tabName + '" style="height:' + this.getIframeHeight() + ' ; width: 100%;" src="' + url + '" frameborder="0" border="0" marginwidth="0" marginheight="0" scrolling="auto" allowtransparency="true"></iframe>');

            var tabDiv = $('<div id="tab_content_' + options.tabName + '" role="tabpanel" class="tab-pane" id="' + options.tabName + '"></div>');
            $("#" + options.tabContentMainName).append(tabDiv.append(tabIframe));
            $("#tab_a_" + options.tabName).click();
        }
    },
    closeTab: function (button) {

        //通过该button找到对应li标签的id
        var li_id = $(button).parent().parent().attr('id');
        var id = li_id.replace("tab_li_", "");

        //如果关闭的是当前激活的TAB，激活他的前一个TAB
        if ($("li.active").attr('id') == li_id) {
            $("li.active").prev().find("a").click();
        }

        //关闭TAB
        $("#" + li_id).remove();
        $("#tab_content_" + id).remove();
    },
    refreshTab: function (id) {
        $('#' + id).attr('src', $('#' + id).attr('src') );
    },
    //页面操作权限(如果没有权限直接删除对应的操作按钮)
    operationRole: function (sign) {
        $.post("../../Account/GetOperationCode?sign=" + sign, function (result) {
            if (result.Code == 0 && result.Data) {
                $.adminSetting.addLogs($(document).attr("title"), "页面访问", "访问");//添加访问日志

                if (result.Data.Operation == true)
                    return;

                if (result.Data.Operation == false) {
                    window.location.href = "../../Pages/Error/False.html";
                    return;
                }

                $.each($("[type='button'],:button,a"), function (i) {
                    var attrVal = $(this).attr("action");
                    if (attrVal != undefined && attrVal != "undefined" && attrVal != "" && attrVal != null && attrVal != "null") {
                        if (result.Data.Operation.toLocaleLowerCase().indexOf(attrVal.toLocaleLowerCase()) == -1) {
                            $(this).remove();
                        }
                    }
                });
            }
            else {
                alert(result.Msg);
            }
        });
    },
    getIframeHeight: function () {
        return ($(window).height() - $('body > .wrapper >.main-header').height() - $('body > .wrapper .tab-title').height() * 2 - $('body > .wrapper .main-footer').height() - 10) + 'px';
    },
    loadContent: function () {
        var url = "../" + BitAdmin.GetQueryString("page") + ".html";
        $("section.content").load(url);
        var sign = BitAdmin.GetQueryString("sign");
        BitAdmin.operationRole(sign);           //初始化操作权限

        var page = window.parent.BitAdmin.Pages[sign];
        if (page != null) {
            $(document).attr("title", page.PageName);
            $(".header-moduleName").text(page.ModuleName);
            $(".header-pageName").text(page.PageName);
            $(".header-PageDesc").text(page.Description);
        }
    },
    GetQueryString: function (name, url) {
        if (url == undefined) url = window.location.search;
        var reg = new RegExp("(\\?|&)" + name + "=([^&]*)(&|$)");
        var r = url.match(reg);
        if (r != null) return unescape(r[2]); return null;
    },
    Pages: {},
    IsLogin: function (success) {
        $.get("../../Account/IsLogin", function (result) {
            if (result == false) window.location.href = "../../Pages/Account/Login.html";
            else {
                if ($.isFunction(success))
                    success();
            }
        });
    }
};
$.fn.extend({
    //获取登录用户信息
    GetUserInfo: function () {
        $.ajax({
            type: "GET",
            url: "../../Account/GetUserInfo",
            datatype: "json",
            async: true,
            success: function (result) {
                if (result.Code == 1) {
                    alert(result.Msg);
                } else {
                    for (var key in result) {
                        $("#logo_" + key).text(result[key]);
                    }
                }

            }
        });
    },
    GetMenus: function () {
        var _this = $(this);
        $.get("/login/getLoginMessage", function (data) {
            if (data.Code == 0)
                BindTreeViewMenu(data.Data, _this, "");
            else
                alert(data.Msg);
        });

        function BindTreeViewMenu(menus, pul, title) {
            if (title != "") title = title + ">";
            $.each(menus, function (index, menu) {
                var moduleICO = menu.Icon == null || menu.Icon == "" ? "fa-edit" : menu.Icon;
                if (menu.children != null && menu.children.length > 0) {
                    var li = $('<li class="treeview"><a href="javascript:void(0);"><i class="fa ' + moduleICO + '"></i><span>' + menu.Name + '</span><span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a></li>');
                    var ul = $('<ul class="treeview-menu"></ul>');
                    li.append(ul);
                    pul.append(li);
                    BindTreeViewMenu(menu.children, ul, title + menu.Name);//绑定新的树结构<li class="treeview"></li>
                }
                else {
                    var li = $('<li><a href="javascript:void(0);"><i class="fa ' + moduleICO + '"></i>' + menu.Name + '</a></li>');
                    pul.append(li);
                    li.click(function () {
                        if (menu.Url != undefined && menu.Url != null && menu.Url != "") {
                            BitAdmin.Pages[menu.PageSign] = {
                                ModuleName: menu.ModuleName,
                                PageName: menu.Name,
                                Description: menu.Description
                            };
                            if (BitAdmin.mode == "tab") {
                                BitAdmin.addTab({
                                    tabMainName: "page-tabs",
                                    tabName: "page_" + menu.ID,
                                    tabSign: menu.PageSign,
                                    tabTitle: menu.Name,
                                    tabUrl: menu.Url,
                                    tabContentMainName: "tab-content",
                                    title: title + menu.Name
                                });
                            } else if (BitAdmin.mode == "iframe") {
                                $("[mode=" + BitAdmin.mode + "] iframe").attr("src", menu.Url);
                            } else if (BitAdmin.mode == "load") {
                                $("[mode=" + BitAdmin.mode + "]").load(menu.Url);
                            }
                        }
                    });
                }
            });
        }
    },
    //将数据字典中的数据绑定到select 中
    ddicSelect: function (option) {
        var type = option.type;
        if (type == undefined || type == null || type == "")
            return false;
        else {
            var Member = option.Member;
            if (Member == undefined || Member == "undefined" || Member == null)
                Member = '';
            var select = $(this);
            select.html('');
            if (option.IsCaption == true) {
                select.append($('<option value="">===请选择===</option>'));
            }
            $.get('../../Picker/GetDropDownList', { Type: type, Member: Member },
                function (result) {
                    if (result.Code == 0) {
                        $.each(result.Data, function (i, d) {
                            select.append($('<option value="' + d.Member + '">' + d.MemberName + '</option>'));
                        });
                    }
                });
        }
    }
});

$.ajaxSetup({
    statusCode: {
        499: function (data) {
            window.location.href = data.responseText;
        }
    }
});