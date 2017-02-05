var adminController = angular.module('adminController', []);

adminController.controller('addController', ['$scope', '$http', '$httpParamSerializerJQLike', '$state', function ($scope, $http, $httpParamSerializerJQLike, $state) {

    $scope.user = {
        userid: '',
        password: '',
        name: '',
        tel: '',
        email: '',
        role : '0',
        dept: ''
    };

    $scope.newUser = {
        error: false,
        msg: ''
    };

    $scope.checkUserid = function () {
        $http({
            method: 'POST',
            url: 'api/admin/user/checkUserid',
            data: $httpParamSerializerJQLike({userid: $scope.user.userid}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            cache: false
        }).success(function (data) {
            if (data.data.exist === true) {
                $scope.newUser.msg = '您输入的用户名已存在';
                $scope.newUser.error = true;
                return false;
            } else {
                $scope.newUser.error = false;
            }
        });
    }

    $scope.addUser = function () {
        if (!$scope.newUser.error) {
            $http({
                method: 'POST',
                url: 'api/admin/user/addUser',
                data: $httpParamSerializerJQLike($scope.user),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                cache: false
            }).success(function () {
                layer.msg('添加成功!');
                $state.reload('admin.user.add');
            });
        }
    }
}]);

adminController.controller('userListController', ['$scope', '$http', '$httpParamSerializerJQLike', function ($scope, $http, $httpParamSerializerJQLike) {
    $scope.currentPage = 1;
    $scope.maxSize = 10;
    $scope.pageSize = 10;

    $scope.searchParam = {
        userid : '',
        name :'',
        dept :'',
        tel : '',
        email : '',
        role : '',
        status : ''
    };

    $scope.init = function () {
        $http({
            method: 'POST',
            url: 'api/admin/user/search',
            cache: false
        }).success(function (data) {
            $scope.users = data.data.users;
            $scope.totals = data.data.totals;
            $scope.pages = Math.ceil(data.data.totals / $scope.pageSize);
        });
    };

    $scope.search = function() {
        $scope.currentPage = 1;
        $scope.maxSize = 10;
        $scope.pageSize = 10;
        doSearch();
    };

    $scope.clear = function() {
        $scope.searchParam = {
            userid : '',
            name :'',
            dept :'',
            tel : '',
            email : '',
            role : '',
            status : ''
        }
    };

    $scope.pageChanged = function() {
        doSearch();
    };

    $scope.modifyStatus = function(userid, status) {
        var action;
        if(status === -1) {
            action = '删除';
        } else if(status === 0) {
            action = '禁用';
        } else if(status === 1) {
            action = '启用';
        }
        layer.confirm('您确定要'+ action +'该用户吗?', {
            btn : ['确定','取消']
        }, function(){
            $http({
                method: 'POST',
                url: 'api/admin/user/modifyStatus',
                data: $httpParamSerializerJQLike({
                        userid : userid,
                        status : status
                    }),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                cache: false
            }).success(function (data) {
                if(data.success === true) {
                    angular.forEach($scope.users, function (u ,i) {
                        if(u.userid === userid) {
                            $scope.users[i].status = status;
                        }
                    })
                    layer.closeAll();
                }
            });
        });
    };

    $scope.modify = function (u) {
        layer.msg('待完成：编辑用户' + u.userid);
        //iframe层-父子操作
        layer.open({
            type: 1,
            area: ['800px', '530px'],
            fixed: true, //不固定
            maxmin: true,
            content: $('#modifyUser')
        });
    }

    function doSearch() {
        $http({
            method: 'POST',
            url: 'api/admin/user/search',
            data: $httpParamSerializerJQLike({
                userid: $scope.searchParam.userid,
                name: $scope.searchParam.name,
                dept: $scope.searchParam.dept,
                tel: $scope.searchParam.tel,
                email: $scope.searchParam.email,
                role: $scope.searchParam.role,
                status: $scope.searchParam.status,
                page: $scope.currentPage,
                size:$scope.pageSize
            }),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            cache: false
        }).success(function (data) {
            $scope.users = data.data.users;
            $scope.totals = data.data.totals;
            $scope.pages = Math.ceil(data.data.totals / $scope.pageSize);
        });
    }
}]);

adminController.controller('personalInfoController', ['$scope', '$http', function ($scope, $http) {
    $scope.init = function () {
        $http({
            method: 'POST',
            url: 'api/user/personalInfo',
            cache: false
        }).success(function (data) {
            $scope.user = data.data.user;
        });
    };
}]);

adminController.controller('updatePasswordController', ['$scope', '$http', '$httpParamSerializerJQLike', '$state', function ($scope, $http, $httpParamSerializerJQLike, $state) {
    $scope.password = {
        oldPassword: '',
        newPassword: '',
        rePassword: '',
        correct: 'true',
        msg: ''
    };
    $scope.checkPassword = function () {
        $http({
            method: 'POST',
            url: 'api/user/checkPassword',
            data: $httpParamSerializerJQLike({
                password: $.base64.encode($scope.password.oldPassword)
            }),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            cache: false
        }).success(function (data) {
            $scope.password.correct = data.data.correct;
            $scope.password.msg = '您输入的原密码不正确';
        });
    };

    $scope.updatePassword = function () {
        $http({
            method: 'POST',
            url: 'api/user/updatePassword',
            data: $httpParamSerializerJQLike({
                oldPassword: $.base64.encode($scope.password.oldPassword),
                newPassword: $.base64.encode($scope.password.newPassword)
            }),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            cache: false
        }).success(function (data) {
            if(data.success === true) {
                layer.msg('修改成功!');
                if(data.data.role === 3) {
                    $state.reload('admin.settings.updatePassword');
                } else {
                    $state.reload('index.settings.updatePassword');
                }
            } else {
                layer.msg('请稍后再试!');
            }
        });
    };
}]);