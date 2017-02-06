var indexController = angular.module('indexController', []);

indexController.controller('mainController', ['$scope', '$http', '$httpParamSerializerJQLike', function ($scope, $http, $httpParamSerializerJQLike) {

    var domain = 'http://upshi.cc/';

    var qrcode = new QRCode("qrcode", {
        width: 256,
        height: 256,
        colorDark : "#E27206",
        colorLight : "#ffffff",
        correctLevel : QRCode.CorrectLevel.M
    });

    $scope.short = function() {
        $http({
            method: 'POST',
            url: 'dlj',
            data: $httpParamSerializerJQLike({
                url : $.base64.encode($scope.longurl)
            }),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            cache: false
        }).success(function (data) {
            if(data.success === true) {
                $scope.code = data.data.code;
                qrcode.makeCode(domain + $scope.code);
            } else {
                layer.msg(data.error, function () {});
            }
        });
    };

}]);
