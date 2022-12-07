angular.module('market-front').controller('orderPayController', function ($scope, $http, $location, $localStorage, $routeParams) {

    $scope.loadOrder = function () {
        $http({
            url: 'http://localhost:5555/gateway/api/v1/orders/' + $routeParams.orderId,
            method: 'GET'
        }).then(function (response) {
            $scope.order = response.data;
        });
    };

    $scope.renderPaymentButtons = function () {
        $http({
            url: 'http://localhost:5555/gateway/api/v1/paypal/create/' + $scope.order.id,
            method: 'GET'
        }).then(function (response) {
            $scope.billid = response.data.billid;
            params = {
                payUrl: response.data.responseUrl,
            }
            QiwiCheckout.openInvoice(params)
                .then(function (onFullField) {
                    $location.path("http://localhost:3000/front") // Заглушка. ToDo при корректной отправке платежа, необходимо отправить запрос на /capture/{billId} (billid уже задан в scope)
                })
                .then(function (onRejection) {
                    $location.path("http://localhost:3000/front")
                });

        });
    };

