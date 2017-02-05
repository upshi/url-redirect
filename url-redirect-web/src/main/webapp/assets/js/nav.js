var nav = angular.module('nav', []);

nav.controller('navController', ['$scope', '$http', '$httpParamSerializerJQLike', function ($scope, $http, $httpParamSerializerJQLike) {

    $scope.logout = function() {
        $http({
            method: 'POST',
            url: 'api/user/logout',
            cache: false
        }).success(function () {
            location.href = 'login.html'
        });
    }

}]);