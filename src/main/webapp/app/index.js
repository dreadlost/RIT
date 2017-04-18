(function () {
    'use strict';
    angular
        .module('app', [
            'app.rest'
        ])
        .run(ApplicationLoader)
        .controller('IndexController', IndexController);

    function ApplicationLoader($rootScope, $http) {

    }

    function getId() {
        var rand = 50 - 0.5 + Math.random() * (100 - 50 + 1)
        rand = Math.round(rand);
        return rand;
    }

    function getUrl(action, type) {
        let link = '';
        if (action === 'edit') {
            if (type == 'bank') {
                link = '/RIT/rs/rit/updateMonetaryBank'
            } else if (type == 'cash') {
                link = '/RIT/rs/rit/updateMonetaryCash'
            }
            else if (type == 'notmoney') {
                link = '/RIT/rs/rit/updateNonMonetary'
            }
        } else if (action == 'create') {
            if (type == 'bank') {
                link = '/RIT/rs/rit/addMonetaryBank'
            } else if (type == 'cash') {
                link = '/RIT/rs/rit/addMonetaryCash'
            }
            else if (type == 'notmoney') {
                link = '/RIT/rs/rit/addNonMonetary'
            }
        }
        return link
    }

    function prepareParams(type, data) {
        let params = {};
        switch (type) {
            case 'bank':
                params.id = data.id = getId();
                params.name = data.name;
                if (data.bank) {
                    params.summ = data.bank.summ;
                    params.currencyName = data.bank.currencyName;
                    params.nameBank = data.bank.name;
                    params.numberAcc = data.bank.numberAccount;
                }
                break;
            case 'cash':
                params.id = getId();
                params.id = getId();
                params.name = data.name;
                if (data.cash) {
                    params.summ = data.cash.summ;
                    params.currencyName = data.cash.currencyName;
                    params.nameCash = data.cash.name;
                    params.numberAcc = data.cash.numberAcc;
                }
                break;
            case 'notmoney':
                params.id = getId();
                params.primary = data.primary;
                params.residual = data.residual;
                params.valuation = data.valuation;

        }
        return params;
    }

    function IndexController($scope, $http) {
        let $ctrl = this;
        $ctrl.newItemType = 'bank';
        $ctrl.modalType = 'edit';
        $ctrl.selectedActive = {};
        $ctrl.data = [];
        $http.get('/RIT/rs/rit/addActives').then(res = > {
            console.log('Данные добавлены:');
        $http.get('/RIT/rs/rit/getActives').then(function (resp) {
            console.log(resp);
            $ctrl.data = resp.data;
        })
    },
        err =
    >
        {
            console.log(err);
        }
    )
        ;

        this.resetSelectedItem = function () {
            this.selectedActive = {}
        };

        $ctrl.removeItem = function (item) {
            $http({
                url: '/RIT/rs/rit/removeActive',
                method: "GET",
                params: {id: item.id}
            }).then(resp = > {
                $ctrl.data = $ctrl.data.filter(function (obj) {
                return obj.id !== item.id;
            });
            console.log('запрос выполнен успешно');
        },
            err =
            >
            {
                console.log('запрос не удался')
            }
            )
            ;
        };

        this.patch = function () {
            let params = prepareParams($ctrl.newItemType, $ctrl.selectedActive);
            console.log('Выполняется запрос');
            console.log(params);
            if (this.modalType === 'edit') {
                $http({
                    url: getUrl('edit', $ctrl.newItemType),
                    method: "GET",
                    params: params
                }).then(resp = > {
                    console.log('запрос выполнен успешно');
            },
                err =
            >
                {
                    console.log('запрос не удался')
                }
            )
                ;
            }
            else if (this.modalType === 'create') {
                $http({
                    url: getUrl('create', $ctrl.newItemType),
                    method: "GET",
                    params: params
                }).then(resp = > {
                    $ctrl.data.push($ctrl.selectedActive);
                $ctrl.selectedActive = {};
                console.log('запрос выполнен успешно');
            },
                err =
            >
                {
                    console.log('запрос не удался')
                }
            )
                ;
            }
        }

    }
})();
