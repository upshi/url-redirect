var app = angular.module('app', ['ui.router','ui.bootstrap','ngFileUpload','adminController','nav','share']);

app.config(function($stateProvider, $urlRouterProvider) {
    // For any unmatched url, redirect to /state1
    $urlRouterProvider.otherwise("/admin");
    // Now set up the states
    $stateProvider
        .state('admin', {
            url: "/admin",
            views: {
                '': {
                    templateUrl: 'templates/common/content.html'
                },
                'nav@admin': {
                    templateUrl: 'templates/common/nav.html',
                    controller : 'navController'
                },
                'main@admin': {
                    templateUrl: 'templates/admin/main.html'
                },
                'footer@admin': {
                    templateUrl: 'templates/common/footer.html'
                }
            }
        })
        .state('admin.user', {
            url: '/user',
            views: {
                'main@admin': {
                    templateUrl: 'templates/admin/main.html'
                }
            }
        })
        .state('admin.user.add', {
            url: '/add',
            templateUrl: 'templates/admin/user/addUser.html',
            controller: 'addController'
        })
        .state('admin.user.list', {
            url: '/list',
            templateUrl: 'templates/admin/user/userList.html',
            controller: 'userListController'
        })
        .state('admin.settings', {
            url: '/settings',
            views: {
                'main@admin': {
                    templateUrl: 'templates/admin/main.html'
                }
            }
        })
        .state('admin.settings.personalInfo', {
            url: '/personalInfo',
            templateUrl: 'templates/admin/user/personalInfo.html',
            controller: 'personalInfoController'
        })
        .state('admin.settings.updatePassword', {
            url: '/updatePassword',
            templateUrl: 'templates/admin/user/updatePassword.html',
            controller: 'updatePasswordController'
        })
});
