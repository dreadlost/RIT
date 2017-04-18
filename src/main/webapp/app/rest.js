(function () {
    'use strict';

    angular
        .module('app.rest', ['ngResource'])
        .factory('$rest', RestService);

    function RestService($resource) {
        return $resource(null, null,
            {
                getData: {url: 'assets/data.json', method: 'GET', isArray: false}
            }
        );
    }

})();
