var app = angular.module('app', []);

app.controller('loginController', ['$scope', '$http', '$httpParamSerializerJQLike', function ($scope, $http, $httpParamSerializerJQLike) {
    $scope.user = {
        userid : '',
        password : ''
    }
    
    $scope.doLogin = function () {
        $http({
            method : 'POST',
            url : 'api/login',
            data : $httpParamSerializerJQLike({
                    userid : $scope.user.userid,
                    password : $.base64.encode($scope.user.password)
                }),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            cache : false
        }).success(function(data){
            if(data.success === false) {
                layer.msg('用户名或密码错误', function () {});
            } else {
                if(data.data.user.role == 3) {
                    location.href = 'admin.html';
                } else {
                    location.href = 'index.html';
                }
            }
        });
    }
}]);