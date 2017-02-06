var indexController = angular.module('indexController', []);

indexController.controller('mainController', ['$scope', '$http', '$httpParamSerializerJQLike', function ($scope, $http, $httpParamSerializerJQLike) {
    $scope.currentPage = 1;
    $scope.maxSize = 10;
    $scope.pageSize = 10;

    $scope.searchParam = {
        name : '',
        type :'',
        startTime : '',
        endTime : ''
    }


    $scope.init = function () {
        $http({
            method: 'POST',
            url: 'api/doc/search',
            cache: false
        }).success(function (data) {
            $scope.docs = data.data.docs;
            $scope.totals = data.data.totals;
            $scope.pages = Math.ceil(data.data.totals / $scope.pageSize);
        });

        $('#timeRange').daterangepicker({
            maxDate : nextDate(),
            locale: {
                cancelLabel: '清除'
            }
        });

        $('#timeRange').on('cancel.daterangepicker', function(ev, picker) {
            $scope.searchParam.startTime = '';
            $scope.searchParam.endTime = '';
            $(this).val('');
        });
        $('#timeRange').on('apply.daterangepicker', function(ev, picker) {
            $scope.searchParam.startTime = picker.startDate.format('YYYY-MM-DD');
            $scope.searchParam.endTime = picker.endDate.format('YYYY-MM-DD');
            $(this).val($scope.searchParam.startTime + ' 至 ' + $scope.searchParam.endTime);
        });
    };

    $scope.search = function() {
        $scope.currentPage = 1;
        $scope.maxSize = 10;
        $scope.pageSize = 10;
        doSearch();
    };

    $scope.pageChanged = function() {
        doSearch();
    };

    function doSearch() {
        $http({
            method: 'POST',
            url: 'api/doc/search',
            data: $httpParamSerializerJQLike({
                name: $scope.searchParam.name,
                type: $scope.searchParam.type,
                startTime: $scope.searchParam.startTime,
                endTime: $scope.searchParam.endTime,
                page: $scope.currentPage,
                size:$scope.pageSize
            }),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            cache: false
        }).success(function (data) {
            $scope.docs = data.data.docs;
            $scope.totals = data.data.totals;
            $scope.pages = Math.ceil(data.data.totals / $scope.pageSize);
        });
    }
}]);
